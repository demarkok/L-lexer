package tokens;

public final class Bool extends Token {

    private boolean value;

    public Bool(String value, int line, int from, int to) {
        super(value, line, from, to);
        this.value = Boolean.parseBoolean(value);
    }

    @Override
    public String toString() {
        return String.format("Bool(%b, line=%d, from=%d, to=%d);",
                value, getLine(), getFrom(), getTo());
    }
}
