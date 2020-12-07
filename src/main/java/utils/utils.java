package utils;
import shape.Shape;

import java.io.*;
import java.util.Vector;


public class utils {
    public static void saveObject(Vector<Shape> vector, File fileName) throws Exception {
        ObjectOutputStream out = null;
        FileOutputStream fout = null;
        try {
            fout = new FileOutputStream(fileName);
            out = new ObjectOutputStream(fout);
            System.out.println(vector);
            for (Shape shape : vector) {
                System.out.println(shape);
                out.writeObject(shape);
            }
            out.writeObject(null);
        } finally {
            fout.close();
            out.close();
        }
    }

    // 读取对象，反序列化
    public static void readObject(Vector<Shape> vector, File fileName) throws Exception {
        ObjectInputStream in = null;
        FileInputStream fin = null;
        try {

            fin = new FileInputStream(fileName);
            in = new ObjectInputStream(fin);
            while (fin.available() > 0) {
                Object object = in.readObject();
                if (object == null) {
                    break;
                }
                vector.add((Shape) object);
            }

        } finally {
            fin.close();
            in.close();
        }
    }
}
