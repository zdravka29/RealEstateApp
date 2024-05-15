package uni.fmi.RealEstate.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uni.fmi.RealEstate.models.Email;
import uni.fmi.RealEstate.services.EmailService;

@Controller
public class Main {
    @Autowired
    EmailService emailService;

    @GetMapping(path = "/")
    public String getMain() { return "index"; }

    @GetMapping(path = "/register")
    public String getRegister() { return "register"; }

    @GetMapping(path = "/apartments")
    public String getApartments() { return "apartment"; }

    @GetMapping(path = "/contact")
    public String getContact() {
        return "contact";
    }

    @RequestMapping("/send-message")
    public String sendContactMessage(@RequestParam(value = "fullName") String name,
                                     @RequestParam(value = "companyName") String company,
                                     @RequestParam(value = "email") String email,
                                     @RequestParam(value = "subject") String subject,
                                     @RequestParam(value = "body") String body){
        try{
            emailService.setTemplateEmail(name,company,email,subject,body,"stu2301717020@uni-plovdiv.bg");

            Email emailToSave = new Email();
            emailToSave.setBody(body);
            emailToSave.setFromEmail(email);
            emailToSave.setClientName(name);
            emailToSave.setCompanyName(company);
            emailToSave.setSubject(subject);

            emailService.create(emailToSave);

            return "Successfully sent";
        } catch (Exception ex){
            return "error";
        }
    }


}
