package chap8.tree;

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

public class Tree extends Applet implements Runnable, ActionListener {
    private Thread runner;
    private int groupSize = 24;
    private personGroup thePersonGroup;
    private boolean runFlag;
    private int order = 1;
    private int GPNumber = -1;
    private boolean isNumber = false;
    private TextField tf = new TextField("", 3);
    private int MAX_KEY = 99;
    private Button fillButton;
    private Button findButton;
    private Button insButton;
    private Button travButton;
    private Button delButton;

    public void init() {
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
        this.findButton = new Button("Find");
        var2.add(this.findButton);
        this.findButton.addActionListener(this);
        this.insButton = new Button("Ins");
        var2.add(this.insButton);
        this.insButton.addActionListener(this);
        this.travButton = new Button("Trav");
        var2.add(this.travButton);
        this.travButton.addActionListener(this);
        this.delButton = new Button("Del");
        var2.add(this.delButton);
        this.delButton.addActionListener(this);
        Panel var3 = new Panel();
        var1.add(var3);
        var3.setLayout(new FlowLayout(2));
        var3.add(new Label("Enter number: "));
        var3.add(this.tf);
        this.thePersonGroup = new personGroup();
        this.thePersonGroup.doFill(20);
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
        } else if (var1.getSource() == this.findButton) {
            this.thePersonGroup.find(this.isNumber, this.GPNumber);
        } else if (var1.getSource() == this.insButton) {
            this.thePersonGroup.insert(this.isNumber, this.GPNumber);
        } else if (var1.getSource() == this.travButton) {
            this.thePersonGroup.traverse();
        } else if (var1.getSource() == this.delButton) {
            this.thePersonGroup.remove(this.isNumber, this.GPNumber);
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

    public Tree() {
    }
}
