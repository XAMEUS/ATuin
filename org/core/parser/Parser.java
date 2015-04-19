package org.core.parser;

import org.core.syntax.Instruction;
import org.core.syntax.instructions.Decl;
import org.core.syntax.instructions.Program;
import org.core.tokens.Sym;

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
	
	public Program build() throws Exception {
		Program prog = new Program(decl(), inst());
		reader.eat(Sym.EOF);
		return prog;
	}

	private Decl decl() throws Exception {
		if (reader.check(Sym.VAR)) {
			reader.eat(Sym.VAR);
			String var = reader.getStringValue();
			reader.eat(Sym.VARIABLE);
			reader.eat(Sym.ENDL);
			return new Decl(var, decl());
		}
		return null;
	}

	private Instruction inst() throws Exception {
		if (reader.check(Sym.EOF) || reader.check(Sym.RBRA))
			return null;
		return null;
	}
	
}
