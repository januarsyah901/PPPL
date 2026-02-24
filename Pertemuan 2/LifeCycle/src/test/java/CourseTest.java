import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CourseTest {
    private Course course;
    private Student student;

    // initCalss
    @BeforeAll
    void setUp() {
        course = new Course("PPPL", "Yo iku lah");
        student = new Student("S001", "Mas Anis");
    }

    // CleanClass
    @AfterAll
    void tearDown() {
        course = null;
        student = null;
    }

    @BeforeEach
    void enrollStudent() {
        course.enrollStudent(student);
    }

    @AfterEach
    void unenrollStudent() {
        course.unenrollStudent(student);
    }

    @Test
    void testEnrollStudent() {
        assertEquals(1,course.getStudentCount());
    }

    @Test
    void testUnenrollStudent() {
        course.unenrollStudent(student);
        assertEquals(0,course.getStudentCount());
    }

    @Test
    void testClearAllStudents() {
        course.clearAllStudents();
        assertEquals(0,course.getStudentCount());
    }

    @Test
    void testGetAllStudents() {
        assertEquals(1,course.getAllStudents().size());
    }

    @Test
    void testIsStudentEnrolled() {
        assertTrue(course.isStudentEnrolled(student));
    }

    @Test
    void testGetStudentCount() {
        assertEquals(1,course.getStudentCount());
    }

    @Test
    void testPrintAllStudents() {
        course.printAllStudents();
    }
}