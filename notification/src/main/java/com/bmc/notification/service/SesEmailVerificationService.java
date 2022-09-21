package com.bmc.notification.service;

import com.bmc.appointmentService.DTO.AppointmentDTO;
import com.bmc.appointmentService.DTO.PrescriptionDTO;
import com.bmc.doctorService.DTO.DoctorDTO;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ses.SesClient;

import javax.annotation.PostConstruct;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Component
public class SesEmailVerificationService {

    private SesClient sesClient;

    private final FreeMarkerConfigurer configurer;

    public SesEmailVerificationService(FreeMarkerConfigurer configurer) {
        this.configurer = configurer;
    }

    private String accessKey;
    private String privateKey;

    @PostConstruct
    public void init(){
        accessKey="AKIASROXFHBBB252A6MR";
        privateKey="0FyACBg6bkMVkfOXJRB4fNnOzBk7E+3JnyREb2S2";
        StaticCredentialsProvider staticCredentialsProvider = StaticCredentialsProvider
                .create(AwsBasicCredentials.create(accessKey,privateKey));
        sesClient = SesClient
                .builder()
                .credentialsProvider(staticCredentialsProvider)
                .region(Region.US_EAST_1)
                .build();
    }

    public void verifyEmail(String emailId){
        sesClient.verifyEmailAddress(req->req.emailAddress(emailId));
    }

    public void sendApprovalMail(DoctorDTO doctorDTO) throws IOException, TemplateException {
        Map<String,Object> templateModel = new HashMap<>();
        templateModel.put("doctor",doctorDTO);
        Template freeMarkerTemplate  = configurer.getConfiguration().getTemplate("doctorApproval.ftl");
        String htmlbody = FreeMarkerTemplateUtils.processTemplateIntoString(freeMarkerTemplate,templateModel);
        sendSimpleMail(doctorDTO.getEmailId(),"Application Confirmed",htmlbody);
    }

    public void sendRejectMail(DoctorDTO doctorDTO) throws IOException, TemplateException {
        Map<String,Object> templateModel = new HashMap<>();
        templateModel.put("doctor",doctorDTO);
        Template freeMarkerTemplate  = configurer.getConfiguration().getTemplate("doctorRejected.ftl");
        String htmlbody = FreeMarkerTemplateUtils.processTemplateIntoString(freeMarkerTemplate,templateModel);
        sendSimpleMail(doctorDTO.getEmailId(),"Application Rejected",htmlbody);
    }

    public void sendPrescriptionMail(PrescriptionDTO prescriptionDTO) throws IOException, TemplateException {
        Map<String,Object> templateModel = new HashMap<>();
        templateModel.put("prescription",prescriptionDTO);
        Template freeMarkerTemplate  = configurer.getConfiguration().getTemplate("prescription.ftl");
        String htmlbody = FreeMarkerTemplateUtils.processTemplateIntoString(freeMarkerTemplate,templateModel);
        sendSimpleMail(prescriptionDTO.getEmailId(),"Prescription",htmlbody);
    }

    public void sendAppointmentMail(AppointmentDTO appointmentDTO) throws IOException, TemplateException {
        Map<String,Object> templateModel = new HashMap<>();
        templateModel.put("appointment",appointmentDTO);
        Template freeMarkerTemplate  = configurer.getConfiguration().getTemplate("appointment.ftl");
        String htmlbody = FreeMarkerTemplateUtils.processTemplateIntoString(freeMarkerTemplate,templateModel);
        sendSimpleMail(appointmentDTO.getEmailId(),"appointment",htmlbody);
    }

    private void sendSimpleMail(String toMail,String subject,String body)  {
        try {
            Properties properties = System.getProperties();
            properties.put("mail.transport.protocol", "smtp");
            properties.put("mail.smtp.port", 587);
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.auth", "true");
            Session session = Session.getDefaultInstance(properties);
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom("nvp956@gmail.com");
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(toMail));
            msg.setSubject(subject);
            msg.setContent(body, "text/html");
            Transport transport = session.getTransport();
            transport.connect("email-smtp.us-east-1.amazonaws.com", "AKIASROXFHBBJ2O5DBVJ", "BK71Ld3+fL6McZnviRUojVSezA0saFxGlWVuzU/4XmJZ");
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
        } catch (Exception e){
            System.out.println(e);
        }
    }
}
