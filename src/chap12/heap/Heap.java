package chap12.heap;

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

public class Heap extends Applet implements Runnable, ActionListener, MouseListener {
    private Thread runner;
    private personGroup thePersonGroup;
    private boolean runFlag;
    private int order = 1;
    private int GPNumber = -1;
    private boolean isNumber = false;
    private TextField tf = new TextField("", 3);
    private int MAX_KEY = 99;
    private Button fillButton;
    private Button chngButton;
    private Button remButton;
    private Button insButton;

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
        this.fillButton = new Button("Fill");
        var2.add(this.fillButton);
        this.fillButton.addActionListener(this);
        this.chngButton = new Button("Chng");
        var2.add(this.chngButton);
        this.chngButton.addActionListener(this);
        this.remButton = new Button("Rem");
        var2.add(this.remButton);
        this.remButton.addActionListener(this);
        this.insButton = new Button("Ins");
        var2.add(this.insButton);
        this.insButton.addActionListener(this);
        Panel var3 = new Panel();
        var1.add(var3);
        var3.setLayout(new FlowLayout(2));
        var3.add(new Label("Enter number: "));
        var3.add(this.tf);
        this.thePersonGroup = new personGroup();
        this.thePersonGroup.doFill(10);
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
        if (var1.getSource() == this.fillButton) {
            this.thePersonGroup.fill(this.isNumber, this.GPNumber);
        } else if (var1.getSource() == this.chngButton) {
            this.thePersonGroup.change(this.isNumber, this.GPNumber);
        } else if (var1.getSource() == this.remButton) {
            this.thePersonGroup.remove();
        } else if (var1.getSource() == this.insButton) {
            this.thePersonGroup.insert(this.isNumber, this.GPNumber);
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

    public Heap() {
    }
}
