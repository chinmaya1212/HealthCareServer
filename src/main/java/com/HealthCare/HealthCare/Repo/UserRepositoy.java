package com.HealthCare.HealthCare.Repo;

import com.HealthCare.HealthCare.models.Doctors;
import com.HealthCare.HealthCare.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepositoy extends MongoRepository<User,String> {
}
