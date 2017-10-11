package tokens;

public final class ClosingPar extends Token {

    public ClosingPar(int line, int from, int to) {
        super(")", line, from, to);
    }

    @Override
    public String toString() {
        return String.format("ClosingPar(line=%d, from=%d, to=%d);",
                getLine(), getFrom(), getTo());
    }
}
