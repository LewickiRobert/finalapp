package pl.sda.finalapp.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void registerUser(UserRegistrationDTO dto) {
        Optional<User> optionalUser = userRepository.findByEMail(dto.geteMail());
        userRepository.findByEMail(dto.geteMail())
                .ifPresent(e -> {
                    throw new RuntimeException("Email " + dto.geteMail() + " already in use.");
                });
        userRepository.save(User.applyDTO(dto));
    }
}
