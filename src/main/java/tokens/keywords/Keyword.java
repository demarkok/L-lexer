package tokens.keywords;

import tokens.Token;

public final class Keyword extends Token {

    public Keyword(KeywordType word, int line, int from, int to) {
        super(word.name(), line, from, to);
    }

    @Override
    public String toString() {
        return String.format("KW_%s(line=%d, from=%d, to=%d);",
                getValue(), getLine(), getFrom(), getTo());
    }
}
