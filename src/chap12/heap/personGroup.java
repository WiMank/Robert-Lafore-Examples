package chap12.heap;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.awt.Color;
import java.awt.Graphics;

class personGroup {
    private final int appletWidth = 440;
    private final int appletHeight = 300;
    private final int maxHeight = 200;
    private final int topMargin = 70;
    private final int leftMargin = 10;
    private final int textHeight = 13;
    private final int nodeDiameter = 20;
    private final int levelSeparation = 40;
    private final int horizSeparation = 26;
    private final int noteBoxTop = 45;
    private final int noteBoxHeight = 25;
    private final int noteBoxWidth = 250;
    private final int ASIZE = 31;
    private final int MAX_KEY = 99;
    private person[] treeArray = new person[31];
    private int nNodes;
    private String note;
    private int value;
    private int codePart = 1;
    private int opMode;
    private int curIn;
    private int curInOld;
    private int oldArrow;
    private boolean drawAll;
    private int drawMode = 3;
    private person tempNode;
    private person blankNode;
    private int largerChild;
    private person largestNode;
    private int oldChild;
    private int oldHeight;

    public personGroup() {
        for(int var1 = 0; var1 < 31; ++var1) {
            this.treeArray[var1] = null;
        }

        this.nNodes = 0;
        this.note = "Press a button";
        this.blankNode = new person(-1, Color.lightGray);
    }

    public person makePerson(int var1) {
        int var2 = 100 + (int)(Math.random() * 154.0D);
        int var3 = 100 + (int)(Math.random() * 154.0D);
        int var4 = 100 + (int)(Math.random() * 154.0D);
        Color var5 = new Color(var2, var3, var4);
        return new person(var1, var5);
    }

    public void setDrawAll(boolean var1) {
        this.drawAll = var1;
    }

    public void fill(boolean var1, int var2) {
        if (this.opMode != 1) {
            this.opMode = 1;
            this.codePart = 1;
        }

        switch(this.codePart) {
            case 1:
                this.note = "Enter number of nodes (1 to 31)";
                this.curIn = 0;
                this.oldArrow = this.curInOld;
                this.curInOld = 0;
                this.drawMode = 0;
                this.codePart = 2;
                return;
            case 2:
                if (var1 && var2 >= 0 && var2 <= 31) {
                    this.note = "Will create heap of " + var2 + " nodes";
                    this.codePart = 3;
                    return;
                }

                this.note = "Inappropriate number of nodes";
                this.codePart = 1;
                return;
            case 3:
                this.doFill(var2);
                this.drawMode = 3;
                this.note = "Press any button";
                this.oldArrow = this.curInOld;
                this.curInOld = 0;
                this.codePart = 1;
                return;
            default:
        }
    }

    public void doFill(int var1) {
        byte var2 = 0;

        for(int var4 = 0; var4 < 31; ++var4) {
            this.treeArray[var4] = null;
        }

        this.nNodes = 0;
        boolean[] var5 = new boolean[100];

        for(int var6 = 0; var6 < 100; ++var6) {
            var5[var6] = false;
        }

        while(this.nNodes < var1 && var2 < 100) {
            int var3;
            do {
                var3 = (int)(Math.random() * 99.0D);
            } while(var5[var3]);

            var5[var3] = true;
            person var7 = this.makePerson(var3);
            this.treeArray[this.nNodes] = var7;
            this.trickleUp(this.nNodes);
            ++this.nNodes;
        }

    }

    public void trickleUp(int var1) {
        for(this.tempNode = this.treeArray[var1]; var1 > 0 && this.treeArray[(var1 - 1) / 2].getHeight() < this.tempNode.getHeight(); var1 = (var1 - 1) / 2) {
            this.treeArray[var1] = this.treeArray[(var1 - 1) / 2];
        }

        this.treeArray[var1] = this.tempNode;
    }

