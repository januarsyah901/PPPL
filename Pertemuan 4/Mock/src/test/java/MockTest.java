import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class MockTest {
    @Test
    public void testMock(){
        UserRepository mockRepo =  mock(UserRepository.class);
        UserService userService = new UserService(mockRepo);
        User user = new User("A01","Budiono");

        userService.register(user);

        verify(mockRepo, times(1)).save(user);

        verify(mockRepo,never()).findById(anyString());
    }
}
