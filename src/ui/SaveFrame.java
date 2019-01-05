package ui;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JPanel;

import domain.controller.GameController;

@SuppressWarnings("serial")
public class SaveFrame extends JPanel {
	JFileChooser fc;

	public SaveFrame() {
		// TODO Auto-generated constructor stub
        fc = new JFileChooser();
        int returnVal = fc.showSaveDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            //This is where a real application would open the file.
            try {
				GameController.getInstance().saveGame(file);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } else {
       }
	}
	

}
