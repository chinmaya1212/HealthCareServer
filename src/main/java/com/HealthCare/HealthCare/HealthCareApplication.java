package com.HealthCare.HealthCare;

import com.HealthCare.HealthCare.Repo.DoctorRepositoy;
import com.HealthCare.HealthCare.configuration.DoctorService;
import com.HealthCare.HealthCare.models.Doctors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableScheduling
public class HealthCareApplication {
	@Autowired DoctorRepositoy doctorRepositoy;


	public static void main(String[] args) {
		SpringApplication.run(HealthCareApplication.class, args);
	}
	// Runs every day at 11:15 PM
	@Scheduled(cron = "0 32 10 * * ?")
	public void resetAboutField() {
		System.out.println("Enter to update!!!!!");
		List<Doctors> doctorsList = this.doctorRepositoy.findAll();

		for(int i=0;i<doctorsList.size();i++){
			Doctors tmpdoc=doctorsList.get(i);
			tmpdoc.setSlot1_2(false);
			tmpdoc.setSlot11_12(false);
			tmpdoc.setSlot2_3(false);
			tmpdoc.setSlot10_11(false);
			doctorRepositoy.save(tmpdoc);

		}
	}

}
