package shape;

import java.awt.*;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;


import listener.ColorHandler;
import listener.LineWidthPanel;
import mygui.DrawShape;

public class Circle extends Shape {

	public Circle(Color color, int width, int x1, int y1, int x2, int y2,int types) {
		super(color, width, x1, y1, x2, y2, types);
	}

	public Circle(FileReader fileReader) throws IOException {

		super(fileReader);
	}

	public String toString() {
		return "Circle:" + super.toString();
	}

	public void output(PrintWriter printWriter) {
		printWriter.printf("C");
		super.output(printWriter);

	}

	public void DrawShape(Graphics2D g) {
		g.setColor(color);
		g.setStroke(new BasicStroke(lineWidth));
		min = new Point(Math.min(points.get(0).x,points.get(1).x), Math.min(points.get(0).y,points.get(1).y));
		w=Math.abs(points.get(1).x-points.get(0).x);
		h=Math.abs(points.get(1).y-points.get(0).y);
		g.drawOval(min.x, min.y, w, h);
		g.setColor(ColorHandler.color);
		g.setStroke(new BasicStroke(LineWidthPanel.width));


	}
}
