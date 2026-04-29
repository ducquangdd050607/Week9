import org.junit.jupiter.api.Test;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PathTest {

    @Test
    public void testHardcodedFilePath() {
        // Cố tình dùng định dạng cứng của Windows (dấu \ )
        /*
        String logPath = "var\\log\\app.log";
        
        File file = new File(logPath);
        String fileName = file.getName();
        
        assertEquals("app.log", fileName, "Tên file trích xuất bị sai do lỗi hệ điều hành!");*/
        Path logPath = Paths.get("var", "log", "app.log");
        
        String fileName = logPath.getFileName().toString();
        
        assertEquals("app.log", fileName, "Tên file trích xuất phải luôn là app.log trên mọi HĐH");

    }
}