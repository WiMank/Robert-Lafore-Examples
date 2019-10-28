package chap9.rbtTree;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.applet.Applet;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RBTree extends Applet implements Runnable, ActionListener, MouseListener {
    private Thread runner;
    private int groupSize = 24;
    private personGroup thePersonGroup;
    private boolean runFlag;
    private int order = 1;
    private int GPNumber = -1;
    private boolean isNumber = false;
    private TextField tf = new TextField("", 3);
    private int MAX_KEY = 99;
    private long lastWhen;
    private boolean itsSingleClick;
    private Button startButton;
    private Button insButton;
    private Button delButton;
    private Button flipButton;
    private Button rolButton;
    private Button rorButton;
    private Button rbButton;

    public void init() {
        this.addMouseListener(this);
        this.runFlag = false;
        this.thePersonGroup = new personGroup();
        this.setLayout(new FlowLayout());
        Panel var1 = new Panel();
        this.add(var1);
        var1.setLayout(new FlowLayout());
        Panel var2 = new Panel();
        var1.add(var2);
        var2.setLayout(new FlowLayout(0));
        this.startButton = new Button("Start");
        var2.add(this.startButton);
        this.startButton.addActionListener(this);
        this.insButton = new Button("Ins");
        var2.add(this.insButton);
        this.insButton.addActionListener(this);
        this.delButton = new Button("Del");
        var2.add(this.delButton);
        this.delButton.addActionListener(this);
        this.flipButton = new Button("Flip");
        var2.add(this.flipButton);
        this.flipButton.addActionListener(this);
        this.rolButton = new Button("RoL");
        var2.add(this.rolButton);
        this.rolButton.addActionListener(this);
        this.rorButton = new Button("RoR");
        var2.add(this.rorButton);
        this.rorButton.addActionListener(this);
        this.rbButton = new Button("R/B");
        var2.add(this.rbButton);
        this.rbButton.addActionListener(this);
        Panel var3 = new Panel();
        var1.add(var3);
        var3.setLayout(new FlowLayout(2));
        var3.add(new Label("Number: "));
        var3.add(this.tf);
        this.thePersonGroup = new personGroup();
        this.thePersonGroup.fillRoot();
        this.repaint();
    }

    public void start() {
        if (this.runner == null) {
            this.runner = new Thread(this);
            this.runner.start();
        }

    }

    public void stop() {
        if (this.runner != null) {
            this.runner.stop();
            this.runner = null;
        }

    }

    public void paint(Graphics var1) {
        this.thePersonGroup.draw(var1);
    }

    public void update(Graphics var1) {
        this.paint(var1);
    }

    public void actionPerformed(ActionEvent var1) {
        this.isNumber = true;
        String var2 = this.tf.getText();

        try {
            this.GPNumber = Integer.parseInt(var2);
        } catch (NumberFormatException var4) {
            this.GPNumber = 0;
            this.isNumber = false;
        }

        this.thePersonGroup.setDrawAll(false);
        if (var1.getSource() == this.startButton) {
            this.thePersonGroup.fillRoot();
        } else if (var1.getSource() == this.insButton) {
            this.thePersonGroup.quickInsert(this.isNumber, this.GPNumber);
        } else if (var1.getSource() == this.delButton) {
            this.thePersonGroup.quickRemove(this.isNumber, this.GPNumber);
        } else if (var1.getSource() == this.flipButton) {
            this.thePersonGroup.flip();
        } else if (var1.getSource() == this.rolButton) {
            this.thePersonGroup.rotateLeft();
        } else if (var1.getSource() == this.rorButton) {
            this.thePersonGroup.rotateRight();
        } else if (var1.getSource() == this.rbButton) {
            this.thePersonGroup.toggleRB();
        }

        this.repaint();

        try {
            Thread.sleep(10L);
        } catch (InterruptedException var3) {
        }
    }

    public void run() {
        // $FF: Couldn't be decompiled
    }

    public void mousePressed(MouseEvent var1) {
        int var2 = var1.getX();
        int var3 = var1.getY();
        int var4 = this.thePersonGroup.xyToIndex(var2, var3);
        if (var1.getClickCount() == 1) {
            this.thePersonGroup.setArrow(var4);
            this.repaint();
        }

    }

    public void mouseReleased(MouseEvent var1) {
    }

    public void mouseEntered(MouseEvent var1) {
    }

    public void mouseExited(MouseEvent var1) {
    }

    public void mouseClicked(MouseEvent var1) {
    }

    public RBTree() {
    }
}
