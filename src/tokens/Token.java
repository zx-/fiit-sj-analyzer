package tokens;

/**
 * Created by Gamer on 11/25/2016.
 */
public abstract class Token {



    private String name;

    Token(String name) { this.name = name; }
    abstract public boolean isNonTerminal();
    public boolean isTerminal() { return !this.isNonTerminal(); }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

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

    @Override
    public String toString(){
        String s = isTerminal()?"Terminal: ":"NonTerminal: ";
        return s+getName();
    }

    @Override
    public int hashCode(){
        return toString().hashCode();
    }

}
