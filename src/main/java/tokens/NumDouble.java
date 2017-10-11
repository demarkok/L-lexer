package tokens;

public final class NumDouble extends Token {

    private double value;

    public NumDouble(String value, int line, int from, int to) {
        super(value, line, from, to);
        this.value = Double.parseDouble(value);
    }

    @Override
    public String toString() {
        return String.format("NumDouble(%f, line=%d, from=%d, to=%d);",
                value, getLine(), getFrom(), getTo());
    }
}
