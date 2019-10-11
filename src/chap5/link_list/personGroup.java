package chap5.link_list;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.awt.*;

class personGroup {
    private final int appletWidth = 440;
    private final int appletHeight = 300;
    private final int topMargin = 80;
    private final int leftMargin = 10;
    private final int textHeight = 13;
    private final int hF1 = 11;
    private final int hF2 = 5;
    private final int hF3 = 0;
    private final int vF = 8;
    private final int nColumns = 7;
    private final int nRows = 4;
    private final int columnWidth = 57;
    private final int linkWidth = 35;
    private final int interLink = 20;
    private final int linkHeight = 17;
    private final int digits3Width = 18;
    private final int rowHeight = 57;
    private final int noteBoxTop = 55;
    private final int noteBoxHeight = 25;
    private final int noteBoxWidth = 300;
    private final int ASIZE = 28;
    private final int MAX_KEY = 999;
    private link[] linkArray = new link[28];
    private int totalLinks = 0;
    private person tempPers;
    private String note;
    private int fillValue;
    private int insKey;
    private int findKey;
    private int delKey;
    private int codePart;
    private int codePart2;
    private int opMode;
    private int curIn;
    private int oldCurIn;
    private int drawMode;
    private boolean notSorted;
    private boolean isOKChangeSort;
    private int insDex;
    private boolean areInserting;
    private boolean insertAtEnd;
    private int delDex;
    private boolean areDeleting;

    public personGroup() {
        this.curIn = this.oldCurIn = 0;
        this.codePart = 1;
        this.codePart2 = 1;
        this.drawMode = 2;
        this.note = "Press any button";
        this.notSorted = true;
        this.isOKChangeSort = false;
        this.areInserting = false;
    }

    private person makePerson(int var1) {
        int var2 = 100 + (int) (Math.random() * 154.0D);
        int var3 = 100 + (int) (Math.random() * 154.0D);
        int var4 = 100 + (int) (Math.random() * 154.0D);
        Color var5 = new Color(var2, var3, var4);
        return new person(var1, var5);
    }

    public boolean getSortStatus() {
        return this.notSorted;
    }

    public boolean getChangeStatus() {
        return this.isOKChangeSort;
    }

    public void setSortStatus(boolean var1) {
        if (this.isOKChangeSort && var1 != this.notSorted) {
            this.notSorted = var1;
        }

        if (!this.isOKChangeSort) {
            this.note = "To change sort status, create list with New";
        }

        this.drawMode = 1;
    }

    public void newList(boolean var1, int var2) {
        this.areInserting = false;
        this.areDeleting = false;
        if (this.opMode != 1) {
            this.opMode = 1;
            this.codePart = 1;
        }

        switch (this.codePart) {
            case 1:
                this.note = "Enter size of linked list to create";
                this.drawMode = 1;
                this.codePart = 2;
                this.oldCurIn = this.curIn;
                this.curIn = 0;
                return;
            case 2:
                if (var1 && var2 >= 0 && var2 <= 28) {
                    this.note = "Will create list with " + var2 + " links";
                    this.codePart = 3;
                } else {
                    this.note = "ERROR: use size between 0 and " + 28;
                    this.codePart = 1;
                }

                this.drawMode = 1;
                return;
            case 3:
                this.note = "Select unsorted or sorted data";
                this.isOKChangeSort = true;
                this.drawMode = 1;
                this.codePart = 4;
                return;
            case 4:
                if (this.notSorted) {
                    this.note = "Data will not be sorted";
                } else {
                    this.note = "Data will be sorted";
                }

                this.isOKChangeSort = false;
                this.totalLinks = 0;
                this.drawMode = 2;
                this.codePart = 5;
                return;
            case 5:
                this.totalLinks = var2;
                this.doFill(this.totalLinks);
                this.note = "New list created; total links = " + this.totalLinks;
                this.oldCurIn = this.curIn;
                this.curIn = 0;
                this.drawMode = 2;
                this.codePart = 6;
                return;
            case 6:
                this.note = "Press any button";
                this.drawMode = 1;
                this.codePart = 1;
                return;
            default:
        }
    }

