package chap3.bubble;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.awt.Color;
import java.awt.Graphics;

class groupBS {
    private final int appletWidth = 370;
    private final int appletHeight = 320;
    private final int maxHeight = 200;
    private final int topMargin = 30;
    private final int leftMargin = 10;
    private final int barLeftMargin = 35;
    private final int textHeight = 13;
    private int aSize;
    private personBS[] theArray;
    private int barWidth;
    private int barSeparation;
    private int outer;
    private int inner;
    private int innerOld;
    private boolean swapFlag;
    private boolean doneFlag;
    private int comps;
    private int swaps;
    private int initOrder;
    Color newColor;
    private int drawMode;

    public groupBS(int var1, int var2) {
        this.aSize = var1;
        this.initOrder = var2;
        this.theArray = new personBS[this.aSize];
        if (this.aSize == 100) {
            this.barWidth = 2;
            this.barSeparation = 1;
        } else {
            this.barWidth = 20;
            this.barSeparation = 10;
        }

        this.outer = this.aSize - 1;
        this.inner = 0;
        this.comps = 0;
        this.swaps = 0;
        this.swapFlag = false;
        this.doneFlag = false;
        this.drawMode = 2;
        int var3;
        int var4;
        int var5;
        int var6;
        if (this.initOrder == 1) {
            for(var3 = 0; var3 < this.aSize; ++var3) {
                var4 = (int)(Math.random() * 199.0D);
                var5 = (int)(Math.random() * 254.0D);
                var6 = (int)(Math.random() * 254.0D);
                int var7 = (int)(Math.random() * 254.0D);
                this.newColor = new Color(var5, var6, var7);
                this.theArray[var3] = new personBS(var4, this.newColor);
            }

        } else {
            for(var3 = 0; var3 < this.aSize; ++var3) {
                var4 = 199 - 199 * var3 / this.aSize;
                var5 = 255 - var4;
                var6 = 85 * (var3 % 3);
                this.newColor = new Color(var5, var6, var4);
                this.theArray[var3] = new personBS(var4, this.newColor);
            }

        }
    }

    public void setDrawMode(int var1) {
        this.drawMode = var1;
    }

    public int getAppletWidth() {
        return 370;
    }

    public int getAppletHeight() {
        return 320;
    }

    public boolean getDone() {
        return this.doneFlag;
    }

    public void arrowText(Graphics var1, Color var2, String var3, int var4, int var5, boolean var6, boolean var7) {
        int var8 = 35 + var4 * (this.barWidth + this.barSeparation);
        int var9 = 230 + (var5 + 1) * 13;
        var1.setColor(var2);
        if (var7) {
            var1.drawString(var3, var8, var9);
        }

        if (var6) {
            var1.drawLine(var8 + this.barWidth / 2, 232, var8 + this.barWidth / 2, var9 - 13);
            var1.drawLine(var8 + this.barWidth / 2, 232, var8 + this.barWidth / 2 - 3, 237);
            var1.drawLine(var8 + this.barWidth / 2, 232, var8 + this.barWidth / 2 + 3, 237);
        }

    }

    public void drawOneBar(Graphics var1, int var2) {
        int var3 = this.theArray[var2].getHeight();
        int var4 = 35 + var2 * (this.barWidth + this.barSeparation);
        int var5 = 230 - var3;
        Color var6 = this.theArray[var2].getColor();
        var1.setColor(Color.lightGray);
        var1.fillRect(var4, 30, this.barWidth, 200);
        var1.setColor(var6);
        var1.fill3DRect(var4, var5, this.barWidth, var3, true);
    }

    public void draw(Graphics var1) {
        if (this.drawMode != 2) {
            if (this.swapFlag) {
                this.drawOneBar(var1, this.innerOld);
                this.drawOneBar(var1, this.innerOld + 1);
                this.swapFlag = false;
            }
        } else {
            var1.setColor(Color.lightGray);
            var1.fillRect(0, 0, 370, 320);

            for(int var2 = 0; var2 < this.aSize; ++var2) {
                this.drawOneBar(var1, var2);
            }
        }

        var1.setColor(Color.lightGray);
        var1.fillRect(0, 0, 150, 32);
        var1.setColor(Color.black);
        var1.drawString("Comparisons = " + this.comps, 10, 28);
        var1.drawString("Swaps = " + this.swaps, 10, 15);
        var1.setColor(Color.lightGray);
        var1.fillRect(0, 230, 370, 65);
        if (this.aSize == 10) {
            this.arrowText(var1, Color.red, "outer", this.outer, 3, true, true);
            this.arrowText(var1, Color.blue, "inner", this.inner, 1, true, true);
            this.arrowText(var1, Color.blue, "inner+1", this.inner + 1, 1, true, true);
            if (this.doneFlag) {
                this.arrowText(var1, Color.black, "Sort is complete", this.inner, 2, false, true);
            } else if (this.theArray[this.inner].getHeight() > this.theArray[this.inner + 1].getHeight()) {
                this.arrowText(var1, Color.blue, "Will be swapped", this.inner, 2, false, true);
            } else {
                this.arrowText(var1, Color.blue, "Will not be swapped", this.inner, 2, false, true);
            }
        } else {
            this.arrowText(var1, Color.red, "xxx", this.outer, 3, true, false);
            this.arrowText(var1, Color.blue, "xxx", this.inner, 1, true, false);
            this.arrowText(var1, Color.blue, "xxx", this.inner + 1, 1, true, false);
        }

        this.drawMode = 2;
    }

    public void sortStep() {
        if (!this.doneFlag) {
            ++this.comps;
            if (this.theArray[this.inner].getHeight() > this.theArray[this.inner + 1].getHeight()) {
                this.swap(this.inner, this.inner + 1);
                this.swapFlag = true;
                ++this.swaps;
            }

            this.innerOld = this.inner++;
            if (this.inner > this.outer - 1) {
                this.inner = 0;
                --this.outer;
                if (this.outer == 0) {
                    this.doneFlag = true;
                }
            }

        }
    }

    public void swap(int var1, int var2) {
        personBS var3 = this.theArray[var1];
        this.theArray[var1] = this.theArray[var2];
        this.theArray[var2] = var3;
    }
}
