package tokens.operators;

import tokens.Token;

public final class Operator extends Token {

    public Operator(OperatorType operator, int line, int from, int to) {
        super(operator.name(), line, from, to);
    }

    @Override
    public String toString() {
        return String.format("Op(%s, line=%d, from=%d, to=%d);",
                getValue(), getLine(), getFrom(), getTo());
    }
}