    public void doFill(int var1) {
        this.totalLinks = var1;

        int var2;
        for (var2 = 0; var2 < 28; ++var2) {
            this.linkArray[var2] = null;
        }

        this.oldCurIn = this.curIn;
        this.curIn = 0;
        this.codePart = 1;
        int var3;
        if (this.notSorted) {
            for (var2 = 0; var2 < this.totalLinks; ++var2) {
                var3 = (int) (Math.random() * 999.0D);
                this.tempPers = this.makePerson(var3);
                this.linkArray[var2] = new link(this.tempPers);
            }

        } else {
            int var4 = 0;
            int var6 = 0;

            for (var2 = 0; var2 < this.totalLinks; ++var2) {
                int var5 = (int) ((999.0F - (float) var6) / ((float) this.totalLinks - (float) var2));
                var3 = (int) (Math.random() * (double) var5);
                var4 += var3;
                var6 = var4;
                this.tempPers = this.makePerson(var4);
                this.linkArray[var2] = new link(this.tempPers);
            }

        }
    }

    public void insert(boolean var1, int var2) {
        this.areDeleting = false;
        if (this.opMode != 3) {
            this.opMode = 3;
            this.codePart = 1;
        }

        switch (this.codePart) {
            case 1:
                this.oldCurIn = this.curIn;
                this.curIn = 0;
                this.insertAtEnd = false;
                this.note = "Enter key of item to insert";
                this.drawMode = 1;
                this.codePart = 2;
                return;
            case 2:
                if (var1 && var2 >= 0 && var2 <= 999) {
                    if (this.totalLinks >= 28) {
                        this.note = "CAN'T INSERT: no room in display";
                        this.codePart = 6;
                    } else {
                        this.insKey = var2;
                        this.tempPers = this.makePerson(this.insKey);
                        if (this.notSorted) {
                            this.note = "Will insert item with key " + this.insKey;
                            this.codePart = 4;
                        } else {
                            this.note = "Will search for insertion point";
                            this.codePart = 3;
                        }
                    }
                } else {
                    this.note = "CAN'T INSERT: need key between 0 and " + 999;
                    this.codePart = 1;
                }

                this.drawMode = 1;
                return;
            case 3:
                if (this.curIn == this.totalLinks - 1 && this.insKey > this.linkArray[this.curIn].persData.getHeight()) {
                    this.note = "Found insertion point at end of list";
                    this.insertAtEnd = true;
                    this.codePart = 5;
                } else if (this.insKey > this.linkArray[this.curIn].persData.getHeight()) {
                    this.note = "Searching for insertion point";
                    this.oldCurIn = this.curIn++;
                    this.codePart = 3;
                } else {
                    this.note = "Have found insertion point";
                    this.codePart = 4;
                }

                this.drawMode = 1;
                return;
            case 4:
                this.areInserting = true;
                if (this.notSorted) {
                    this.insDex = 0;
                } else {
                    this.insDex = this.curIn;
                }

                this.note = "Inserted item; will redraw list";
                this.drawMode = 1;
                this.codePart = 5;
                return;
            case 5:
                if (this.insertAtEnd) {
                    this.oldCurIn = this.curIn++;
                    this.note = "Inserted item with key " + this.insKey + " at end of list";
                } else {
                    this.areInserting = false;

                    for (int var3 = this.totalLinks; var3 > this.curIn; --var3) {
                        this.linkArray[var3] = this.linkArray[var3 - 1];
                    }

                    this.note = "Inserted item with key " + this.insKey;
                }

                this.linkArray[this.curIn] = new link(this.tempPers);
                ++this.totalLinks;
                this.drawMode = 2;
                this.codePart = 6;
                return;
            case 6:
                this.note = "Insertion completed; total items = " + this.totalLinks;
                this.drawMode = 1;
                this.codePart = 7;
                return;
            case 7:
                this.oldCurIn = this.curIn;
                this.curIn = 0;
                this.note = "Press any button";
                this.drawMode = 1;
                this.codePart = 1;
                return;
            default:
        }
    }

    public void find(boolean var1, int var2) {
        this.areInserting = false;
        this.areDeleting = false;
        if (this.opMode != 4) {
            this.opMode = 4;
            this.codePart = 1;
        }

        switch (this.codePart) {
            case 1:
                this.note = "Enter key of item to find";
                this.codePart = 2;
                break;
            case 2:
                if (var1 && var2 >= 0 && var2 <= 999) {
                    this.findKey = var2;
                    this.oldCurIn = this.curIn;
                    this.curIn = 0;
                    this.note = "Looking for item with key " + this.findKey;
                    this.codePart = 3;
                } else {
                    this.note = "ERROR: use key between 0 and " + 999;
                    this.codePart = 1;
                }
                break;
            case 3:
                if (this.linkArray[this.curIn].persData.getHeight() == this.findKey) {
                    this.note = "Have found item with key " + this.findKey;
                    this.codePart = 6;
                } else if (this.curIn == this.totalLinks - 1 || !this.notSorted && this.linkArray[this.curIn].persData.getHeight() > this.findKey) {
                    this.note = "Can't locate item with key " + this.findKey;
                    this.codePart = 6;
                } else {
                    this.note = "Searching for item with key " + this.findKey;
                    this.oldCurIn = this.curIn++;
                    this.codePart = 3;
                }
            case 4:
            case 5:
            default:
                break;
            case 6:
                this.oldCurIn = this.curIn;
                this.curIn = 0;
                this.note = "Press any button";
                this.codePart = 1;
        }

        this.drawMode = 1;
    }

