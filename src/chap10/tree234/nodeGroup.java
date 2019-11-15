package chap10.tree234;

import java.awt.Color;
import java.awt.Graphics;

class nodeGroup {
    private final int appletWidth = 440;
    private final int appletHeight = 300;
    private final int topMargin = 70;
    private final int leftMargin = 10;
    private final int zoomLeftMargin = 30;
    private final int textHeight = 13;
    private final int digits3Width = 13;
    private final int hF1 = 10;
    private final int hF2 = 7;
    private final int hF3 = 4;
    private final int vF = 8;
    private final int nodeWidth = 80;
    private final int nodeHeight = 25;
    private final int nodeArcDiam = 15;
    private final int personWidth = 26;
    private final int horizSeparation = 105;
    private final int levelSeparation = 60;
    private final int noteBoxTop = 45;
    private final int noteBoxHeight = 25;
    private final int noteBoxWidth = 300;
    private final int smallAppletWidth = 384;
    private final int smallNodeWidth = 4;
    private final int smallNodeHeight = 6;
    private final int ASIZE = 85;
    private final int MAX_KEY = 999;
    private node root = new node();
    private node[] nodeArray = new node[85];
    private String note = "Press a button to start";
    private int value;
    private int codePart = 1;
    private int codePart2 = 1;
    private int opMode;
    private node curNo;
    private int lev1View;
    private int lev2View;
    private boolean usedLowestLevel = false;
    private boolean zoomIn = true;
    private person tempPers;
    private int start1;
    private int start2;
    private int oldStart1;
    private int oldStart2;
    private int curIn;
    private int oldCurIn;
    private boolean viewSetByMouse = true;
    private int drawMode;
    private int utilNumber;

    public nodeGroup() {
        this.curNo = this.root;
        this.curNo.setCurrentNode(true);
    }

    public void toggleZoom() {
        this.drawMode = 2;
        this.zoomIn = !this.zoomIn;
    }

    public person makePerson(int var1) {
        int var2 = 100 + (int)(Math.random() * 154.0D);
        int var3 = 100 + (int)(Math.random() * 154.0D);
        int var4 = 100 + (int)(Math.random() * 154.0D);
        Color var5 = new Color(var2, var3, var4);
        return new person(var1, var5);
    }

    public void setView(int var1, int var2) {
        if (this.zoomIn) {
            this.drawMode = 2;
            int var3 = (var2 - 70) / 60;
            int var4 = (var1 - 10) / 105;
            if (var3 == 1) {
                this.lev1View = var4;
            }

            if (var3 == 2) {
                this.lev2View = var4;
            }

            this.viewSetByMouse = true;
        }
    }

    public void fill(boolean var1, int var2) {
        if (this.zoomIn) {
            if (this.opMode != 3 || this.codePart == 1) {
                if (this.opMode != 1) {
                    this.opMode = 1;
                    this.codePart2 = 1;
                }

                switch(this.codePart2) {
                    case 1:
                        this.note = "Enter number of items to fill in";
                        this.curNo.setCurrentNode(false);
                        this.curNo = this.root;
                        this.curNo.setCurrentNode(true);
                        this.drawMode = 1;
                        this.codePart2 = 2;
                        return;
                    case 2:
                        if (var1 && var2 >= 0 && var2 <= 45) {
                            this.doFill(var2);
                            this.opMode = 1;
                            this.note = "Fill completed";
                            this.drawMode = 2;
                            this.codePart2 = 6;
                            return;
                        }

                        this.note = "ERROR: use number between 0 and 45";
                        this.drawMode = 1;
                        this.codePart2 = 1;
                        return;
                    case 6:
                        this.note = "Press any button";
                        this.drawMode = 1;
                        this.codePart2 = 1;
                        return;
                    default:
                }
            }
        }
    }

    public void doFill(int var1) {
        this.root = new node();
        this.usedLowestLevel = false;

        for(int var2 = 0; var2 < var1; ++var2) {
            this.insert(true, 1000);
            this.insert(true, (int)(Math.random() * 999.0D));

            while(this.codePart != 1) {
                this.insert(true, 1000);
            }
        }

    }

