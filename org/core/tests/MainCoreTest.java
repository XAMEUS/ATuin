package org.core.tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.core.lexer.Lexer;
import org.core.parser.LookAhead1;
import org.core.parser.Parser;
import org.core.syntax.Instruction;

public class MainCoreTest {
	
	public static void main(String[] args) {
		
		if (args.length < 1) {
			System.out.println("Missing file argument\n use \"java MainCoreTest <filename>\"");
			System.exit(1);
		}
		
		File input = new File(args[0]);
		FileReader reader = null;
		try {
			reader = new FileReader(input);
		} catch (FileNotFoundException e) {
			System.err.println("404: File not found!\n");
			e.printStackTrace();
			System.exit(1);
		}
		
		Lexer lexer = new Lexer(reader);
		LookAhead1 look = null;
		try {
			look = new LookAhead1(lexer);
		} catch (Exception e) {
			System.err.println("Cannot create the lookAhead1. Maybe an empty input.");
			e.printStackTrace();
		}
		
		Parser parser = new Parser(look);
		try {
			Instruction inst = parser.build();
			System.out.println("The expression is correct.");
			//TODO inst.exec();
		} catch (Exception e) {
        	System.err.println("The expression is not correct.");
			e.printStackTrace();
		}
		System.out.println("Done");
	}
	
}
