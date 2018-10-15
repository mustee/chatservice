import com.chatserver.Application;
import com.chatserver.models.User;
import com.chatserver.repository.UserRepository;
import com.chatserver.result.EntityResult;
import com.chatserver.result.Result;
import com.chatserver.service.UserService;
import com.chatserver.service.impl.UserServiceImpl;
import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Optional;

import static org.mockito.Mockito.doReturn;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class UserServiceTest {

    private final UserRepository userRepository = EasyMock.mock(UserRepository.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenStore tokenStore;

    private UserService userService;


    private User user;

    @Before
    public void setup() {
        userService = new UserServiceImpl(userRepository, tokenStore, passwordEncoder);
        user = new User(1,"test", passwordEncoder.encode("test"), false, new Date());


    }

    @Test
    public void signUp() {
        doReturn(Optional.empty()).when(userRepository).findByUsername("test");

        Result result = userService.signUp("test", "test");

        Assert.assertNotNull(result);
        Assert.assertFalse(result.hasError());
    }

    @Test
    public void signUpUserExists() {
        doReturn(Optional.of(user)).when(userRepository).findByUsername("test");

        Result result = userService.signUp("test", "test");

        Assert.assertNotNull(result);
        Assert.assertTrue(result.hasError());
        Assert.assertEquals(result.getErrors().size(), 1);
    }

   /* @Test
    public void signIn() {
        doReturn(Optional.of(user)).when(userRepository).findByUsername("test");

        Result result = userService.signIn("test", "test");

        Assert.assertNotNull(result);
        Assert.assertFalse(result.hasError());
        Assert.assertTrue(result instanceof EntityResult);
    }

    @Test
    public void signInUserDoesNotExist() {
        doReturn(Optional.empty()).when(userRepository).findByUsername("test");

        Result result = userService.signIn("test", "test");

        Assert.assertNotNull(result);
        Assert.assertTrue(result.hasError());
        Assert.assertEquals(result.getErrors().size(), 1);
    }*/
}
