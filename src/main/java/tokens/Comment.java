package tokens;

public final class Comment extends Token {

    public Comment(String value, int line, int from, int to) {
        super(value, line, from, to);
    }

    @Override
    public String toString() {
        return String.format("Comment(\"%s\", line=%d, from=%d, to=%d);",
                getValue(), getLine(), getFrom(), getTo());
    }
}