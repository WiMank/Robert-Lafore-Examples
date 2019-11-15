package chap11.hash_double;

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

public class HashDouble extends Applet implements Runnable, ActionListener, ItemListener {
    private Thread runner;
    private personGroup thePersonGroup;
    private int GPNumber = -1;
    private boolean isNumber = false;
    private TextField tf = new TextField("", 4);
    private Checkbox doubleH;
    private Checkbox quad;
    private Button newButton;
    private Button fillButton;
    private Button insButton;
    private Button findButton;

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
        Panel var3 = new Panel();
        var1.add(var3);
        var3.setLayout(new GridLayout(2, 1));
        CheckboxGroup var4 = new CheckboxGroup();
        this.doubleH = new Checkbox("Double", true, var4);
        var3.add(this.doubleH);
        this.doubleH.addItemListener(this);
        this.quad = new Checkbox("Quad", false, var4);
        var3.add(this.quad);
        this.quad.addItemListener(this);
        Panel var5 = new Panel();
        var1.add(var5);
        var5.setLayout(new FlowLayout(2));
        var5.add(new Label("Enter number: "));
        var5.add(this.tf);
        this.thePersonGroup = new personGroup(59);
        this.thePersonGroup.doFill(30);
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
            this.thePersonGroup.newArray(this.isNumber, this.GPNumber);
        } else if (var1.getSource() == this.fillButton) {
            this.thePersonGroup.fill(this.isNumber, this.GPNumber);
        } else if (var1.getSource() == this.insButton) {
            this.thePersonGroup.insert(this.isNumber, this.GPNumber);
        } else if (var1.getSource() == this.findButton) {
            this.thePersonGroup.find(this.isNumber, this.GPNumber);
        }

        this.repaint();

        try {
            Thread.sleep(10L);
        } catch (InterruptedException var3) {
        }
    }

    public void itemStateChanged(ItemEvent var1) {
        boolean var2 = var1.getSource() == this.doubleH;
        boolean var3 = this.thePersonGroup.probeDouble();
        boolean var4 = this.thePersonGroup.changeProbeOK();
        this.thePersonGroup.setProbe(var2);
        if (var2 && var4 && !var3 || !var2 && !var4 && var3) {
            this.doubleH.setState(true);
            this.quad.setState(false);
        }

        if (!var2 && var4 && var3 || var2 && !var4 && !var3) {
            this.doubleH.setState(false);
            this.quad.setState(true);
        }

    }

    public void run() {
        // $FF: Couldn't be decompiled
    }

    public HashDouble() {
    }
}
