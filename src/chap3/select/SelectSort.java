package chap3.select;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.applet.Applet;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectSort extends Applet implements Runnable, ActionListener {
    private Image offscreenImage;
    private Graphics offscreenGraphics;
    private int aWidth;
    private int aHeight;
    private Thread runner;
    private int groupSize = 10;
    private groupSS thePersonGroup;
    private boolean runFlag;
    private int order = 1;
    private Button newButton;
    private Button sizeButton;
    private Button drawButton;
    private Button runButton;
    private Button stepButton;

    public void init() {
        this.thePersonGroup = new groupSS(this.groupSize, this.order);
        this.setLayout(new FlowLayout(2));
        this.newButton = new Button("New");
        this.add(this.newButton);
        this.newButton.addActionListener(this);
        this.sizeButton = new Button("Size");
        this.add(this.sizeButton);
        this.sizeButton.addActionListener(this);
        this.drawButton = new Button("Draw");
        this.add(this.drawButton);
        this.drawButton.addActionListener(this);
        this.runButton = new Button("Run");
        this.add(this.runButton);
        this.runButton.addActionListener(this);
        this.stepButton = new Button("Step");
        this.add(this.stepButton);
        this.stepButton.addActionListener(this);
        this.aWidth = this.thePersonGroup.getAppletWidth();
        this.aHeight = this.thePersonGroup.getAppletHeight();
        this.offscreenImage = this.createImage(this.aWidth, this.aHeight);
        this.offscreenGraphics = this.offscreenImage.getGraphics();
        this.runFlag = false;
        this.thePersonGroup.setDrawMode(2);
    }

    public void paint(Graphics var1) {
        this.thePersonGroup.draw(this.offscreenGraphics);
        var1.drawImage(this.offscreenImage, 0, 0, this);
    }

    public void update(Graphics var1) {
        this.paint(var1);
    }

    public void actionPerformed(ActionEvent var1) {
        if (var1.getSource() == this.newButton) {
            this.runFlag = false;
            this.order = this.order == 1 ? 2 : 1;
            this.thePersonGroup = new groupSS(this.groupSize, this.order);
        } else if (var1.getSource() == this.sizeButton) {
            this.runFlag = false;
            this.groupSize = this.groupSize == 10 ? 100 : 10;
            this.thePersonGroup = new groupSS(this.groupSize, this.order);
        } else if (var1.getSource() == this.drawButton) {
            this.runFlag = false;
            this.thePersonGroup.setDrawMode(2);
        } else if (var1.getSource() == this.runButton) {
            this.thePersonGroup.setDrawMode(1);
            this.runFlag = true;
        } else if (var1.getSource() == this.stepButton) {
            this.runFlag = false;
            this.thePersonGroup.sortStep();
            this.thePersonGroup.setDrawMode(1);
        }

        this.repaint();
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
            if (this.runFlag && !this.thePersonGroup.getDone()) {
                this.thePersonGroup.sortStep();
                this.repaint();
                this.thePersonGroup.setDrawMode(1);
                int var2 = this.groupSize == 10 ? 250 : 75;

                try {
                    Thread.sleep((long)var2);
                } catch (InterruptedException var3) {
                }
            }
        }

    }

    public SelectSort() {
    }
}