    public void insert(boolean var1, int var2) {
        if (this.opMode != 3) {
            this.opMode = 3;
            this.codePart = 1;
        }

        switch(this.codePart) {
            case 1:
                this.curIn = 0;
                this.oldArrow = this.curInOld;
                this.curInOld = 0;
                if (this.nNodes > 30) {
                    this.note = "Can't insert; no room in display";
                    this.codePart = 10;
                } else {
                    this.note = "Enter value of node to insert";
                    this.codePart = 2;
                }

                this.drawMode = 0;
                return;
            case 2:
                this.value = var2;
                if (var1 && var2 >= 0 && var2 <= 99) {
                    this.note = "Will insert node with key " + this.value;
                    this.codePart = 3;
                } else {
                    this.note = "Nodes have values from 0 to 99";
                    this.codePart = 1;
                }

                this.drawMode = 0;
                return;
            case 3:
                this.oldArrow = this.curInOld;
                this.curIn = this.curInOld = this.nNodes;
                this.treeArray[this.curIn] = this.makePerson(this.value);
                this.note = "Placed node in first empty cell";
                this.codePart = 4;
                this.drawMode = 1;
                return;
            case 4:
                this.tempNode = this.treeArray[this.curIn];
                this.treeArray[this.curIn] = this.blankNode;
                this.note = "Saved new node; will trickle up";
                this.codePart = 5;
                this.drawMode = 1;
                return;
            case 5:
                if (this.curIn > 0 && this.treeArray[(this.curIn - 1) / 2].getHeight() < this.tempNode.getHeight()) {
                    this.treeArray[this.curIn] = this.treeArray[(this.curIn - 1) / 2];
                    this.treeArray[(this.curIn - 1) / 2] = this.blankNode;
                    this.oldArrow = this.curInOld;
                    this.oldChild = this.curIn;
                    this.curInOld = this.curIn = (this.curIn - 1) / 2;
                    this.note = "Moved empty node up";
                    this.codePart = 5;
                    this.drawMode = 2;
                    return;
                }

                this.note = "Trickle-up completed";
                this.codePart = 6;
                this.drawMode = 0;
                return;
            case 6:
                this.treeArray[this.curIn] = this.tempNode;
                this.note = "Inserted new node in empty node";
                this.codePart = 9;
                this.drawMode = 1;
                return;
            case 7:
            case 8:
            default:
                return;
            case 9:
                ++this.nNodes;
                this.note = "Insert completed; node count is " + this.nNodes;
                this.drawMode = 0;
                this.codePart = 10;
                return;
            case 10:
                this.note = "Press any button";
                this.oldArrow = this.curInOld;
                this.curInOld = 0;
                this.codePart = 1;
        }
    }

