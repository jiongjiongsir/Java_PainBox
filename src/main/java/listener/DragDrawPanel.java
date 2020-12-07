package listener;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import fileoper.SaveFile;
import mygui.DrawShape;
import shape.Dot;
import shape.Line;
import shape.MyPoint;
import shape.Shape;

public class DragDrawPanel extends JPanel implements MouseMotionListener, MouseListener , FocusListener {

	private Point Start = null;
	private Point Stop = null;
	private boolean dottedTag = true;
	private Color color;
	public static int width = 1;
	public static int w = 0;
	public static int h = 0;
	Dot dot;

	public DragDrawPanel() {
		this.setLayout(null);
		setPreferredSize(new Dimension(600, 800));
		Border border = new LineBorder(Color.black);
		setBorder(border);
		setBackground(Color.white);
		Start = new MyPoint(0, 0);
		Stop = new MyPoint(0, 0);
		dot = new Dot();
		addMouseListener(this);
		addMouseMotionListener(this);

	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		color = ColorHandler.color;
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(color);
		/** ���εĿ��* */
		w = Math.abs(Stop.x - Start.x);
		/** ���εĸ߶�* */
		h = Math.abs(Stop.y - Start.y);
		/** ����յ����Сֵ��Ϊ���* */
		Point min = new Point(0, 0);
		min.x = Math.min(Stop.x, Start.x);
		min.y = Math.min(Stop.y, Start.y);
		/** ����ǻ������߾���* */
		System.out.println(	DrawShape.shapes);
		for (shape.Shape shape : DrawShape.shapes) {
			System.out.println("233");
			g2.setStroke(new BasicStroke());
			shape.DrawShape(g2);
		}
		System.out.println(dottedTag);
		if (dottedTag) {

			Stroke dash = new BasicStroke(width, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 0.5f,
					new float[] { 5, 5, }, 0f);
			g2.setStroke(dash);
			if (DrawShape.type == DrawShape.RECT) {
				g2.drawRect(min.x, min.y, w, h);
			} else if (DrawShape.type == DrawShape.LINE) {
				g2.drawLine(Start.x, Start.y, Stop.x, Stop.y);
			} else if (DrawShape.type == DrawShape.CIRCLE) {
				g2.drawOval(min.x, min.y, w, h);
			}
			dot.DrawShape(g2);
		}

	}

	public void mouseDragged(MouseEvent mouseEvent) {
		// TODO Auto-generated method stub
		Point x=mouseEvent.getPoint();
		int x_change= (int) (x.getX()-Stop.getX());
		int y_change= (int) (x.getY()-Stop.getY());
		Stop = mouseEvent.getPoint();

		if (DrawShape.type == DrawShape.OTHER)
		{
			dot.addpoint(Start.x, Start.y, Stop.x, Stop.y);
			Start = Stop;
		}
		if(DrawShape.action==DrawShape.REMOVE)
		{
			dottedTag=false;
			for(Shape shape:DrawShape.shapes)
			{
				if(shape.isSel()==true)
				{
//					System.out.println(shape);


//					System.out.println("Stop"+Stop.x+" "+Stop.y+"Start"+Start.x+" "+Start.y);
					shape.move(x_change,y_change);
					System.out.println(shape.getPoints());

//


				}
			}
			System.out.println("start"+Start+" "+"end"+Stop);
		}

//		不明觉厉
		else if (Tools.type == 1) {

			if (Stop.x > Start.x) {
				Stop.x = Start.x + Math.min(Math.abs(Stop.x - Start.x), Math.abs(Stop.y - Start.y));
		  	} else {
				Stop.x = Start.x - Math.min(Math.abs(Stop.x - Start.x), Math.abs(Stop.y - Start.y));

			}
			if (Stop.y < Start.y) {
				Stop.y = Start.y - Math.min(Math.abs(Stop.x - Start.x), Math.abs(Stop.y - Start.y));
			} else {
				Stop.y = Start.y + Math.min(Math.abs(Stop.x - Start.x), Math.abs(Stop.y - Start.y));
			}
		}
		repaint();

	}

	public void mousePressed(MouseEvent mouseEvent) {
		dottedTag = true;
		Start = mouseEvent.getPoint();
		Stop = Start;
		if(DrawShape.action==DrawShape.Eraser)
		{
			dot = new Dot();
			color = Color.white;
			dot.setcolorwidth(color, width);
			dot.setType(DrawShape.OTHER);
		}
		else if (DrawShape.action==-1&&DrawShape.type == DrawShape.OTHER) {
			dot = new Dot();
			color = ColorHandler.color;
			dot.setcolorwidth(color, width);
			dot.setType(DrawShape.OTHER);
		}

	}

