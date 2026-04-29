import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Lớp đại diện cho một ngân hàng.
 * Quản lý danh sách khách hàng và cung cấp các chức năng liên quan đến khách hàng.
 */
public class Bank {
  /** Mẫu regex để kiểm tra số CMND (9 chữ số). */
  private static final Pattern CMND_PATTERN = Pattern.compile("\\d{9}");

  private List<Customer> customersList; 

  /** Khởi tạo ngân hàng với danh sách khách hàng rỗng. */
  public Bank() {
    this.customersList = new ArrayList<>();
  }

  /**
   * Lấy danh sách khách hàng.
   *
   * @return danh sách khách hàng
   */
  public List<Customer> getCustomerList() {
    return customersList;
  }

  /**
   * Thiết lập danh sách khách hàng.
   *
   * @param customerList danh sách khách hàng mới
   */
  public void setCustomerList(List<Customer> customerList) {
    if (customerList == null) {
      this.customersList = new ArrayList<>();
    } else {
      this.customersList = customerList;
    }
  }

  /**
   * Đọc danh sách khách hàng từ một luồng đầu vào.
   * Định dạng: Dòng có 9 chữ số là dòng khách hàng (ID + Tên), các dòng tiếp theo là tài khoản.
   *
   * @param inputStream luồng đầu vào chứa dữ liệu khách hàng
   */
  public void readCustomerList(InputStream inputStream) {
    if (inputStream == null) {
      return;
    }

    try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
      String line;
      Customer currentCustomer = null;

      while ((line = reader.readLine()) != null) {
        line = line.trim();
        if (line.isEmpty()) {
          continue;
        }

        int lastSpaceIndex = line.lastIndexOf(' ');
        if (lastSpaceIndex <= 0) {
          continue;
        }

        String token = line.substring(lastSpaceIndex + 1).trim();
        if (CMND_PATTERN.matcher(token).matches()) {
          // Dòng khách hàng mới
          String name = line.substring(0, lastSpaceIndex).trim();
          currentCustomer = new Customer(Long.parseLong(token), name);
          customersList.add(currentCustomer);
        } else if (currentCustomer != null) {
          // Dòng tài khoản
          parseAndAddAccount(line, currentCustomer);
        }
      }
    } catch (Exception e) {
      System.err.println("Lỗi đọc dữ liệu: " + e.getMessage());
    }
  }

  /**
   * Phân tích chuỗi và thêm tài khoản vào khách hàng.
   *
   * @param line chuỗi chứa thông tin tài khoản
   * @param customer khách hàng cần thêm tài khoản
   */
  private void parseAndAddAccount(String line, Customer customer) {
    String[] parts = line.split("\\s+");
    if (parts.length < 3) {
      return;
    }

    try {
      long accountNumber = Long.parseLong(parts[0]);
      String accountType = parts[1];
      double balance = Double.parseDouble(parts[2]);

      if (Account.CHECKING_TYPE.equals(accountType)) {
        customer.addAccount(new CheckingAccount(accountNumber, balance));
      } else if (Account.SAVINGS_TYPE.equals(accountType)) {
        customer.addAccount(new SavingsAccount(accountNumber, balance));
      }
    } catch (NumberFormatException e) {
      System.err.println("Lỗi phân tích dữ liệu tài khoản: " + e.getMessage());
    }
  }

  /**
   * Lấy thông tin khách hàng sắp xếp theo ID.
   *
   * @return chuỗi chứa thông tin các khách hàng sắp xếp theo ID
   */
  public String getCustomersInfoByIdOrder() {
    List<Customer> sortedList = new ArrayList<>(customersList);
    sortedList.sort((c1, c2) -> Long.compare(c1.getIdNumber(), c2.getIdNumber()));
    return formatCustomerList(sortedList);
  }

  /**
   * Lấy thông tin khách hàng sắp xếp theo tên.
   *
   * @return chuỗi chứa thông tin các khách hàng sắp xếp theo tên
   */
  public String getCustomersInfoByNameOrder() {
    List<Customer> sortedList = new ArrayList<>(customersList);
    sortedList.sort(
        (c1, c2) -> {
          int nameComparison = c1.getFullName().compareTo(c2.getFullName());
          return nameComparison != 0
              ? nameComparison
              : Long.compare(c1.getIdNumber(), c2.getIdNumber());
        });
    return formatCustomerList(sortedList);
  }

  /**
   * Định dạng danh sách khách hàng thành chuỗi.
   *
   * @param customers danh sách khách hàng
   * @return chuỗi thông tin khách hàng
   */
  private String formatCustomerList(List<Customer> customers) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < customers.size(); i++) {
      sb.append(customers.get(i).getCustomerInfo());
      if (i < customers.size() - 1) {
        sb.append("\n");
      }
    }
    return sb.toString();
  }
}