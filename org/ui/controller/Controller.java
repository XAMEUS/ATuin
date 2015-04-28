package org.ui.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javafx.stage.Stage;

import org.core.lexer.Lexer;
import org.core.parser.LookAhead1;
import org.core.parser.Parser;
import org.core.syntax.Instruction;
import org.ui.console.GConsoleFX;
import org.ui.dialogs.Dialogs;
import org.ui.editor.GEditorFX;

public class Controller {
	
	public static GConsoleFX out;
	public static Stage primaryStage;
	
	public static void build(GEditorFX gefx) {

		try {
			PrintWriter writer = new PrintWriter("src.txt", "UTF-8");
			writer.print(gefx.getText());
			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		File input = new File("src.txt");
		FileReader reader = null;
		try {
			reader = new FileReader(input);
		} catch (FileNotFoundException e) {
			out.println("404: File not found!\n");
			e.printStackTrace();
			System.exit(1);
		}
		
		Lexer lexer = new Lexer(reader);
		LookAhead1 look = null;
		try {
			look = new LookAhead1(lexer);
		} catch (Exception e) {
			out.println("Cannot create the lookAhead1. Incorrect input.");
			e.printStackTrace();
		}
		
		Parser parser = new Parser(look);
		try {
			Instruction inst = parser.build();
			out.println("The expression is correct.");
			//TODO inst.exec();
		} catch (Exception e) {
        	out.println("The expression is not correct.");
        	out.println(e.getMessage());
			e.printStackTrace();
		}
		
		System.out.println("Done...");
		
	}
	
	public static void run(GEditorFX gefx) {
		
		try {
			PrintWriter writer = new PrintWriter("src.txt", "UTF-8");
			writer.print(gefx.getText());
			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
    		Dialogs.showMessage(primaryStage, "File creation error", e.getMessage());
			e.printStackTrace();
		}

		File input = new File("src.txt");
		FileReader reader = null;
		try {
			reader = new FileReader(input);
		} catch (FileNotFoundException e) {
			out.println("404: File not found!\n");
    		Dialogs.showMessage(primaryStage, "Cannot find the file!", e.getMessage());
			e.printStackTrace();
			//System.exit(1);
		}
		
		Lexer lexer = new Lexer(reader);
		LookAhead1 look = null;
		try {
			look = new LookAhead1(lexer);
		} catch (Exception e) {
			out.println("Cannot create the lookAhead1. Incorrect input.");
    		Dialogs.showMessage(primaryStage, "Cannot create the lookAhead1. Incorrect input.", e.getMessage());
			e.printStackTrace();
		}
		
		Parser parser = new Parser(look);
		try {
			Instruction inst = parser.build();
			out.println("The expression is correct.");
			//TODO inst.exec();
		} catch (Exception e) {
        	out.println("The expression is not correct.");
        	out.println(e.getMessage());
    		Dialogs.showMessage(primaryStage, "The expression is not correct.", e.getMessage());
			e.printStackTrace();
		}
		
		System.out.println("Done...");
	}
	
}
