import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StubTest {
    @Test
    public void test() {
        // 1. setup stub (tiruan)
        UserRepository stubRepo = mock(UserRepository.class);

        // 2. setup passive
        User fakeUser = new User("A01", "Budiono");
        when(stubRepo.findById("A01")).thenReturn(fakeUser);

        // 3. service test
        UserService service = new UserService(stubRepo);
        String name = service.getUserName("A01");

        assertEquals(name, fakeUser.name);
    }
}
