package uni.fmi.RealEstate.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import uni.fmi.RealEstate.models.Apartment;
import uni.fmi.RealEstate.models.ApartmentCategory;
import uni.fmi.RealEstate.models.Email;
import uni.fmi.RealEstate.services.ApartmentCategoryService;
import uni.fmi.RealEstate.services.ApartmentService;
import uni.fmi.RealEstate.services.EmailService;

import java.util.List;
import java.util.Optional;

@Controller
public class AdminController {

    @Autowired
    ApartmentService apartmentService;
    @Autowired
    ApartmentCategoryService apartmentCategoryService;

    @Autowired
    EmailService emailService;

    @RequestMapping("/dashboard/send-message")
    public String sendContactMessage(@RequestParam(value = "subject") String subject,
                                     @RequestParam(value = "body") String body,
                                     @RequestParam(value = "toEmail") String toEmail
    ){
        try{
            emailService.sendReplyMail(subject, body, toEmail);

            return "thankYou";
        }catch (Exception ex){
            return "error";
        }
    }

    @GetMapping(path = "/dashboard")
    public String getDashboard(Model model) {
        model.addAttribute("message", "Hello admin!");

        return "admin/index.html";
    }

    @GetMapping(path = "/dashboard/emails")
    public String getMessagesPage(Model model){
        List<Email> emails = emailService.findAll();
        model.addAttribute("emails", emails);

        return "admin/emails";
    }

    @GetMapping(path = "/dashboard/emails/{filter}")
    public String getFilteredEmails(@PathVariable(name = "filter") String filter,
                                    Model model){

        List<Email> emails = emailService.getFilteredEmailsByCompany(filter);
        model.addAttribute("emails", emails);

        return "admin/emails";
    }

    @GetMapping(path="/dashboard/apartments")
    public String getApartmentsPage(Model model){
        List<Apartment> apartments = apartmentService.findAll();
        model.addAttribute("apartments", apartments);

        return "admin/apartments";
    }

    @GetMapping(path="/dashboard/apartment/delete/{apartmentId}")
    public String deleteApartment(@PathVariable(name = "apartmentId") long apartmentId){

        Optional<Apartment> apartment = apartmentService.getEntity(apartmentId);

        if(!apartment.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Apartment not found");
        }

        apartmentService.remove(apartmentId);

        return "redirect:/dashboard/apartments";
    }

    @GetMapping(path="/dashboard/apartment/{apartmentId}")
    public String getEditApartmentPage(@PathVariable(name = "apartmentId") long apartmentId,
                                     Model model){
        Optional<Apartment> apartment = apartmentService.getEntity(apartmentId);

        if(!apartment.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The apartment does not exists!");
        }

        model.addAttribute("apartmentCategories", apartmentCategoryService.findAll());
        model.addAttribute("apartment", apartment.get());

        return "admin/addEditApartment";
    }

    @GetMapping(path="/dashboard/apartment/new")
    public String getNewApartmentPage(Model model){
        model.addAttribute("apartmentCategories", apartmentCategoryService.findAll());
        model.addAttribute("message", "Add new apartment offer");
        return "admin/addEditApartment";
    }

    @RequestMapping("/dashboard/apartment/save")
    public String saveApartment(@RequestParam(value = "apartmentId") long apartmentId,
                              @RequestParam(value = "apartmentCategory") long apartmentCategoryId,
                              @RequestParam(value="name") String name,
                              @RequestParam(value = "description", required = false) String description,
                              @RequestParam(value = "price") double price,
                              Model model){

        Optional<ApartmentCategory> apartmentCategory = apartmentCategoryService.getEntity(apartmentCategoryId);

        if(!apartmentCategory.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The apartment category does not exists!");
        }

        Optional<Apartment> apartment = apartmentService.getEntity(apartmentId);

        Apartment apart;

        if(apartment.isPresent()){
            apart = apartment.get();
        }else{
            apart = new Apartment();
        }

        apart.setName(name);
        apart.setPrice(price);
        apart.setDescription(description);

        apart.setApartmentCategory(apartmentCategory.get());

        if(apart.getId() > 0){
            apartmentService.update(apart);
        }else{
            apartmentService.create(apart);
        }
        return "redirect:/dashboard/products";
    }
}
