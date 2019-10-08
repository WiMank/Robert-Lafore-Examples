package chap4.stack;

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

public class Stack extends Applet implements Runnable, ActionListener {
    private Thread runner;
    private personGroup thePersonGroup;
    private int GPNumber = -1;
    private boolean isNumber = false;
    private TextField tf = new TextField("", 4);
    private Button newButton;
    private Button pushButton;
    private Button popButton;
    private Button peekButton;

    public void init() {
        this.setLayout(new FlowLayout());
        Panel var1 = new Panel();
        this.add(var1);
        var1.setLayout(new FlowLayout());
        Panel var2 = new Panel();
        var1.add(var2);
        var2.setLayout(new FlowLayout(0));
        this.newButton = new Button("New");
        var2.add(this.newButton);
        this.newButton.addActionListener(this);
        this.pushButton = new Button("Push");
        var2.add(this.pushButton);
        this.pushButton.addActionListener(this);
        this.popButton = new Button("Pop");
        var2.add(this.popButton);
        this.popButton.addActionListener(this);
        this.peekButton = new Button("Peek");
        var2.add(this.peekButton);
        this.peekButton.addActionListener(this);
        Panel var3 = new Panel();
        var1.add(var3);
        var3.setLayout(new FlowLayout(2));
        var3.add(new Label("Number: "));
        var3.add(this.tf);
        this.thePersonGroup = new personGroup();
        this.thePersonGroup.doFill();
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

        if (var1.getSource() == this.newButton) {
            this.thePersonGroup.newStack();
        } else if (var1.getSource() == this.pushButton) {
            this.thePersonGroup.push(this.isNumber, this.GPNumber);
        } else if (var1.getSource() == this.popButton) {
            var2 = this.thePersonGroup.pop();
            this.tf.setText(var2);
        } else if (var1.getSource() == this.peekButton) {
            var2 = this.thePersonGroup.peek();
            this.tf.setText(var2);
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

    public Stack() {
    }
}
