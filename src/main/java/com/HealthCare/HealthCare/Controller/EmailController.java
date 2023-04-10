package com.HealthCare.HealthCare.Controller;

import com.HealthCare.HealthCare.configmail.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@RestController
@RequestMapping
public class EmailController {
    @Autowired
    private EmailSenderService emailSenderService;
    @GetMapping("/sendEmail")
    public String sendEmail(@RequestParam("email") String email, @RequestParam("slot") String slot){
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
    @GetMapping("/SendMailHomeVisit")
    public String sendmailHomeVisit(@RequestParam("emaildoc") String emaildoctor,@RequestParam("emailuser") String emailuser, @RequestParam("slot") String slot,@RequestParam("address")String address){
        System.out.println("hiii");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date tomorrowDate = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = sdf.format(tomorrowDate);
        emailSenderService.sendEmail(emaildoctor,"Slot Booked","You Can visit on the Address "+address+" On "+formattedDate+" on your Booked Slot"+slot);
        emailSenderService.sendEmail(emailuser,"Slot Conformed","Doctor Email : "+emaildoctor + "A Doctor Visit to your given address on your Booked Slot"+formattedDate+" at "+slot);
        System.out.println("Tomorrow's date is: " + formattedDate);
        return "sucess";
    }

}