    public void find(boolean var1, int var2) {
        if (this.zoomIn) {
            if (this.opMode != 2) {
                this.opMode = 2;
                this.codePart = 1;
            }

            switch(this.codePart) {
                case 1:
                    this.note = "Enter value of item to find";
                    this.curNo.setCurrentNode(false);
                    this.curNo = this.root;
                    this.curNo.setCurrentNode(true);
                    this.drawMode = 1;
                    this.codePart = 2;
                    return;
                case 2:
                    if (var1 && var2 >= 0 && var2 <= 999) {
                        this.value = var2;
                        this.note = "Will find item with value " + this.value;
                        this.codePart = 3;
                    } else {
                        this.note = "Items have values from 0 to " + 999;
                        this.codePart = 1;
                    }

                    this.drawMode = 1;
                    return;
                case 3:
                    if ((this.utilNumber = this.curNo.findItem((double)this.value)) != -1) {
                        this.note = "Found item; number " + this.utilNumber + " in this node";
                        this.viewSetByMouse = false;
                        this.drawMode = 1;
                        this.codePart = 5;
                        return;
                    } else {
                        if (this.curNo.isLeaf()) {
                            this.note = "Can't find item";
                            this.viewSetByMouse = false;
                            this.drawMode = 1;
                            this.codePart = 5;
                            return;
                        }

                        this.curNo.setCurrentNode(false);
                        this.curNo = this.getNextChild(this.curNo, this.value);
                        this.curNo.setCurrentNode(true);
                        this.note = "Went to child number " + this.utilNumber;
                        this.viewSetByMouse = false;
                        this.drawMode = 1;
                        this.codePart = 3;
                        return;
                    }
                case 4:
                default:
                    return;
                case 5:
                    this.note = "Search completed";
                    this.drawMode = 1;
                    this.codePart = 6;
                    return;
                case 6:
                    this.note = "Press any button";
                    this.drawMode = 1;
                    this.codePart = 1;
            }
        }
    }

    public void insert(boolean var1, int var2) {
        if (this.zoomIn) {
            if (this.opMode != 3) {
                this.opMode = 3;
                this.codePart = 1;
            }

            switch(this.codePart) {
                case 1:
                    this.note = "Enter value of item to insert";
                    this.curNo.setCurrentNode(false);
                    this.curNo = this.root;
                    this.curNo.setCurrentNode(true);
                    this.drawMode = 1;
                    this.codePart = 2;
                    return;
                case 2:
                    if (var1 && var2 >= 0 && var2 <= 999) {
                        this.value = var2;
                        this.tempPers = this.makePerson(this.value);
                        this.note = "Will insert item with value " + this.value;
                        this.codePart = 3;
                    } else {
                        this.note = "Items have values from 0 to " + 999;
                        this.codePart = 1;
                    }

                    this.drawMode = 1;
                    return;
                case 3:
                    if (this.curNo.isFull()) {
                        int var3 = this.split(this.curNo);
                        if (var3 != -1) {
                            this.note = "Node was split";
                            if (this.curNo != this.root) {
                                this.curNo.setCurrentNode(false);
                                this.curNo = this.curNo.getParent();
                                this.curNo.setCurrentNode(true);
                            }

                            this.curNo.setCurrentNode(false);
                            this.curNo = this.getNextChild(this.curNo, this.value);
                            this.curNo.setCurrentNode(true);
                            this.viewSetByMouse = false;
                            this.drawMode = 2;
                            this.codePart = 3;
                            return;
                        }

                        this.note = "Tree depth too great, CAN'T INSERT";
                        this.curNo.setCurrentNode(false);
                        this.curNo = this.root;
                        this.curNo.setCurrentNode(true);
                        this.drawMode = 1;
                        this.codePart = 6;
                        return;
                    } else {
                        if (this.curNo.isLeaf()) {
                            this.curNo.insertPers(this.tempPers);
                            this.note = "Inserted new item in leaf";
                            this.viewSetByMouse = false;
                            this.drawMode = 1;
                            this.codePart = 5;
                            return;
                        }

                        this.curNo.setCurrentNode(false);
                        this.curNo = this.getNextChild(this.curNo, this.value);
                        this.curNo.setCurrentNode(true);
                        this.note = "Searching for insertion point";
                        this.viewSetByMouse = false;
                        this.drawMode = 1;
                        this.codePart = 3;
                        return;
                    }
                case 4:
                default:
                    return;
                case 5:
                    this.note = "Insertion completed";
                    this.drawMode = 1;
                    this.codePart = 6;
                    return;
                case 6:
                    this.note = "Press any button";
                    this.drawMode = 1;
                    this.codePart = 1;
            }
        }
    }

