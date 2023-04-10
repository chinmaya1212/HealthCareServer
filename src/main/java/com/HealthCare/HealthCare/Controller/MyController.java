package com.HealthCare.HealthCare.Controller;

import com.HealthCare.HealthCare.Repo.DoctorRepositoy;
import com.HealthCare.HealthCare.Repo.UserRepositoy;
import com.HealthCare.HealthCare.configmail.EmailSenderService;
import com.HealthCare.HealthCare.models.Doctors;
import com.HealthCare.HealthCare.models.Response;
import com.HealthCare.HealthCare.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import javax.mail.internet.InternetAddress;
import javax.print.Doc;
import java.text.SimpleDateFormat;
import java.util.*;


@RestController
@RequestMapping
public class MyController {
    Response response;
    @Autowired
    private UserRepositoy userRepositoy;


    @Autowired
    private EmailSenderService emailService;
    @Autowired
    private MongoTemplate mongoTemplate;



    //To Add User


    private int seconds = 0;
    @PostMapping("/email_verification")
    public Response test(@RequestBody User user){
        String subject="Welcome "+user.getName()+" to HealthCare";
        String body="Hii,welcome to our app your email is "+user.getEmail()+" and your password is "+user.getPassword()+" Keep this mail with you for your future reference";
        emailService.sendEmail(user.getEmail(),subject,body);
        return  new Response("sucess");
    }

    @GetMapping("/test")
    public String check(){
        emailService.sendEmail("babunchandapur@gmail.com","df","ddjbfjb");
        if(seconds<29){
            return "allow";
        }else{
            return "not";
        }
    }






}
