package chap11.hash_chain;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Scrollbar;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

public class HashChain extends Applet implements Runnable, ActionListener, AdjustmentListener {
    private Thread runner;
    private personGroup thePersonGroup;
    private int GPNumber = -1;
    private boolean isNumber = false;
    private TextField tf = new TextField("", 4);
    private Button newButton;
    private Button fillButton;
    private Button insButton;
    private Button findButton;
    private Button delButton;
    private Scrollbar sbar;
    private int scrollValue;
    private int scrollRange;
    private int linesInView;

    public void init() {
        this.thePersonGroup = new personGroup(25);
        this.thePersonGroup.doFill(25);
        this.setLayout(new BorderLayout());
        this.scrollRange = this.thePersonGroup.getScrollRange();
        this.linesInView = this.thePersonGroup.getLinesInView() - 1;
        this.sbar = new Scrollbar(1, 0, this.linesInView, 0, this.scrollRange);
        this.add("East", this.sbar);
        this.sbar.addAdjustmentListener(this);
        this.resize(440, 320);
        Panel var1 = new Panel();
        this.add("North", var1);
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
        var3.setLayout(new FlowLayout(2));
        var3.add(new Label("Enter number: "));
        var3.add(this.tf);
        this.thePersonGroup.setDrawMode(2);
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
        } catch (NumberFormatException var5) {
            this.GPNumber = 0;
            this.isNumber = false;
        }

        if (var1.getSource() == this.newButton) {
            this.thePersonGroup.newArray(this.isNumber, this.GPNumber);
            this.scrollRange = this.thePersonGroup.getScrollRange();
            this.sbar.setValues(0, this.linesInView, 0, this.scrollRange);
        } else if (var1.getSource() == this.fillButton) {
            this.thePersonGroup.fill(this.isNumber, this.GPNumber);
            this.sbar.setValue(this.scrollValue);
        } else {
            int var3;
            if (var1.getSource() == this.insButton) {
                this.thePersonGroup.insert(this.isNumber, this.GPNumber);
                var3 = this.thePersonGroup.getScrollValue();
                if (var3 != this.scrollValue) {
                    this.scrollValue = var3;
                    this.sbar.setValue(this.scrollValue);
                    this.thePersonGroup.setDrawMode(2);
                }
            } else if (var1.getSource() == this.findButton) {
                this.thePersonGroup.find(this.isNumber, this.GPNumber);
                var3 = this.thePersonGroup.getScrollValue();
                if (var3 != this.scrollValue) {
                    this.scrollValue = var3;
                    this.sbar.setValue(this.scrollValue);
                    this.thePersonGroup.setDrawMode(2);
                }
            } else if (var1.getSource() == this.delButton) {
                this.thePersonGroup.delete(this.isNumber, this.GPNumber);
                var3 = this.thePersonGroup.getScrollValue();
                if (var3 != this.scrollValue) {
                    this.scrollValue = var3;
                    this.sbar.setValue(this.scrollValue);
                    this.thePersonGroup.setDrawMode(2);
                }
            }
        }

        this.repaint();

        try {
            Thread.sleep(10L);
        } catch (InterruptedException var4) {
        }
    }

    public void adjustmentValueChanged(AdjustmentEvent var1) {
        this.scrollValue = this.sbar.getValue();
        this.thePersonGroup.setScrollValue(this.scrollValue);
        this.sbar.setValue(this.scrollValue);
        this.repaint();
    }

    public void run() {
        // $FF: Couldn't be decompiled
    }

    public HashChain() {
    }
}
