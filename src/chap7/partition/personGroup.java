package chap7.partition;

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
    private int oldCodePart;
    private int comps;
    private int swaps;
    private int initOrder;
    private int center;
    private int pivot;
    private int leftScan;
    private int rightScan;
    private int partition;
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
        this.leftScan = -1;
        this.rightScan = this.aSize;
        this.codePart = 1;
        new Color(0, 0, 0);
        this.note = "Press any button";
        Color var3;
        int var4;
        int var5;
        int var6;
        int var7;
        if (this.initOrder == 1) {
            for(var4 = 0; var4 < this.aSize; ++var4) {
                var5 = (int)(Math.random() * 199.0D);
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
        if (var4 >= 0 && var4 <= this.aSize - 1) {
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
        }
    }

    public void draw(Graphics var1) {
        int var2;
        if (this.drawMode != 2) {
            this.drawOneBar(var1, this.leftScan);
            this.drawOneBar(var1, this.rightScan);
        } else {
            var1.setColor(Color.lightGray);
            var1.fillRect(0, 0, 370, 300);

            for(var2 = 0; var2 < this.aSize; ++var2) {
                this.drawOneBar(var1, var2);
            }
        }

        var1.setColor(Color.black);
        var2 = 230 - this.pivot;
        var1.drawLine(5, var2, 350, var2);
        var1.setColor(Color.lightGray);
        var1.fillRect(0, 0, 120, 32);
        var1.setColor(Color.black);
        var1.drawString("Comparisons = " + this.comps, 10, 28);
        var1.drawString("Swaps = " + this.swaps, 10, 15);
        var1.setColor(Color.lightGray);
        var1.fillRect(0, 230, 370, 78);
        if (this.aSize == 12) {
            if (this.oldCodePart != 7 && this.oldCodePart != 8) {
                this.arrowText(var1, Color.blue, "leftScan", this.leftScan, 2, true, true);
                this.arrowText(var1, Color.blue, "rightScan", this.rightScan, 3, true, true);
            } else {
                this.arrowText(var1, Color.magenta, "partition", this.partition, 1, true, true);
            }

            this.arrowText(var1, Color.black, this.note, 0, 4, false, true);
        } else if (this.oldCodePart != 7 && this.oldCodePart != 8) {
            this.arrowText(var1, Color.blue, "xxx", this.leftScan, 2, true, false);
            this.arrowText(var1, Color.blue, "xxx", this.rightScan, 2, true, false);
        } else {
            this.arrowText(var1, Color.magenta, "xxx", this.partition, 3, true, false);
        }

        this.drawMode = 2;
    }

    public void partStep() {
        if (!this.doneFlag) {
            switch(this.codePart) {
                case 1:
                    this.pivot = 70 + (int)(Math.random() * 60.0D);
                    this.note = "Pivot value is " + this.pivot;
                    this.oldCodePart = this.codePart;
                    this.codePart = 3;
                    return;
                case 2:
                default:
                    return;
                case 3:
                    this.note = "Will scan from left";
                    this.oldCodePart = this.codePart;
                    this.codePart = 4;
                    return;
                case 4:
                    this.oldCodePart = this.codePart;
                    ++this.comps;
                    if (this.theArray[++this.leftScan].getHeight() < this.pivot) {
                        this.note = "Continuing left scan";
                        this.codePart = 4;
                        return;
                    } else {
                        if (this.leftScan >= this.rightScan) {
                            this.note = "Scans have met";
                            this.codePart = 7;
                            return;
                        }

                        this.note = "Will scan from right";
                        this.codePart = 5;
                        return;
                    }
                case 5:
                    this.oldCodePart = this.codePart;
                    ++this.comps;
                    if (this.theArray[--this.rightScan].getHeight() > this.pivot) {
                        this.note = "Continuing right scan";
                        this.codePart = 5;
                        return;
                    } else {
                        if (this.leftScan >= this.rightScan) {
                            this.note = "Scans have met";
                            this.codePart = 7;
                            return;
                        }

                        this.note = "Will swap leftScan and rightScan";
                        this.codePart = 6;
                        return;
                    }
                case 6:
                    this.swap(this.leftScan, this.rightScan);
                    this.note = "Will scan again from left";
                    this.oldCodePart = this.codePart;
                    this.codePart = 4;
                    return;
                case 7:
                    this.partition = this.leftScan;
                    this.note = "Arrow shows partition";
                    this.oldCodePart = this.codePart;
                    this.codePart = 8;
                    return;
                case 8:
                    this.doneFlag = true;
                    this.leftScan = 0;
                    this.rightScan = this.aSize - 1;
                    this.note = "Partition is complete";
                    this.oldCodePart = this.codePart;
                    this.codePart = 1;
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
