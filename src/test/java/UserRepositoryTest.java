import com.chatserver.Application;
import com.chatserver.models.User;
import com.chatserver.models.draft.UserDraft;
import com.chatserver.repository.UserRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private long userId;

    @Before
    public void setUp() {
        UserDraft draft = new UserDraft().draft().username("test").password("test").dateAdded(new Date()).build();
        userId = userRepository.insert(draft);
    }

    @Test
    public void findByUsername(){
        Optional<User> user = userRepository.findByUsername("test");
        Assert.assertTrue(user.isPresent());
    }

    @After
    public void tearDown(){
        userRepository.delete(userId);
    }
}
