package uni.fmi.RealEstate.utils;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uni.fmi.RealEstate.models.Apartment;
import uni.fmi.RealEstate.models.ApartmentCategory;
import uni.fmi.RealEstate.models.User;
import uni.fmi.RealEstate.repo.ApartmentCategoryRepo;
import uni.fmi.RealEstate.repo.ApartmentRepo;
import uni.fmi.RealEstate.repo.UserRepo;

@Component
public class DataGenerator {

    @Autowired
    private ApartmentCategoryRepo apartmentCategoryRepo;
    @Autowired
    private ApartmentRepo apartmentRepo;
    @Autowired
    private UserRepo userRepo;

    @PostConstruct
    public void initApartmentCategories(){
        if (apartmentCategoryRepo.count() == 0) {
            ApartmentCategory apartmentCategory = new ApartmentCategory();
            apartmentCategory.setName("Sale");
            Apartment apart1 = createApartment(apartmentCategory,100000,"New One-Room Apartment");

            apartmentCategory.addApartment(apart1);
            apartmentCategory.addApartment(createApartment(apartmentCategory, 250000, "Double-Room Apartment"));
            apartmentCategoryRepo.save(apartmentCategory);
            apartmentRepo.saveAll(apartmentCategory.getApartments());

            ApartmentCategory apartmentCategory2 = new ApartmentCategory();
            apartmentCategory2.setName("For Rent");
            apartmentCategory2.addApartment(createApartment(apartmentCategory2, 680, "Single-Room in Boyana"));
            apartmentCategoryRepo.save(apartmentCategory2);
            apartmentRepo.saveAll(apartmentCategory2.getApartments());

        }

        if (userRepo.count() == 0) {
            User user = new User();
            user.setUsername("zdravkai");
            user.setEmail("stu2301717020@uni-plovdiv.bg");
            user.setPassword("password");
            userRepo.save(user);
        }
    }

    private Apartment createApartment(ApartmentCategory apartmentCategory, double price, String name){
        Apartment apartment = new Apartment();
        apartment.setPrice(price);
        apartment.setName(name);
        apartment.setApartmentCategory(apartmentCategory);
        return apartment;
    }
}
