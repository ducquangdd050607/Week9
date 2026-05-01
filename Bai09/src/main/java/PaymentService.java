import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PaymentService {
    // Khởi tạo Logger chuẩn của SLF4J
    private static final Logger logger = LoggerFactory.getLogger(PaymentService.class);

    public void processTransaction(String username, int amount) {
        // Sử dụng INFO để theo dõi mốc quan trọng, dùng {} để truyền tham số
        logger.info("Bắt đầu xử lý giao dịch số tiền {} VNĐ cho tài khoản: {}", amount, username);

        try {
            if (amount < 0) {
                throw new IllegalArgumentException("Số tiền giao dịch không hợp lệ (âm)!");
            }
            logger.info("Giao dịch của {} đã hoàn tất thành công.", username);
        } catch (Exception e) {
            // Sử dụng ERROR để ghi nhận ngoại lệ. Trực tiếp truyền 'e' vào cuối để in ra StackTrace
            logger.error("Phát hiện lỗi khi xử lý giao dịch cho tài khoản {}", username, e);
        }
    }
}