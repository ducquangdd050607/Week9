/**
 * Tài khoản vãng lai - cho phép gửi và rút tiền tự do.
 */
public class CheckingAccount extends Account {
  /**
   * Khởi tạo tài khoản vãng lai.
   *
   * @param accountNumber số tài khoản
   * @param balance số dư ban đầu
   */
  public CheckingAccount(long accountNumber, double balance) {
    super(accountNumber, balance);
  }

  @Override
  public void deposit(double amount) {
    double initialBalance = getBalance();
    try {
      doDepositing(amount);
      double finalBalance = getBalance();
      Transaction transaction = new Transaction(
          Transaction.TYPE_DEPOSIT_CHECKING, amount, initialBalance, finalBalance);
      addTransaction(transaction);
    } catch (BankException e) {
      System.err.println("Lỗi gửi tiền: " + e.getMessage());
    }
  }

  @Override
  public void withdraw(double amount) {
    double initialBalance = getBalance();
    try {
      doWithdrawing(amount);
      double finalBalance = getBalance();
      Transaction transaction = new Transaction(
          Transaction.TYPE_WITHDRAW_CHECKING, amount, initialBalance, finalBalance);
      addTransaction(transaction);
    } catch (BankException e) {
      System.err.println("Lỗi rút tiền: " + e.getMessage());
    }
  }
}
