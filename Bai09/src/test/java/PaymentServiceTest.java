import org.junit.jupiter.api.Test;

public class PaymentServiceTest {
    @Test
    public void testLogging() {
        PaymentService service = new PaymentService();
        
        // Kịch bản 1: Giao dịch thành công (Sẽ in ra INFO)
        service.processTransaction("nguyen_khanh_phong", 500000);
        
        // Kịch bản 2: Cố tình tạo giao dịch lỗi (Sẽ in ra ERROR và ngoại lệ)
        service.processTransaction("hacker_anonymous", -100);
    }
}