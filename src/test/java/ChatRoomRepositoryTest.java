import com.chatserver.Application;
import com.chatserver.models.ChatRoom;
import com.chatserver.models.draft.ChatRoomDraft;
import com.chatserver.repository.ChatRoomRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public class ChatRoomRepositoryTest {

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    private long chatRoomId;

    @Before
    public void setUp() {
        ChatRoomDraft draft = new ChatRoomDraft().draft().name("test").dateCreated(new Date()).build();
        chatRoomId = chatRoomRepository.insert(draft);
    }

    @Test
    public void findByName(){
        Optional<ChatRoom> chatRoom = chatRoomRepository.findByName("test");
        Assert.assertTrue(chatRoom.isPresent());
    }

    @Test
    public void getAll(){
        List<ChatRoom> chatRooms = chatRoomRepository.getAll();
        Assert.assertTrue(chatRooms.size() > 0);
    }

    @After
    public void tearDown(){
        chatRoomRepository.delete(chatRoomId);
    }
}
