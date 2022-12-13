import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Operation {
    private final long value;
    private final boolean isUSD;
    private final boolean isDeposit;
    private final Date timestamp;
    private final DateFormat df;

    public Operation(boolean isDeposit, boolean isUSD, long value) {
        this.value = value;
        this.isDeposit = isDeposit;
        this.isUSD = isUSD;
        timestamp = Calendar.getInstance().getTime();
        df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    }

    public long getValue() {
        return value;
    }

    public boolean isDeposit() {
        return isDeposit;
    }

    public boolean isUSD() {
        return isUSD;
    }

    public String getTimestamp() {
        return df.format(timestamp);
    }
}
