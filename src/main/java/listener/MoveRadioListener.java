package listener;


import mygui.DrawShape;
import shape.Shape;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class MoveRadioListener implements ItemListener {
    @Override
    public void itemStateChanged(ItemEvent e) {
        // TODO Auto-generated method stub

        ItemSelectable itemSelectable = e.getItemSelectable();
        System.out.println("MoveRadioListener:"+itemSelectable.equals(DrawShape.rdoMove));
        DrawShape.action=-1;
        if(itemSelectable.equals(DrawShape.rdoSelect))
        {
            DrawShape.perType=DrawShape.type;
            DrawShape.type=DrawShape.RECT;
            DrawShape.action=DrawShape.Select;
        }
        else if (itemSelectable.equals(DrawShape.rdoMove))
        {
            System.out.println("233");

            DrawShape.action=DrawShape.REMOVE;
        }
        else if (itemSelectable.equals(DrawShape.rdoNoSelect))
        {

            DrawShape.type= DrawShape.perType;
            System.out.println("前一个形状"+DrawShape.perType+"====后一个形状"+DrawShape.type);
            DrawShape.action=-1;
            Tools.noSelect();
        }
        else if (itemSelectable.equals(DrawShape.rdoEraser))

        {
            DrawShape.perType=DrawShape.type;
            DrawShape.type=DrawShape.OTHER;
            DrawShape.action=DrawShape.Eraser;

        }


    }
}
