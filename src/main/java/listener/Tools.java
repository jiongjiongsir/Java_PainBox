package listener;

import javax.swing.JOptionPane;

import fileoper.OpenFile;
import fileoper.SaveFile;
import mygui.DrawShape;
import shape.Shape;

import java.awt.*;
import java.io.IOException;

public class Tools {
	public static final int EXIT = 1;
	public static final int OPEN = 2;
	public static final int NEW = 3;

	public static int type=0;
	private static void Jopen() throws Exception {
		OpenFile openFile = new OpenFile();
		DrawShape.pnlDisplayArea.repaint();
	}

	private static void Jnew() {
		DrawShape.shapes.clear();
		DrawShape.pnlDisplayArea.repaint();
	}

	public static int windoexit() throws Exception {
		if (SaveFile.hassave == false) {
			Object[] options = { "Save", "Not" };
			int num;
			num = JOptionPane.showOptionDialog(null, "Save Change Or Not?", "Warning", JOptionPane.DEFAULT_OPTION,
					JOptionPane.WARNING_MESSAGE, null, options, options[0]);
			if (num == 0) {
				SaveFile savefile = new SaveFile();
				System.exit(0);
			} else if (num == 1) {
				System.exit(0);
			}
		} else {
			System.exit(0);

		}

		return 0;
	}

	public static void Revoke() {
		if (!DrawShape.shapes.isEmpty()) {
			DrawShape.shapes.removeElementAt(DrawShape.shapes.size() - 1);
			DrawShape.pnlDisplayArea.repaint();
		} else {
			JOptionPane.showMessageDialog(null, "Null Shapes");
		}
	}

	public static void Save() throws Exception {
		SaveFile savefile = new SaveFile();
	}

	public static void Open() throws Exception {
		judgesave(OPEN);
	}

	public static void New() throws Exception {
		judgesave(NEW);

	}

	public static void Help() {
		JOptionPane.showMessageDialog(null, "The Same As WINDOWS_DRAW\n Other With White Means Eraser");
	}

	public static void About() {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, "Created by Zhangxuri\n2016/5/28\nVersion:1.0", "About", 1);

	}

	public static void Zheng() {
		
		if(type==0)
			{
			type=1;
			}
		else {
			type=0;
		}
		
	}
	public static void judgesave(int judge) throws Exception {
		if (SaveFile.hassave == false) {
			Object[] options = { "Save", "Not" };
			int num;
			num = JOptionPane.showOptionDialog(null, "Save Change Or Not?", "Warning", JOptionPane.DEFAULT_OPTION,
					JOptionPane.WARNING_MESSAGE, null, options, options[0]);
			if (num == 0) {
				SaveFile savefile = new SaveFile();
				if (judge == EXIT) {
					System.exit(0);
				} else if (judge == OPEN) {
					Jopen();
				} else {
					Jnew();
				}
			} else if (num == 1) {
				if (judge == EXIT) {
					System.exit(0);
				} else if (judge == OPEN) {
					Jopen();
				} else {
					Jnew();
				}
			}
		} else {
			if (judge == EXIT) {
				System.exit(0);
			} else if (judge == OPEN) {
				Jopen();
			} else {
				Jnew();
			}

		}

	}

	public static void Clear() {
		DrawShape.shapes.clear();
		DrawShape.pnlDisplayArea.repaint();
	}
	public static void noSelect()
	{
		System.out.println(DrawShape.shapes.size());
		for(Shape shape:DrawShape.shapes)
		{
			if(shape.isSel()==true)
			{
				System.out.println(shape);
				shape.setSel(false);

			}
		}
	}
	public static void Rotate() {
		for(Shape shape:DrawShape.shapes)
		{
			if(shape.isSel()==true)
			{
//					System.out.println(shape);

				shape.rotate(90);
				System.out.println(shape.getPoints());
				DrawShape.pnlDisplayArea.repaint();

			}
		}
	}

}
