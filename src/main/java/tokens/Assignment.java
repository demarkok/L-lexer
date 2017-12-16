package tokens;

public class Assignment extends Token {

    public Assignment(int line, int from, int to) {
        super(":=", line, from, to);
    }

    @Override
    public String toString() {
        return String.format("Assignment(line=%d, from=%d, to=%d);",
                getLine(), getFrom(), getTo());
    }
}