    public int split(node var1) {
        person var3 = var1.getp(0);
        person var4 = var1.getp(1);
        person var5 = var1.getp(2);
        node var6 = var1.getChild(0);
        node var7 = var1.getChild(1);
        node var8 = var1.getChild(2);
        node var9 = var1.getChild(3);
        node var11;
        if (var1 == this.root) {
            if (this.usedLowestLevel) {
                return -1;
            }

            this.root.clear();
            node var10 = new node();
            this.root.connectChild(0, var10);
            var11 = new node();
            this.root.connectChild(1, var11);
            var10.insertPers(var3);
            this.root.insertPers(var4);
            var11.insertPers(var5);
            this.root.connectChild(0, var10);
            this.root.connectChild(1, var11);
            this.root.connectChild(2, (node)null);
            this.root.connectChild(3, (node)null);
            if (var6 != null) {
                var10.connectChild(0, var6);
                if (var7 != null) {
                    var10.connectChild(1, var7);
                    if (var8 != null) {
                        var11.connectChild(0, var8);
                        if (var9 != null) {
                            var11.connectChild(1, var9);
                        }
                    }
                }
            }
        } else {
            int var14 = var1.getChildNumber();
            var11 = var1.getParent();
            int var12 = var11.getNumPers() + 1;

            node var13;
            for(int var2 = var12 - 1; var2 > var14; --var2) {
                var13 = var11.getChild(var2);
                var11.connectChild(var2 + 1, var13);
            }

            var13 = new node();
            var11.connectChild(var14 + 1, var13);
            var1.clear();
            var1.insertPers(var3);
            var11.insertPers(var4);
            var13.insertPers(var5);
            if (var8 != null) {
                var13.connectChild(0, var8);
                var1.connectChild(2, (node)null);
                if (var9 != null) {
                    var13.connectChild(1, var9);
                    var1.connectChild(3, (node)null);
                }
            }
        }

        return 0;
    }

    public node getNextChild(node var1, int var2) {
        int var4 = var1.getNumPers();

        int var3;
        for(var3 = 0; var3 < var4; ++var3) {
            if (var2 < var1.getp(var3).getHeight()) {
                this.utilNumber = var3;
                return var1.getChild(var3);
            }
        }

        this.utilNumber = var3;
        return var1.getChild(var3);
    }

    public void drawOneNode(Graphics var1, int var2) {
        if (this.nodeArray[var2] != null) {
            byte var10 = 0;
            int var3 = (var2 - 1) % 4;
            byte var4;
            if (var2 == 0) {
                var4 = 0;
            } else if (var2 > 0 && var2 < 5) {
                var4 = 1;
            } else if (var2 > 4 && var2 < 21) {
                var4 = 2;
            } else {
                var4 = 3;
            }

            int var5;
            if (var4 == 0) {
                var5 = 167;
            } else {
                var5 = 10 + var3 * 105;
            }

            int var6 = 70 + var4 * 60;
            this.drawPerson(var1, var5, var6, var2, 0);
            this.drawPerson(var1, var5, var6, var2, 2);
            this.drawPerson(var1, var5, var6, var2, 1);
            var1.setColor(Color.lightGray);
            var1.fillRect(var5 + 80 + 2, var6, 13, 13);
            var1.setColor(Color.black);
            var1.drawString(String.valueOf(var2), var5 + 80 + 2, var6 + 12);
            var1.drawLine(var5 + 26 + 1, var6, var5 + 26 + 1, var6 + 25);
            var1.drawLine(var5 + 52 + 2, var6, var5 + 52 + 2, var6 + 25);
            var1.drawRoundRect(var5, var6, 80, 25, 15, 15);
            if (this.nodeArray[var2].areWeCurrentNode()) {
                var1.setColor(Color.red);
            } else {
                var1.setColor(Color.lightGray);
            }

            int var11 = var5 + 40;
            var1.drawLine(var11, var6 - 1, var11, var6 - 20);
            var1.drawLine(var11 - 1, var6 - 1, var11 - 1, var6 - 20);
            var1.drawLine(var11, var6 - 1, var11 - 3, var6 - 7);
            var1.drawLine(var11 - 1, var6 - 1, var11 - 4, var6 - 7);
            var1.drawLine(var11, var6 - 1, var11 + 3, var6 - 7);
            var1.drawLine(var11 - 1, var6 - 1, var11 + 2, var6 - 7);

            int var13;
            int var14;
            for(int var12 = 0; var12 < 4 && this.nodeArray[var2].getChild(var12) != null; ++var12) {
                var13 = var5;
                switch(var12) {
                    case 0:
                        var13 = var5 + 7;
                        break;
                    case 1:
                        var13 = var5 + 26 + 1;
                        break;
                    case 2:
                        var13 = var5 + 52 + 2;
                        break;
                    case 3:
                        var13 = var5 + 80 - 7;
                }

                var1.setColor(Color.blue);

                for(var14 = 0; var14 < 5; ++var14) {
                    var1.drawLine(var13 - var14, var6 + 25 - 4 + var14, var13 + var14, var6 + 25 - 4 + var14);
                }
            }

            if (var2 != 0) {
                node var16 = this.nodeArray[var2].getParent();
                var14 = this.nodeArray[var2].getChildNumber();
                node var15 = var16.getChild(var14);
                if (var15 != this.nodeArray[var2]) {
                    this.note = "ERROR: parent wrong in node " + var2;
                    var1.setColor(Color.black);
                    var1.drawLine(var5, var6, var5 + 80, var6 + 25);
                    var1.drawLine(var5, var6 + 1, var5 + 80, var6 + 25 + 1);
                    var1.drawLine(var5, var6 + 2, var5 + 80, var6 + 25 + 2);
                }
            }

            if (var2 != 0) {
                var13 = (var2 - 1) / 4;
                var1.setColor(Color.black);
                switch(var3) {
                    case 0:
                        var10 = 7;
                        break;
                    case 1:
                        var10 = 27;
                        break;
                    case 2:
                        var10 = 54;
                        break;
                    case 3:
                        var10 = 73;
                }

                int var7;
                if (var13 == 0) {
                    var7 = 167 + var10;
                } else {
                    int var9 = (var13 - 1) % 4;
                    var7 = 10 + var9 * 105 + var10;
                }

                int var8 = var6 - 60 + 25;
                var1.drawLine(var5 + 40, var6, var7, var8);
            }
        }
    }

