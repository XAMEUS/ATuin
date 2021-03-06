%%
%public
%class Lexer
%unicode
%type Token
%line
%column

%{
	private Token token(Sym type) {
		return new Token(type);
	}
	private IntToken token(Sym type, int n) {
		return new IntToken(type, n);
	}
	private VarToken token(Sym type, String name) {
		return new VarToken(type, name);
	}
	public int[] getPosition() {
		return new int[]{yyline, yycolumn}; 
	}
%}

%yylexthrow{
	LexerException
%yylexthrow}

blank = "\n" | "\r" | " " | "\t"
int   = [1-9][0-9]* | 0
variable = [a-z][a-zA-Z]*[0-9]*

%%

<YYINITIAL> {

	{blank}	{}

	"(" {return token(Sym.LPAR);}
	")" {return token(Sym.RPAR);}
	
	"+" {return token(Sym.PLUS);}
	"-" {return token(Sym.MINUS);}
	"*" {return token(Sym.TIMES);}
	"/" {return token(Sym.DIV);}
	"%" {return token(Sym.MODULO);}
	"int" {return token(Sym.TOINT);}
	
	"=" {return token(Sym.EQ);}
	"<" {return token(Sym.INF);}
	">" {return token(Sym.SUP);}
	"and" {return token(Sym.AND);}
	"or" {return token(Sym.OR);}
	"xor" {return token(Sym.XOR);}
	
	"not" {return token(Sym.NOT);}
	
	"{" {return token(Sym.LBRA);}
	"}" {return token(Sym.RBRA);}
	
	"if" {return token(Sym.IF);}
	"elif" {return token(Sym.ELIF);}
	"else" {return token(Sym.ELSE);}
	"for" {return token(Sym.FOR);}
	"while" {return token(Sym.WHILE);}
	"pass" {return token(Sym.PASS);}
	"def" {return token(Sym.DEF);}
	"call" {return token(Sym.CALL);}
	"return" {return token(Sym.RETURN);}
	
	"var" {return token(Sym.VAR);}
	"forward" {return token(Sym.FORWARD);}
	"turn" {return token(Sym.TURN);}
	"up" {return token(Sym.UP);}
	"color" {return token(Sym.COLOR);}
	"down" {return token(Sym.DOWN);}
	"start" {return token(Sym.START);}
	"end" {return token(Sym.END);}
	
	"," {return token(Sym.COMMA);}
	":" {return token(Sym.COLON);}
	";" {return token(Sym.ENDL);}
	
	"print" {return token(Sym.PRINT);}
	
	{variable} {return token(Sym.VARIABLE, yytext());}
	{int}    {return token(Sym.INT, Integer.parseInt(yytext()));}
	
	<<EOF>>	{return token(Sym.EOF);}
	
	[^]		{throw new LexerException(yyline, yycolumn);}
}



