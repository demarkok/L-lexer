import tokens.*;
import tokens.keywords.*;
import tokens.operators.*;

import static tokens.operators.OperatorType.*;
import static tokens.keywords.KeywordType.*;



%%
/*-*
 * LEXICAL FUNCTIONS:
 */

%class LLexer
%type Token
%line
%column

%{

    private int yyline = 0;
    private int yycolumn = 0;

    private String text() {
        return yytext().toString();
    }

    private Operator newOperator(OperatorType operator) {
        return new Operator(operator, yyline, yycolumn, yycolumn + text().length());
    }

    private Keyword newKeyword(KeywordType keyword) {
        return new Keyword(keyword, yyline, yycolumn, yycolumn + text().length());
    }

%}


/*-*
 * PATTERN DEFINITIONS:
 */






digit           = [0-9]
letter          = [A-Za-z]

eol             = \r | \n | \r\n
whitespace      = {eol} | [ \n\t\f]
comment         = "//" [^\r\n]* (\r|\n|\r\n)?
ident           = ({letter}|_)({letter} | {digit}|_)*



digits          = {digit} | ({digit}({digit}|_)*{digit})
expPart         = [eE][+-]?{digits}
suffix          = [fFdD]
integer         = (0 | [1-9] {digits}? | [1-9] _+ {digits})[lL]?
floating        = {digits}\.{digits}?{expPart}?{suffix}? |
                  \.{digits}{expPart}?{suffix}? |
                  {digits}{expPart}{suffix}? |
                  {digits}{expPart}?{suffix}
%%
/**
 * LEXICAL RULES:
 */

{comment} { return new Comment(text(), yyline, yycolumn, yycolumn + text().length()); }
{whitespace} {}

{integer}   {return new NumInt(text(), yyline, yycolumn, yycolumn + text().length()); }
{floating}   {return new NumDouble(text(), yyline, yycolumn, yycolumn + text().length()); }
"true"  {return new Bool(text(), yyline, yycolumn, yycolumn + text().length()); }
"false" {return new Bool(text(), yyline, yycolumn, yycolumn + text().length()); }

"+"     { return newOperator(PLUS); }
"-"     { return newOperator(MINUS); }
"*"     { return newOperator(MUL); }
"/"     { return newOperator(DIV); }
"%"     { return newOperator(MOD); }
"=="    { return newOperator(EQ); }
"!="    { return newOperator(NEQ); }
">"     { return newOperator(G); }
"<"     { return newOperator(L); }
">="    { return newOperator(GEQ); }
"<="    { return newOperator(LEQ); }
"&&"    { return newOperator(AND); }
"||"    { return newOperator(OR); }

"("     { return new OpeningPar(yyline, yycolumn, yycolumn + text().length()); }
")"     { return new ClosingPar(yyline, yycolumn, yycolumn + text().length()); }
";"     { return new Semicolon(yyline, yycolumn, yycolumn + text().length()); }

"if"    { return newKeyword(IF); }
"then"  { return newKeyword(THEN); }
"else"  { return newKeyword(ELSE); }
"while" { return newKeyword(WHILE); }
"do"    { return newKeyword(DO); }
"read"  { return newKeyword(READ); }
"write" { return newKeyword(WRITE); }
"begin" { return newKeyword(BEGIN); }
"end"   { return newKeyword(END); }

{ident} { return new Ident(text(), yyline, yycolumn, yycolumn + text().length()); }

[^]     { throw new ParsingErrorException(); }

