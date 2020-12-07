package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import fileoper.OpenFile;
import fileoper.SaveFile;
import mygui.DrawShape;
import shape.Shape;

public class MenuListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("New")) {
			try {
				Tools.New();
			} catch (IOException ioException) {
				ioException.printStackTrace();
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		} else if (e.getActionCommand().equals("Open")) {
			try {
				Tools.Open();
			} catch (Exception ioException) {
				ioException.printStackTrace();
			}
		} else if (e.getActionCommand().equals("Revoke")) {
			Tools.Revoke();
		} else if (e.getActionCommand().equals("Help")) {
			Tools.Help();
		} else if (e.getActionCommand().equals("Save")) {
			try {
				Tools.Save();
			} catch (Exception ioException) {
				ioException.printStackTrace();
			}
		} else if (e.getActionCommand().equals("About")) {
			Tools.About();
		} else if (e.getActionCommand().equals("Clear")) {
			Tools.Clear();
		}else if (e.getActionCommand().equals("Zheng")) {
			Tools.Zheng();
		}
	}

}
