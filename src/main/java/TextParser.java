import java.util.Arrays;
import java.util.List;

public class TextParser {

    /*              CONSTANT FIELDS                    */
    private static final List<String> GO_SYNONYMS = Arrays.asList("move", "walk", "travel", "skip");
    private static final List<String> GET_SYNONYMS = Arrays.asList("pickup", "grab", "obtain");
    private static final List<String> QUIT_SYNONYMS = Arrays.asList("exit", "end");

    /*              PRIVATE CONSTRUCTOR                 */
    private TextParser() {

    }
    /*              BUSINESS METHODS                   */
    public static String[] parseText(String userInput) {
        String[] parsed = userInput.strip().toLowerCase().split(" ", 2);
        if (GO_SYNONYMS.contains(parsed[0])) {
            parsed[0] = "go";
        } else if (GET_SYNONYMS.contains(parsed[0])) {
            parsed[0] = "get";
        } else if (QUIT_SYNONYMS.contains(parsed[0])) {
            parsed[0] = "quit";
        }
        return parsed;
    }
}
