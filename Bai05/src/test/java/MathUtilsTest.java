import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MathUtilsTest {
    @Test
    public void testAdd() {
        MathUtils math = new MathUtils();
        // chỉ test hàm add()
        assertEquals(5, math.add(2, 3));
        //thêm test các hàm khác 
        assertEquals(1, math.subtract(3, 2));
        assertEquals(6, math.multiply(2, 3));
        assertEquals(2, math.divide(6, 3));
    }
}