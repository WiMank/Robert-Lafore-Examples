package chap6.towers;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.awt.Color;
import java.awt.Graphics;

class gameGroup {
    private final int maxDiskWidth = 120;
    private final int appletWidth = 440;
    private final int appletHeight = 325;
    private final int topMargin = 70;
    private final int leftMargin = 10;
    private final int textHeight = 13;
    private final int xTowerA = 80;
    private final int xTowerB = 220;
    private final int xTowerC = 360;
    private final int noteBoxTop = 45;
    private final int noteBoxHeight = 25;
    private final int noteBoxWidth = 430;
    private final int drawingBoxHeight = 229;
    private String note;
    private int opMode;
    private int codePart;
    private int drawMode;
    private boolean diskMoved;
    private int ddCode;
    private tower[] towerArray;
    private int nDisks;
    private int widthFactor;
    private stack theStack;
    private params theseParams;
    private int n;
    private int from;
    private int to;
    private int inter;
    private boolean isDoneFlag;

    public gameGroup(int var1) {
        this.nDisks = var1;
        this.widthFactor = 120 / this.nDisks;
        this.towerArray = new tower[3];
        this.towerArray[0] = new tower(80, 'A', this.nDisks);
        this.towerArray[1] = new tower(220, 'B', this.nDisks);
        this.towerArray[2] = new tower(360, 'C', this.nDisks);

        for(int var2 = 0; var2 < this.nDisks; ++var2) {
            int var3 = 120 - var2 * this.widthFactor;
            int var4 = 75 + (int)(Math.random() * 180.0D);
            int var5 = 75 + (int)(Math.random() * 180.0D);
            int var6 = 75 + (int)(Math.random() * 180.0D);
            Color var7 = new Color(var4, var5, var6);
            disk var8 = new disk(var3, var7, String.valueOf(this.nDisks - var2));
            this.towerArray[0].insertDisk(var8);
        }

        this.theStack = new stack(this.nDisks);
        this.note = "Press any button, or drag disk to another post";
        this.diskMoved = false;
        this.drawMode = 2;
    }

    public void creationError() {
        this.note = "Before using New, enter number of disks (1 to 10)";
        this.drawMode = 1;
    }

    public boolean isDone() {
        return this.isDoneFlag;
    }

    public void setDone(boolean var1) {
        this.isDoneFlag = var1;
    }

    public void startDrag(int var1, int var2) {
        this.diskMoved = false;
        this.from = this.closeTo(var1, var2);
        if (this.from == -1) {
            this.note = "DRAG the CENTER of the disk";
        } else if (this.towerArray[this.from].isEmpty()) {
            this.note = "NO DISKS on tower " + this.towerArray[this.from].label;
            this.from = -1;
        } else {
            this.note = "Dragging from tower " + this.towerArray[this.from].label;
            this.drawMode = 1;
        }
    }

    public void endDrag(int var1, int var2) {
        this.diskMoved = false;
        this.to = this.closeTo(var1, var2);
        if (this.from != -1 && this.to != -1 && this.from != this.to) {
            this.note = "Dragged to tower " + this.towerArray[this.to].label;
            if (!this.towerArray[this.to].isEmpty() && this.towerArray[this.from].peekDisk().width > this.towerArray[this.to].peekDisk().width) {
                this.note = "Must put a SMALLER disk ON a LARGER disk";
            } else {
                disk var3 = this.towerArray[this.from].removeDisk();
                this.towerArray[this.to].insertDisk(var3);
                this.diskMoved = true;
                this.note = "Moved disk " + var3.label + " from " + this.towerArray[this.from].label + " to " + this.towerArray[this.to].label;
                if (this.towerArray[2].isFull()) {
                    this.note = "Congratulations! You moved all the disks!";
                } else {
                    this.drawMode = 1;
                }
            }
        } else {
            this.note = "Drag a colored DISK to a different black TOWER";
        }
    }

    public int closeTo(int var1, int var2) {
        byte var3 = 35;
        if (Math.abs(var1 - 80) < var3) {
            return 0;
        } else if (Math.abs(var1 - 220) < var3) {
            return 1;
        } else {
            return Math.abs(var1 - 360) < var3 ? 2 : -1;
        }
    }

