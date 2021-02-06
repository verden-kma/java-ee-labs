import kma.topic2.junit.Main;
import kma.topic2.junit.exceptions.UserNotFoundException;
import kma.topic2.junit.model.NewUser;
import kma.topic2.junit.model.User;
import kma.topic2.junit.repository.UserRepository;
import kma.topic2.junit.service.UserService;
import kma.topic2.junit.validation.UserValidator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = Main.class)
public class UserServiceTest {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @MockBean
    private UserValidator userValidator;

    @Test
    void createNewUser() {
        NewUser user = NewUser.builder().login("ukman").fullName("Andrew Rozhko").password("12345").build();
        userService.createNewUser(user);

        verify(userValidator).validateNewUser(any());

        User rUser = userRepository.getUserByLogin(user.getLogin());
        assertEquals(user.getFullName(), rUser.getFullName());
        assertEquals(user.getLogin(), rUser.getLogin());
        assertEquals(user.getPassword(), rUser.getPassword());
    }

    @Test
    void getUserByLogin() {
        NewUser user = NewUser.builder().login("ukman").fullName("Andrew Rozhko").password("12345").build();
        userRepository.saveNewUser(user);

        User rUser = userService.getUserByLogin(user.getLogin());
        assertEquals(user.getFullName(), rUser.getFullName());
        assertEquals(user.getLogin(), rUser.getLogin());
        assertEquals(user.getPassword(), rUser.getPassword());
    }

    @Test
    void failGetUserByLogin() {
        assertThrows(UserNotFoundException.class, () -> {userService.getUserByLogin("none");});
    }
}
