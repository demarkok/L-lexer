import tokens.Token;

import java.io.IOException;
import java.util.Iterator;

public class TokenIterator implements Iterator<Token> {

    private LLexer lexer = new LLexer(null);
    private Token nextToken;

    public TokenIterator(String s) {
        lexer.reset(s, 0, s.length(), 0);
        updateToken();
    }

    @Override
    public boolean hasNext() {
        return nextToken != null;
    }

    @Override
    public Token next() {
        Token oldToken = nextToken;
        updateToken();
        return oldToken;
    }

    private void updateToken() {
        try {
            nextToken = lexer.yylex();
        } catch (IOException e) {
            throw new ParsingErrorException();
        }
    }
}
