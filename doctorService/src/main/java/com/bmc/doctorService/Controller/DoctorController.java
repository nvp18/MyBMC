package com.bmc.doctorService.Controller;

import com.bmc.doctorService.DTO.ApproverDTO;
import com.bmc.doctorService.DTO.DoctorDTO;
import com.bmc.doctorService.DTO.DoctorRegistrationDTO;
import com.bmc.doctorService.Entity.Doctor;
import com.bmc.doctorService.Service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @PostMapping("/doctors")
    @PreAuthorize("hasAuthority('DOCTOR_INFO')")
    public ResponseEntity<DoctorRegistrationDTO> collectDoctorInfo(@Valid  @RequestBody Doctor doctor){
        DoctorRegistrationDTO res = doctorService.collectDoctorInfo(doctor);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/doctors/{doctorId}/document")
    @PreAuthorize("hasAuthority('UPLOAD_DOCTOR_DOCUMENTS')")
    public ResponseEntity<String> uploadDocuments(@RequestBody MultipartFile[] files, @PathVariable("doctorId") String doctorId) throws Exception {
        String res = doctorService.uploadDocuments(doctorId,files);
        return ResponseEntity.ok(res);
    }

    @PutMapping("/doctors/{doctorId}/approve")
    @PreAuthorize("hasAuthority('APPROVE_REGISTRATION')")
    public ResponseEntity<DoctorDTO> approveDoctor(@RequestBody ApproverDTO approverDTO,@PathVariable("doctorId") String doctorId) {
        DoctorDTO doctorDTO = doctorService.approveDoctor(approverDTO,doctorId);
        return ResponseEntity.ok(doctorDTO);
    }

    @PutMapping("/doctors/{doctorId}/reject")
    @PreAuthorize("hasAuthority('REJECT_REGISTRATION')")
    public ResponseEntity<DoctorDTO> rejectDoctor(@RequestBody ApproverDTO approverDTO,@PathVariable("doctorId") String doctorId){
        DoctorDTO doctorDTO = doctorService.rejectDoctor(approverDTO,doctorId);
        return ResponseEntity.ok(doctorDTO);
    }

    @GetMapping("/doctors")
    @PreAuthorize("hasAuthority('TOP_20_DOCTOR_INFO')")
    public ResponseEntity<List<DoctorDTO>> getDoctorsDetails(@RequestParam("status") String status,@RequestParam(value = "speciality",defaultValue = "NOT_GIVEN") String speciality){
        List<DoctorDTO> doctorDTOS = doctorService.get20Doctors(status,speciality);
        return ResponseEntity.ok(doctorDTOS);
    }

    @GetMapping("/doctors/{doctorId}")
    @PreAuthorize("hasAuthority('DOCTOR_INFO_ON_ID')")
    public ResponseEntity<DoctorDTO> getDoctorDetails(@PathVariable("doctorId") String doctorId) {
        DoctorDTO doctorDTO = doctorService.getDoctorDetails(doctorId);
        return ResponseEntity.ok(doctorDTO);
    }
}

