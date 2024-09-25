package app.server.inventory.test;

import app.server.inventory.auth.entities.AuthUser;
import app.server.inventory.auth.repository.AuthUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class TestAPI {
    public AuthUserRepository userRepository;

    @Test
     void test (){
//        List<User> users = new ArrayList<>();

//        userRepository.findAll().forEach(users::add);
    Optional<AuthUser> user = Optional.of(new AuthUser());
    user=userRepository.findByEmail("aman11@gamil.com");
        System.out.println(user);

    }

}
