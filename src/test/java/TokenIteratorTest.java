import com.google.common.collect.ImmutableList;
import org.junit.Test;
import tokens.*;
import tokens.keywords.Keyword;
import tokens.keywords.KeywordType;
import tokens.operators.Operator;
import tokens.operators.OperatorType;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class TokenIteratorTest {

    private List<Token> tokenize(TokenIterator it) {
        List<Token> list = new LinkedList<>();
        while (it.hasNext()) {
            list.add(it.next());
        }
        return list;
    }

    @Test
    public void emptyInputTest() {
        TokenIterator it = new TokenIterator("");
        assertFalse(it.hasNext());
    }

    @Test
    public void commentTest() {
        TokenIterator it = new TokenIterator("// hello world");
        assertEquals(ImmutableList.of(new Comment("// hello world", 0, 0, 14)), tokenize(it));
    }

    @Test
    public void onlyWsTest() {
        TokenIterator it = new TokenIterator("  \n  \n \t \f \r \r\n");
        assertFalse(it.hasNext());
    }

    @Test
    public void integerNumberTest() {
        TokenIterator it = new TokenIterator("239 0 3");
        assertEquals(ImmutableList.of(new NumInt("239", 0, 0, 3),
                                      new NumInt("0", 0, 4, 5),
                                      new NumInt("3", 0, 6, 7)),
                tokenize(it));
    }

    @Test
    public void floatingPointNumberTest() {
        TokenIterator it = new TokenIterator(".239 0.1 15.6 1e-7 2e4");
        //                                       0    5   9    14   19

        assertEquals(ImmutableList.of(new NumDouble(".239", 0, 0, 4),
                                      new NumDouble("0.1", 0, 5, 8),
                                      new NumDouble("15.6", 0, 9, 13),
                                      new NumDouble("1e-7", 0, 14, 18),
                                      new NumDouble("2e4", 0, 19, 22)),
                tokenize(it));
        assertFalse(it.hasNext());
    }

    @Test
    public void keywordsTest() {
        TokenIterator it = new TokenIterator("if then else while do read write begin end");
        //                                       0  3    8    13    19 22   27    33    39

        assertEquals(ImmutableList.of(new Keyword(KeywordType.IF, 0, 0, 2),
                                      new Keyword(KeywordType.THEN, 0, 3, 7),
                                      new Keyword(KeywordType.ELSE, 0, 8, 12),
                                      new Keyword(KeywordType.WHILE, 0, 13, 18),
                                      new Keyword(KeywordType.DO, 0, 19, 21),
                                      new Keyword(KeywordType.READ, 0, 22, 26),
                                      new Keyword(KeywordType.WRITE, 0, 27, 32),
                                      new Keyword(KeywordType.BEGIN, 0, 33, 38),
                                      new Keyword(KeywordType.END, 0, 39, 42)),
                tokenize(it));
    }

    @Test
    public void operatorsTest() {
        TokenIterator it = new TokenIterator("+  -  *  /  %  == != >  >= <  <= && ||");
        //                                       0  3  6  9  12 15 18 21 24 27 30 33 36

        assertEquals(ImmutableList.of(new Operator(OperatorType.PLUS, 0, 0, 1),
                                      new Operator(OperatorType.MINUS, 0, 3, 4),
                                      new Operator(OperatorType.MUL, 0, 6, 7),
                                      new Operator(OperatorType.DIV, 0, 9, 10),
                                      new Operator(OperatorType.MOD, 0, 12, 13),
                                      new Operator(OperatorType.EQ, 0, 15, 17),
                                      new Operator(OperatorType.NEQ, 0, 18, 20),
                                      new Operator(OperatorType.G, 0, 21, 22),
                                      new Operator(OperatorType.GEQ, 0, 24, 26),
                                      new Operator(OperatorType.L, 0, 27, 28),
                                      new Operator(OperatorType.LEQ, 0, 30, 32),
                                      new Operator(OperatorType.AND, 0, 33, 35),
                                      new Operator(OperatorType.OR, 0, 36, 38)),
                tokenize(it));
    }

    @Test
    public void delimitersTest() {
        TokenIterator it = new TokenIterator("(  )  ;");
        //                                       0  3  6
        assertEquals(ImmutableList.of(new OpeningPar(0, 0, 1),
                                      new ClosingPar(0, 3, 4),
                                      new Semicolon(0, 6, 7)),
                tokenize(it));
    }

    @Test
    public void booleanTest() {
        TokenIterator it = new TokenIterator("true false");
        //                                       0    5
        assertEquals(ImmutableList.of(new Bool("true", 0, 0, 4),
                                      new Bool("false", 0, 5, 10)),
                tokenize(it));
    }

    @Test
    public void commonTest() {
        TokenIterator it = new TokenIterator("read x; if y + 1 == x then write y else write x");
        //                                       0    5    10   15   20   25   30   35   40   45
        assertEquals(ImmutableList.of(new Keyword(KeywordType.READ, 0, 0, 4),
                                      new Ident("x", 0, 5, 6),
                                      new Semicolon(0, 6, 7),
                                      new Keyword(KeywordType.IF, 0, 8, 10),
                                      new Ident("y", 0, 11, 12),
                                      new Operator(OperatorType.PLUS, 0, 13, 14),
                                      new NumInt("1", 0, 15, 16),
                                      new Operator(OperatorType.EQ, 0, 17, 19),
                                      new Ident("x", 0, 20, 21),
                                      new Keyword(KeywordType.THEN, 0, 22, 26),
                                      new Keyword(KeywordType.WRITE, 0, 27, 32),
                                      new Ident("y", 0, 33, 34),
                                      new Keyword(KeywordType.ELSE, 0, 35, 39),
                                      new Keyword(KeywordType.WRITE, 0, 40, 45),
                                      new Ident("x", 0, 46, 47)),
                tokenize(it));
    }
}