package tokens;

/**
 * Created by Gamer on 11/25/2016.
 */
public class Terminal extends Token {

    public Terminal(String name) {
        super(name);
    }

    @Override
    public boolean isNonTerminal() {
        return false;
    }
}
