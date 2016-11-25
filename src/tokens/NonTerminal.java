package tokens;

/**
 * Created by Gamer on 11/25/2016.
 */
public class NonTerminal extends Token {

    public NonTerminal(String name) {
        super(name);
    }

    @Override
    public boolean isNonTerminal() {
        return true;
    }
}
