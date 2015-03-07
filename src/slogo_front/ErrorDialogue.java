package slogo_front;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ErrorDialogue {
	public void displayMessage(String dialogue){
		JFrame frame = new JFrame();
		JOptionPane.showMessageDialog(frame,dialogue);
	}

//    public static void main (String[] args) {
//        ErrorDialogue ed = new ErrorDialogue();
//        ed.displayMessage("'This' is not a valid argument.");
//    }
    
}
