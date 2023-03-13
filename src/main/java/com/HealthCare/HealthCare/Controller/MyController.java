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
import java.util.List;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;


@RestController
@RequestMapping
public class MyController {
    Response response;
    @Autowired
    private UserRepositoy userRepositoy;
    @Autowired
    private DoctorRepositoy doctorRepositoy;
    @Autowired
    private EmailSenderService emailService;
    @Autowired
    private MongoTemplate mongoTemplate;


    @PostMapping("/signin")
    public Response Login(@RequestBody User user){
        Optional<User> li=this.userRepositoy.findById(user.getEmail());
        //return li.get().getPassword();
       if(li.isEmpty()){

            return new Response("User Doesn't Exist");
        }else{
            if(user.getPassword().equals(li.get().getPassword())){
                return new Response("Logged In");


            }else{
                return new Response("Wrong Password");

            }
        }


    }

    //To Add User
    @PostMapping("/addUser")
    public  Response addUser(@RequestBody User user){
        //email validation
        String email=user.getEmail();
        if(!isValidEmailAddress(email)){
            response=new Response("Please enter Valid Email Id");
            return response;
        }
        //password validation
        String password=user.getPassword();
        if(password.length()<6){
            response=new Response("password length must be greater than 6");
            return response;

        }
        //check if the email already exist or not
        Optional<User> li=this.userRepositoy.findById(email);
        if(!li.isEmpty()){
            response=new Response("Email id is already Registered");
            return response;

        }


        //if pass all the validation the save the user
        try {
            this.userRepositoy.save(user);
            response=new Response("Register");
            return response;

        }catch (Exception e){
            response=new Response("error");
            return response;

        }
    }
    @PostMapping("/addDoctors")
    public Response addDoctors(@RequestBody Doctors doctors){
        Optional<Doctors> li=doctorRepositoy.findById(doctors.getEmail());
        if(li.isEmpty()){
            doctors.setSlot11_12(false);
            doctors.setSlot10_11(false);
            doctors.setSlot1_2(false);
            doctors.setSlot2_3(false);

            this.doctorRepositoy.save(doctors);
            return new Response("Added Successfully");
        }
        return new Response("Doctor already Exist");


    }
    private int seconds = 0;
    @PostMapping("/email_verification")
    public Response test(@RequestBody User user){
        String subject="Welcome "+user.getName()+" to HealthCare";
        String body="Hii,welcome to our app your email is "+user.getEmail()+" and your password is "+user.getPassword()+" Keep this mail with you for your future reference";
        emailService.sendEmail(user.getEmail(),subject,body);
        return  new Response("sucess");
    }
    @GetMapping("/getdoctorlist")
    public List<Doctors> getdoctor(){
        System.out.println("call");
        return this.doctorRepositoy.findAll();
       // Query query = new Query();
        //query.addCriteria(Criteria.where("specialist").is("Tumor"));

// Execute the query and retrieve the results
        //return  mongoTemplate.find(query, Doctors.class);
    }
    @GetMapping("/test")
    public String check(){
        if(seconds<29){
            return "allow";
        }else{
            return "not";
        }
    }

    private Timer timer = new Timer();
    private TimerTask task = new TimerTask() {
        public void run() {
            if (seconds == 30) {
                timer.cancel();
            } else {
                System.out.println("Elapsed time: " + seconds + " seconds.");
                seconds++;
            }
        }
    };

    public void start() {
        timer.scheduleAtFixedRate(task, 0, 1000);
    }
    public static boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (Exception ex) {
            result = false;
        }
        return result;
    }

}