    public void remove() {
        if (this.opMode != 4) {
            this.opMode = 4;
            this.codePart = 1;
        }

        switch(this.codePart) {
            case 1:
                this.oldArrow = this.curInOld;
                this.curIn = this.curInOld = 0;
                if (this.nNodes == 0) {
                    this.note = "Can't remove; heap empty";
                    this.codePart = 1;
                } else {
                    this.largestNode = this.treeArray[0];
                    this.note = "Will remove largest node (" + this.largestNode.getHeight() + ")";
                    this.codePart = 2;
                }

                this.drawMode = 0;
                return;
            case 2:
                this.treeArray[0] = this.blankNode;
                this.note = "Will replace with \"last\" node (" + this.treeArray[this.nNodes - 1].getHeight() + ")";
                this.drawMode = 1;
                this.codePart = 3;
                return;
            case 3:
                this.oldArrow = this.curInOld;
                this.curIn = this.curInOld = 0;
                this.treeArray[0] = this.treeArray[this.nNodes - 1];
                this.treeArray[this.nNodes - 1] = null;
                --this.nNodes;
                this.note = "Will trickle down";
                this.drawMode = 3;
                this.codePart = 4;
                return;
            case 4:
                this.oldArrow = this.curInOld;
                this.curIn = this.curInOld = 0;
                this.tempNode = this.treeArray[0];
                this.treeArray[0] = this.blankNode;
                this.note = "Saved root node (" + this.tempNode.getHeight() + ")";
                this.drawMode = 1;
                this.codePart = 5;
                return;
            case 5:
                if (this.curIn <= (this.nNodes - 1) / 2) {
                    int var1 = 2 * this.curIn + 1;
                    int var2 = var1 + 1;
                    if (var2 < this.nNodes && this.treeArray[var1].getHeight() < this.treeArray[var2].getHeight()) {
                        this.largerChild = var2;
                    } else {
                        this.largerChild = var1;
                    }

                    if (this.treeArray[this.largerChild] != null) {
                        this.note = "Key " + this.treeArray[this.largerChild].getHeight() + " is larger child";
                        this.codePart = 6;
                    } else {
                        this.note = "Node has no children, so done";
                        this.codePart = 7;
                    }
                } else {
                    this.note = "Reached bottom row; so done";
                    this.codePart = 7;
                }

                this.drawMode = 0;
                return;
            case 6:
                if (this.tempNode.getHeight() >= this.treeArray[this.largerChild].getHeight()) {
                    this.note = "\"Last\" node larger; will insert it";
                    this.drawMode = 0;
                    this.codePart = 7;
                    return;
                }

                this.note = "Moved node up";
                this.treeArray[this.curIn] = this.treeArray[this.largerChild];
                this.treeArray[this.largerChild] = this.blankNode;
                this.oldArrow = this.curInOld;
                this.curIn = this.curInOld = this.largerChild;
                this.drawMode = 2;
                this.codePart = 5;
                return;
            case 7:
                this.treeArray[this.curIn] = this.tempNode;
                this.note = "Inserted \"last\" node";
                this.drawMode = 1;
                this.codePart = 9;
                return;
            case 8:
            default:
                return;
            case 9:
                this.note = "Finished deleting largest node (" + this.largestNode.getHeight() + ")";
                this.drawMode = 0;
                this.codePart = 10;
                return;
            case 10:
                this.note = "Press any button";
                this.oldArrow = this.curInOld;
                this.curInOld = this.curIn = 0;
                this.codePart = 1;
        }
    }