    public void step() {
        this.diskMoved = false;
        if (this.opMode != 1) {
            this.opMode = 1;
            this.codePart = 1;
        }

        disk var1;
        switch(this.codePart) {
            case 1:
                if (!this.towerArray[0].isEmpty() && this.towerArray[1].isEmpty() && this.towerArray[2].isEmpty()) {
                    this.note = "Will shift all disks from A to C";
                    this.theseParams = new params(this.nDisks, 0, 2, 1, 8);
                    this.theStack.push(this.theseParams);
                    this.codePart = 2;
                } else {
                    this.note = "You must begin with ALL DISKS ON TOWER A";
                    this.codePart = 1;
                }
                break;
            case 2:
                this.theseParams = this.theStack.peek();
                this.n = this.theseParams.n;
                this.from = this.theseParams.from;
                this.to = this.theseParams.to;
                this.inter = this.theseParams.inter;
                this.note = "Entering function with " + this.n + " disks";
                this.codePart = 3;
                break;
            case 3:
                if (this.n == 1) {
                    var1 = this.towerArray[this.from].removeDisk();
                    this.towerArray[this.to].insertDisk(var1);
                    this.diskMoved = true;
                    this.note = "Moved last disk " + var1.label + " from " + this.towerArray[this.from].label + " to " + this.towerArray[this.to].label;
                    if (this.towerArray[2].isFull()) {
                        this.note = "Congratulations! You moved all the disks!";
                    }

                    this.codePart = 7;
                } else {
                    this.note = "More than one disk, will continue";
                    this.codePart = 4;
                }
                break;
            case 4:
                this.note = "Will move top " + (this.n - 1) + " disks from " + this.towerArray[this.from].label + " to " + this.towerArray[this.inter].label;
                this.theseParams = new params(this.n - 1, this.from, this.inter, this.to, 5);
                this.theStack.push(this.theseParams);
                this.codePart = 2;
                break;
            case 5:
                var1 = this.towerArray[this.from].removeDisk();
                this.towerArray[this.to].insertDisk(var1);
                this.diskMoved = true;
                this.note = "Moved remaining disk " + this.n + " from " + this.towerArray[this.from].label + " to " + this.towerArray[this.to].label;
                this.codePart = 6;
                break;
            case 6:
                this.note = "Will move top " + (this.n - 1) + " disks from " + this.towerArray[this.inter].label + " to " + this.towerArray[this.to].label;
                this.theseParams = new params(this.n - 1, this.inter, this.to, this.from, 7);
                this.theStack.push(this.theseParams);
                this.codePart = 2;
                break;
            case 7:
                int var2 = this.n;
                this.codePart = this.theseParams.codePart;
                this.theStack.pop();
                if (!this.theStack.isEmpty()) {
                    this.theseParams = this.theStack.peek();
                    this.n = this.theseParams.n;
                    this.from = this.theseParams.from;
                    this.to = this.theseParams.to;
                    this.inter = this.theseParams.inter;
                }

                this.note = "Returning from function with " + var2 + " disks";
                break;
            case 8:
                this.note = "Press New to reset";
                this.isDoneFlag = true;
                this.codePart = 1;
        }

        this.drawMode = 1;
    }

    public void warningNew() {
        this.note = "ARE YOU SURE? Press again to reset game";
    }

    public void draw(Graphics var1) {
        int var2;
        if (this.drawMode == 2) {
            var1.setColor(Color.lightGray);
            var1.fillRect(0, 0, 440, 325);
            var1.setColor(Color.black);
            var1.drawRect(2, 71, 436, 229);

            for(var2 = 0; var2 < 3; ++var2) {
                this.towerArray[var2].drawTower(var1, this.drawMode, 0);
            }
        } else {
            for(var2 = 0; var2 < 3; ++var2) {
                if (this.diskMoved && var2 == this.from) {
                    this.ddCode = 1;
                    this.towerArray[var2].drawTower(var1, this.drawMode, this.ddCode);
                } else if (this.diskMoved && var2 == this.to) {
                    this.ddCode = 2;
                    this.towerArray[var2].drawTower(var1, this.drawMode, this.ddCode);
                }
            }
        }

        var1.setColor(Color.lightGray);
        var1.fillRect(10, 45, 430, 25);
        var1.setColor(Color.black);
        var1.drawString(this.note, 16, 64);
        this.drawMode = 2;
    }
}
