import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class LoginTest {

    static Login login;

    @BeforeAll
    static void setup() {
        login = new Login();
    }

    @Test
    @Order(2)
    void testCrudAccess() {
        login.CRUD(true);
        assertTrue(login.isCrudAllowed());
        System.out.println("lu bisa CRUD");
    }

    @Test
    @Order(1)
    void testLoginSuccess() {
        login.login(true);
        assertTrue(login.isLogin());
        System.out.println("lu dah login");
    }
}