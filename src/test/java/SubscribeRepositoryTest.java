import com.chatserver.Application;
import com.chatserver.models.draft.ChatRoomDraft;
import com.chatserver.models.draft.SubscribeDraft;
import com.chatserver.models.draft.UserDraft;
import com.chatserver.repository.ChatRoomRepository;
import com.chatserver.repository.SubscribeRepository;
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

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public class SubscribeRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @Autowired
    private SubscribeRepository subscribeRepository;

    private long id;
    private long userId;
    private long chatRoomId;

    @Before
    public void setUp() {
        UserDraft draft = new UserDraft().draft().username("test").password("test").dateAdded(new Date()).build();
        userId = userRepository.insert(draft);

        ChatRoomDraft chatRoomDraft = new ChatRoomDraft().draft().name("test").dateCreated(new Date()).build();
        chatRoomId = chatRoomRepository.insert(chatRoomDraft);

        SubscribeDraft subscribeDraft = new SubscribeDraft().draft().chatRoomId(chatRoomId).userId(userId).build();
        id = subscribeRepository.insert(subscribeDraft);
    }

    @Test
    public void get(){
        Assert.assertTrue(true);
    }

    @After
    public void tearDown(){
        userRepository.delete(userId);
        chatRoomRepository.delete(chatRoomId);
        subscribeRepository.delete(id);
    }
}
