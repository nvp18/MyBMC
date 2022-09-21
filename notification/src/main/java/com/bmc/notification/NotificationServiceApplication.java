package com.bmc.notification;

import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

@SpringBootApplication
public class NotificationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationServiceApplication.class, args);
	}

	@Bean
	public FreeMarkerConfigurer freeMarkerConfigurer(){
		Configuration configuration = new Configuration(Configuration.VERSION_2_3_27);
		TemplateLoader loader = new ClassTemplateLoader(this.getClass(),"/templates");
		configuration.setTemplateLoader(loader);
		FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
		configurer.setConfiguration(configuration);
		return configurer;
	}
}
