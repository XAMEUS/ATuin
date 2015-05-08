package org.ui.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javafx.stage.Stage;

import org.core.env.Envionment;
import org.core.lexer.Lexer;
import org.core.parser.LookAhead1;
import org.core.parser.Parser;
import org.core.syntax.Instruction;
import org.core.syntax.instructions.Program;
import org.ui.canvas.GDrawingFX;
import org.ui.console.GConsoleFX;
import org.ui.dialogs.Dialogs;
import org.ui.editor.GEditorFX;

public class Controller {
	
	public static GConsoleFX out;
	public static Stage primaryStage;
	public static GDrawingFX drawing;
	public static GEditorFX editor;
	private static Instruction last_instruction;
	
	public static Program build(GEditorFX gefx) {

		try {
			PrintWriter writer = new PrintWriter("src.txt", "UTF-8");
			writer.print(gefx.getText());
			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
    		Dialogs.showErrorMessage(primaryStage, "File creation error", e.getMessage());
			e.printStackTrace();
		}

		File input = new File("src.txt");
		FileReader reader = null;
		try {
			reader = new FileReader(input);
		} catch (FileNotFoundException e) {
			out.println("404: File not found!\n");
    		Dialogs.showErrorMessage(primaryStage, "Cannot find the file!", e.getMessage());
			e.printStackTrace();
			//System.exit(1);
		}
		
		Lexer lexer = new Lexer(reader);
		LookAhead1 look = null;
		try {
			look = new LookAhead1(lexer);
		} catch (Exception e) {
			out.println("Cannot create the lookAhead1. Incorrect input.");
    		Dialogs.showErrorMessage(primaryStage, "Cannot create the lookAhead1. Incorrect input.", e.getMessage());
			e.printStackTrace();
		}
		
		Parser parser = new Parser(look);
		Program prog = null;
		try {
			prog = parser.build();
			out.println("The expression is correct.");
		} catch (Exception e) {
        	out.println("The expression is not correct.");
        	out.println(e.getMessage());
    		Dialogs.showErrorMessage(primaryStage, "The expression is not correct.", e.getMessage());
			e.printStackTrace();
		}
		try {
			reader.close();
		} catch (IOException e) {
			Dialogs.showErrorMessage(primaryStage, "aze", e.getMessage());
			e.printStackTrace();
		}
		
		System.out.println("Tree created...");
		return prog;
		
	}
	
	public static void save_build(Program prog, String filename) {

		FileOutputStream f = null;
		try {
			f = new FileOutputStream(filename);
		} catch (FileNotFoundException e) {
			out.println(e.getMessage());
    		Dialogs.showErrorMessage(primaryStage, "File not found.", e.getMessage());
    		e.printStackTrace();
		}
		try {
			ObjectOutputStream o = new ObjectOutputStream(f);
			o.writeObject(prog);
		} catch (IOException e) {
			out.println(e.getMessage());
    		Dialogs.showErrorMessage(primaryStage, "IOException.", e.getMessage());
    		e.printStackTrace();
		}
		
	}
	
	public static Program load_build(String filename) {
		
		//TODO
		return null;
		
	}
	
	public static void run(GEditorFX gefx) {

		Envionment.cleanAll();
		TurtleBrain.newTurtle();
		Instruction inst = build(gefx);
		
		if (inst != null) {
			try {
				drawing.clear();
				inst.exec();
				System.out.println("Executed...");
				out.println("Executed...");
			} catch (Exception e) {
				out.println(e.getMessage());
	    		Dialogs.showErrorMessage(primaryStage, "Execution error.", e.getMessage());
				e.printStackTrace();
			}
		}
		
		Controller.last_instruction = inst;
		
	}
	
	public static void recall() {
		
		if (Controller.last_instruction != null) {
			try {
				TurtleBrain.newTurtle();
				Controller.last_instruction.exec();
			} catch (Exception e) {
				out.println(e.getMessage());
	    		Dialogs.showErrorMessage(primaryStage, "Execution error.", e.getMessage());
				e.printStackTrace();
			}
		}
		
	}

	public static void open(String filepath) {
		String[] t = filepath.split("/");
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(new File(filepath)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
    		Dialogs.showErrorMessage(primaryStage, "Error.", e.getMessage());
		}
	    try {
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();

	        while (line != null) {
	            sb.append(line);
	            sb.append(System.lineSeparator());
	            line = br.readLine();
	        }
	         Controller.editor.getTextEditor().newTab(t[t.length - 1].split("\\.")[0], sb.toString());
	    } catch (IOException e) {
			e.printStackTrace();
    		Dialogs.showErrorMessage(primaryStage, "Error.", e.getMessage());
		} finally {
	        try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
	    		Dialogs.showErrorMessage(primaryStage, "Error.", e.getMessage());
			}
	    }
		
	}
	
}
