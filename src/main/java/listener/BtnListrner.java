package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;

import fileoper.OpenFile;
import fileoper.SaveFile;
import mygui.DrawShape;

public class BtnListrner implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if ("Exit" == e.getActionCommand()) {
			try {
				Tools.windoexit();
			} catch (IOException ioException) {
				ioException.printStackTrace();
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		} else if (e.getActionCommand().equals("Save")) {
			try {
				Tools.Save();
			} catch (Exception ioException) {
				ioException.printStackTrace();
			}
		} else if (e.getActionCommand().equals("Open")) {
			try {
				Tools.Open();
			} catch (Exception ioException) {
				ioException.printStackTrace();
			}
		} else if (e.getActionCommand().equals("New")) {
			try {
				Tools.New();
			} catch (Exception ioException) {
				ioException.printStackTrace();
			}
		}
		else if(e.getActionCommand().equals("Rotate"))
		{
			Tools.Rotate();
		}
	}

}
