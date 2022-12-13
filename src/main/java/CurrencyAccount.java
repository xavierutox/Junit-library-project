public class CurrencyAccount {
    private long balance;
    private final boolean isUSD;

    public CurrencyAccount(boolean isUSD) {
        this.isUSD = isUSD;
        reset();
    }

    public long getBalance() {
        return balance;
    }

    private void reset() {
        if (isUSD) {
            balance = 0;
        } else {
            balance = 1_000_000;
        }
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }
}
