
package listener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import mygui.DrawShape;
import shape.Shape;

public class ColorHandler extends JPanel {
	JColorChooser jColorChooser;
	JButton button;
	Color tempcolor;
	public static Color color = new Color(0, 0, 0);
	JPanel jPanel;

	public ColorHandler() {
		super(new GridLayout(2, 1));
		jPanel = new JPanel();
		color = new Color(0, 0, 0);
		setBorder(new TitledBorder("Color"));
		jPanel.setBackground(color);
		setPreferredSize(new Dimension(190, 140));
		setBackground(Color.white);
		button = new JButton();
		jColorChooser = new JColorChooser();
		button.setText("Open the Color mannger");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				tempcolor = JColorChooser.showDialog(new JFrame(), "ColorChooser", color.BLACK);
				if (tempcolor != null) {
					color = tempcolor;
					DrawShape.lineWidthPanel.repaint();
					for(Shape shape:DrawShape.shapes)
					{
						if(shape.isSel()==true)
						{
//					System.out.println(shape);

							shape.setColor(color);
							DrawShape.pnlDisplayArea.repaint();
							//shape.DrawShape((Graphics2D) g);

						}
					}
				}
				jPanel.setBackground(color);
			}

		});

		add(button);
		add(jPanel);
	}

}
