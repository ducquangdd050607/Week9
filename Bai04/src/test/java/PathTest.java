import org.junit.jupiter.api.Test;
import java.io.File;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PathTest {

    @Test
    public void testHardcodedFilePath() {
        // Cố tình dùng định dạng cứng của Windows (dấu \ )
        String logPath = "var\\log\\app.log";
        
        File file = new File(logPath);
        String fileName = file.getName(); // Lấy tên tệp từ đường dẫn
        
        // KIẾN THỨC: 
        // - Trên Windows: Máy hiểu '\' là phân cách thư mục, nên nó tách được tên file là "app.log". Test PASS.
        // - Trên Linux/Mac: Máy chỉ hiểu '/' là phân cách. Nó coi toàn bộ chuỗi "var\log\app.log" 
        //   là TÊN CỦA 1 FILE DUY NHẤT. Do đó getName() trả về "var\log\app.log". Test FAIL.
        assertEquals("app.log", fileName, "Tên file trích xuất bị sai do lỗi hệ điều hành!");
    }
}