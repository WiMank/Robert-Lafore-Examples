package chap7.quick_sort1;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.awt.Color;
import java.awt.Graphics;

class personGroup {
    private final int appletWidth = 370;
    private final int appletHeight = 300;
    private final int maxHeight = 200;
    private final int topMargin = 30;
    private final int leftMargin = 10;
    private final int barLeftMargin = 35;
    private final int textHeight = 13;
    private int aSize;
    private person[] theArray;
    private int barWidth;
    private int barSeparation;
    private boolean doneFlag;
    private int codePart;
    private int comps;
    private int swaps;
    private int initOrder;
    private int left;
    private int right;
    private int pivot;
    private int leftScan;
    private int rightScan;
    private int phonyLeft;
    private stack leftStack;
    private stack rightStack;
    private String note;
    private int drawMode;

    public personGroup(int var1, int var2) {
        this.aSize = var1;
        this.initOrder = var2;
        this.theArray = new person[this.aSize];
        if (this.aSize == 100) {
            this.barWidth = 2;
            this.barSeparation = 1;
        } else {
            this.barWidth = 18;
            this.barSeparation = 7;
        }

        this.comps = 0;
        this.swaps = 0;
        this.doneFlag = false;
        this.left = 0;
        this.right = this.aSize - 1;
        this.pivot = this.right;
        this.leftScan = this.left;
        this.rightScan = this.right - 1;
        this.codePart = 1;
        this.leftStack = new stack();
        this.rightStack = new stack();
        new Color(0, 0, 0);
        this.note = "Press any button";
        Color var3;
        int var4;
        int var5;
        int var6;
        int var7;
        if (this.initOrder == 1) {
            for(var4 = 0; var4 < this.aSize; ++var4) {
                var5 = (int)(Math.random() * 195.0D);
                var6 = (int)(Math.random() * 254.0D);
                var7 = (int)(Math.random() * 254.0D);
                int var8 = (int)(Math.random() * 254.0D);
                var3 = new Color(var6, var7, var8);
                this.theArray[var4] = new person(var5, var3);
            }
        } else {
            for(var4 = 0; var4 < this.aSize; ++var4) {
                var5 = 195 - 195 * var4 / this.aSize;
                var6 = 255 - var5;
                var7 = 85 * (var4 % 3);
                var3 = new Color(var6, var7, var5);
                this.theArray[var4] = new person(var5, var3);
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
        return 300;
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
        if (var2 >= 0 && var2 <= this.aSize - 1) {
            int var3 = this.theArray[var2].getHeight();
            int var4 = 35 + var2 * (this.barWidth + this.barSeparation);
            int var5 = 230 - var3;
            Color var6 = this.theArray[var2].getColor();
            var1.setColor(Color.lightGray);
            var1.fillRect(var4, 30, this.barWidth, 200);
            var1.setColor(var6);
            var1.fill3DRect(var4, var5, this.barWidth, var3, true);
            if (this.aSize == 12) {
                var1.setColor(Color.black);
                int var7 = var2 > 9 ? var4 + 2 : var4 + 5;
                var1.drawString(String.valueOf(var2), var7, 228);
            }

        }
    }

    public void drawHorizSubArrayLine(Graphics var1) {
        int var2 = 33 + this.leftScan * (this.barWidth + this.barSeparation);
        int var3 = 37 + (this.rightScan + 1) * (this.barWidth + this.barSeparation) + this.barWidth;
        int var4 = this.theArray[this.pivot].getHeight();
        int var5 = 230 - var4;
        var1.setColor(Color.black);
        var1.drawLine(var2, var5, var3, var5);
    }

    public void draw(Graphics var1) {
        int var2;
        if (this.drawMode != 2) {
            this.drawOneBar(var1, this.leftScan);
            this.drawOneBar(var1, this.rightScan);
            this.drawOneBar(var1, this.pivot);
        } else {
            var1.setColor(Color.lightGray);
            var1.fillRect(0, 0, 370, 300);

            for(var2 = 0; var2 < this.aSize; ++var2) {
                this.drawOneBar(var1, var2);
            }
        }

        if (this.codePart == 2) {
            this.drawHorizSubArrayLine(var1);
        }

        var1.setColor(Color.lightGray);
        var1.fillRect(0, 0, 140, 32);
        var1.setColor(Color.black);
        var1.drawString("Comparisons = " + this.comps, 10, 28);
        var1.drawString("Swaps = " + this.swaps, 10, 15);
        var1.setColor(Color.lightGray);
        var1.fillRect(0, 230, 370, 78);
        if (this.aSize == 12) {
            this.arrowText(var1, Color.red, "left", this.left, 1, true, true);
            this.arrowText(var1, Color.red, "right", this.right, 1, true, true);
            this.arrowText(var1, Color.blue, "leftScan", this.leftScan, 2, true, true);
            this.arrowText(var1, Color.blue, "rightScan", this.rightScan, 3, true, true);
            var2 = this.right - this.pivot < 2 ? 2 : 1;
            this.arrowText(var1, Color.magenta, "pivot", this.pivot, var2, true, true);
            this.arrowText(var1, Color.black, this.note, 0, 4, false, true);
        } else {
            this.arrowText(var1, Color.red, "xxx", this.left, 1, true, false);
            this.arrowText(var1, Color.red, "xxx", this.right, 1, true, false);
            this.arrowText(var1, Color.blue, "xxx", this.leftScan, 2, true, false);
            this.arrowText(var1, Color.blue, "xxx", this.rightScan, 2, true, false);
            this.arrowText(var1, Color.magenta, "xxx", this.pivot, 3, true, false);
        }

        this.drawMode = 2;
    }

    public void sortStep() {
        if (!this.doneFlag) {
            switch(this.codePart) {
                case 1:
                    int var1 = this.right - this.left + 1;
                    if (var1 <= 1) {
                        this.note = "Entering quickSort(); partition (" + this.left + "-" + this.right + ") too small to sort";
                        this.codePart = 7;
                        return;
                    }

                    this.pivot = this.right;
                    this.note = "Entering quickSort(); will partition (" + this.left + "-" + this.right + ")";
                    this.codePart = 2;
                    return;
                case 2:
                    this.leftScan = this.left - 1;
                    this.rightScan = this.right;
                    this.note = "leftScan=" + this.leftScan + ", rightScan=" + this.rightScan + "; will scan";
                    this.codePart = 3;
                    return;
                case 3:
                    ++this.comps;

                    while(this.theArray[++this.leftScan].getHeight() < this.theArray[this.pivot].getHeight()) {
                        ++this.comps;
                    }

                    if (this.rightScan > this.left) {
                        ++this.comps;
                    }

                    while(this.rightScan > this.left && this.theArray[--this.rightScan].getHeight() > this.theArray[this.pivot].getHeight()) {
                        if (this.rightScan > this.left) {
                            ++this.comps;
                        }
                    }

                    if (this.leftScan >= this.rightScan) {
                        this.note = "Scans have met; will swap pivot and leftScan";
                        this.codePart = 5;
                        return;
                    } else {
                        this.note = "Will swap leftScan and rightScan";
                        this.codePart = 4;
                        return;
                    }
                case 4:
                    this.swap(this.leftScan, this.rightScan);
                    this.note = "Will scan again";
                    this.codePart = 3;
                    return;
                case 5:
                    this.swap(this.leftScan, this.pivot);
                    this.codePart = 6;
                    this.note = "Array partitioned: left (" + this.left + "-" + (this.leftScan - 1) + "), right (" + (this.leftScan + 1) + "-" + this.right + ")";
                    return;
                case 6:
                    this.leftStack.push(this.leftScan + 1);
                    this.rightStack.push(this.right);
                    this.note = "Will sort left partition (" + this.left + "-" + (this.leftScan - 1) + ")";
                    this.right = this.leftScan - 1;
                    this.leftScan = this.left;
                    this.rightScan = this.right - 1;
                    this.pivot = this.right;
                    this.codePart = 1;
                    return;
                case 7:
                    if (this.leftStack.isEmpty()) {
                        this.doneFlag = true;
                        this.note = "Sort is complete";
                        this.codePart = 8;
                        return;
                    }

                    this.left = this.leftStack.pop();
                    this.right = this.rightStack.pop();
                    this.leftScan = this.left;
                    this.rightScan = this.right - 1;
                    this.pivot = this.right;
                    this.note = "Will sort right partition (" + this.left + "-" + this.right + ")";
                    this.codePart = 1;
                    return;
                case 8:
                    this.codePart = 8;
                    this.note = "Press any button";
                    return;
                default:
            }
        }
    }

    public void swap(int var1, int var2) {
        person var3 = this.theArray[var1];
        this.theArray[var1] = this.theArray[var2];
        this.theArray[var2] = var3;
        ++this.swaps;
    }
}
