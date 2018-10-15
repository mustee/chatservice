import com.chatserver.Application;
import com.chatserver.models.draft.MessageDraft;
import com.chatserver.models.draft.UserDraft;
import com.chatserver.repository.MessageRepository;
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
public class MessageRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageRepository messageRepository;

    private long userId;
    private long messageId;

    @Before
    public void setUp() {
        UserDraft draft = new UserDraft().draft().username("test").password("test").dateAdded(new Date()).build();
        userId = userRepository.insert(draft);

        MessageDraft messageDraft = new MessageDraft().draft().fromUserId(userId).toUserId(Optional.empty()).chatRoomId(Optional.empty())
            .message("test").timestamp(new Date()).build();
        messageId = messageRepository.insert(messageDraft);
    }

    @Test
    public void get(){
        Assert.assertTrue(true);
    }

    @After
    public void tearDown(){
        messageRepository.delete(messageId);
        userRepository.delete(userId);
    }
}
