package pl.sda.finalapp.app.users;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

  @GetMapping("/login")
    public String loginpage(){
      return "loginPage";
  }

}