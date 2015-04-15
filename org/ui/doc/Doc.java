package org.ui.doc;

public class Doc {
	
	public static String getRessource(String res) {
		return Doc.class.getResource(res).toString();
	}

}