    public void delete(boolean var1, int var2) {
        this.areInserting = false;
        if (this.opMode != 5) {
            this.opMode = 5;
            this.codePart = 1;
        }

        switch (this.codePart) {
            case 1:
                this.note = "Enter key of item to delete";
                this.drawMode = 1;
                this.codePart = 2;
                return;
            case 2:
                if (var1 && var2 >= 0 && var2 <= 999) {
                    this.delKey = var2;
                    this.oldCurIn = this.curIn;
                    this.curIn = 0;
                    this.note = "Looking for item with key " + this.delKey;
                    this.codePart = 3;
                } else {
                    this.note = "ERROR: use key between 0 and " + 999;
                    this.codePart = 1;
                }

                this.drawMode = 1;
                return;
            case 3:
                if (this.linkArray[this.curIn].persData.getHeight() == this.delKey) {
                    this.note = "Have found item with key " + this.delKey;
                    if (this.curIn == this.totalLinks - 1) {
                        this.codePart = 5;
                    } else {
                        this.codePart = 4;
                    }
                } else if (this.curIn != this.totalLinks - 1 && (this.notSorted || this.linkArray[this.curIn].persData.getHeight() <= this.delKey)) {
                    this.note = "Searching for item with key " + this.delKey;
                    this.oldCurIn = this.curIn++;
                    this.codePart = 3;
                } else {
                    this.note = "Can't locate item with key " + this.delKey;
                    this.codePart = 6;
                }

                this.drawMode = 1;
                return;
            case 4:
                this.areDeleting = true;
                this.delDex = this.curIn;
                this.note = "Deleted item; will redraw list";
                this.drawMode = 1;
                this.codePart = 5;
                return;
            case 5:
                this.areDeleting = false;

                for (int var3 = this.curIn; var3 < this.totalLinks - 1; ++var3) {
                    this.linkArray[var3] = this.linkArray[var3 + 1];
                }

                --this.totalLinks;
                this.oldCurIn = this.curIn;
                this.curIn = 0;
                this.note = "Deleted item with key " + this.delKey;
                this.drawMode = 2;
                this.codePart = 6;
                return;
            case 6:
                this.oldCurIn = this.curIn;
                this.curIn = 0;
                this.note = "Press any button";
                this.codePart = 1;
                return;
            default:
        }
    }

    public void drawLink(Graphics var1, int var2) {
        int var3 = 32 + 57 * (var2 % 7);
        int var4 = 89 + 57 * (var2 / 7);
        int var7;
        if (this.linkArray[this.curIn] != null) {
            var1.setColor(Color.black);
            var1.drawRect(var3, var4 - 5, 35, 17);
            int var6 = this.linkArray[var2].persData.getHeight();
            var1.setColor(this.linkArray[var2].persData.getColor());
            var1.fill3DRect(var3 + 1, var4 - 4, 34, 16, true);
            byte var5;
            if (var6 < 10) {
                var5 = 11;
            } else if (var6 < 100) {
                var5 = 5;
            } else {
                var5 = 0;
            }

            var1.setColor(Color.black);
            var1.drawString(String.valueOf(var6), var3 + var5 + 10, var4 + 17 - 8);
            if (var2 < this.totalLinks - 1) {
                if (var2 % 7 != 6) {
                    var1.drawLine(var3 + 35, var4 + 8 - 4, var3 + 35 + 20, var4 + 8 - 4);
                    this.smallArrow(var1, var3 + 35 + 20, var4 + 8 - 4);
                } else {
                    var7 = var4 + 8 - 4;
                    var1.drawLine(var3 + 35, var7, var3 + 35 + 8, var7);
                    var1.drawLine(var3 + 35 + 8, var7, var3 + 35 + 8, var7 + 34 + 4);
                    var1.drawLine(var3 + 35 + 8, var7 + 34 + 4, 10, var7 + 34 + 4);
                    var1.drawLine(10, var7 + 34 + 4, 10, var7 + 57);
                    var1.drawLine(10, var7 + 57, 30, var7 + 57);
                    this.smallArrow(var1, 30, var7 + 57);
                }
            }
        }

        if (var2 == this.curIn && !this.areInserting) {
            var1.setColor(Color.red);
        } else {
            var1.setColor(Color.lightGray);
        }

        var7 = var3 + 17;
        int var8 = var4 + 17 - 3;
        var1.drawLine(var7, var8, var7, var8 + 19);
        var1.drawLine(var7 + 1, var8, var7 + 1, var8 + 19);
        var1.drawLine(var7, var8, var7 + 6, var8 + 10);
        var1.drawLine(var7 + 1, var8, var7 + 7, var8 + 10);
        var1.drawLine(var7, var8, var7 - 6, var8 + 10);
        var1.drawLine(var7 + 1, var8, var7 - 5, var8 + 10);
    }

