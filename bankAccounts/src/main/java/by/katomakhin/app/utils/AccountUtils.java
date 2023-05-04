package by.katomakhin.app.utils;

public class AccountUtils {
    private AccountUtils() {
        throw new IllegalStateException("Utility class");
    }
    public static String generateAccNumber(String userName) {
        return String.format("%s%s%d", "by", userName, userName.hashCode());
    }
}
