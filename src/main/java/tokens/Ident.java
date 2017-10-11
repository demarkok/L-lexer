package tokens;

public final class Ident extends Token {

    public Ident(String value, int line, int from, int to) {
        super(value, line, from, to);
    }

    @Override
    public String toString() {
        return String.format("Ident(\"%s\", line=%d, from=%d, to=%d);",
                getValue(), getLine(), getFrom(), getTo());
    }
}
