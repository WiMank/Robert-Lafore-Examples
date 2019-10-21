package chap8.tree;
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
    private final int noteBoxWidth = 200;
    private final int visitBoxTop = 280;
    private final int visitBoxHeight = 25;
    private final int visitBoxWidth = 430;
    private final int ASIZE = 31;
    private final int MAX_KEY = 99;
    private person[] treeArray = new person[31];
    stack theStack = new stack();
    private int filledNodes;
    private String note;
    private boolean isRand = true;
    private int value;
    private int codePart = 1;
    private int opMode;
    private int curIn;
    private int curInOld;
    private int oldArrow;
    private int[] visitArray;
    private int visitIndex;
    private int successor;
    private boolean drawAll;
    private int drawMode = 2;

    public personGroup() {
        for(int var1 = 0; var1 < 31; ++var1) {
            this.treeArray[var1] = null;
        }

        this.filledNodes = 0;
        this.note = "Press a button";
        this.visitArray = new int[31];
        this.visitIndex = 0;
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
                this.visitIndex = 0;
                this.note = "Enter number of nodes (1 to 31)";
                this.curIn = 0;
                this.oldArrow = this.curInOld;
                this.curInOld = 0;
                this.drawMode = 0;
                this.codePart = 2;
                return;
            case 2:
                if (var1 && var2 >= 0 && var2 <= 31) {
                    this.note = "Will create tree of " + var2 + " nodes";
                    this.codePart = 3;
                    return;
                }

                this.note = "Inappropriate number of nodes";
                this.codePart = 1;
                return;
            case 3:
                this.doFill(var2);
                this.drawMode = 2;
                this.note = "Press any button";
                this.oldArrow = this.curInOld;
                this.curInOld = 0;
                this.codePart = 1;
                return;
            default:
        }
    }

    public void doFill(int var1) {
        int var2 = 0;

        for(int var4 = 0; var4 < 31; ++var4) {
            this.treeArray[var4] = null;
        }

        this.filledNodes = 0;
        boolean[] var5 = new boolean[100];

        for(int var6 = 0; var6 < 100; ++var6) {
            var5[var6] = false;
        }

        label41:
        while(this.filledNodes < var1 && var2 < 100) {
            int var3;
            do {
                var3 = (int)(Math.random() * 99.0D);
            } while(var5[var3]);

            person var7 = this.makePerson(var3);
            this.curIn = 0;

            while(this.curIn <= 30) {
                if (this.treeArray[this.curIn] == null) {
                    this.treeArray[this.curIn] = var7;
                    ++this.filledNodes;
                    var5[var3] = true;
                    continue label41;
                }

                if (var3 < this.treeArray[this.curIn].getHeight()) {
                    this.curIn = 2 * this.curIn + 1;
                } else {
                    this.curIn = 2 * this.curIn + 2;
                }
            }

            ++var2;
        }

    }

    public void find(boolean var1, int var2) {
        if (this.opMode != 2) {
            this.opMode = 2;
            this.codePart = 1;
        }

        switch(this.codePart) {
            case 1:
                this.visitIndex = 0;
                this.note = "Enter key of node to find";
                this.curIn = 0;
                this.oldArrow = this.curInOld;
                this.curInOld = 0;
                this.drawMode = 0;
                this.codePart = 2;
                return;
            case 2:
                if (var1 && var2 >= 0 && var2 <= 99) {
                    this.note = "Will try to find node with key " + var2;
                    this.codePart = 3;
                    return;
                }

                this.note = "Nodes have values from 0 to 99";
                this.codePart = 1;
                return;
            case 3:
                if (this.treeArray[this.curIn] == null) {
                    this.note = "Can't find node with that value";
                    this.codePart = 1;
                    return;
                } else if (var2 == this.treeArray[this.curIn].getHeight()) {
                    this.note = "Have found node " + var2;
                    this.oldArrow = this.curInOld;
                    this.curInOld = this.curIn;
                    this.codePart = 4;
                    return;
                } else {
                    this.oldArrow = this.curInOld;
                    this.curInOld = this.curIn;
                    if (var2 < this.treeArray[this.curIn].getHeight()) {
                        this.curIn = 2 * this.curIn + 1;
                        this.note = "Going to left child";
                    } else {
                        this.curIn = 2 * this.curIn + 2;
                        this.note = "Going to right child";
                    }

                    this.codePart = 3;
                    if (this.curIn > 30) {
                        this.note = "Can't find node with that value";
                        this.codePart = 1;
                        return;
                    }
                }
            default:
                return;
            case 4:
                this.note = "Search is complete";
                this.codePart = 5;
                return;
            case 5:
                this.note = "Press any button";
                this.oldArrow = this.curInOld;
                this.curInOld = 0;
                this.codePart = 1;
        }
    }

    public void insert(boolean var1, int var2) {
        if (this.opMode != 3) {
            this.opMode = 3;
            this.codePart = 1;
        }

        switch(this.codePart) {
            case 1:
                this.visitIndex = 0;
                this.note = "Enter value of node to insert";
                this.curIn = 0;
                this.oldArrow = this.curInOld;
                this.curInOld = 0;
                this.drawMode = 0;
                this.codePart = 2;
                return;
            case 2:
                this.value = var2;
                if (var1 && var2 >= 0 && var2 <= 99) {
                    this.note = "Will insert node with key " + this.value;
                    this.codePart = 3;
                    return;
                }

                this.note = "Nodes have values from 0 to 99";
                this.codePart = 1;
                return;
            case 3:
                this.oldArrow = this.curInOld;
                this.curInOld = this.curIn;
                if (this.curIn > 30) {
                    this.note = "Level is too great";
                    this.codePart = 4;
                    return;
                } else {
                    if (this.treeArray[this.curIn] == null) {
                        this.treeArray[this.curIn] = this.makePerson(this.value);
                        this.value = this.treeArray[this.curIn].getHeight();
                        this.note = "Have inserted node with key " + this.value;
                        ++this.filledNodes;
                        this.curInOld = this.curIn;
                        this.drawMode = 1;
                        this.codePart = 4;
                        return;
                    }

                    if (this.value < this.treeArray[this.curIn].getHeight()) {
                        this.curIn = 2 * this.curIn + 1;
                        this.note = "Going to left child";
                    } else {
                        this.curIn = 2 * this.curIn + 2;
                        this.note = "Going to right child";
                    }

                    this.codePart = 3;
                    if (this.curIn > 30) {
                        this.note = "Can't insert: Level is too great";
                        this.codePart = 1;
                    }

                    this.drawMode = 0;
                    return;
                }
            case 4:
                this.note = "Insertion completed";
                this.drawMode = 0;
                this.codePart = 5;
                return;
            case 5:
                this.note = "Press any button";
                this.oldArrow = this.curInOld;
                this.curInOld = 0;
                this.codePart = 1;
                return;
            default:
        }
    }

    public void remove(boolean var1, int var2) {
        if (this.opMode != 4) {
            this.opMode = 4;
            this.codePart = 1;
        }

        switch(this.codePart) {
            case 1:
                this.visitIndex = 0;
                this.note = "Enter key of node to delete";
                this.codePart = 2;
                this.curIn = 0;
                this.oldArrow = this.curInOld;
                this.curInOld = 0;
                this.drawMode = 0;
                return;
            case 2:
                if (var1 && var2 >= 0 && var2 <= 99) {
                    this.note = "Will try to delete node " + var2;
                    this.codePart = 3;
                } else {
                    this.note = "Nodes have values from 0 to 99";
                    this.codePart = 1;
                }

                this.curIn = 0;
                this.oldArrow = this.curInOld;
                this.curInOld = 0;
                return;
            case 3:
                if (this.treeArray[this.curIn] == null) {
                    this.note = "Can't find node with that value";
                    this.codePart = 1;
                    return;
                } else if (var2 == this.treeArray[this.curIn].getHeight()) {
                    this.note = "Have found node to delete";
                    this.oldArrow = this.curInOld;
                    this.curInOld = this.curIn;
                    this.codePart = 4;
                    return;
                } else {
                    this.oldArrow = this.curInOld;
                    this.curInOld = this.curIn;
                    if (var2 < this.treeArray[this.curIn].getHeight()) {
                        this.curIn = 2 * this.curIn + 1;
                        this.note = "Going to left child";
                    } else {
                        this.curIn = 2 * this.curIn + 2;
                        this.note = "Going to right child";
                    }

                    this.codePart = 3;
                    if (this.curIn > 30) {
                        this.note = "Can't find node with that value";
                        this.codePart = 1;
                        return;
                    }
                }
            default:
                return;
            case 4:
                if (this.curIn > 14 || this.treeArray[2 * this.curIn + 1] == null && this.treeArray[2 * this.curIn + 2] == null) {
                    this.note = "Will delete node without complication";
                    this.codePart = 5;
                    return;
                } else if (this.treeArray[2 * this.curIn + 1] == null) {
                    this.note = "Will replace node with its right subtree";
                    this.codePart = 6;
                    return;
                } else {
                    if (this.treeArray[2 * this.curIn + 2] == null) {
                        this.note = "Will replace node with its left subtree";
                        this.codePart = 7;
                        return;
                    }

                    this.successor = this.inorderSuccessor(this.curIn);
                    this.note = "Will replace node with " + this.treeArray[this.successor].getHeight();
                    this.codePart = 8;
                    return;
                }
            case 5:
                this.treeArray[this.curIn] = null;
                this.note = "Node was deleted";
                this.drawMode = 2;
                this.codePart = 10;
                return;
            case 6:
                this.treeArray[this.curIn] = null;
                this.moveUpSubTree(1, this.curIn);
                this.note = "Node was replaced by its right subtree";
                this.drawMode = 2;
                this.codePart = 10;
                return;
            case 7:
                this.treeArray[this.curIn] = null;
                this.moveUpSubTree(0, this.curIn);
                this.note = "Node was replaced by its left subtree";
                this.drawMode = 2;
                this.codePart = 10;
                return;
            case 8:
                this.treeArray[this.curIn] = this.treeArray[this.successor];
                int var3 = 2 * this.successor + 2;
                if (this.successor < 15 && this.treeArray[var3] != null) {
                    int var4 = this.treeArray[this.successor].getHeight();
                    this.note = "and replace " + var4 + " with its right subtree";
                    this.drawMode = 0;
                    this.codePart = 9;
                    return;
                }

                this.treeArray[this.successor] = null;
                this.note = "Node was replaced by successor";
                this.drawMode = 2;
                this.codePart = 10;
                return;
            case 9:
                this.moveUpSubTree(1, this.successor);
                this.note = "Removed node in 2-step process";
                this.drawMode = 2;
                this.codePart = 10;
                return;
            case 10:
                this.note = "Press any button";
                this.oldArrow = this.curInOld;
                this.curInOld = 0;
                this.codePart = 1;
        }
    }

    public void moveUpSubTree(int var1, int var2) {
        if (var2 <= 14 && var2 >= 0) {
            int var3;
            if (var1 == 1) {
                var3 = 2 * var2 + 2;
            } else {
                var3 = 2 * var2 + 1;
            }

            byte var6;
            if (var3 > 0 && var3 < 3) {
                var6 = 1;
            } else if (var3 > 2 && var3 < 7) {
                var6 = 2;
            } else if (var3 > 6 && var3 < 15) {
                var6 = 3;
            } else {
                var6 = 4;
            }

            int var7 = var3;
            int var4 = var3;
            int var5 = 1;

            for(int var11 = var6; var11 < 5; ++var11) {
                for(int var12 = 0; var12 < var5; ++var12) {
                    int var9 = (var4 - 1) / 2;
                    int var8 = var5 - var12 - 1;
                    int var10;
                    if (var1 == 1) {
                        var10 = var9 - (var8 + 1) / 2;
                    } else {
                        var10 = var9 + (var12 + 1) / 2;
                    }

                    this.treeArray[var10] = this.treeArray[var4];
                    if (var11 == 4) {
                        this.treeArray[var4] = null;
                    }

                    ++var4;
                }

                var7 = 2 * var7 + 1;
                var4 = var7;
                var5 *= 2;
            }

        }
    }

    public int inorderSuccessor(int var1) {
        int var2 = var1;

        for(int var3 = 2 * var1 + 2; var3 < 31 && this.treeArray[var3] != null; var3 = 2 * var3 + 1) {
            var2 = var3;
        }

        return var2;
    }

    public void traverse() {
        if (this.opMode != 5) {
            this.opMode = 5;
            this.codePart = 1;
        }

        switch(this.codePart) {
            case 1:
                this.visitIndex = 0;
                this.note = "Will traverse tree in \"inorder\"";
                this.curIn = 0;
                this.oldArrow = this.curInOld;
                this.curInOld = 0;
                this.drawMode = 0;
                this.codePart = 2;
                return;
            case 2:
                this.note = "Will check left child";
                this.codePart = 3;
                return;
            case 3:
                if (this.curIn <= 14 && this.treeArray[2 * this.curIn + 1] != null) {
                    this.theStack.push(this.curIn);
                    this.curIn = 2 * this.curIn + 1;
                    this.oldArrow = this.curInOld;
                    this.curInOld = this.curIn;
                    this.note = "Will check for left child";
                    this.codePart = 3;
                    return;
                }

                this.note = "Will visit this node";
                this.codePart = 4;
                return;
            case 4:
                this.visitArray[this.visitIndex++] = this.treeArray[this.curIn].getHeight();
                this.note = "Will check for right child";
                this.codePart = 5;
                return;
            case 5:
                if (this.curIn <= 14 && this.treeArray[2 * this.curIn + 2] != null) {
                    this.curIn = 2 * this.curIn + 2;
                    this.oldArrow = this.curInOld;
                    this.curInOld = this.curIn;
                    this.note = "Will check left child";
                    this.codePart = 3;
                    return;
                }

                this.note = "Will go to root of last subtree";
                this.codePart = 6;
                return;
            case 6:
                if (this.theStack.isEmpty()) {
                    this.note = "Done traversal";
                    this.codePart = 7;
                    return;
                }

                this.curIn = this.theStack.pop();
                this.oldArrow = this.curInOld;
                this.curInOld = this.curIn;
                this.note = "Will visit this node";
                this.codePart = 4;
                return;
            case 7:
                this.note = "Press any button";
                this.oldArrow = this.curInOld;
                this.curInOld = 0;
                this.codePart = 1;
                return;
            default:
        }
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
            if (var3 < 10) {
                var1.drawString(String.valueOf(var3), var9 + 7, var10 + 20 - 5);
            } else {
                var1.drawString(String.valueOf(var3), var9 + 4, var10 + 20 - 5);
            }
        }
    }

    public void draw(Graphics var1) {
        int var2 = this.drawAll ? 2 : this.drawMode;
        int var4;
        switch(var2) {
            case 0:
            default:
                break;
            case 1:
                for(int var3 = this.curInOld; var3 > 0; var3 = (var3 - 1) / 2) {
                    this.drawOneNode(var1, var3);
                }

                this.drawOneNode(var1, 0);
                break;
            case 2:
                var1.setColor(Color.lightGray);
                var1.fillRect(0, 0, 440, 300);

                for(var4 = 30; var4 >= 0; --var4) {
                    this.drawOneNode(var1, var4);
                }
        }

        var1.setColor(Color.lightGray);
        var1.fillRect(10, 45, 200, 25);
        var1.setColor(Color.black);
        var1.drawString(this.note, 16, 64);
        var1.setColor(Color.lightGray);
        var1.fillRect(10, 280, 430, 25);
        var1.setColor(Color.black);
        String var5 = "";

        for(var4 = 0; var4 < this.visitIndex; ++var4) {
            var5 = var5 + this.visitArray[var4] + " ";
        }

        var1.drawString(var5, 16, 296);
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
