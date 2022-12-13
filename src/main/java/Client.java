import com.google.common.math.LongMath;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class Client {
    private final int userId;
    private final String storedPassword;
    private final CurrencyAccount accountUSD;
    private final CurrencyAccount accountCLP;
    private final LinkedList<Operation> history;
    private MessageDigest hashing;

    public Client(int id, String clearPassword) {
        accountCLP = new CurrencyAccount(false);
        accountUSD = new CurrencyAccount(true);
        try {
            hashing = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException exception) {
            System.exit(2);
        }
        hashing.update(clearPassword.getBytes(StandardCharsets.UTF_8));
        storedPassword = DatatypeConverter.printHexBinary(hashing.digest()).toLowerCase(Locale.ROOT);
        userId = id;
        history = new LinkedList<>();
    }

    public boolean validatePassword(String password) {
        hashing.update(password.getBytes(StandardCharsets.UTF_8));
        String hashDigest = DatatypeConverter.printHexBinary(hashing.digest()).toLowerCase(Locale.ROOT);
        return storedPassword.equals(hashDigest);
    }

    public long getBalance(boolean isUSD) {
        if (isUSD) {
            return accountUSD.getBalance();
        } else {
            return accountCLP.getBalance();
        }
    }

    public int getUserId() {
        return userId;
    }

    public List<Operation> getHistory() {
        return history;
    }

    public SystemError doOperation(Operation op) {
        history.add(op);
        if (op.isDeposit()) {
            if (op.isUSD()) {
                return doDeposit(op, accountUSD);
            } else {
                return doDeposit(op, accountCLP);
            }
        } else {
            if (op.isUSD()) {
                return doWithdraw(op, accountUSD);
            } else {
                return doWithdraw(op, accountCLP);
            }
        }
    }

    private SystemError doDeposit(Operation op, CurrencyAccount account) {
        try {
            long result = LongMath.checkedAdd(account.getBalance(), op.getValue());
            account.setBalance(result);
            return SystemError.OK;
        } catch (ArithmeticException except) {
            return SystemError.INVALID_OPERATION_AMOUNT_MAX_OVERFLOW;
        }
    }

    private SystemError doWithdraw(Operation op, CurrencyAccount account) {
        try {
            long result = LongMath.checkedSubtract(account.getBalance(), op.getValue());
            if (result >= 0) {
                account.setBalance(result);
                return SystemError.OK;
            } else {
                return SystemError.INVALID_OPERATION_AMOUNT_NO_FUNDS;
            }
        } catch (ArithmeticException except) {
            return SystemError.INVALID_OPERATION_AMOUNT_MIN_OVERFLOW;
        }
    }
}
