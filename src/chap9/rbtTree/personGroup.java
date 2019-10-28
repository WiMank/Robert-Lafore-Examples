package chap9.rbtTree;

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
    private final int topMargin = 90;
    private final int leftMargin = 10;
    private final int textHeight = 13;
    private final int nodeDiameter = 20;
    private final int levelSeparation = 40;
    private final int horizSeparation = 26;
    private final int noteBoxTop = 45;
    private final int noteBoxHeight = 25;
    private final int noteBoxWidth = 300;
    private final int visitBoxTop = 280;
    private final int visitBoxHeight = 25;
    private final int visitBoxWidth = 430;
    private final int ASIZE = 31;
    private final int MAX_KEY = 99;
    private person[] treeArray = new person[31];
    private String note;
    private boolean isRand = true;
    private int value;
    private int codePart = 1;
    private int opMode;
    private int curIn;
    private int curInOld;
    private int oldArrow;
    private int successor;
    private boolean drawAll;
    private int drawMode = 2;

    public personGroup() {
        for(int var1 = 0; var1 < 31; ++var1) {
            this.treeArray[var1] = null;
        }

        this.note = "Press a button, or click on a node";
        this.curIn = this.curInOld = 0;
    }

    public person makePerson(int var1) {
        int var2 = 100 + (int)(Math.random() * 154.0D);
        int var3 = 100 + (int)(Math.random() * 154.0D);
        int var4 = 100 + (int)(Math.random() * 154.0D);
        Color var5 = new Color(var2, var3, var4);
        return new person(var1, var5);
    }

    public void drawOneNode(Graphics var1, int var2) {
        if (this.treeArray[var2] != null) {
            int var3 = this.treeArray[var2].getHeight();
            Color var4 = this.treeArray[var2].getColor();
            boolean var5 = this.treeArray[var2].getRed();
            int var6 = var2 % 2;
            int var7 = 15;
            byte var8 = 0;
            int var9 = -1;
            if (var2 > 0 && var2 < 3) {
                var7 = 7 + (var2 - 1) * 16;
                var8 = 1;
                var9 = var6 == 1 ? var7 + 8 : var7 - 8;
            } else if (var2 > 2 && var2 < 7) {
                var7 = 3 + (var2 - 3) * 8;
                var8 = 2;
                var9 = var6 == 1 ? var7 + 4 : var7 - 4;
            } else if (var2 > 6 && var2 < 15) {
                var7 = 1 + (var2 - 7) * 4;
                var8 = 3;
                var9 = var6 == 1 ? var7 + 2 : var7 - 2;
            } else if (var2 > 14 && var2 < 31) {
                var7 = (var2 - 15) * 2;
                var8 = 4;
                var9 = var6 == 1 ? var7 + 1 : var7 - 1;
            }

            int var10 = 10 + var7 * 26 / 2;
            int var11 = 90 + var8 * 40;
            int var12 = 10 + var9 * 26 / 2;
            int var13 = 90 + (var8 - 1) * 40;
            if (var8 > 0) {
                var1.setColor(Color.black);
                var1.drawLine(var10 + 10, var11 + 10, var12 + 10, var13 + 10);
            }

            var1.setColor(var4);
            var1.fillOval(var10, var11, 20, 20);
            if (!var5) {
                var1.setColor(Color.black);
            } else {
                var1.setColor(Color.red);
            }

            var1.drawOval(var10, var11, 20, 20);
            var1.drawOval(var10 - 1, var11 - 1, 22, 22);
            var1.setColor(Color.black);
            if (var3 < 10) {
                var1.drawString(String.valueOf(var3), var10 + 7, var11 + 20 - 5);
            } else {
                var1.drawString(String.valueOf(var3), var10 + 4, var11 + 20 - 5);
            }
        }
    }

    public void draw(Graphics var1) {
        int var2 = this.drawAll ? 2 : this.drawMode;
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

                for(int var4 = 30; var4 >= 0; --var4) {
                    this.drawOneNode(var1, var4);
                }
        }

        var1.setColor(Color.lightGray);
        var1.fillRect(10, 45, 300, 25);
        var1.setColor(Color.black);
        var1.drawString(this.note, 16, 64);
        var1.setColor(Color.black);
        String var5 = "";
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
            int var7 = 90 + var5 * 40;
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

    public void setDrawAll(boolean var1) {
        this.drawAll = var1;
    }

    public void fillRoot() {
        for(int var1 = 0; var1 < 31; ++var1) {
            this.treeArray[var1] = null;
        }

        this.treeArray[0] = this.makePerson(50);
        this.treeArray[0].setRed(false);
        this.curIn = this.curInOld = 0;
        this.drawAll = true;
        this.check();
    }

    public void quickInsert(boolean var1, int var2) {
        if (var1 && var2 >= 0 && var2 <= 99) {
            this.curIn = 0;

            do {
                if (this.treeArray[this.curIn] == null) {
                    this.treeArray[this.curIn] = this.makePerson(var2);
                    if (this.curIn == 0) {
                        this.treeArray[this.curIn].setRed(false);
                    }

                    this.oldArrow = this.curInOld;
                    this.curInOld = this.curIn;
                    this.drawAll = false;
                    this.drawMode = 1;
                    this.check();
                    return;
                }

                if (this.curIn < 15 && this.treeArray[2 * this.curIn + 1] != null && this.treeArray[2 * this.curIn + 2] != null && !this.treeArray[this.curIn].getRed() && this.treeArray[2 * this.curIn + 1].getRed() && this.treeArray[2 * this.curIn + 2].getRed()) {
                    this.oldArrow = this.curInOld;
                    this.curInOld = this.curIn;
                    this.note = "CAN'T INSERT: needs color flip";
                    return;
                }

                if (var2 < this.treeArray[this.curIn].getHeight()) {
                    this.curIn = 2 * this.curIn + 1;
                } else {
                    this.curIn = 2 * this.curIn + 2;
                }
            } while(this.curIn <= 30);

            this.note = "CAN'T INSERT: Level is too deep";
        } else {
            this.note = "Nodes have values from 0 to 99";
        }
    }

    public void quickRemove(boolean var1, int var2) {
        this.curIn = 0;
        this.oldArrow = this.curInOld;
        this.curInOld = 0;
        if (var1 && var2 >= 0 && var2 <= 99) {
            do {
                if (var2 == this.treeArray[this.curIn].getHeight()) {
                    if (this.curIn > 14 || this.treeArray[2 * this.curIn + 1] == null && this.treeArray[2 * this.curIn + 2] == null) {
                        this.treeArray[this.curIn] = null;
                    } else if (this.treeArray[2 * this.curIn + 1] == null) {
                        this.treeArray[this.curIn] = null;
                        this.moveSubTree(2 * (this.curIn + 1), this.curIn);
                    } else if (this.treeArray[2 * this.curIn + 2] == null) {
                        this.treeArray[this.curIn] = null;
                        this.moveSubTree(2 * this.curIn + 1, this.curIn);
                    } else {
                        this.successor = this.inorderSuccessor(this.curIn);
                        this.treeArray[this.curIn] = this.treeArray[this.successor];
                        int var3 = 2 * this.successor + 2;
                        if (this.successor < 15 && this.treeArray[var3] != null) {
                            this.moveSubTree(2 * (this.successor + 1), this.successor);
                        } else {
                            this.treeArray[this.successor] = null;
                        }
                    }

                    this.drawAll = true;
                    this.check();
                    return;
                }

                if (var2 < this.treeArray[this.curIn].getHeight()) {
                    this.curIn = 2 * this.curIn + 1;
                } else {
                    this.curIn = 2 * this.curIn + 2;
                }
            } while(this.curIn <= 30 && this.treeArray[this.curIn] != null);

            this.note = "Can't find node with that value";
        } else {
            this.note = "Nodes have values from 0 to 99";
        }
    }

    public int inorderSuccessor(int var1) {
        int var2 = var1;

        for(int var3 = 2 * var1 + 2; var3 < 31 && this.treeArray[var3] != null; var3 = 2 * var3 + 1) {
            var2 = var3;
        }

        return var2;
    }

    public void flip() {
        int var1 = 2 * this.curIn + 1;
        int var2 = 2 * (this.curIn + 1);
        if (this.curIn <= 14 && this.treeArray[var1] != null && this.treeArray[var2] != null) {
            if (this.curIn == 0 && this.treeArray[var1].getRed() == this.treeArray[var2].getRed()) {
                this.treeArray[var1].setRed(!this.treeArray[var1].getRed());
                this.treeArray[var2].setRed(!this.treeArray[var2].getRed());
                this.drawAll = true;
                this.check();
            } else if (this.treeArray[var1] != null && this.treeArray[var2] != null && this.treeArray[var1].getRed() == this.treeArray[var2].getRed() && this.treeArray[this.curIn].getRed() != this.treeArray[var1].getRed()) {
                this.treeArray[this.curIn].setRed(!this.treeArray[this.curIn].getRed());
                this.treeArray[var1].setRed(!this.treeArray[var1].getRed());
                this.treeArray[var2].setRed(!this.treeArray[var2].getRed());
                this.drawAll = true;
                this.check();
            } else {
                this.note = "Can't flip this color arrangement.";
                this.drawAll = false;
                this.drawMode = 0;
            }
        } else {
            this.note = "Node has no children";
            this.drawAll = false;
            this.drawMode = 0;
        }
    }

    public void rotateRight() {
        int var1 = this.curIn;
        int var2 = 2 * var1 + 1;
        if (this.treeArray[var2] == null) {
            this.note = "Can't rotate right; node has no left child";
        } else {
            int var3 = 2 * (var1 + 1);
            int var4 = 2 * (var3 + 1);
            this.moveSubTree(var3, var4);
            if (var3 < 31) {
                this.treeArray[var3] = this.treeArray[var1];
            }

            this.treeArray[var1] = this.treeArray[var2];
            if (var2 > 14) {
                this.treeArray[var2] = null;
            }

            int var7 = 2 * (var2 + 1);
            int var5 = 2 * var3 + 1;
            this.moveSubTree(var7, var5);
            int var6 = 2 * var2 + 1;
            this.moveSubTree(var6, var2);
            this.drawMode = 2;
            this.check();
        }
    }

    public void rotateLeft() {
        int var1 = this.curIn;
        int var3 = 2 * (var1 + 1);
        if (this.treeArray[var3] == null) {
            this.note = "Can't rotate left, no right child";
        } else {
            int var2 = 2 * var1 + 1;
            int var6 = 2 * var2 + 1;
            this.moveSubTree(var2, var6);
            if (var2 < 31) {
                this.treeArray[var2] = this.treeArray[var1];
            }

            this.treeArray[var1] = this.treeArray[var3];
            if (var3 > 14) {
                this.treeArray[var3] = null;
            }

            int var5 = 2 * var3 + 1;
            int var7 = 2 * (var2 + 1);
            this.moveSubTree(var5, var7);
            int var4 = 2 * (var3 + 1);
            this.moveSubTree(var4, var3);
            this.drawMode = 2;
            this.check();
        }
    }

    public void moveSubTree(int var1, int var2) {
        person[] var11 = new person[31];
        if (var1 >= 0 && var1 <= 30) {
            if (var2 >= 0 && var2 <= 30) {
                byte var7;
                if (var1 == 0) {
                    var7 = 0;
                } else if (var1 > 0 && var1 < 3) {
                    var7 = 1;
                } else if (var1 > 2 && var1 < 7) {
                    var7 = 2;
                } else if (var1 > 6 && var1 < 15) {
                    var7 = 3;
                } else {
                    var7 = 4;
                }

                int var8 = var1;
                int var5 = var1;
                int var6 = 1;
                int var9 = 0;

                int var3;
                int var4;
                for(var3 = var7; var3 < 5; ++var3) {
                    for(var4 = 0; var4 < var6; ++var4) {
                        var11[var9++] = this.treeArray[var5];
                        this.treeArray[var5] = null;
                        ++var5;
                    }

                    var8 = 2 * var8 + 1;
                    var5 = var8;
                    var6 *= 2;
                }

                if (var2 == 0) {
                    var7 = 0;
                } else if (var2 > 0 && var2 < 3) {
                    var7 = 1;
                } else if (var2 > 2 && var2 < 7) {
                    var7 = 2;
                } else if (var2 > 6 && var2 < 15) {
                    var7 = 3;
                } else {
                    var7 = 4;
                }

                var8 = var2;
                var6 = 1;
                var5 = var2;
                int var10 = 0;

                for(var3 = var7; var3 < 5; ++var3) {
                    for(var4 = 0; var4 < var6; ++var4) {
                        if (var10 < var9) {
                            this.treeArray[var5] = var11[var10++];
                        } else {
                            this.treeArray[var5] = null;
                        }

                        ++var5;
                    }

                    var8 = 2 * var8 + 1;
                    var5 = var8;
                    var6 *= 2;
                }

            }
        }
    }

    public int xyToIndex(int var1, int var2) {
        int var5 = 0;
        int var3 = (var1 - 10) * 2 / 26;
        int var4 = (var2 - 90) / 40;
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

    public void setArrow(int var1) {
        this.oldArrow = this.curInOld;
        this.curIn = this.curInOld = var1;
        this.drawAll = false;
        this.drawMode = 0;
    }

    public void toggleRB() {
        if (this.treeArray[this.curInOld].getRed()) {
            this.treeArray[this.curInOld].setRed(false);
        } else {
            this.treeArray[this.curInOld].setRed(true);
        }

        this.note = "Toggled color at " + this.treeArray[this.curInOld].getHeight();
        this.drawAll = false;
        this.drawMode = 1;
        this.check();
    }

    public void check() {
        int var1 = -1;
        int var2 = -1;
        int var3 = 0;
        int var4 = this.curIn;
        if (this.treeArray[0].getRed()) {
            this.note = "ERROR: Root must be black";
        } else {
            for(int var5 = 30; var5 > 0; --var5) {
                if (this.treeArray[var5] != null && (var5 >= 15 || this.treeArray[2 * var5 + 1] == null || this.treeArray[2 * var5 + 2] == null)) {
                    this.oldArrow = this.curInOld;
                    this.curInOld = this.curIn;
                    this.drawAll = true;
                    this.curIn = var5;
                    var1 = 0;
                    ++var3;

                    for(boolean var6 = false; this.curIn > 0; this.curIn = (this.curIn - 1) / 2) {
                        if (!this.treeArray[this.curIn].getRed()) {
                            ++var1;
                            var6 = false;
                        } else {
                            if (var6) {
                                this.note = "ERROR: parent and child are both red";
                                return;
                            }

                            var6 = true;
                        }

                        this.oldArrow = this.curInOld;
                        this.curInOld = this.curIn;
                        this.drawAll = true;
                    }

                    if (var2 == -1) {
                        var2 = var1;
                    } else {
                        if (var1 != var2) {
                            this.note = "ERROR: Black counts differ";
                            this.oldArrow = this.curInOld;
                            this.curInOld = this.curIn = 0;
                            this.drawAll = true;
                            return;
                        }

                        var1 = 0;
                    }
                }
            }

            if (var3 == 1 && var1 > 0) {
                this.note = "ERROR: Black counts differ";
                this.oldArrow = this.curInOld;
                this.curInOld = this.curIn = 0;
                this.drawAll = true;
            } else {
                this.oldArrow = this.curInOld;
                this.curInOld = this.curIn = var4;
                this.drawAll = true;
                this.note = "Tree is red-black correct";
            }
        }
    }
}
