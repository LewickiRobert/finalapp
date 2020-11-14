package pl.sda.finalapp.app.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u where LOWER(u.eMail) = LOWER(?1)")
    Optional<User> findByEMail (String eMail);
}
