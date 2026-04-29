/**
 * Ngoại lệ cơ bản cho hệ thống ngân hàng.
 * Được sử dụng làm lớp cha cho các ngoại lệ ngân hàng cụ thể.
 */
public class BankException extends Exception {
  /**
   * Khởi tạo ngoại lệ với thông báo lỗi.
   *
   * @param message thông báo lỗi
   */
  public BankException(String message) {
    super(message);
  }
}

