package org.ui;

import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.SplitPane;

class TabPaneWrapper {
	
	private SplitPane split;

	public TabPaneWrapper(Orientation o, double splitLocation) {
		split = new SplitPane();
		split.setOrientation(o);
		split.setDividerPosition(0, splitLocation);
	}

	public void addNodes(final Node node1, final Node node2) {
		split.getItems().addAll(node1, node2);
	}

	public Parent getNode() {
		return split;
	}
	
	public SplitPane getSplitPane() {
		return this.split;
	}
}
