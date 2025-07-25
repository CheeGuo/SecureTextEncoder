public class Encoded {
    private final String groupID = "G01/CS-G10";
    private int count;
    private String resultText;
    private String inputText;

    // Default constructor
    public Encoded() {}

    // Constructor with inputText
    public Encoded(String inputText) {
        this.inputText = inputText;
        this.count = 0;
        this.resultText = "";
    }

    // Count characters ignoring spaces
    public int countCharacters(String inputText) {
        count = 0;
        for (char x : inputText.toCharArray()) {
            if (x != ' ') {
                ++count;
            }
        }
        return count;
    }

    // Validate input to ensure only uppercase letters and digits
    public boolean validateInput(String inputText) {
        for (char x : inputText.toCharArray()) {
            if (!(Character.isUpperCase(x) || Character.isDigit(x)|| x == ' ')) {
                return false; // Reject if NOT uppercase letter or digit
            }
        }
        return true; // Accept if all characters are valid
    }

    // Generate a shift value based on groupID hashCode
    public int generateShift() {
        return (Math.abs(groupID.hashCode()) % 10) + 1;
    }

    // Apply Caesar cipher encoding with a dynamic shift value
    public String applyCipher(String inputText, int shift) {
        StringBuilder encodedAnswer = new StringBuilder();
        for (char x : inputText.toCharArray()) {
            if (x == ' ') {
                encodedAnswer.append(' ');
            } else if (Character.isUpperCase(x)) {
                encodedAnswer.append((char) ((x - 'A' + shift) % 26 + 'A'));
            } else if (Character.isDigit(x)) {
                encodedAnswer.append((char) ((x - '0' + shift) % 10 + '0'));
            }
        }
        return encodedAnswer.toString();
    }
}