    public void drawPerson(Graphics var1, int var2, int var3, int var4, int var5) {
        int var7;
        Color var8;
        if (this.nodeArray[var4].getp(var5) == null) {
            var7 = -9999;
            var8 = Color.lightGray;
        } else {
            var7 = this.nodeArray[var4].getp(var5).getHeight();
            var8 = this.nodeArray[var4].getp(var5).getColor();
        }

        var1.setColor(var8);
        int var9 = var5 == 2 ? var2 + 40 : var2;
        if (var5 == 1) {
            var1.fillRect(var2 + 26 + 1, var3, 28, 25);
        } else {
            var1.fillRoundRect(var9, var3, 40, 25, 15, 15);
        }

        byte var6;
        if (var7 < 10) {
            var6 = 10;
        } else if (var7 < 100) {
            var6 = 7;
        } else {
            var6 = 4;
        }

        if (var7 != -9999) {
            var1.setColor(Color.black);
            var1.drawString(String.valueOf(var7), var2 + var6 + var5 * 26, var3 + 25 - 8);
        }

    }

    public void transfer(node var1, int var2) {
        if (var2 > 20) {
            this.usedLowestLevel = true;
        }

        if (var1.areWeCurrentNode()) {
            this.curIn = var2;
        }

        this.nodeArray[var2] = var1;
        if (!var1.isLeaf()) {
            for(int var3 = 0; var3 < 4; ++var3) {
                node var4 = var1.getChild(var3);
                if (var4 == null) {
                    break;
                }

                int var5 = var2 * 4 + var3 + 1;
                this.transfer(var4, var5);
            }

        }
    }

