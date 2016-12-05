package tokens;

/**
 * Created by Gamer on 11/25/2016.
 */
public class Terminal extends Token {

    private String text = null;

    public Terminal(String name) {
        super(name);
    }

    @Override
    public boolean isNonTerminal() {
        return false;
    }

//    @Override
//    public String toString() {
//        if(text == null) return super.toString();
//        return super.toString() + " - \"" + text+ "\"";
//    }

    public void appendText(String s) { text = (text==null)? s:text+s; }
    public void appendText(Terminal t) { text += t.getText(); }

    public boolean isText() { return text != null; }

    public String getText() { return text; }
}
