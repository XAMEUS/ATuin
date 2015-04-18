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
%}

%yylexthrow{
	LexerException
%yylexthrow}

blank = "\n" | "\r" | " " | "\t"
int   = [1-9][0-9]*
variable = [a-z][a-zA-Z]*

%%

<YYINITIAL> {

	{blank}	{}

	"(" {return token(Sym.LPAR);}
	")" {return token(Sym.RPAR);}
	
	"+" {return token(Sym.PLUS);}
	"-" {return token(Sym.MINUS);}
	"*" {return token(Sym.TIMES);}
	"/" {return token(Sym.DIV);}
	
	"=" {return token(Sym.EQ);}
	
	"{" {return token(Sym.LBRA);}
	"}" {return token(Sym.RBRA);}
	
	"VAR" {return token(Sym.VAR);}
	
	";" {return token(Sym.END);}
	
	"PRINT" {return token(Sym.PRINT);}
	
	{variable} {return token(Sym.VARIABLE, yytext());}
	{int}    {return token(Sym.INT, Integer.parseInt(yytext()));}
	
	<<EOF>>	{return token(Sym.EOF);}
	
	[^]		{throw new LexerException(yyline, yycolumn);}
}



