package slogo_front;

import javafx.scene.control.TextField;

public class CommandLine {
	TextField commandLine;

	public CommandLine(double height) {
		commandLine = new TextField();
		commandLine.setPrefHeight(height);
	}

	TextField getCommandLine() {
		return commandLine;
	}
}
