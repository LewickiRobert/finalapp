package pl.sda.finalapp.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRegistartionValidationService validationService;

    @GetMapping("/registration")
    public String registrationForm(Model model){
        UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO();
        model.addAttribute("userRegistrationData",userRegistrationDTO);
        model.addAttribute("countryList",Countries.values());
        return "registrationPage";
    }
    @PostMapping("/registration")
    public String registrationEffect(UserRegistrationDTO userRegistrationDTO, Model model){
        Map<String, String> validationExceptionMap = validationService.validateUser(userRegistrationDTO);
        if(!validationExceptionMap.isEmpty()){
            model.addAllAttributes(validationExceptionMap);
            model.addAttribute("userRegistrationData",userRegistrationDTO);
            model.addAttribute("countryList",Countries.values());
            return "registrationPage";

        }
        userService.registerUser(userRegistrationDTO);
        model.addAttribute("email", userRegistrationDTO.geteMail());
        return "welcomePage";
    }
}
