import tokens.Token;

import java.io.IOException;
import java.util.Iterator;

public class TokenIterator implements Iterator<Token> {

    private LLexer lexer = new LLexer(null);
    private Token nextToken;

    public TokenIterator(String s) {
        lexer.reset(s, 0, s.length(), 0);
    }

    @Override
    public boolean hasNext() {
        updateToken();
        return nextToken != null;
    }

    @Override
    public Token next() {
        updateToken();
        Token result = nextToken;
        nextToken = null;
        return result;
    }

    private void updateToken() {
        if (nextToken == null) {
            try {
                nextToken = lexer.yylex();
            } catch (IOException e) {
                throw new ParsingErrorException(e.getMessage());
            }
        }
    }
}
