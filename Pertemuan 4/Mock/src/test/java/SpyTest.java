import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class SpyTest {
    @Test
    public void spyTest(){
        // 1. setup spy
        List<String> realList =  new ArrayList<>();
        List<String> spyList =  spy(realList);

        // 2. execution
        spyList.add("A01");
        spyList.add("A02");

        // 3. state verification
        assertEquals(2, spyList.size());
        assertEquals("A01",spyList.get(0));

        // 4. interaction verification
        verify(spyList).add("A02");
        verify(spyList).add("A01");
    }
}
