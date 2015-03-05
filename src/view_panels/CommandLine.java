package view_panels;

import javafx.scene.control.TextField;

public class CommandLine {
	TextField commandLine;

	public CommandLine(double height) {
		commandLine = new TextField();
		commandLine.setPrefHeight(height);
	}

	public TextField getCommandLine() {
		return commandLine;
	}
}
