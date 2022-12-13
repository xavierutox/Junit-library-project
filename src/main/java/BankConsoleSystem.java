import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;

import java.util.HashMap;
import java.util.List;

public class BankConsoleSystem {
    private Client currentUser;
    private HashMap<Integer, Client> systemUsers;
    private final ScreenManager manager;
    protected final long MIN_DEPOSIT_USD = 10;
    protected final long MIN_DEPOSIT_CLP = 2000;
    protected final long MIN_WITHDRAW_USD = 10;
    protected final long MIN_WITHDRAW_CLP = 2000;
    protected final long MAX_WITHDRAW_USD = 100;
    protected final long MAX_WITHDRAW_CLP = 200_000;
    private final int MAX_OPERATIONS_SESSION = 4;
    private int currentOperations;

    public BankConsoleSystem() {
        manager = new ScreenManager(this);
        setup();
    }

    private void setup() {
        currentOperations = 0;
        currentUser = null;
        systemUsers = new HashMap<>();
        createUsers();
    }

    private void createUsers() {
        systemUsers.put(2021, new Client(2021, "new_year!"));
    }

    public SystemError deposit(long amount, boolean isUSD) {
        if (currentUser != null) {
            if (currentOperations >= MAX_OPERATIONS_SESSION) {
                return SystemError.SESSION_NUMBER_OF_OPERATIONS_EXCEEDED;
            }
            Operation op;
            if (isUSD) {
                if (amount >= MIN_DEPOSIT_USD) {
                    op = new Operation(true, true, amount);
                    SystemError rc = currentUser.doOperation(op);
                    if (rc == SystemError.OK) {
                        currentOperations += 1;
                    }
                    return rc;
                } else {
                    return SystemError.INVALID_OPERATION_AMOUNT;
                }
            } else {
                if (amount >= MIN_DEPOSIT_CLP) {
                    op = new Operation(true, false, amount);
                    SystemError rc = currentUser.doOperation(op);
                    if (rc == SystemError.OK) {
                        currentOperations += 1;
                    }
                    return rc;
                } else {
                    return SystemError.INVALID_OPERATION_AMOUNT;
                }
            }
        } else {
            return SystemError.USER_NOT_LOGGED_IN;
        }
    }

    public SystemError withdraw(long amount, boolean isUSD) {
        if (currentUser != null) {
            if (currentOperations >= MAX_OPERATIONS_SESSION) {
                return SystemError.SESSION_NUMBER_OF_OPERATIONS_EXCEEDED;
            }
            Operation op;
            if (isUSD) {
                if (amount >= MIN_WITHDRAW_USD && amount <= MAX_WITHDRAW_USD) {
                    op = new Operation(false, true, amount);
                    SystemError rc = currentUser.doOperation(op);
                    if (rc == SystemError.OK) {
                        currentOperations += 1;
                    }
                    return rc;
                } else {
                    return SystemError.INVALID_OPERATION_AMOUNT;
                }
            } else {
                if (amount >= MIN_WITHDRAW_CLP && amount <= MAX_WITHDRAW_CLP) {
                    op = new Operation(false, false, amount);
                    SystemError rc = currentUser.doOperation(op);
                    if (rc == SystemError.OK) {
                        currentOperations += 1;
                    }
                    return rc;
                } else {
                    return SystemError.INVALID_OPERATION_AMOUNT;
                }
            }
        } else {
            return SystemError.USER_NOT_LOGGED_IN;
        }
    }

    public SystemError logIn(int id, String password) {
        if (currentUser == null) {
            if (systemUsers.containsKey(id)) {
                Client client = systemUsers.get(id);
                if (client.validatePassword(password)) {
                    currentUser = client;
                    currentOperations = 0;
                    return SystemError.OK;
                } else {
                    return SystemError.USER_INVALID_CREDENTIALS;
                }
            } else {
                return SystemError.USER_NOT_EXISTS;
            }
        } else {
            return SystemError.USER_ALREADY_LOGGED_IN;
        }
    }

    public SystemError logOut() {
        if (currentUser != null) {
            currentUser = null;
            return SystemError.OK;
        } else {
            return SystemError.USER_NOT_LOGGED_IN;
        }
    }

    public Pair<SystemError, List<Operation>> getHistory() {
        Pair<SystemError, List<Operation>> result = new Pair<>(SystemError.UNKNOWN, null);
        if (currentUser != null) {
            result.setFirst(SystemError.OK);
            result.setSecond(currentUser.getHistory());
        } else {
            result.setFirst(SystemError.USER_NOT_LOGGED_IN);
        }
        return result;
    }

    public Client getCurrentUser() {
        return currentUser;
    }

    public void reloadDefaults() {
        setup();
    }

    public void interactive() {
        manager.login();
    }

    private static class ScreenManager {
        private final BankConsoleSystem system;
        private final TextIO textIO;
        private final TextTerminal<?> terminal;

        public ScreenManager(BankConsoleSystem system) {
            this.system = system;
            textIO = TextIoFactory.getTextIO();
            terminal = textIO.getTextTerminal();
        }

