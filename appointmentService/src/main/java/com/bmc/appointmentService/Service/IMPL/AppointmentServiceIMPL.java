package com.bmc.appointmentService.Service.IMPL;

import com.bmc.appointmentService.DAO.AppointmentDAO;
import com.bmc.appointmentService.DAO.AvailabilityDAO;
import com.bmc.appointmentService.DAO.PrescriptionDAO;
import com.bmc.appointmentService.DTO.AppointmentDTO;
import com.bmc.appointmentService.DTO.AvailabilityDTO;
import com.bmc.appointmentService.DTO.PrescriptionDTO;
import com.bmc.appointmentService.Entity.Appointment;
import com.bmc.appointmentService.Entity.Availability;
import com.bmc.appointmentService.Entity.Prescription;
import com.bmc.appointmentService.Exception.RequestedResourceNotFoundException;
import com.bmc.appointmentService.Exception.PaymentNotMadeException;
import com.bmc.appointmentService.Mapper.AppointmentMapper;
import com.bmc.appointmentService.Mapper.PrescriptionMapper;
import com.bmc.appointmentService.Service.AppointmentService;
import com.bmc.payment.DTO.PaymentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class AppointmentServiceIMPL implements AppointmentService {

    @Autowired
    private AppointmentDAO appointmentDAO;

    @Autowired
    private PrescriptionDAO prescriptionDAO;

    @Autowired
    private AvailabilityDAO availabilityDAO;

    @Value("${precription.topic}")
    private String prescriptionTopic;

    @Value("${appointment.topic}")
    private String appointmentTopic;

    @Autowired
    private KafkaTemplate<String, PrescriptionDTO> kafkaTemplate;

    @Autowired
    private KafkaTemplate<String, AppointmentDTO> kafkaAppoinmentTemplate;

    @Override
    public void postDoctorAvailability(AvailabilityDTO availabilityDTO,String doctorID) {
        if(availabilityDAO.findAllBydoctorId(doctorID).isPresent()){
            availabilityDAO.deleteAllBydoctorId(doctorID);
        }
        List<Availability> doctorAvailability = new ArrayList<>();
        LinkedHashMap<Date,List<String>> availabilityMap = availabilityDTO.getAvailabilityMap();
        for(Date date : availabilityMap.keySet()){
            List<String> timeZones = availabilityMap.get(date);
            for(String timeZone : timeZones) {
                Availability availability = new Availability();
                availability.setDoctorId(doctorID);
                availability.setBooked(false);
                availability.setTimeSlot(timeZone);
                availability.setAvailabilityDate(date);
                doctorAvailability.add(availability);
            }
        }
        availabilityDAO.saveAll(doctorAvailability);
    }

    @Override
    public AvailabilityDTO getDoctorAvailability(String doctorId) {
        Optional<List<Availability>> doctorAvailability = availabilityDAO.findAllBydoctorId(doctorId);
        if(doctorAvailability.get().size()!=0) {
            List<Availability> availabilityList = doctorAvailability.get();
            AvailabilityDTO availabilityDTO = new AvailabilityDTO();
            availabilityDTO.setDoctorId(doctorId);
            LinkedHashMap<Date,List<String>> availabilityMap = new LinkedHashMap<>();
            List<Date> availabilityDates = new ArrayList<>();
            for(Availability availability : availabilityList){
                if(!availabilityDates.contains(availability.getAvailabilityDate())) {
                    availabilityDates.add(availability.getAvailabilityDate());
                }
            }
            for(Date availabilityDate : availabilityDates){
                List<String> timeSlot = new ArrayList<>();
                for(Availability availability : availabilityList){
                    if(availability.getAvailabilityDate().equals(availabilityDate)) {
                        timeSlot.add(availability.getTimeSlot());
                    }
                }
                availabilityMap.put(availabilityDate,timeSlot);
            }
            availabilityDTO.setAvailabilityMap(availabilityMap);
            return availabilityDTO;
        } else {
            throw new RequestedResourceNotFoundException("Availability not found for the given doctor ID");
        }
    }

    @Override
    public String bookAppointment(AppointmentDTO appointmentDTO) {
        Optional<Availability> availabilityObj = availabilityDAO.findBydoctorIdAndTimeSlotAndAvailabilityDate(appointmentDTO.getDoctorId(),
                appointmentDTO.getTimeSlot(),appointmentDTO.getAppointmentDate());
        if(availabilityObj.isPresent()) {
            Availability availability = availabilityObj.get();
            if(!availability.isBooked()) {
                Appointment appointment = AppointmentMapper.convertDTOToEntity(appointmentDTO);
                appointment.setStatus("PendingPayment");
                LocalDateTime todayDate = LocalDateTime.now();
                appointment.setCreatedDate(todayDate);
                Appointment savedAppointment = appointmentDAO.save(appointment);
                availability.setBooked(true);
                availabilityDAO.save(availability);
                sendAppointmentConfirmationToUser(savedAppointment);
                return savedAppointment.getAppointmentId();
            } else {
                return "Doctor Booked for the given time slot";
            }
        } else {
            throw new RequestedResourceNotFoundException("No Doctor found with the given time slot");
        }
    }

    @Override
    public AppointmentDTO findAppointment(String appointmentId) {
        Optional<Appointment> appointmentObj = appointmentDAO.findById(appointmentId);
        if(appointmentObj.isPresent()){
            Appointment appointment = appointmentObj.get();
            AppointmentDTO appointmentDTO = AppointmentMapper.convertEntityToDTO(appointment);
            return appointmentDTO;
        } else {
            throw new RequestedResourceNotFoundException("Appointment not found for the given Appointment ID");
        }
    }

    @Override
    public List<AppointmentDTO> findAppointmentsForUser(String userId) {
         Optional<List<Appointment>> appointmentObj = appointmentDAO.findByuserId(userId);
         if(appointmentObj.get().size()!=0){
             List<Appointment> userAppointmentsList = appointmentObj.get();
             List<AppointmentDTO> appointmentDTOS = new ArrayList<>();
             for(Appointment userAppointment: userAppointmentsList) {
                 AppointmentDTO userAppointmentDTO = AppointmentMapper.convertEntityToDTO(userAppointment);
                 appointmentDTOS.add(userAppointmentDTO);
             }
             return appointmentDTOS;
         } else {
             throw new RequestedResourceNotFoundException("Appointments not found for the given UserID");
         }
    }

    @Override
    public void sendPrescription(PrescriptionDTO prescriptionDTO) {
        Optional<Appointment> appointmentObj = appointmentDAO.findById(prescriptionDTO.getAppointmentId());
        if(appointmentObj.isPresent()){
            Appointment appointment = appointmentObj.get();
            if(appointment.getStatus().equals("Confirmed")) {
                Prescription prescription = PrescriptionMapper.convertDTOToEntity(prescriptionDTO);
                prescription.setDoctorName(appointment.getDoctorName());
                Prescription savedPrescription = prescriptionDAO.save(prescription);
                sendPrescriptionToUser(savedPrescription,appointment.getUserEmailId());
            } else {
                throw new PaymentNotMadeException();
            }
        } else {
            throw new RequestedResourceNotFoundException("Appointment not found for the given Appointment ID");
        }
    }

    private void sendPrescriptionToUser(Prescription prescription,String emailID){
        PrescriptionDTO prescriptionDTO = PrescriptionMapper.convertEntityToDTO(prescription,emailID);
        kafkaTemplate.send(prescriptionTopic,prescriptionDTO);
    }

    private void sendAppointmentConfirmationToUser(Appointment appointment){
        AppointmentDTO appointmentDTO = AppointmentMapper.convertEntityToDTO(appointment);
        kafkaAppoinmentTemplate.send(appointmentTopic,appointmentDTO);
    }

    @KafkaListener(topics = "${payment.topic}",groupId = "${payment.group.id}", containerFactory = "concurrentKafkaListenerContainerFactory")
    public void userListen(@Payload PaymentDTO payment) {
        String appointmentID = payment.getAppointmentID();
        Optional<Appointment> appointment = appointmentDAO.findById(appointmentID);
        if(appointment.isPresent()){
            Appointment appointmentObj = appointment.get();
            appointmentObj.setStatus("Confirmed");
            appointmentDAO.save(appointmentObj);
        }
    }

}
