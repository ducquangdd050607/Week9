import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MathUtilsTest {
    @Test
    public void testAdd() {
        MathUtils math = new MathUtils();
        // chỉ test hàm add()
        assertEquals(5, math.add(2, 3)); 
    }
}