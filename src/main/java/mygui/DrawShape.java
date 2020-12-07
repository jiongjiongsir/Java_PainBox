package mygui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
//import java.nio.channels.NetworkChannel;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.*;

import fileoper.SaveFile;
import listener.*;
import shape.Shape;

public class DrawShape extends JFrame {

	JPanel pnlCommandArea = new JPanel(new FlowLayout());
	public static DragDrawPanel pnlDisplayArea = new DragDrawPanel();
	JPanel pnlShape = new JPanel(new GridLayout(3, 2));
	JPanel pnlFile = new JPanel(new GridLayout(2, 2));
	JPanel pnlMove=new JPanel(new GridLayout(2,2));
	JPanel pnlAction=new JPanel(new GridLayout(2,1));
	public static LineWidthPanel lineWidthPanel = new LineWidthPanel();
	public static ColorHandler colorHandler = new ColorHandler();
	Menu menu=new Menu();

	static JButton btnO = new JButton("Open");
	static JButton btnS = new JButton("Save");
	static JButton btnN = new JButton("New");
	static JButton btnE = new JButton("Exit");
	static JButton btnR=new JButton("Rotate");

	public static int perType=0;

	public static JRadioButton rdoC = new JRadioButton("Circle");
	public static JRadioButton rdoL = new JRadioButton("Line", true);
	public static JRadioButton rdoR = new JRadioButton("Rect");
	public static JRadioButton rdoO = new JRadioButton("Other");
	public static JRadioButton rdoText=new JRadioButton("Text");
	public static Vector<shape.Shape> shapes = new Vector<shape.Shape>();

	public static JRadioButton rdoSelect=new JRadioButton("Select");
	public static JRadioButton rdoMove=new JRadioButton("Move");
	public static JRadioButton rdoNoSelect=new JRadioButton("NoSel");
	public static JRadioButton rdoEraser=new JRadioButton("Eraser");
	ButtonGroup grpShape = new ButtonGroup();
	ButtonGroup grpMove=new ButtonGroup();
	ButtonGroup grpDraw = new ButtonGroup();
	Container contentPane;

	public final static int LINE = 0;
	public final static int CIRCLE = 1;
	public final static int RECT = 2;
	public final static int Text = 3;
	public final static int OTHER = 4;
	public static int type = LINE;

	public final static int Select=5;
	public final static int NoSelect=6;
	public final static int REMOVE=7;
	public final static int ROTATE=8;
	public final static int Eraser=9;
	public static int action=-1;
	public DrawShape() {
		setTitle("绘图板");
		setSize(850, 880);
		setLayout(new FlowLayout());
		pnlCommandArea.setBorder(new TitledBorder("Command"));
		Dimension d = new Dimension(200, 800);

		pnlCommandArea.setPreferredSize(d);
		pnlCommandArea.setLayout(new GridLayout(6, 1));

		shapes = pnlDisplayArea.getshapes();

		setJMenuBar(menu);
		add(pnlCommandArea);
		add(pnlDisplayArea);

		pnlShape.setPreferredSize(new Dimension(190, 140));
		pnlFile.setPreferredSize(new Dimension(190, 140));
		pnlAction.setPreferredSize(new Dimension(190,140));
		pnlShape.setBackground(Color.red);
		pnlFile.setBackground(Color.green);

		pnlShape.setBorder(new TitledBorder("Shape"));
		pnlFile.setBorder(new TitledBorder("File"));
		pnlMove.setBorder(new TitledBorder("Select"));
		pnlAction.setBorder(new TitledBorder("Action"));

		pnlCommandArea.add(pnlShape);
		pnlCommandArea.add(lineWidthPanel);
		pnlCommandArea.add(colorHandler);
		pnlCommandArea.add(pnlFile);
		pnlCommandArea.add(pnlMove);
		pnlCommandArea.add(pnlAction);

		pnlShape.add(rdoL, 0);
		pnlShape.add(rdoR, 1);
		pnlShape.add(rdoC, 2);
		pnlShape.add(rdoO, 3);
		pnlShape.add(rdoText,4);

		grpShape.add(rdoC);
		grpShape.add(rdoO);
		grpShape.add(rdoL);
		grpShape.add(rdoR);
		grpShape.add(rdoText);

		pnlMove.add(rdoSelect,0);
		pnlMove.add(rdoMove,1);
		pnlMove.add(rdoNoSelect,2);
		pnlMove.add(rdoEraser,3);
//		pnlMove.add(rdoText,3);


		grpMove.add(rdoSelect);
		grpMove.add(rdoMove);
		grpMove.add(rdoNoSelect);
		grpMove.add(rdoEraser);
//		grpMove.add(rdoText);

		// ??????????????
		RadioListener radioListener = new RadioListener();
		MoveRadioListener moveRadioListener=new MoveRadioListener();
		rdoL.addItemListener(radioListener);
		rdoR.addItemListener(radioListener);
		rdoC.addItemListener(radioListener);
		rdoO.addItemListener(radioListener);
		rdoText.addItemListener(radioListener);
		rdoSelect.addItemListener(moveRadioListener);
		rdoMove.addItemListener(moveRadioListener);
		rdoNoSelect.addItemListener(moveRadioListener);
		rdoEraser.addItemListener(moveRadioListener);

		btnO.setMnemonic(KeyEvent.VK_O);
		btnS.setMnemonic(KeyEvent.VK_S);
		btnN.setMnemonic(KeyEvent.VK_N);
		btnE.setMnemonic(KeyEvent.VK_E);

		BtnListrner btnListrner = new BtnListrner();
		btnO.addActionListener(btnListrner);
		btnS.addActionListener(btnListrner);
		btnN.addActionListener(btnListrner);
		btnE.addActionListener(btnListrner);
		btnR.addActionListener(btnListrner);

		pnlFile.add(btnO, 0);
		pnlFile.add(btnS, 1);
		pnlFile.add(btnN, 2);
		pnlFile.add(btnE, 3);

		pnlAction.add(btnR,0);

		Windowclose windowclose = new Windowclose();
		addWindowListener(windowclose);
		setVisible(true);

	}



}
