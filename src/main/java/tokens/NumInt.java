package tokens;

public final class NumInt extends Token {

    private int value;

    public NumInt(String value, int line, int from, int to) {
        super(value, line, from, to);
        this.value = Integer.parseInt(value);
    }

    @Override
    public String toString() {
        return String.format("NumInt(%d, line=%d, from=%d, to=%d);",
                value, getLine(), getFrom(), getTo());
    }
}
