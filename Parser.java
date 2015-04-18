import org.core.parser.LookAhead1;


public class Parser {
	
	/*
	 * s -> decl inst $
	 * decl -> Var id ; Decl | e
	 * inst -> id = exp |
	 * 		   Avance exp |
	 * 		   Tourne exp |
	 * 		   Debut blocInst Fin |
	 * 		   BasPinceau | HauPinceau
	 * blocInst -> inst ; blocInst | e
	 * exp -> int expSuite | id expSuite | (exp) expSuite
	 * expSuite -> + exp | - exp | * exp | / exp | e
	 * 
	 * id -> [a-z][a-zA-Z0-9]*
	 * int -> [1-9][0-9]* | 0
	 */
	
	protected LookAhead1 reader;
	
	public Parser(LookAhead1 r) {
		this.reader = r;
	}
	
}
