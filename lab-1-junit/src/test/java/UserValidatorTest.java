import kma.topic2.junit.exceptions.ConstraintViolationException;
import kma.topic2.junit.exceptions.LoginExistsException;
import kma.topic2.junit.model.NewUser;
import kma.topic2.junit.repository.UserRepository;
import kma.topic2.junit.validation.UserValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserValidatorTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserValidator validator;

    @Test
    void successValidateNewUser() {
        NewUser user = NewUser.builder().login("ukman").fullName("Andrew Rozhko").password("12345").build();
        validator.validateNewUser(user);
    }

    void loginDuplicate() {
        when(userRepository.isLoginExists(any())).thenReturn(true);
        NewUser user = NewUser.builder().login("ukman").fullName("Andrew Rozhko").password("12345").build();
        assertThrows(LoginExistsException.class, () -> validator.validateNewUser(user));
    }

    @Test
    void malformedPassword() {
        NewUser user = NewUser.builder().login("ukman").fullName("Andrew Rozhko").password("u4$71#").build();
        assertThatThrownBy(() -> validator.validateNewUser(user))
                .isInstanceOfSatisfying(ConstraintViolationException.class,
                        e -> assertThat(e.getErrors().contains("Password doesn't match regex")));
    }

    @Test
    void shortPassword() {
        NewUser user = NewUser.builder().login("ukman").fullName("Andrew Rozhko").password("u4").build();
        assertThatThrownBy(() -> validator.validateNewUser(user))
                .isInstanceOfSatisfying(ConstraintViolationException.class,
                        e -> assertThat(e.getErrors().contains("Password has invalid size")));
    }
}
