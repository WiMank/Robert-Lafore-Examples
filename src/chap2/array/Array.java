package chap2.array;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.applet.Applet;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Array extends Applet implements ActionListener, ItemListener, Runnable {
    private volatile Thread runner;
    private personGroup thePersonGroup;
    private int GPNumber = -1;
    private boolean isNumber = false;
    private TextField tf = new TextField("", 4);
    private Checkbox dups;
    private Checkbox nodups;
    private Button newButton;
    private Button fillButton;
    private Button insButton;
    private Button findButton;
    private Button delButton;

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
        this.fillButton = new Button("Fill");
        var2.add(this.fillButton);
        this.fillButton.addActionListener(this);
        this.insButton = new Button("Ins");
        var2.add(this.insButton);
        this.insButton.addActionListener(this);
        this.findButton = new Button("Find");
        var2.add(this.findButton);
        this.findButton.addActionListener(this);
        this.delButton = new Button("Del");
        var2.add(this.delButton);
        this.delButton.addActionListener(this);
        Panel var3 = new Panel();
        var1.add(var3);
        var3.setLayout(new GridLayout(2, 1));
        CheckboxGroup var4 = new CheckboxGroup();
        this.dups = new Checkbox("Dups OK", false, var4);
        this.nodups = new Checkbox("No dups", true, var4);
        var3.add(this.dups);
        this.dups.addItemListener(this);
        var3.add(this.nodups);
        this.nodups.addItemListener(this);
        Panel var5 = new Panel();
        var1.add(var5);
        var5.setLayout(new FlowLayout(2));
        var5.add(new Label("Number: "));
        var5.add(this.tf);
        this.thePersonGroup = new personGroup(20);
        this.thePersonGroup.doFill(10);
        this.thePersonGroup.setDrawMode(2);
        this.repaint();
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
            this.thePersonGroup.newArray(this.isNumber, this.GPNumber);
        } else if (var1.getSource() == this.fillButton) {
            this.thePersonGroup.fill(this.isNumber, this.GPNumber);
        } else if (var1.getSource() == this.insButton) {
            this.thePersonGroup.insert(this.isNumber, this.GPNumber);
        } else if (var1.getSource() == this.findButton) {
            this.thePersonGroup.find(this.isNumber, this.GPNumber);
        } else if (var1.getSource() == this.delButton) {
            this.thePersonGroup.delete(this.isNumber, this.GPNumber);
        }

        this.repaint();

        try {
            Thread.sleep(10L);
        } catch (InterruptedException var3) {
        }
    }

    public void itemStateChanged(ItemEvent var1) {
        boolean var2 = var1.getSource() == this.dups;
        boolean var3 = this.thePersonGroup.getDupsStatus();
        boolean var4 = this.thePersonGroup.getChangeStatus();
        this.thePersonGroup.setDupsStatus(var2);
        if (var2 && var4 && !var3 || !var2 && !var4 && var3) {
            this.dups.setState(true);
            this.nodups.setState(false);
        }

        if (!var2 && var4 && var3 || var2 && !var4 && !var3) {
            this.dups.setState(false);
            this.nodups.setState(true);
        }

    }

    public void start() {
        if (this.runner == null) {
            this.runner = new Thread(this);
            this.runner.start();
        }

    }

    public void stop() {
        this.runner = null;
    }

    public void run() {
        Thread var1 = Thread.currentThread();

        while(this.runner == var1) {
        }

    }

    public Array() {
    }
}
