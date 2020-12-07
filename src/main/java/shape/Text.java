package shape;

import listener.ColorHandler;
import listener.LineWidthPanel;

import java.awt.*;

public class Text extends Shape{
    protected String text;

    public Text(Color color, int width, int x1, int y1, int x2, int y2, int types, String text) {
        super(color, width, x1, y1, x2, y2, types);
        this.text = text;
    }

    @Override
    public String toString() {
        return "Rectangle:" + super.toString()+"Text"+text;
    }
    public void DrawShape(Graphics2D g) {
        g.setColor(color);
        g.setStroke(new BasicStroke(lineWidth));
        min = new Point(Math.min(points.get(0).x,points.get(1).x), Math.min(points.get(0).y,points.get(1).y));
        w=Math.abs(points.get(1).x-points.get(0).x);
        h=Math.abs(points.get(1).y-points.get(0).y);
        g.drawString(text,min.x,min.y);
        g.setColor(ColorHandler.color);
        g.setStroke(new BasicStroke(LineWidthPanel.width));
    }
}