    private void drawInsertLink(Graphics var1) {
        int var2 = 15 + 57 * (this.insDex % 7);
        int var3 = 115 + 57 * (this.insDex / 7);
        var1.setColor(Color.black);
        var1.drawRect(var2, var3 - 5, 35, 17);
        int var5 = this.tempPers.getHeight();
        var1.setColor(this.tempPers.getColor());
        var1.fill3DRect(var2 + 1, var3 - 4, 34, 16, true);
        byte var4;
        if (var5 < 10) {
            var4 = 11;
        } else if (var5 < 100) {
            var4 = 5;
        } else {
            var4 = 0;
        }

        var1.setColor(Color.black);
        var1.drawString(String.valueOf(var5), var2 + var4 + 10, var3 + 17 - 8);
        int var6 = var3 + 8 - 3;
        if (this.insDex != 0) {
            var1.setColor(Color.lightGray);
            var1.drawLine(var2 - 4, var6 - 27, var2 + 8 + 7, var6 - 27);
            var1.setColor(Color.black);
            var1.drawLine(var2 - 4, var6 - 27, var2 + 2, var6 - 27);
            var1.drawLine(var2 + 2, var6 - 27, var2 + 2, var6 - 14);
            var1.drawLine(var2 + 2, var6 - 14, var2 - 8, var6 - 14);
            var1.drawLine(var2 - 8, var6 - 14, var2 - 8, var6 - 1);
            var1.drawLine(var2 - 8, var6 - 1, var2 - 1, var6 - 1);
            this.smallArrow(var1, var2 - 1, var6 - 1);
        }

        var1.drawLine(var2 + 35, var6 - 1, var2 + 35 + 8, var6 - 1);
        var1.drawLine(var2 + 35 + 8, var6 - 1, var2 + 35 + 8, var6 - 14);
        var1.drawLine(var2 + 35 + 8, var6 - 14, var2 + 8, var6 - 14);
        var1.drawLine(var2 + 8, var6 - 14, var2 + 8, var6 - 27);
        var1.drawLine(var2 + 8, var6 - 27, var2 + 8 + 7, var6 - 27);
        this.smallArrow(var1, var2 + 8 + 7, var6 - 27);
    }

    private void drawDeleteLink(Graphics var1) {
        int var2 = 32 + 57 * (this.delDex % 7);
        int var3 = 89 + 57 * (this.delDex / 7);
        var1.setColor(Color.lightGray);
        var1.fillRect(var2 - 10, var3 - 5, 46, 18);
        if (this.delDex == this.totalLinks - 1) {
            var1.setColor(Color.lightGray);
        } else {
            var1.setColor(Color.black);
        }

        var1.drawLine(var2 - 20 - 1, var3 + 8 - 4, var2 + 35 + 7, var3 + 8 - 4);
    }

    private void smallArrow(Graphics var1, int var2, int var3) {
        var1.drawLine(var2, var3, var2 - 5, var3 - 3);
        var1.drawLine(var2, var3, var2 - 5, var3 + 3);
    }

    public void draw(Graphics var1) {
        if (this.drawMode == 1) {
            var1.setColor(Color.lightGray);
            var1.fillRect(10, 55, 300, 25);
            var1.setColor(Color.black);
            var1.drawString(this.note, 16, 74);
            this.drawLink(var1, this.oldCurIn);
            this.drawLink(var1, this.curIn);
            this.drawMode = 2;
        } else {
            var1.setColor(Color.lightGray);
            var1.fillRect(0, 0, 440, 300);

            for (int var2 = 0; var2 < this.totalLinks; ++var2) {
                this.drawLink(var1, var2);
            }

            var1.setColor(Color.black);
            var1.drawString(this.note, 16, 74);
        }

        if (this.areInserting) {
            this.drawInsertLink(var1);
        } else {
            if (this.areDeleting) {
                this.drawDeleteLink(var1);
            }

        }
    }
}

