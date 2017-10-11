package tokens;

public final class OpeningPar extends Token {

    public OpeningPar(int line, int from, int to) {
        super("(", line, from, to);
    }

    @Override
    public String toString() {
        return String.format("OpeningPar(line=%d, from=%d, to=%d);",
                getLine(), getFrom(), getTo());
    }
}
