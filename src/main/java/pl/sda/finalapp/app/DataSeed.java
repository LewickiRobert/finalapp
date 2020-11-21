package pl.sda.finalapp.app;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.sda.finalapp.app.users.*;

@Service
public class DataSeed implements InitializingBean {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void afterPropertiesSet() throws Exception {
        if (roleRepository.count()==0){
            roleRepository.save(new Role(Role.ADMIN));
            roleRepository.save(new Role(Role.USER));
        }
        User user1 = new User("user"
                ,"user"
                ,"user@user"
                ,passwordEncoder.encode("123")
                ,"lbn"
                ,"pl"
                ,"20-825",
                "Ulica",
                "19-12-1995",
                "90605",
                "854545",
                true);
        userRepository.save(user1);
        User admin = new User("admin"
                ,"admin"
                ,"admin@admin"
                ,passwordEncoder.encode("123")
                ,"lbn"
                ,"pl"
                ,"20-825",
                "Ulica",
                "19-12-1995",
                "90605",
                "854545",
                true);
        userRepository.save(admin);
    }
}
