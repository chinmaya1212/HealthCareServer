package com.HealthCare.HealthCare.Controller;

import com.HealthCare.HealthCare.Repo.DoctorRepositoy;
import com.HealthCare.HealthCare.configmail.EmailSenderService;
import com.HealthCare.HealthCare.models.Doctors;
import com.HealthCare.HealthCare.models.Response;
import com.HealthCare.HealthCare.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping
public class DoctorController {
    @Autowired
    private DoctorRepositoy doctorRepositoy;
    @Autowired
    private EmailSenderService emailSenderService;
    @Autowired
    private MongoTemplate mongoTemplate;
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
    @GetMapping("/getdoctorlistgetdoctorlistbyclicnvisit")
    public List<Doctors> getdoctor(){
        System.out.println("call");
        Query query = new Query(Criteria.where("ishomvisit").is(false));

        return mongoTemplate.find(query, Doctors.class);


    }
    @GetMapping("/getdoctorlist")
    public List<Doctors> getdoctorlist(){
        return this.doctorRepositoy.findAll();
    }
    @GetMapping("/getdoctorbycategory")
    public List<Doctors> getdoctorbyCategory(@RequestParam("q") String name){
        String regxname=".*"+name+".*";

        Query query=new Query();
        query.addCriteria(Criteria.where("specialist").regex(regxname,"i"));
        return mongoTemplate.find(query,Doctors.class);
    }
    @GetMapping("/getDoctorbyId")
    public Optional<Doctors> getdoctorbyId(@RequestParam("q") String email){
        System.out.println(email);
        return this.doctorRepositoy.findById(email);
    }
    @GetMapping("/sendEmail")
    public String sendEmail(@RequestParam("email") String email,@RequestParam("slot") String slot){
        System.out.println("jhhj");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date tomorrowDate = calendar.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = sdf.format(tomorrowDate);
        emailSenderService.sendEmail(email,"Slot Booked","You Can visit the Clicn on "+formattedDate+" on your Booked Slot"+slot);
        System.out.println("Tomorrow's date is: " + formattedDate);
        return "sucesss";

    }
    @PostMapping("/updatedoctorslot")
    public String updateDoctorSlot(@RequestBody Doctors doctors){
        System.out.println("hwl");
        String email=doctors.getEmail();
        Optional<Doctors> doctor=this.doctorRepositoy.findById(email);
        doctor.get().setSlot10_11(doctors.isSlot10_11());
        doctor.get().setSlot11_12(doctors.isSlot11_12());
        doctor.get().setSlot1_2(doctors.isSlot1_2());
        doctor.get().setSlot2_3(doctors.isSlot2_3());
        doctorRepositoy.save(doctor.get());
        return "sucesss";

    }
    @GetMapping("/getDoctorsByHomeVisit")
    public List<Doctors> getDoctorsByHomeVisit() {
        Query query = new Query(Criteria.where("ishomvisit").is(true));

        return mongoTemplate.find(query, Doctors.class);
    }


}
