package chap3.select;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.awt.Color;
import java.awt.Graphics;

class groupSS {
    private final int appletWidth = 370;
    private final int appletHeight = 320;
    private final int maxHeight = 200;
    private final int topMargin = 30;
    private final int leftMargin = 10;
    private final int barLeftMargin = 35;
    private final int textHeight = 13;
    private int aSize;
    private personSS[] theArray;
    private int barWidth;
    private int barSeparation;
    private int outer;
    private int inner;
    private int min;
    private int outerOld;
    private int minOld;
    private boolean searchFlag;
    private boolean swapFlag;
    private boolean doneFlag;
    private int comps;
    private int swaps;
    private int initOrder;
    private Color newColor;
    private int drawMode;

    public groupSS(int var1, int var2) {
        this.aSize = var1;
        this.initOrder = var2;
        this.theArray = new personSS[this.aSize];
        if (this.aSize == 100) {
            this.barWidth = 2;
            this.barSeparation = 1;
        } else {
            this.barWidth = 20;
            this.barSeparation = 10;
        }

        this.outer = 0;
        this.inner = 1;
        this.min = 0;
        this.searchFlag = true;
        this.comps = 0;
        this.swaps = 0;
        this.swapFlag = false;
        this.doneFlag = false;
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
                this.theArray[var3] = new personSS(var4, this.newColor);
            }
        } else {
            for(var3 = 0; var3 < this.aSize; ++var3) {
                var4 = 199 - 199 * var3 / this.aSize;
                var5 = 255 - var4;
                var6 = 85 * (var3 % 3);
                this.newColor = new Color(var5, var6, var4);
                this.theArray[var3] = new personSS(var4, this.newColor);
            }
        }

        this.drawMode = 2;
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
                this.drawOneBar(var1, this.outerOld);
                this.drawOneBar(var1, this.minOld);
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
        var1.fillRect(0, 230, 370, 68);
        if (this.aSize == 10) {
            this.arrowText(var1, Color.red, "outer", this.outer, 1, true, true);
            this.arrowText(var1, Color.blue, "inner", this.inner, 2, true, true);
            this.arrowText(var1, Color.magenta, "min", this.min, 3, true, true);
            if (this.doneFlag) {
                this.arrowText(var1, Color.black, "Sort is complete", 0, 4, false, true);
            } else if (this.searchFlag) {
                this.arrowText(var1, Color.black, "Searching for minimum", 0, 4, false, true);
            } else if (this.outer == this.min) {
                this.arrowText(var1, Color.black, "No swap necessary", 0, 4, false, true);
            } else {
                this.arrowText(var1, Color.black, "Will swap outer & min", 0, 4, false, true);
            }
        } else {
            this.arrowText(var1, Color.red, "xxx", this.outer, 1, true, false);
            this.arrowText(var1, Color.blue, "xxx", this.inner, 2, true, false);
            this.arrowText(var1, Color.magenta, "xxx", this.min, 3, true, false);
        }

        this.drawMode = 2;
    }

    public void sortStep() {
        if (!this.doneFlag) {
            if (this.inner <= this.aSize - 1 || !this.searchFlag) {
                if (this.searchFlag) {
                    ++this.comps;
                    if (this.theArray[this.inner].getHeight() < this.theArray[this.min].getHeight()) {
                        this.minOld = this.min;
                        this.min = this.inner;
                    }

                    ++this.inner;
                    if (this.inner > this.aSize - 1) {
                        this.searchFlag = false;
                        return;
                    }
                } else {
                    if (this.min != this.outer) {
                        this.swap(this.outer, this.min);
                        this.swapFlag = true;
                        ++this.swaps;
                    }

                    this.outerOld = this.outer++;
                    this.inner = this.outer + 1;
                    this.minOld = this.min;
                    this.min = this.outer;
                    this.searchFlag = true;
                    if (this.outer > this.aSize - 2) {
                        this.doneFlag = true;
                    }
                }

            }
        }
    }

    public void swap(int var1, int var2) {
        personSS var3 = this.theArray[var1];
        this.theArray[var1] = this.theArray[var2];
        this.theArray[var2] = var3;
    }
}
