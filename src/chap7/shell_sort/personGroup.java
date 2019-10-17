package chap7.shell_sort;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.awt.Color;
import java.awt.Graphics;

class personGroup {
    private final int appletWidth = 370;
    private final int appletHeight = 320;
    private final int maxHeight = 200;
    private final int topMargin = 30;
    private final int leftMargin = 10;
    private final int barLeftMargin = 35;
    private final int textHeight = 13;
    private int aSize;
    private person[] theArray;
    private int barWidth;
    private int barSeparation;
    private int Outer;
    private int Inner;
    private int outerOld;
    private int innerOld;
    private int h;
    private boolean doneFlag;
    private int codePart;
    private int comps;
    private int copies;
    private int initOrder;
    private String note;
    private int drawMode;

    public personGroup(int var1, int var2) {
        this.aSize = var1;
        this.initOrder = var2;
        this.theArray = new person[this.aSize + 2];
        if (this.aSize == 100) {
            this.barWidth = 2;
            this.barSeparation = 1;
        } else {
            this.barWidth = 18;
            this.barSeparation = 7;
        }

        for(this.h = 1; this.h <= this.aSize / 3; this.h = this.h * 3 + 1) {
        }

        this.Inner = this.Outer = this.h;
        this.innerOld = this.outerOld = this.h;
        this.comps = 0;
        this.copies = 0;
        this.doneFlag = false;
        this.note = "Press any button";
        this.codePart = 1;
        Color var3 = new Color(0, 0, 0);
        int var4;
        int var5;
        int var6;
        int var7;
        if (this.initOrder == 1) {
            for(var4 = 0; var4 < this.aSize; ++var4) {
                var5 = 20 + (int)(Math.random() * 175.0D);
                var6 = (int)(Math.random() * 254.0D);
                var7 = (int)(Math.random() * 254.0D);
                int var8 = (int)(Math.random() * 254.0D);
                var3 = new Color(var6, var7, var8);
                this.theArray[var4] = new person(var5, var3);
            }
        } else {
            for(var4 = 0; var4 < this.aSize; ++var4) {
                var5 = 195 - 175 * var4 / this.aSize;
                var6 = 255 - var5;
                var7 = 85 * (var4 % 3);
                var3 = new Color(var6, var7, var5);
                this.theArray[var4] = new person(var5, var3);
            }
        }

        this.theArray[this.aSize + 1] = new person(0, var3);
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
        } else if (var2 >= 0 && var2 <= this.aSize + 1) {
            var3 = this.theArray[var2].getHeight();
            if (var3 >= 5) {
                int var4 = 35 + var2 * (this.barWidth + this.barSeparation);
                int var5 = 230 - var3;
                Color var6 = this.theArray[var2].getColor();
                var1.setColor(Color.lightGray);
                var1.fillRect(var4, 30, this.barWidth, 200);
                var1.setColor(var6);
                var1.fill3DRect(var4, var5, this.barWidth, var3, true);
            }
        }
    }

    public void draw(Graphics var1) {
        if (this.drawMode != 2) {
            switch(this.codePart) {
                case 2:
                case 5:
                    this.drawOneBar(var1, this.innerOld);
                    this.drawOneBar(var1, this.Inner);
                    this.drawOneBar(var1, this.aSize + 1);
                    break;
                case 3:
                case 6:
                    this.drawOneBar(var1, this.aSize + 1);
                    this.drawOneBar(var1, this.innerOld);
                    this.drawOneBar(var1, this.Inner);
                case 4:
            }
        } else {
            var1.setColor(Color.lightGray);
            var1.fillRect(0, 0, 370, 320);

            for(int var2 = 0; var2 < this.aSize; ++var2) {
                this.drawOneBar(var1, var2);
            }

            this.drawOneBar(var1, this.aSize + 1);
            this.arrowText(var1, Color.magenta, "xxx", this.aSize + 1, 3, true, false);
        }

        var1.setColor(Color.lightGray);
        var1.fillRect(0, 0, 120, 32);
        var1.setColor(Color.black);
        var1.drawString("Comparisons = " + this.comps, 10, 28);
        var1.drawString("Copies = " + this.copies, 10, 15);
        var1.setColor(Color.lightGray);
        var1.fillRect(0, 230, 370, 78);
        this.arrowText(var1, Color.black, "h=" + this.h, 8, 5, false, true);
        if (this.aSize == 10) {
            this.arrowText(var1, Color.red, "outer", this.Outer, 1, true, true);
            this.arrowText(var1, Color.blue, "inner", this.Inner, 2, true, true);
            if (this.Inner - this.h >= 0) {
                this.arrowText(var1, Color.blue, "inner-h", this.Inner - this.h, 3, true, true);
            }

            this.arrowText(var1, Color.magenta, "temp", this.aSize + 1, 1, true, true);
            this.arrowText(var1, Color.black, this.note, 0, 4, false, true);
        } else {
            this.arrowText(var1, Color.red, "xxx", this.Outer, 1, true, false);
            this.arrowText(var1, Color.blue, "xxx", this.Inner, 2, true, false);
            this.arrowText(var1, Color.blue, "xxx", this.Inner - this.h, 2, true, false);
        }

        this.drawMode = 2;
    }

    public void sortStep() {
        if (!this.doneFlag) {
            switch(this.codePart) {
                case 1:
                    this.note = this.h + "-sorting array; will copy outer to temp";
                    this.codePart = 2;
                    return;
                case 2:
                    ++this.copies;
                    this.theArray[this.aSize + 1] = this.theArray[this.Outer];
                    this.theArray[this.Outer] = null;
                    this.innerOld = this.Inner;
                    this.Inner = this.Outer;
                    if (this.Inner > this.h - 1) {
                        this.note = "Will compare inner-h and temp";
                    } else {
                        this.note = "There is no inner-h";
                    }

                    this.codePart = 3;
                    return;
                case 3:
                    if (this.Inner > this.h - 1) {
                        ++this.comps;
                        if (this.theArray[this.Inner - this.h].getHeight() >= this.theArray[this.aSize + 1].getHeight()) {
                            this.note = "inner-h >= temp; will copy inner-h to inner";
                            this.codePart = 4;
                            return;
                        }

                        this.note = "inner-h < temp; will copy temp to inner";
                        this.codePart = 5;
                        return;
                    }

                    this.note = "Will copy temp to inner";
                    this.codePart = 5;
                    return;
                case 4:
                    this.theArray[this.Inner] = this.theArray[this.Inner - this.h];
                    this.theArray[this.Inner - this.h] = null;
                    ++this.copies;
                    this.innerOld = this.Inner;
                    this.Inner -= this.h;
                    if (this.Inner > this.h - 1) {
                        this.note = "Will compare inner-h and temp";
                    } else {
                        this.note = "There is no inner-h";
                    }

                    this.codePart = 3;
                    return;
                case 5:
                    this.theArray[this.Inner] = this.theArray[this.aSize + 1];
                    this.theArray[this.aSize + 1] = null;
                    ++this.copies;
                    this.outerOld = this.Outer++;
                    if (this.Outer < this.aSize) {
                        this.note = "Will copy outer to temp";
                        this.codePart = 2;
                        return;
                    } else {
                        this.h = (this.h - 1) / 3;
                        if (this.h > 0) {
                            this.Inner = this.Outer = this.h;
                            this.note = this.h + "-sorting array. Will copy outer to temp";
                            this.codePart = 2;
                            return;
                        }

                        this.note = "Sort is complete";
                        this.codePart = 6;
                        return;
                    }
                case 6:
                    this.doneFlag = true;
                    this.note = "Sort is complete";
                    this.codePart = 6;
                    return;
                default:
            }
        }
    }
}