    public void draw(Graphics var1) {
        int var2;
        for(var2 = 0; var2 < 85; ++var2) {
            this.nodeArray[var2] = null;
        }

        this.transfer(this.root, 0);
        if (this.viewSetByMouse) {
            this.start1 = 5 + this.lev1View * 4;
            this.start2 = 21 + this.lev1View * 16 + this.lev2View * 4;
        } else if (this.curIn > 0 && this.curIn < 5) {
            if (this.nodeArray[this.start1] != null && this.nodeArray[this.start1].getParent() != this.nodeArray[this.curIn]) {
                this.start1 = this.curIn * 4 + 1;
            }

            if (this.nodeArray[this.start2] != null && this.nodeArray[this.start1] != null) {
                node var3 = this.nodeArray[this.start2].getParent();
                if (var3 != null) {
                    node var4 = var3.getParent();
                    if (var4 != this.nodeArray[this.curIn]) {
                        this.start2 = this.start1 * 4 + 1;
                    }
                }
            }
        } else if (this.curIn > 4 && this.curIn < 21) {
            this.start1 = (this.curIn - 5) / 4 * 4 + 5;
            this.start2 = this.curIn * 4 + 1;
        } else if (this.curIn > 20 && this.curIn < 85) {
            this.start1 = (this.curIn - 21) / 16 * 4 + 5;
            this.start2 = (this.curIn - 21) / 4 * 4 + 21;
        }

        if (this.oldStart1 != this.start1 || this.oldStart2 != this.start2 && this.usedLowestLevel) {
            this.drawMode = 2;
        }

        this.oldStart1 = this.start1;
        this.oldStart2 = this.start2;
        if (this.drawMode == 1) {
            var1.setColor(Color.lightGray);
            var1.fillRect(10, 45, 300, 25);
            var1.setColor(Color.black);
            var1.drawString(this.note, 16, 64);
            if (this.oldCurIn < 5 || this.oldCurIn > 4 && this.oldCurIn < 21 && this.oldCurIn - this.start1 < 4 && this.oldCurIn - this.start1 >= 0 || this.oldCurIn > 20 && this.oldCurIn < 85 && this.oldCurIn - this.start2 < 4 && this.oldCurIn - this.start2 >= 0) {
                this.drawOneNode(var1, this.oldCurIn);
            }

            if (this.curIn < 5 || this.curIn > 4 && this.curIn < 21 && this.curIn - this.start1 < 4 && this.curIn - this.start1 >= 0 || this.curIn > 20 && this.curIn < 85 && this.curIn - this.start2 < 4 && this.curIn - this.start2 >= 0) {
                this.drawOneNode(var1, this.curIn);
            }

            this.drawMode = 2;
        } else {
            var1.setColor(Color.lightGray);
            var1.fillRect(0, 0, 440, 300);
            if (!this.zoomIn) {
                this.drawSmall(var1, 0, 0, -1, -1);
                this.boxAndText(var1, Color.green, 0, "Node exists and is visible in closeup view");
                this.boxAndText(var1, Color.magenta, 15, "Node exists but is not visible");
                this.boxAndText(var1, Color.gray, 30, "Node does not exist");
            } else {
                if (this.nodeArray[0] != null) {
                    this.drawOneNode(var1, 0);
                }

                for(var2 = 0; var2 < 4; ++var2) {
                    if (this.nodeArray[var2 + 1] != null) {
                        this.drawOneNode(var1, var2 + 1);
                    }

                    if (this.nodeArray[this.start1 + var2] != null) {
                        this.drawOneNode(var1, this.start1 + var2);
                    }

                    if (this.nodeArray[this.start2 + var2] != null) {
                        this.drawOneNode(var1, this.start2 + var2);
                    }
                }

                var1.setColor(Color.black);
                var1.drawString(this.note, 16, 64);
            }
        }

        this.oldCurIn = this.curIn;
    }

    public void boxAndText(Graphics var1, Color var2, int var3, String var4) {
        var1.setColor(var2);
        var1.fillRect(10, var3 + 70 - 6 - 10, 4, 6);
        var1.setColor(Color.black);
        var1.drawString(var4, 20, var3 + 70 - 10);
    }

    public void drawSmall(Graphics var1, int var2, int var3, int var4, int var5) {
        byte var6 = 30;
        short var9 = 384;
        int var10 = var9 / 4;
        int var11 = var9 / 16;
        int var12 = var9 / 64;
        byte var13 = 60;
        byte var14 = 85;
        int var7;
        int var16;
        if (var2 == 0) {
            var16 = var6 + var9 / 2;
            var7 = var14;
        } else if (var2 == 1) {
            var16 = var6 + var10 / 2 + var10 * (var3 - 1);
            var7 = var14 + var13;
        } else if (var2 == 2) {
            var16 = var6 + var11 / 2 + var11 * (var3 - 5);
            var7 = var14 + 2 * var13;
        } else if (var2 == 3) {
            var16 = var6 + var12 / 2 + var12 * (var3 - 21);
            var7 = var14 + 3 * var13;
        } else {
            var16 = -1;
            var7 = -1;
        }

        var1.setColor(Color.gray);
        var1.drawRect(var16, var7, 4, 6);
        if (var3 != 0) {
            var1.drawLine(var16 + 2, var7, var4 + 2, var5 + 6);
        }

        if (this.nodeArray[var3] == null) {
            var1.setColor(Color.gray);
        } else if ((var3 < this.start1 || var3 >= this.start1 + 4) && (var3 < this.start2 || var3 >= this.start2 + 4) && var3 >= 5) {
            var1.setColor(Color.magenta);
        } else {
            var1.setColor(Color.green);
        }

        var1.fillRect(var16, var7, 4, 6);
        if (var2 < 3) {
            for(int var15 = 0; var15 < 4; ++var15) {
                this.drawSmall(var1, var2 + 1, var3 * 4 + var15 + 1, var16, var7);
            }
        }

    }
}