	public void mouseReleased(MouseEvent mouseEvent) {
		// TODO Auto-generated method stub
		dottedTag = false;
		if(DrawShape.action==-1)
		{
			if (DrawShape.type == DrawShape.RECT) {
				Shape shape=new shape.Rectangle(color, width, Start.x, Start.y, Stop.x, Stop.y,DrawShape.RECT);

				DrawShape.shapes.add(shape);

			} else if (DrawShape.type == DrawShape.LINE) {
				DrawShape.shapes.add(new shape.Line(color, width, Start.x, Start.y, Stop.x, Stop.y,DrawShape.LINE));
			} else if (DrawShape.type == DrawShape.CIRCLE) {

				DrawShape.shapes.add(new shape.Circle(color, width, Start.x, Start.y, Stop.x, Stop.y,DrawShape.CIRCLE));
			} else if (DrawShape.type == DrawShape.OTHER) {

				DrawShape.shapes.add(dot);
			}else if (DrawShape.type == DrawShape.Text) {

				JTextArea jTextArea=new JTextArea(Math.abs(Stop.x-Start.x),Math.abs(Stop.y-Start.y));
				jTextArea.setBounds(Math.min(Stop.x,Start.x),Math.min(Stop.y,Start.y),Math.abs(Stop.x-Start.x),Math.abs(Stop.y-Start.y));
				jTextArea.addFocusListener(this);
				this.add(jTextArea);

			}

			System.out.println(DrawShape.shapes);
		}

		else if(DrawShape.action==DrawShape.Select)
		{

			for(Shape shape:DrawShape.shapes)
			{
				if(shape.getColor()==Color.white)
				{
					continue;
				}
				Vector<MyPoint>myPointVector=shape.getPoints();
				for(MyPoint myPoint:myPointVector)
				{

						if(myPoint.x>=Start.x&&myPoint.y>=Start.y&&myPoint.x<=Stop.x&&myPoint.y<=Stop.y)
						{
							shape.setSel(true);
							System.out.println("233"+shape);
						}

				}
			}



		}
		else if(DrawShape.action==DrawShape.Eraser)
		{
			DrawShape.shapes.add(dot);

		}

//		else if(DrawShape.action==DrawShape.REMOVE)
//		{
//
//			for(Shape shape:DrawShape.shapes)
//			{
//				if(shape.isSel()==true)
//				{
////					System.out.println(shape);
//					shape.move(Stop.x-Start.x,Stop.y-Start.y);
//					System.out.println(shape.getPoints());
//					Graphics g=this.getGraphics();
//					g.drawString("2333",100,100);
//
//					shape.DrawShape((Graphics2D) g);
//
//				}
//			}
//			System.out.println("start"+Start+" "+"end"+Stop);
//		}
		else if(DrawShape.action==DrawShape.ROTATE)
		{

//			for(Shape shape:DrawShape.shapes)
//			{
//				if(shape.isSel()==true)
//				{
////					System.out.println(shape);
//
//					shape.rotate(90);
//					System.out.println(shape.getPoints());
//					Graphics g=this.getGraphics();
//					shape.DrawShape((Graphics2D) g);
//
//				}
//
//			}
//			System.out.println("start"+Start+" "+"end"+Stop);


		}
		SaveFile.hassave = false;
		repaint();
	}

	public void mouseMoved(MouseEvent mouseEvent) {
		// TODO Auto-generated method stub

	}

	public void mouseClicked(MouseEvent mouseEvent) {
		// TODO Auto-generated method stub

	}

	public void mouseEntered(MouseEvent mouseEvent) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent mouseEvent) {
		// TODO Auto-generated method stub

	}

	public Vector<shape.Shape> getshapes() {
		return DrawShape.shapes;

	}

	@Override
	public void focusGained(FocusEvent e) {

	}

	@Override
	public void focusLost(FocusEvent e) {

		JTextArea jTextArea= (JTextArea) e.getSource();

		String text=jTextArea.getText();
		DrawShape.shapes.add(new shape.Text(color, width, Start.x, Start.y, Stop.x, Stop.y,DrawShape.Text,text));
		this.remove(jTextArea);
	}
}
