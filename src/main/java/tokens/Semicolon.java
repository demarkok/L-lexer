package tokens;

public final class Semicolon extends Token {

    public Semicolon(int line, int from, int to) {
        super(";", line, from, to);
    }

    @Override
    public String toString() {
        return String.format("Semicolon(line=%d, from=%d, to=%d);",
                getLine(), getFrom(), getTo());
    }
}
