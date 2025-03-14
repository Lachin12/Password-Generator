package src;
import java.security.SecureRandom;

public class PasswordGenerator {
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMBERS = "0123456789";
    private static final String SYMBOLS = "!@#$%^&*()-_=+[]{};:,.<>?";

    private final SecureRandom random = new SecureRandom();

    public String generatePassword(int length, boolean includeUppercase, boolean includeLowercase, boolean includeNumbers, boolean includeSymbols) {
        StringBuilder password = new StringBuilder();
        String characterPool = buildCharacterPool(includeUppercase, includeLowercase, includeNumbers, includeSymbols);

        if (characterPool.isEmpty()) {
            throw new IllegalArgumentException("At least one character type must be selected.");
        }

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characterPool.length());
            password.append(characterPool.charAt(randomIndex));
        }

        return password.toString();
    }

    private String buildCharacterPool(boolean includeUppercase, boolean includeLowercase, boolean includeNumbers, boolean includeSymbols) {
        StringBuilder pool = new StringBuilder();

        if (includeUppercase) pool.append(UPPERCASE);
        if (includeLowercase) pool.append(LOWERCASE);
        if (includeNumbers) pool.append(NUMBERS);
        if (includeSymbols) pool.append(SYMBOLS);

        return pool.toString();
    }
}