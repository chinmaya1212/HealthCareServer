package com.HealthCare.HealthCare.Repo;

import com.HealthCare.HealthCare.models.Doctors;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface DoctorRepositoy extends MongoRepository<Doctors, String> {

}
