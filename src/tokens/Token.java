package tokens;

/**
 * Created by Gamer on 11/25/2016.
 */
public abstract class Token {

    public String getName() {
        return name;
    }

    String name;

    Token(String name) { this.name = name; };
    abstract public boolean isNonTerminal();
    abstract public boolean isTerminal();

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!Token.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final Token other = (Token) obj;

        if (this.isTerminal() != other.isTerminal()) {
            return false;
        }

        return this.getName().equals(other.getName());
    }

}