        public void login() {
            terminal.println("===============================[CREDENCIALES]==================================");
            terminal.println("Bienvenido a Banco Azul, debe iniciar sesion para comenzar:\n");
            int uid = textIO.newIntInputReader().withMinVal(1).read("ID del usuario =>");
            String password = textIO.newStringInputReader().withInputMasking(true).read("Contrasena =>");
            SystemError rc = system.logIn(uid, password);
            if (rc == SystemError.OK) {
                terminal.println("Bienvenido!");
                screen0();
            } else {
                terminal.println("ERROR: " + rc);
                login();
            }
        }

        private void screen0() {
            terminal.println("==================================[CUENTAS]====================================");
            terminal.println("Tipo de Cuenta\t\tMonto Disponible\n");
            terminal.println("Cuenta CLP\t\t$" + system.getCurrentUser().getBalance(false));
            terminal.println("Cuenta USD\t\t$" + system.getCurrentUser().getBalance(true));
            terminal.println("=====================================[MENU]====================================");
            terminal.println("Bienvenido a Banco Azul, selecciona operacion a realizar:\n");
            terminal.println("1 - Deposito");
            terminal.println("2 - Retiro");
            terminal.println("3 - Ver transacciones");
            terminal.println("4 - Cerrar sesion");
            int option = textIO.newIntInputReader().withMinVal(1).withMaxVal(4).read("Opcion =>");
            switch (option) {
                case 1:
                    screen1();
                    break;
                case 2:
                    screen2();
                    break;
                case 3:
                    screen3();
                    break;
                case 4:
                    screen4();
                    break;
            }
        }

        private void screen1() {
            terminal.println("===================================[DEPOSITO]==================================");
            terminal.println("Selecciona el tipo de cuenta sobre la cual operar:\n");
            terminal.println("1 - Cuenta CLP");
            terminal.println("2 - Cuenta USD");
            int account_option = textIO.newIntInputReader().withMinVal(1).withMaxVal(2).read("Opcion =>");
            terminal.println("Ingresa el monto a depositar:\n");
            long value;
            SystemError rc;
            if (account_option == 1) {
                value = textIO.newLongInputReader().withMinVal(system.MIN_DEPOSIT_CLP).withMaxVal(Long.MAX_VALUE).read("Monto =>");
                rc = system.deposit(value, false);
            } else {
                value = textIO.newLongInputReader().withMinVal(system.MIN_DEPOSIT_USD).withMaxVal(Long.MAX_VALUE).read("Monto =>");
                rc = system.deposit(value, true);
            }
            if (rc == SystemError.OK) {
                terminal.println("Deposito realizado correctamente");
            } else {
                terminal.println("ERROR: " + rc);
            }
            screen0();
        }

        private void screen2() {
            terminal.println("====================================[RETIRO]===================================");
            terminal.println("Selecciona el tipo de cuenta sobre la cual operar:\n");
            terminal.println("1 - Cuenta CLP");
            terminal.println("2 - Cuenta USD");
            int account_option = textIO.newIntInputReader().withMinVal(1).withMaxVal(2).read("Opcion =>");
            terminal.println("Ingresa el monto a retirar:\n");
            long value;
            SystemError rc;
            if (account_option == 1) {
                value = textIO.newLongInputReader().withMinVal(system.MIN_WITHDRAW_CLP).withMaxVal(system.MAX_WITHDRAW_CLP).read("Monto =>");
                rc = system.withdraw(value, false);
            } else {
                value = textIO.newLongInputReader().withMinVal(system.MIN_WITHDRAW_USD).withMaxVal(system.MAX_WITHDRAW_USD).read("Monto =>");
                rc = system.withdraw(value, true);
            }
            if (rc == SystemError.OK) {
                terminal.println("Deposito realizado correctamente");
            } else {
                terminal.println("ERROR: " + rc);
            }
            screen0();
        }

        private void screen3() {
            terminal.println("================================[HISTORIAL]====================================");
            terminal.println("Mostrando el historial de transacciones realizadas:\n");
            terminal.println("Fecha\t\t\tTipo de Cuenta\t\tTipo Operacion\t\tMonto Operacion\n");
            for (Operation op: system.getCurrentUser().getHistory()) {
                if (op.isUSD()) {
                    if (op.isDeposit()) {
                        terminal.println(op.getTimestamp() + "\tUSD\t\t\tDeposito\t\t" + op.getValue());
                    } else {
                        terminal.println(op.getTimestamp() + "\tUSD\t\t\tRetiro\t\t\t" + op.getValue());
                    }
                } else {
                    if (op.isDeposit()) {
                        terminal.println(op.getTimestamp() + "\tCLP\t\t\tDeposito\t\t" + op.getValue());
                    } else {
                        terminal.println(op.getTimestamp() + "\tCLP\t\t\tRetiro\t\t\t" + op.getValue());
                    }
                }
            }
            screen0();
        }

        private void screen4() {
            SystemError rc = system.logOut();
            if (rc == SystemError.OK) {
                terminal.println("Ha cerrado sesion correctamente. Gracias por preferirnos!");
            } else {
                terminal.println("ERROR: " + rc);
            }
            login();
        }
    }
}
