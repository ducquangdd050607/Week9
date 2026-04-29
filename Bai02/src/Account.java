
import java.util.ArrayList;
import java.util.List;

/**
 * Lớp trừu tượng đại diện cho một tài khoản ngân hàng.
 * Cung cấp các chức năng cơ bản như gửi tiền, rút tiền và quản lý lịch sử giao dịch.
 */
public abstract class Account {
  /** Loại tài khoản vãng lai. */
  public static final String CHECKING_TYPE = "CHECKING";

  /** Loại tài khoản tiết kiệm. */
  public static final String SAVINGS_TYPE = "SAVINGS";

  private long accountNumber;
  private double balance;
  protected List<Transaction> transactionList; 

  /**
   * Khởi tạo tài khoản với số tài khoản và số dư ban đầu.
   *
   */
  public Account(long accountNumber, double balance) {
    this.accountNumber = accountNumber;
    this.balance = balance;
    this.transactionList = new ArrayList<>();
  }

  /**
   * Lấy số tài khoản.
   *
   * @return số tài khoản
   */
  public long getAccountNumber() {
    return accountNumber;
  }

  /**
   * Thiết lập số tài khoản.
   *
   * @param accountNumber số tài khoản mới
   */
  public void setAccountNumber(long accountNumber) {
    this.accountNumber = accountNumber;
  }

  /**
   * Lấy số dư hiện tại của tài khoản.
   *
   * @return số dư tài khoản
   */
  public double getBalance() {
    return balance;
  }

  /**
   * Thiết lập số dư tài khoản.
   *
   * @param balance số dư mới
   */
  protected void setBalance(double balance) {
    this.balance = balance;
  }

  /**
   * Lấy danh sách giao dịch của tài khoản.
   *
   * @return danh sách giao dịch
   */
  public List<Transaction> getTransactionList() {
    return transactionList;
  }

  /**
   * Thiết lập danh sách giao dịch.
   *
   * @param transactionList danh sách giao dịch mới
   */
  public void setTransactionList(List<Transaction> transactionList) {
    if (transactionList == null) {
      this.transactionList = new ArrayList<>();
    } else {
      this.transactionList = transactionList;
    }
  }

  /**
   * Gửi tiền vào tài khoản. Phương thức trừu tượng được triển khai bởi các lớp con.
   *
   * @param amount số tiền gửi
   */
  public abstract void deposit(double amount);

  /**
   * Rút tiền từ tài khoản. Phương thức trừu tượng được triển khai bởi các lớp con.
   *
   * @param amount số tiền rút
   */
  public abstract void withdraw(double amount);

  /**
   * Xử lý gửi tiền với kiểm tra hợp lệ.
   *
   * @param amount số tiền gửi
   * @throws InvalidFundingAmountException nếu số tiền không hợp lệ
   */
  protected void doDepositing(double amount) throws InvalidFundingAmountException {
    if (amount <= 0) {
      throw new InvalidFundingAmountException(amount);
    }
    balance += amount;
  }

  /**
   * Xử lý rút tiền với kiểm tra hợp lệ.
   *
   * @param amount số tiền rút
   * @throws InvalidFundingAmountException nếu số tiền không hợp lệ
   * @throws InsufficientFundsException nếu số dư không đủ
   */
  protected void doWithdrawing(double amount)
      throws InvalidFundingAmountException, InsufficientFundsException {
    if (amount <= 0) {
      throw new InvalidFundingAmountException(amount);
    }
    if (amount > balance) {
      throw new InsufficientFundsException(amount);
    }
    balance -= amount;
  }

  /**
   * Thêm một giao dịch vào danh sách giao dịch của tài khoản.
   *
   * @param transaction giao dịch cần thêm
   */
  public void addTransaction(Transaction transaction) {
    if (transaction != null) {
      transactionList.add(transaction);
    }
  }

  /**
   * Lấy lịch sử giao dịch của tài khoản dưới dạng chuỗi.
   *
   * @return lịch sử giao dịch
   */
  public String getTransactionHistory() {
    StringBuilder sb = new StringBuilder();
    sb.append("Lịch sử giao dịch của tài khoản ").append(accountNumber).append(":\n");
    for (int i = 0; i < transactionList.size(); i++) {
      sb.append(transactionList.get(i).getTransactionSummary());
      if (i < transactionList.size() - 1) {
        sb.append("\n");
      }
    }
    return sb.toString();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof Account)) {
      return false;
    }
    Account other = (Account) obj;
    return this.accountNumber == other.accountNumber;
  }

  @Override
  public int hashCode() {
    return (int) (accountNumber ^ (accountNumber >>> 32));
  }
}