    public void change(boolean var1, int var2) {
        if (this.opMode != 2) {
            this.opMode = 2;
            this.codePart = 1;
        }

        switch(this.codePart) {
            case 1:
                this.oldArrow = this.curInOld;
                this.curIn = this.curInOld = 0;
                if (this.nNodes == 0) {
                    this.note = "Can't change; heap empty";
                    this.codePart = 1;
                } else {
                    this.note = "Click on node to be changed";
                    this.codePart = 2;
                }

                this.drawMode = 0;
                return;
            case 2:
                this.note = "Type node's new key value";
                this.drawMode = 0;
                this.codePart = 3;
                return;
            case 3:
                this.value = var2;
                if (var1 && var2 >= 0 && var2 <= 99) {
                    this.oldHeight = this.treeArray[this.curIn].getHeight();
                    this.note = "Will change node from " + this.oldHeight + " to " + this.value;
                    this.codePart = 4;
                } else {
                    this.note = "Nodes have values from 0 to 99";
                    this.codePart = 2;
                }

                this.drawMode = 0;
                return;
            case 4:
                this.treeArray[this.curIn].setHeight(this.value);
                if (this.oldHeight < this.value) {
                    this.note = "Key increased; will trickle up";
                    this.codePart = 5;
                } else if (this.oldHeight > this.value) {
                    this.note = "Key decreased; will trickle down";
                    this.codePart = 8;
                } else {
                    this.note = "Key not changed.";
                    this.codePart = 16;
                }

                this.drawMode = 1;
                return;
            case 5:
                this.tempNode = this.treeArray[this.curIn];
                this.treeArray[this.curIn] = this.blankNode;
                this.note = "Saved changed node (" + this.tempNode.getHeight() + ")";
                this.codePart = 6;
                this.drawMode = 1;
                return;
            case 6:
                if (this.curIn > 0 && this.treeArray[(this.curIn - 1) / 2].getHeight() < this.tempNode.getHeight()) {
                    this.treeArray[this.curIn] = this.treeArray[(this.curIn - 1) / 2];
                    this.treeArray[(this.curIn - 1) / 2] = this.blankNode;
                    this.oldArrow = this.curInOld;
                    this.oldChild = this.curIn;
                    this.curInOld = this.curIn = (this.curIn - 1) / 2;
                    this.note = "Moved empty node up";
                    this.codePart = 6;
                    this.drawMode = 2;
                    return;
                }

                this.note = "Trickle-up completed";
                this.codePart = 7;
                this.drawMode = 0;
                return;
            case 7:
                this.treeArray[this.curIn] = this.tempNode;
                this.note = "Inserted changed item in empty node";
                this.codePart = 15;
                this.drawMode = 1;
                return;
            case 8:
                this.tempNode = this.treeArray[this.curIn];
                this.treeArray[this.curIn] = this.blankNode;
                this.note = "Saved changed node (" + this.tempNode.getHeight() + ")";
                this.drawMode = 1;
                this.codePart = 9;
                return;
            case 9:
                if (this.curIn <= (this.nNodes - 1) / 2) {
                    int var3 = 2 * this.curIn + 1;
                    int var4 = var3 + 1;
                    if (var4 < this.nNodes && this.treeArray[var3].getHeight() < this.treeArray[var4].getHeight()) {
                        this.largerChild = var4;
                    } else {
                        this.largerChild = var3;
                    }

                    if (this.treeArray[this.largerChild] != null) {
                        this.note = "Key " + this.treeArray[this.largerChild].getHeight() + " is larger child";
                        this.codePart = 10;
                    } else {
                        this.note = "Node has no children, so done";
                        this.codePart = 11;
                    }
                } else {
                    this.note = "Reached bottom row; so done";
                    this.codePart = 11;
                }

                this.drawMode = 0;
                return;
            case 10:
                if (this.tempNode.getHeight() >= this.treeArray[this.largerChild].getHeight()) {
                    this.note = "Changed node is larger; will insert it";
                    this.drawMode = 0;
                    this.codePart = 11;
                    return;
                }

                this.note = "Moved empty node down";
                this.treeArray[this.curIn] = this.treeArray[this.largerChild];
                this.treeArray[this.largerChild] = this.blankNode;
                this.oldArrow = this.curInOld;
                this.curIn = this.curInOld = this.largerChild;
                this.drawMode = 2;
                this.codePart = 9;
                return;
            case 11:
                this.treeArray[this.curIn] = this.tempNode;
                this.note = "Inserted changed node";
                this.drawMode = 1;
                this.codePart = 15;
                return;
            case 12:
            case 13:
            case 14:
            default:
                return;
            case 15:
                this.note = "Finished changing node (" + this.treeArray[this.curIn].getHeight() + ")";
                this.drawMode = 0;
                this.codePart = 16;
                return;
            case 16:
                this.note = "Press any button";
                this.oldArrow = this.curInOld;
                this.curInOld = this.curIn = 0;
                this.codePart = 1;
        }
    }

    public void setArrow(int var1) {
        this.oldArrow = this.curInOld;
        this.curIn = this.curInOld = var1;
        this.drawAll = false;
        this.drawMode = 0;
    }

    public int xyToIndex(int var1, int var2) {
        int var5 = 0;
        int var3 = (var1 - 10) * 2 / 26;
        int var4 = (var2 - 70) / 40;
        switch(var4) {
            case 0:
                var5 = 0;
                break;
            case 1:
                var5 = (var3 + 9) / 16;
                break;
            case 2:
                var5 = (var3 + 21) / 8;
                break;
            case 3:
                var5 = (var3 + 27) / 4;
                break;
            case 4:
                var5 = (var3 + 30) / 2;
        }

        return var5;
    }

