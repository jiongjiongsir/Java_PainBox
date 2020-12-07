package fileoper;

import java.io.*;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import mygui.DrawShape;
import shape.Shape;
import utils.utils;

public class SaveFile {
	FileWriter fileWriter;
	PrintWriter printWriter;
	JFileChooser jFileChooser;
	ObjectOutputStream out = null;
	FileOutputStream fout = null;
	public static boolean hassave=true;
	public SaveFile() throws Exception {
		// TODO Auto-generated constructor stub
		jFileChooser=new JFileChooser();
		FileFilter filenameFilter=new FileFilter() {
			@Override
			public boolean accept(File f) {
				if (f.isDirectory()) {
					return false;
				}
				if (f.getName().endsWith("txt")){
					return true;
				}
				else {
					return false;
				}
			}

			@Override
			public String getDescription() {
				return null;
			}
		};
		jFileChooser.setFileFilter(filenameFilter);
		jFileChooser.showSaveDialog(null);


//			fileWriter =new FileWriter(jFileChooser.getSelectedFile());
//			System.out.println();
//			printWriter = new PrintWriter(fileWriter);
//			System.out.println("filenameFilter"+filenameFilter);
//			System.out.println("jFileChooser.getSelectedFile()"+jFileChooser.getSelectedFile());
			utils.saveObject(DrawShape.shapes,jFileChooser.getSelectedFile());
//			printWriter.close();
//			fileWriter.close();
			hassave=true;

	}

}
