package com.bmc.notification.Config;

import com.bmc.appointmentService.DTO.AppointmentDTO;
import com.userService.user.DTO.UserDTO;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class AppointmentConsumerConfig {
    @Value("${bootstrap.server}")
    private String bootstrapServer;

    @Value("${appointment.group.id}")
    private String groupId;

    @Bean
    ConsumerFactory<String, AppointmentDTO> consumerFactoryForAppointment(){
        JsonDeserializer jsonDeserializer = new JsonDeserializer();
        jsonDeserializer.addTrustedPackages("*");
        return  new DefaultKafkaConsumerFactory<>(getConfig(), new StringDeserializer(),jsonDeserializer);
    }

    private Map<String,Object> getConfig(){
        Map<String,Object> configProps = new HashMap<>();
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapServer);
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG,groupId);
        return configProps;
    }

    @Bean
    ConcurrentKafkaListenerContainerFactory concurrentKafkaListenerContainerFactoryForAppointment(){
        ConcurrentKafkaListenerContainerFactory concurrentKafkaListenerContainerFactory = new ConcurrentKafkaListenerContainerFactory();
        concurrentKafkaListenerContainerFactory.setConsumerFactory(consumerFactoryForAppointment());
        return concurrentKafkaListenerContainerFactory;
    }
}
