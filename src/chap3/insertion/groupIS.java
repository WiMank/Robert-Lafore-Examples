package chap3.insertion;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.awt.Color;
import java.awt.Graphics;

class groupIS {
    private final int appletWidth = 370;
    private final int appletHeight = 320;
    private final int maxHeight = 200;
    private final int topMargin = 30;
    private final int leftMargin = 10;
    private final int barLeftMargin = 35;
    private final int textHeight = 13;
    private int aSize;
    private personIS[] theArray;
    private int barWidth;
    private int barSeparation;
    private int outer;
    private int inner;
    private int outerOld;
    private int innerOld;
    private boolean doneFlag;
    private int codePart;
    private int comps;
    private int copies;
    private int initOrder;
    private Color newColor;
    private int drawMode;

    public groupIS(int var1, int var2) {
        this.aSize = var1;
        this.initOrder = var2;
        this.theArray = new personIS[this.aSize + 2];
        if (this.aSize == 100) {
            this.barWidth = 2;
            this.barSeparation = 1;
        } else {
            this.barWidth = 18;
            this.barSeparation = 7;
        }

        this.inner = this.outer = 1;
        this.innerOld = this.outerOld = 1;
        this.comps = 0;
        this.copies = 0;
        this.doneFlag = false;
        this.codePart = 1;
        new Color(0, 0, 0);
        Color var3;
        int var4;
        int var5;
        int var6;
        int var7;
        if (this.initOrder == 1) {
            for(var4 = 0; var4 < this.aSize; ++var4) {
                var5 = 10 + (int)(Math.random() * 189.0D);
                var6 = (int)(Math.random() * 254.0D);
                var7 = (int)(Math.random() * 254.0D);
                int var8 = (int)(Math.random() * 254.0D);
                var3 = new Color(var6, var7, var8);
                this.theArray[var4] = new personIS(var5, var3);
            }
        } else {
            for(var4 = 0; var4 < this.aSize; ++var4) {
                var5 = 195 - 195 * var4 / this.aSize;
                var6 = 255 - var5;
                var7 = 85 * (var4 % 3);
                var3 = new Color(var6, var7, var5);
                this.theArray[var4] = new personIS(var5, var3);
            }
        }

        this.theArray[this.aSize + 1] = new personIS(0, Color.lightGray);
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
        int var3;
        if (this.theArray[var2] == null) {
            var3 = 35 + var2 * (this.barWidth + this.barSeparation);
            var1.setColor(Color.lightGray);
            var1.fillRect(var3, 30, this.barWidth, 200);
        } else {
            var3 = this.theArray[var2].getHeight();
            int var4 = 35 + var2 * (this.barWidth + this.barSeparation);
            int var5 = 230 - var3;
            Color var6 = this.theArray[var2].getColor();
            var1.setColor(Color.lightGray);
            var1.fillRect(var4, 30, this.barWidth, 200);
            var1.setColor(var6);
            var1.fill3DRect(var4, var5, this.barWidth, var3, true);
        }
    }

    public void draw(Graphics var1) {
        if (this.drawMode != 2) {
            switch(this.codePart) {
                case 1:
                    this.drawOneBar(var1, this.inner);
                    this.drawOneBar(var1, this.aSize + 1);
                    break;
                case 2:
                    this.drawOneBar(var1, this.aSize + 1);
                    this.drawOneBar(var1, this.innerOld);
                    this.drawOneBar(var1, this.inner);
                    break;
                case 3:
                    this.drawOneBar(var1, this.inner);
                    this.drawOneBar(var1, this.aSize + 1);
            }
        } else {
            var1.setColor(Color.lightGray);
            var1.fillRect(0, 0, 370, 320);

            for(int var2 = 0; var2 < this.aSize; ++var2) {
                this.drawOneBar(var1, var2);
            }

            this.drawOneBar(var1, this.aSize + 1);
        }

        var1.setColor(Color.lightGray);
        var1.fillRect(0, 0, 135, 32);
        var1.setColor(Color.black);
        var1.drawString("Comparisons = " + this.comps, 10, 28);
        var1.drawString("Copies = " + this.copies, 10, 15);
        var1.setColor(Color.lightGray);
        var1.fillRect(0, 230, 370, 78);
        if (this.aSize == 10) {
            this.arrowText(var1, Color.red, "outer", this.outer, 1, true, true);
            this.arrowText(var1, Color.blue, "inner", this.inner, 2, true, true);
            this.arrowText(var1, Color.magenta, "temp", this.aSize + 1, 1, true, true);
            switch(this.codePart) {
                case 1:
                    if (this.doneFlag) {
                        this.arrowText(var1, Color.black, "Sort is complete", 0, 3, false, true);
                    } else {
                        this.arrowText(var1, Color.black, "Will copy outer to temp", 0, 3, false, true);
                    }
                    break;
                case 2:
                    if (this.inner > 0) {
                        this.arrowText(var1, Color.black, "Have compared inner-1 and temp", 0, 3, false, true);
                    } else {
                        this.arrowText(var1, Color.black, "Now inner is 0, so", 0, 3, false, true);
                    }

                    if (this.inner > 0 && this.theArray[this.inner - 1].getHeight() >= this.theArray[this.aSize + 1].getHeight()) {
                        this.arrowText(var1, Color.black, "   Will copy inner-1 to inner", 0, 4, false, true);
                    } else {
                        this.arrowText(var1, Color.black, "   No copy necessary", 0, 4, false, true);
                    }
                    break;
                case 3:
                    this.arrowText(var1, Color.black, "Will copy temp to inner", 0, 3, false, true);
            }
        } else {
            this.arrowText(var1, Color.red, "xxx", this.outer, 1, true, false);
            this.arrowText(var1, Color.blue, "xxx", this.inner, 2, true, false);
            this.arrowText(var1, Color.magenta, "xxx", this.aSize + 1, 3, true, false);
        }

        this.drawMode = 2;
    }

    public void sortStep() {
        switch(this.codePart) {
            case 1:
                this.theArray[this.aSize + 1] = this.theArray[this.outer];
                this.theArray[this.outer] = null;
                ++this.copies;
                this.innerOld = this.inner;
                this.inner = this.outer;
                this.codePart = 2;
                return;
            case 2:
                ++this.comps;
                if (this.inner > 0 && this.theArray[this.inner - 1].getHeight() >= this.theArray[this.aSize + 1].getHeight()) {
                    this.theArray[this.inner] = this.theArray[this.inner - 1];
                    this.theArray[this.inner - 1] = null;
                    ++this.copies;
                    this.innerOld = this.inner--;
                    return;
                }

                this.codePart = 3;
                return;
            case 3:
                this.theArray[this.inner] = this.theArray[this.aSize + 1];
                this.theArray[this.aSize + 1] = null;
                ++this.copies;
                this.outerOld = this.outer++;
                if (this.outer == this.aSize) {
                    this.doneFlag = true;
                }

                this.codePart = 1;
                return;
            default:
        }
    }
}
