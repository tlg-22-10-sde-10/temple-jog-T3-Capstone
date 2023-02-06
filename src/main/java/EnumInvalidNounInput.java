/**
 * Created by dev0 on 2/4/23.
 */
public enum EnumInvalidNounInput {
    BAD_LOOK("Invalid command. Please provide item name to look for or type 'help'."),
    BAD_GET("Invalid command. Please provide the item name trying to get or type 'help'."),
    BAD_USE("Invalid command. Please provide the item name to use or type 'help'."),
    BAD_NAV("Invalid command. Please provide a direction or type 'help'.");
    private final String warning_message;

    private EnumInvalidNounInput(String msg){
        this.warning_message = msg;
    }
    public String getWarning(){
        return this.warning_message;
    }
}