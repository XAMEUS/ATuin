package org.core.parser;

import java.util.ArrayList;

import org.core.env.Envionment;
import org.core.env.Number;
import org.core.syntax.Expression;
import org.core.syntax.Instruction;
import org.core.syntax.expressions.And;
import org.core.syntax.expressions.Difference;
import org.core.syntax.expressions.Division;
import org.core.syntax.expressions.Equal;
import org.core.syntax.expressions.Inf;
import org.core.syntax.expressions.Int;
import org.core.syntax.expressions.Not;
import org.core.syntax.expressions.Or;
import org.core.syntax.expressions.Product;
import org.core.syntax.expressions.Sum;
import org.core.syntax.expressions.Variable;
import org.core.syntax.expressions.Xor;
import org.core.syntax.instructions.Assign;
import org.core.syntax.instructions.Call;
import org.core.syntax.instructions.Decl;
import org.core.syntax.instructions.Down;
import org.core.syntax.instructions.For;
import org.core.syntax.instructions.Forward;
import org.core.syntax.instructions.Function;
import org.core.syntax.instructions.If;
import org.core.syntax.instructions.LinkedInst;
import org.core.syntax.instructions.Pass;
import org.core.syntax.instructions.Print;
import org.core.syntax.instructions.Program;
import org.core.syntax.instructions.Turn;
import org.core.syntax.instructions.Up;
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
		Envionment.setEnvionment("main");
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
		if (reader.check(Sym.PASS)) {
			reader.eat(Sym.PASS);
			reader.eat(Sym.ENDL);
			return new Pass();
		}
		if (reader.check(Sym.PRINT)) {
    		reader.eat(Sym.PRINT);
    		Instruction instr =  new Print(expression());
    		reader.eat(Sym.ENDL);
    		return instr;
    	}
		if (reader.check(Sym.IF)) {
    		reader.eat(Sym.IF);
			Expression exp = expression();
			reader.eat(Sym.LBRA);
			Instruction instr = inst();
			reader.eat(Sym.RBRA);
    		If c = new If(exp, instr);
    		while (reader.check(Sym.ELIF)) {
    			reader.eat(Sym.ELIF);
    			exp = expression();
    			reader.eat(Sym.LBRA);
    			instr = inst();
    			reader.eat(Sym.RBRA);
    			c.add(exp, instr);
			}
    		if (reader.check(Sym.ELSE)) {
    			reader.eat(Sym.ELSE);
    			reader.eat(Sym.LBRA);
    			instr = inst();
    			reader.eat(Sym.RBRA);
    			c.add(null, instr);
    		}
    		return c;
    	}
		if (reader.check(Sym.FOR)) {
			reader.eat(Sym.FOR);
			Expression exp = expression();
			reader.eat(Sym.LBRA);
			Instruction instr = inst();
			reader.eat(Sym.RBRA);
			return new For(exp, instr);
		}
		if (reader.check(Sym.DEF)) {
			reader.eat(Sym.DEF);
			String fname = reader.getStringValue();
			String last = Envionment.getEnvionment();
			Envionment.setEnvionment(fname);
			reader.eat(Sym.VARIABLE);
			Function f = new Function(fname);
			reader.eat(Sym.LPAR);
			if (reader.check(Sym.VARIABLE)) {
				ArrayList<String> args = new ArrayList<String>();
				do {
					String arg = reader.getStringValue();
					reader.eat(Sym.VARIABLE);
					args.add(arg);
					if (reader.check(Sym.COMMA))
						reader.eat(Sym.COMMA);
				} while (reader.check(Sym.VARIABLE));
				String[] aargs = new String[args.size()];
				for (int i = 0; i < aargs.length; i++)
					aargs[i] = args.get(i);
				f.setArgs(aargs);
			}
			reader.eat(Sym.RPAR);
			reader.eat(Sym.LBRA);
			Instruction instr = inst();
			f.setInstr(instr);
			if (reader.check(Sym.RETURN)) {
				reader.eat(Sym.RETURN);
				Expression exp = expression();
				f.setReturn(exp);
				reader.eat(Sym.ENDL);
			}
			reader.eat(Sym.RBRA);
			Envionment.setEnvionment(last);
			f.exec();
		}
		if (reader.check(Sym.CALL)) {
			reader.eat(Sym.CALL);
			String fname = reader.getStringValue();
			reader.eat(Sym.VARIABLE);
			reader.eat(Sym.LPAR);
			Function f = Envionment.getFunction(fname);
			System.out.println(f);
			Expression[] args = new Expression[f.getArgs().length];
			for (int i = 0; i < args.length-1; i++) {
				args[i] = expression();
				reader.eat(Sym.COMMA);
			}
			if (args.length != 0)
				args[args.length-1] = expression();
			reader.eat(Sym.RPAR);
			Call call = new Call(fname);
			call.setArgs(args);
			reader.eat(Sym.ENDL);
			return call;
		}
		if (reader.check(Sym.VARIABLE)) {
			String vname = reader.getStringValue();
    		reader.eat(Sym.VARIABLE);
    		reader.eat(Sym.COLON);
    		reader.eat(Sym.EQ);
    		Instruction instr = new Assign(vname, expression());
    		reader.eat(Sym.ENDL);
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
			Instruction instr = new Forward(exp);
			reader.eat(Sym.ENDL);
			return instr;
		}
		if (reader.check(Sym.TURN)) {
			reader.eat(Sym.TURN);
			Expression exp = expression();
			Instruction instr = new Turn(exp);
			reader.eat(Sym.ENDL);
			return instr;
		}
		if (reader.check(Sym.UP)) {
			reader.eat(Sym.UP);
			Instruction instr = new Up();
			reader.eat(Sym.ENDL);
			return instr;
		}
		if (reader.check(Sym.DOWN)) {
			reader.eat(Sym.DOWN);
			Instruction instr = new Down();
			reader.eat(Sym.ENDL);
			return instr;
		}
		if (reader.check(Sym.RBRA))
			throw new Exception(reader.getPosition() + "\nEmpty core instructions, use 'pass'");
		throw new Exception("Cannot reduce inst");
	}
	
	private LinkedInst procedure() throws Exception {
		if (reader.check(Sym.VARIABLE) || reader.check(Sym.START) ||
				reader.check(Sym.FORWARD) || reader.check(Sym.TURN) || 
				reader.check(Sym.PRINT) || reader.check(Sym.IF) ||
				reader.check(Sym.ELIF) || reader.check(Sym.ELSE) ||
				reader.check(Sym.FOR) || reader.check(Sym.PASS) ||
				reader.check(Sym.DEF) || reader.check(Sym.CALL) ||
				reader.check(Sym.UP) || reader.check(Sym.DOWN))
			return new LinkedInst(inst(), procedure());
		return null;
	}
	
	private Expression expression() throws Exception {
		if (reader.check(Sym.INT)) {
			Expression exp = new Int(new Number(reader.getIntValue()));
			reader.eat(Sym.INT);
			return expFollow(exp);
		}
		else if (reader.check(Sym.VARIABLE)) {
			String s = reader.getStringValue();
			reader.eat(Sym.VARIABLE);
			Expression exp = null;
			if (reader.check(Sym.LPAR)) {
				reader.eat(Sym.LPAR);
				Call call = new Call(s);
				Function f = Envionment.getFunction(s);
				System.out.println(f);
				Expression[] args = new Expression[f.getArgs().length];
				for (int i = 0; i < args.length-1; i++) {
					args[i] = expression();
					reader.eat(Sym.COMMA);
				}
				if (args.length != 0)
					args[args.length-1] = expression();
				call.setArgs(args);
				exp = call;
				reader.eat(Sym.RPAR);
			}
			else {
				exp = new Variable(s);
			}
			return expFollow(exp);
		}
		else if (reader.check(Sym.NOT)) {
			reader.eat(Sym.NOT);
			Expression exp = expression();
			return new Not(exp);
		}
		else {
			reader.eat(Sym.LPAR);
			Expression exp = expression();
			reader.eat(Sym.RPAR);
			Expression right = expFollow(exp);
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
		if (reader.check(Sym.AND)) {
			reader.eat(Sym.AND);
			Expression right = expression();
			return new And(left, right);
		}
		if (reader.check(Sym.OR)) {
			reader.eat(Sym.OR);
			Expression right = expression();
			return new Or(left, right);
		}
		if (reader.check(Sym.XOR)) {
			reader.eat(Sym.XOR);
			Expression right = expression();
			return new Xor(left, right);
		}
		if (reader.check(Sym.EQ)) {
			reader.eat(Sym.EQ);
			Expression right = expression();
			return new Equal(left, right);
		}
		if (reader.check(Sym.INF)) {
			reader.eat(Sym.INF);
			boolean strict = true;
			if (reader.check(Sym.EQ)) {
				reader.eat(Sym.EQ);
				strict = false;
			}
			Expression right = expression();
			return new Inf(left, right, strict);
		}
		if (reader.check(Sym.SUP)) {
			reader.eat(Sym.SUP);
			boolean strict = true;
			if (reader.check(Sym.EQ)) {
				reader.eat(Sym.EQ);
				strict = false;
			}
			Expression right = expression();
			return new Inf(left, right, strict);
		}
		return left;
	}
	
}
