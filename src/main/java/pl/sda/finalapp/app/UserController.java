package pl.sda.finalapp.app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @GetMapping("/registration")
    public String registrationForm(Model model){
        UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO();
        model.addAttribute("userRegistrationData",userRegistrationDTO);
        model.addAttribute("countryList",Countries.values());
        return "registrationPage";
    }
    @PostMapping("/registration")
    public String registrationEffect(UserRegistrationDTO userRegistrationDTO){
        return "registrationPage";
    }
}
