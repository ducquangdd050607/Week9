import java.util.ArrayList;
import java.util.List;

/**
 * Lớp đại diện cho một khách hàng ngân hàng.
 * Mỗi khách hàng có thông tin cá nhân (ID, tên) và quản lý danh sách tài khoản.
 */
public class Customer {
  private long idNumber;
  private String fullName;
  private List<Account> accountList;

  /**
   * Constructor không tham số.
   * Dùng cho các trường hợp cần khởi tạo khách hàng rỗng.
   */
  public Customer() {
    this(0L, "");
  }

  /**
   * Khởi tạo khách hàng với ID và tên.
   *
   * @param idNumber số CMND/ID của khách hàng
   * @param fullName tên đầy đủ của khách hàng
   */
  public Customer(long idNumber, String fullName) {
    this.idNumber = idNumber;
    this.fullName = fullName;
    this.accountList = new ArrayList<>();
  }

  /**
   * Lấy số CMND/ID của khách hàng.
   *
   * @return số ID
   */
  public long getIdNumber() {
    return idNumber;
  }

  /**
   * Thiết lập số CMND/ID của khách hàng.
   *
   * @param idNumber số ID mới
   */
  public void setIdNumber(long idNumber) {
    this.idNumber = idNumber;
  }

  /**
   * Lấy tên đầy đủ của khách hàng.
   *
   * @return tên khách hàng
   */
  public String getFullName() {
    return fullName;
  }

  /**
   * Thiết lập tên đầy đủ của khách hàng.
   *
   * @param fullName tên khách hàng mới
   */
  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  /**
   * Lấy danh sách tài khoản của khách hàng.
   *
   * @return danh sách tài khoản
   */
  public List<Account> getAccountList() {
    return accountList;
  }

  /**
   * Thiết lập danh sách tài khoản của khách hàng.
   *
   * @param accountList danh sách tài khoản mới
   */
  public void setAccountList(List<Account> accountList) {
    if (accountList == null) {
      this.accountList = new ArrayList<>();
    } else {
      this.accountList = accountList;
    }
  }

  /**
   * Thêm tài khoản cho khách hàng.
   * Kiểm tra để tránh thêm tài khoản trùng lặp.
   *
   * @param account tài khoản cần thêm
   */
  public void addAccount(Account account) {
    if (account == null) {
      return;
    }
    if (!accountList.contains(account)) {
      accountList.add(account);
    }
  }

  /**
   * Xóa tài khoản khỏi khách hàng.
   *
   * @param account tài khoản cần xóa
   */
  public void removeAccount(Account account) {
    if (account == null) {
      return;
    }
    accountList.remove(account);
  }

  /**
   * Lấy thông tin khách hàng dưới dạng chuỗi.
   *
   * @return thông tin khách hàng
   */
  public String getCustomerInfo() {
    return "Số CMND: " + idNumber + ". Họ tên: " + fullName + ".";
  }
}

