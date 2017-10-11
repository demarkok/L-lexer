package tokens;

public abstract class Token {

    private String value;

    private int line;

    private int from;

    private int to;


    public Token(String value, int line, int from, int to) {
        this.value = value;
        this.line = line;
        this.from = from;
        this.to = to;
    }

    public String getValue() { return value; }

    public int getLine() { return line; }

    public int getFrom() { return from; }

    public int getTo() { return to; }


    abstract public String toString();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Token token = (Token) o;

        if (line != token.line) return false;
        if (from != token.from) return false;
        if (to != token.to) return false;
        return value != null ? value.equals(token.value) : token.value == null;
    }

    @Override
    public int hashCode() {
        int result = value != null ? value.hashCode() : 0;
        result = 31 * result + line;
        result = 31 * result + from;
        result = 31 * result + to;
        return result;
    }
}

