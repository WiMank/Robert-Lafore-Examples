package chap6.merge_sort;

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
    private person[] workSpace;
    private int barWidth;
    private int barSeparation;
    private boolean doneFlag;
    private int codePart;
    private int drawMode;
    private int oldCodePart;
    private int comps;
    private int copies;
    private int initOrder;
    private String note;
    private stack theStack;
    private params theseParams;
    private int lower;
    private int upper;
    private int mid;
    private int lowPtrM;
    private int highPtrM;
    private int upperBoundM;
    private int lowerBoundM;
    private int jM;
    private int midM;
    private int nM;
    private boolean mergingFlag;

    public personGroup(int var1, int var2) {
        this.aSize = var1;
        this.initOrder = var2;
        this.theArray = new person[this.aSize];
        this.workSpace = new person[this.aSize * 2];
        if (this.aSize == 100) {
            this.barWidth = 2;
            this.barSeparation = 1;
        } else {
            this.barWidth = 18;
            this.barSeparation = 7;
        }

        this.comps = 0;
        this.copies = 0;
        this.doneFlag = false;
        this.mergingFlag = false;
        this.codePart = 1;
        new Color(0, 0, 0);
        this.note = "Press any button";
        this.theStack = new stack(this.aSize);
        this.drawMode = 2;
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
    }

    public boolean getDone() {
        return this.doneFlag;
    }

    public int getAppletWidth() {
        return 370;
    }

    public int getAppletHeight() {
        return 300;
    }

    public void setDrawMode(int var1) {
        this.drawMode = var1;
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
        if (var2 >= 0 && var2 < this.aSize) {
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
                int var7 = var2 > 9 ? var4 : var4 + 5;
                var1.drawString(String.valueOf(var2), var7, 228);
            }

        }
    }

    public void draw(Graphics var1) {
        int var2;
        if (this.drawMode == 2) {
            var1.setColor(Color.lightGray);
            var1.fillRect(0, 0, 370, 300);

            for(var2 = 0; var2 < this.aSize; ++var2) {
                this.drawOneBar(var1, var2);
            }
        } else if (this.drawMode == 1) {
            this.drawOneBar(var1, this.lowerBoundM + this.jM - 1);
        }

        var1.setColor(Color.lightGray);
        var1.fillRect(0, 0, 120, 32);
        var1.setColor(Color.black);
        var1.drawString("Comparisons = " + this.comps, 10, 28);
        var1.drawString("Copies = " + this.copies, 10, 15);
        var1.setColor(Color.lightGray);
        var1.fillRect(0, 230, 370, 78);
        if (this.aSize == 12) {
            this.arrowText(var1, Color.red, "lower", this.lower, 1, true, true);
            this.arrowText(var1, Color.red, "upper", this.upper, 2, true, true);
            var2 = this.lowerBoundM + this.jM - 1;
            if (this.oldCodePart == 9) {
                this.arrowText(var1, Color.magenta, "ptr", this.lowerBoundM, 3, true, true);
            } else if (this.oldCodePart == 10) {
                this.arrowText(var1, Color.magenta, "ptr", var2, 3, true, true);
            } else {
                this.arrowText(var1, Color.blue, "mid", this.mid, 3, true, true);
            }

            this.arrowText(var1, Color.black, this.note, 0, 4, false, true);
        } else {
            this.arrowText(var1, Color.red, "xxx", this.lower, 1, true, false);
            this.arrowText(var1, Color.red, "xxx", this.upper, 2, true, false);
            this.arrowText(var1, Color.blue, "xxx", this.mid, 3, true, false);
        }

        this.drawMode = 2;
    }

    public void sortStep() {
        switch(this.codePart) {
            case 1:
                this.theseParams = new params(0, this.aSize - 1, 0, 8);
                this.theStack.push(this.theseParams);
                this.note = "Initial call to mergeSort";
                this.drawMode = 0;
                this.oldCodePart = this.codePart;
                this.codePart = 2;
                return;
            case 2:
                this.theseParams = this.theStack.peek();
                this.lower = this.theseParams.lower;
                this.upper = this.theseParams.upper;
                this.note = "Entering mergeSort: " + this.lower + "-" + this.upper;
                this.oldCodePart = this.codePart;
                if (this.lower == this.upper) {
                    this.codePart = 7;
                } else {
                    this.codePart = 4;
                }

                this.drawMode = 0;
                return;
            case 3:
            default:
                return;
            case 4:
                this.mid = (this.lower + this.upper) / 2;
                this.note = "Will sort lower half: " + this.lower + "-" + this.mid;
                params var1 = new params(this.lower, this.mid, this.mid, 5);
                this.theStack.push(var1);
                this.drawMode = 0;
                this.oldCodePart = this.codePart;
                this.codePart = 2;
                return;
            case 5:
                this.theseParams = this.theStack.peek();
                this.lower = this.theseParams.lower;
                this.upper = this.theseParams.upper;
                this.mid = (this.lower + this.upper) / 2;
                this.note = "Will sort upper half: " + (this.mid + 1) + "-" + this.upper;
                params var2 = new params(this.mid + 1, this.upper, this.mid, 6);
                this.theStack.push(var2);
                this.drawMode = 0;
                this.oldCodePart = this.codePart;
                this.codePart = 2;
                return;
            case 6:
                this.theseParams = this.theStack.peek();
                this.lower = this.theseParams.lower;
                this.upper = this.theseParams.upper;
                this.mid = (this.lower + this.upper) / 2;
                this.note = "Will merge ranges";
                this.lowPtrM = this.lower;
                this.highPtrM = this.mid + 1;
                this.upperBoundM = this.upper;
                this.drawMode = 0;
                this.oldCodePart = this.codePart;
                this.codePart = 9;
                return;
            case 7:
                this.oldCodePart = this.codePart;
                this.codePart = this.theseParams.codePart;
                this.theStack.pop();
                if (!this.theStack.isEmpty()) {
                    this.theseParams = this.theStack.peek();
                    this.note = "Exiting mergeSort: " + this.lower + "-" + this.upper;
                } else {
                    this.note = "Exciting mergeSort; sort is complete";
                }

                this.drawMode = 0;
                return;
            case 8:
                this.doneFlag = true;
                this.note = "Sort is complete; Press New or Size";
                this.drawMode = 0;
                this.oldCodePart = this.codePart;
                this.codePart = 1;
                return;
            case 9:
                this.jM = 0;
                this.lowerBoundM = this.lowPtrM;
                this.midM = this.highPtrM - 1;
                this.nM = this.upperBoundM - this.lowerBoundM + 1;
                this.note = "Merged " + this.lowPtrM + "-" + this.midM + " and " + this.highPtrM + "-" + this.upperBoundM + " into workspace";

                while(this.lowPtrM <= this.midM && this.highPtrM <= this.upperBoundM) {
                    ++this.comps;
                    ++this.copies;
                    if (this.theArray[this.lowPtrM].getHeight() < this.theArray[this.highPtrM].getHeight()) {
                        this.workSpace[this.jM++] = this.theArray[this.lowPtrM++];
                    } else {
                        this.workSpace[this.jM++] = this.theArray[this.highPtrM++];
                    }
                }

                while(this.lowPtrM <= this.midM) {
                    ++this.copies;
                    this.workSpace[this.jM++] = this.theArray[this.lowPtrM++];
                }

                while(this.highPtrM <= this.upperBoundM) {
                    ++this.copies;
                    this.workSpace[this.jM++] = this.theArray[this.highPtrM++];
                }

                this.mergingFlag = true;
                this.jM = 0;
                this.oldCodePart = this.codePart;
                this.codePart = 10;
                this.drawMode = 0;
                return;
            case 10:
                this.oldCodePart = this.codePart;
                if (this.jM == this.nM) {
                    this.note = "Merge completed";
                    this.codePart = 7;
                    this.drawMode = 0;
                } else {
                    ++this.copies;
                    this.theArray[this.lowerBoundM + this.jM] = this.workSpace[this.jM];
                    this.note = "Copied workspace into " + (this.lowerBoundM + this.jM);
                    ++this.jM;
                    this.codePart = 10;
                    this.drawMode = 1;
                }
        }
    }
}
