package org.core.parser;

import org.core.syntax.Expression;
import org.core.syntax.Instruction;
import org.core.syntax.expressions.Difference;
import org.core.syntax.expressions.Division;
import org.core.syntax.expressions.Int;
import org.core.syntax.expressions.Product;
import org.core.syntax.expressions.Sum;
import org.core.syntax.expressions.Variable;
import org.core.syntax.instructions.Assign;
import org.core.syntax.instructions.Decl;
import org.core.syntax.instructions.LinkedInst;
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
		if (reader.check(Sym.VARIABLE)) {
			String s = reader.getStringValue();
    		reader.eat(Sym.VARIABLE);
    		reader.eat(Sym.EQ);
    		Instruction instr = new Assign(s, expression());
    		reader.eat(Sym.END);
    		return instr;
		}
		if (reader.check(Sym.START)) {
			reader.eat(Sym.START);
			Instruction instr = procedure();
			reader.eat(Sym.END);
			return instr;
		}
		if (reader.check(Sym.FORWARD)) {
			reader.eat(Sym.FORWARD);
			Expression exp = expression();
			//TODO create Forward Instruction
			reader.eat(Sym.ENDL);
		}
		if (reader.check(Sym.TURN)) {
			reader.eat(Sym.TURN);
			Expression exp = expression();
			//TODO create Turn Instruction
			reader.eat(Sym.ENDL);
		}
		if (reader.check(Sym.UP)) {
			reader.eat(Sym.UP);
			//TODO create Up Instruction
			reader.eat(Sym.ENDL);
		}
		if (reader.check(Sym.DOWN)) {
			reader.eat(Sym.DOWN);
			//TODO create Up Instruction
			reader.eat(Sym.ENDL);
		}
		
		return null;
	}
	
	private LinkedInst procedure() throws Exception {
		if (reader.check(Sym.EOF) || reader.check(Sym.RBRA))
			return new LinkedInst(inst(), procedure());
		return null;
	}
	
	private Expression expression() throws Exception {
		if (reader.check(Sym.INT)) {
    		Expression exp = new Int(reader.getIntValue());
    		reader.eat(Sym.INT);
    		return exp;
    	}
    	else if (reader.check(Sym.VARIABLE)) {
    		Expression exp = new Variable(reader.getStringValue());
    		reader.eat(Sym.VARIABLE);
    		return exp;
    	}
    	else {
    		reader.eat(Sym.LPAR);
    		Expression left = expression();
    		Expression right = expFollow(left);
    		reader.eat(Sym.RPAR);
    		return right;
    	}
	}
	
	private Expression expFollow(Expression left) throws Exception {
		if (reader.check(Sym.PLUS)) {
			reader.eat(Sym.PLUS);
			Expression right = expression();
			return new Sum(left, right);
		}
		if (reader.check(Sym.MINUS)) {
			reader.eat(Sym.MINUS);
			Expression right = expression();
			return new Difference(left, right);
		}
		if (reader.check(Sym.TIMES)) {
			reader.eat(Sym.TIMES);
			Expression right = expression();
			return new Product(left, right);
		}
		if (reader.check(Sym.DIV)) {
			reader.eat(Sym.DIV);
			Expression right = expression();
			return new Division(left, right);
		}
		throw new Exception("ParserException: error at position "
				+ reader.getPosition() + "\nCannot reduce S!");
	}
	
}