    public void drawOneNode(Graphics var1, int var2) {
        if (this.treeArray[var2] != null) {
            int var3 = this.treeArray[var2].getHeight();
            Color var4 = this.treeArray[var2].getColor();
            int var5 = var2 % 2;
            int var6 = 15;
            byte var7 = 0;
            int var8 = -1;
            if (var2 > 0 && var2 < 3) {
                var6 = 7 + (var2 - 1) * 16;
                var7 = 1;
                var8 = var5 == 1 ? var6 + 8 : var6 - 8;
            } else if (var2 > 2 && var2 < 7) {
                var6 = 3 + (var2 - 3) * 8;
                var7 = 2;
                var8 = var5 == 1 ? var6 + 4 : var6 - 4;
            } else if (var2 > 6 && var2 < 15) {
                var6 = 1 + (var2 - 7) * 4;
                var7 = 3;
                var8 = var5 == 1 ? var6 + 2 : var6 - 2;
            } else if (var2 > 14 && var2 < 31) {
                var6 = (var2 - 15) * 2;
                var7 = 4;
                var8 = var5 == 1 ? var6 + 1 : var6 - 1;
            }

            int var9 = 10 + var6 * 26 / 2;
            int var10 = 70 + var7 * 40;
            int var11 = 10 + var8 * 26 / 2;
            int var12 = 70 + (var7 - 1) * 40;
            if (var7 > 0) {
                var1.setColor(Color.black);
                var1.drawLine(var9 + 10, var10 + 10, var11 + 10, var12 + 10);
            }

            var1.setColor(var4);
            var1.fillOval(var9, var10, 20, 20);
            var1.setColor(Color.black);
            var1.drawOval(var9, var10, 20, 20);
            if (var3 >= 0) {
                if (var3 < 10) {
                    var1.drawString(String.valueOf(var3), var9 + 7, var10 + 20 - 5);
                    return;
                }

                var1.drawString(String.valueOf(var3), var9 + 4, var10 + 20 - 5);
            }

        }
    }

    public void draw(Graphics var1) {
        int var2 = this.drawAll ? 3 : this.drawMode;
        switch(var2) {
            case 0:
            default:
                break;
            case 2:
                if (this.opMode == 4) {
                    this.drawOneNode(var1, this.largerChild);
                }

                if (this.opMode == 3 || this.opMode == 2 && this.codePart == 6) {
                    this.drawOneNode(var1, this.oldChild);
                }
            case 1:
                for(int var3 = this.curInOld; var3 > 0; var3 = (var3 - 1) / 2) {
                    this.drawOneNode(var1, var3);
                }

                this.drawOneNode(var1, 0);
                break;
            case 3:
                var1.setColor(Color.lightGray);
                var1.fillRect(0, 0, 440, 300);

                for(int var4 = 30; var4 >= 0; --var4) {
                    this.drawOneNode(var1, var4);
                }
        }

        var1.setColor(Color.lightGray);
        var1.fillRect(10, 45, 250, 25);
        var1.setColor(Color.black);
        var1.drawString(this.note, 16, 64);
        this.drawArrow(var1, this.oldArrow, false);
        this.drawArrow(var1, this.curInOld, true);
        this.drawAll = true;
    }

    public void drawArrow(Graphics var1, int var2, boolean var3) {
        if (this.treeArray[var2] != null) {
            int var4 = 15;
            byte var5 = 0;
            if (var2 > 0 && var2 < 3) {
                var4 = 7 + (var2 - 1) * 16;
                var5 = 1;
            } else if (var2 > 2 && var2 < 7) {
                var4 = 3 + (var2 - 3) * 8;
                var5 = 2;
            } else if (var2 > 6 && var2 < 15) {
                var4 = 1 + (var2 - 7) * 4;
                var5 = 3;
            } else if (var2 > 14 && var2 < 31) {
                var4 = (var2 - 15) * 2;
                var5 = 4;
            }

            int var6 = 10 + var4 * 26 / 2;
            int var7 = 70 + var5 * 40;
            if (var3) {
                var1.setColor(Color.red);
            } else {
                var1.setColor(Color.lightGray);
            }

            int var8 = var6 + 10;
            int var9 = var7 - 2;
            byte var10 = 20;
            var1.drawLine(var8, var9, var8, var9 - var10);
            var1.drawLine(var8 - 1, var9, var8 - 1, var9 - var10);
            var1.drawLine(var8, var9, var8 - 3, var9 - 6);
            var1.drawLine(var8 - 1, var9, var8 - 4, var9 - 6);
            var1.drawLine(var8, var9, var8 + 3, var9 - 6);
            var1.drawLine(var8 - 1, var9, var8 + 2, var9 - 6);
        }
    }
}
