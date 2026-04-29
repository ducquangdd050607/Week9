import java.util.Locale;

/**
 * Ngoại lệ được ném khi số dư tài khoản không đủ để thực hiện giao dịch.
 */
public class InsufficientFundsException extends BankException {
  /**
   * Khởi tạo ngoại lệ với số tiền cần thiết.
   *
   * @param amount số tiền giao dịch yêu cầu
   */
  public InsufficientFundsException(double amount) {
    super(
        "Số dư tài khoản không đủ $"
            + String.format(Locale.US, "%.2f", amount)
            + " để thực hiện giao dịch");
  }
}
