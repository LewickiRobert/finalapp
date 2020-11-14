package pl.sda.finalapp.app.users;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserRegistartionValidationService {

    public Map<String, String> validateUser(UserRegistrationDTO dto) {
        Map<String, String> exceptionsMap = new HashMap<>();
        if (!dto.getFirstName().matches("^[A-Z][a-z]{2,}$")) {
            exceptionsMap.put("firstNameValitadionResult",
                    "Imię jest wymagane. Przynajmniej 3 znaki oraz pierwsza duża, reszta małe.");
        }
        return exceptionsMap;
    }
}
