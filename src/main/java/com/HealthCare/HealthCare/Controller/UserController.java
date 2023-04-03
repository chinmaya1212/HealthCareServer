package com.HealthCare.HealthCare.Controller;

import com.HealthCare.HealthCare.Repo.UserRepositoy;
import com.HealthCare.HealthCare.models.Response;
import com.HealthCare.HealthCare.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.internet.InternetAddress;
import java.util.Optional;

@RestController
@RequestMapping
public class UserController {
    Response response;
    @Autowired
    private UserRepositoy userRepositoy;
    @PostMapping("/addUser")
    public Response addUser(@RequestBody User user){
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
    @GetMapping("/getUserbyId")
    public Optional<User> getuserbyId(@RequestParam("q") String email){
        return this.userRepositoy.findById(email);
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
    @PutMapping("/updateMoney")
    public String addMoney(@RequestBody User user){
        Optional<User> currdata=this.userRepositoy.findById(user.getEmail());
        currdata.get().setMoney(user.getMoney());
        if(currdata.isEmpty()){
            return "not found";
        }else{
            this.userRepositoy.save(currdata.get());
            return "sucesss";

        }


    }
    @GetMapping("/getMoney")
    public int getMoney(@RequestParam("q") String email){
        Optional<User> user=this.userRepositoy.findById(email);
        return user.get().getMoney();
    }
}
