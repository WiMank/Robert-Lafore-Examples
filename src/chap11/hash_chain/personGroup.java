package chap11.hash_chain;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.awt.*;

class personGroup {
    private final int appletWidth = 440;
    private final int appletHeight = 320;
    private final int topMargin = 70;
    private final int leftMargin = 10;
    private final int textHeight = 13;
    private final int hF1 = 12;
    private final int hF2 = 6;
    private final int hF3 = 0;
    private final int vF = 8;
    private final int columnWidth = 60;
    private final int cellWidth = 35;
    private final int arrowLen = 22;
    private final int cellHeight = 17;
    private final int digits3Width = 18;
    private final int rowHeight = 24;
    private final int noteBoxTop = 45;
    private final int noteBoxHeight = 25;
    private final int noteBoxWidth = 320;
    private final int MAX_BUCKETS = 1000;
    private final int MAX_KEY = 999;
    private final int linesInView = 10;
    private bucket[] bucketArray;
    private int totalBuckets;
    private int nPersons;
    private person tempPers;
    private String note;
    private int fillValue;
    private int insKey;
    private int findKey;
    private int delKey;
    private int codePart;
    private int codePart2;
    private int opMode;
    private int curList;
    private int curLink;
    private int oldCurList;
    private int oldCurLink;
    private int drawMode;
    private int topDisplayLine;

    public personGroup(int var1) {
        this.totalBuckets = var1;
        this.bucketArray = new bucket[this.totalBuckets];

        for (int var2 = 0; var2 < this.totalBuckets; ++var2) {
            this.bucketArray[var2] = new bucket();
        }

        this.curList = this.oldCurList = 0;
        this.nPersons = 0;
        this.codePart = 1;
        this.codePart2 = 1;
        this.drawMode = 2;
        this.topDisplayLine = 0;
    }

    public void setScrollValue(int var1) {
        this.topDisplayLine = var1;
        this.drawMode = 2;
    }

    public int getScrollValue() {
        return this.topDisplayLine;
    }

    public int getScrollRange() {
        return this.totalBuckets;
    }

    public int getLinesInView() {
        return 10;
    }

    public person makePerson(int var1) {
        int var2 = 100 + (int) (Math.random() * 154.0D);
        int var3 = 100 + (int) (Math.random() * 154.0D);
        int var4 = 100 + (int) (Math.random() * 154.0D);
        Color var5 = new Color(var2, var3, var4);
        return new person(var1, var5);
    }

    public void newArray(boolean var1, int var2) {
        if (this.opMode != 1) {
            this.opMode = 1;
            this.codePart = 1;
        }

        switch (this.codePart) {
            case 1:
                this.note = "Enter number of linked lists in array: ";
                this.drawMode = 1;
                this.codePart = 2;
                this.oldCurList = this.curList;
                this.curList = 0;
                this.oldCurLink = this.curLink;
                this.curLink = 0;
                return;
            case 2:
                if (var1 && var2 >= 0 && var2 <= 1000) {
                    this.totalBuckets = var2;
                    this.note = "Will create empty table with " + this.totalBuckets + " lists";
                    this.codePart = 4;
                } else {
                    this.note = "ERROR: use size between 0 and " + 1000;
                    this.codePart = 1;
                }

                this.drawMode = 1;
                return;
            case 3:
            default:
                return;
            case 4:
                this.bucketArray = new bucket[this.totalBuckets];

                for (int var3 = 0; var3 < this.totalBuckets; ++var3) {
                    this.bucketArray[var3] = new bucket();
                }

                this.nPersons = 0;
                this.topDisplayLine = 0;
                this.note = "New table created; total items = " + this.nPersons;
                this.drawMode = 2;
                this.codePart = 6;
                return;
            case 5:
                this.oldCurList = this.curList;
                this.curList = 0;
                this.drawMode = 1;
                this.codePart = 6;
                return;
            case 6:
                this.note = "Press any button";
                this.drawMode = 1;
                this.codePart = 1;
        }
    }

    public void fill(boolean var1, int var2) {
        if (this.opMode != 2) {
            this.opMode = 2;
            this.codePart2 = 1;
        }

        switch (this.codePart2) {
            case 1:
                this.note = "Enter number of items to fill in";
                this.drawMode = 1;
                this.codePart2 = 2;
                return;
            case 2:
                int var3 = this.totalBuckets * 6;
                if (var1 && var2 >= 0 && var2 <= var3) {
                    for (int var4 = 0; var4 < this.totalBuckets; ++var4) {
                        this.bucketArray[var4] = new bucket();
                    }

                    this.fillValue = var2;
                    this.note = "Will fill in " + this.fillValue + " items";
                    this.drawMode = 2;
                    this.codePart2 = 3;
                    return;
                } else {
                    this.note = "ERROR: can't fill more than " + var3 + " items";
                    this.drawMode = 1;
                    this.codePart2 = 1;
                    return;
                }
            case 3:
                this.nPersons = 0;
                this.doFill(this.fillValue);
                this.opMode = 2;
                this.note = "Fill completed; total items = " + this.nPersons;
                this.oldCurList = this.curList;
                this.curList = 0;
                this.drawMode = 3;
                this.codePart2 = 4;
                return;
            case 4:
                this.note = "Press any button";
                this.drawMode = 1;
                this.codePart2 = 1;
                return;
            default:
        }
    }

    public void doFill(int var1) {
        this.oldCurList = this.curList;
        this.curList = 0;
        this.codePart = 1;

        while (this.nPersons < var1) {
            this.insert(true, -1);
            int var2 = (int) (Math.random() * 999.0D);
            this.insert(true, var2);

            while (this.codePart != 1) {
                this.insert(true, -1);
            }
        }

        this.topDisplayLine = 0;
    }

    public void insert(boolean var1, int var2) {
        if (this.opMode != 3) {
            this.opMode = 3;
            this.codePart = 1;
        }

        switch (this.codePart) {
            case 1:
                this.note = "Enter key of item to insert";
                this.drawMode = 1;
                this.codePart = 2;
                return;
            case 2:
                if (var1 && var2 >= 0 && var2 <= 999) {
                    this.insKey = var2;
                    this.tempPers = this.makePerson(this.insKey);
                    this.note = "Will insert item with key " + this.insKey;
                    this.codePart = 3;
                } else {
                    this.note = "CAN'T INSERT: need key between 0 and " + 999;
                    this.codePart = 1;
                }

                this.drawMode = 1;
                return;
            case 3:
                this.oldCurList = this.curList;
                this.curList = this.hashFunction(this.insKey);
                this.topDisplayLine = this.curList;
                this.oldCurLink = this.curLink;
                this.curLink = 0;
                this.note = "Will insert in list " + this.curList;
                this.drawMode = 1;
                this.codePart = 4;
                return;
            case 4:
                if (this.bucketArray[this.curList].nLinks == 6) {
                    this.note = "CAN'T INSERT: list " + this.curList + " is full";
                    this.drawMode = 1;
                    this.codePart = 6;
                    return;
                }

                for (int var3 = this.bucketArray[this.curList].nLinks; var3 > 0; --var3) {
                    this.bucketArray[this.curList].linkArray[var3] = this.bucketArray[this.curList].linkArray[var3 - 1];
                }

                this.bucketArray[this.curList].linkArray[0] = new link(this.tempPers);
                ++this.bucketArray[this.curList].nLinks;
                ++this.nPersons;
                this.note = "Inserted item with key " + this.insKey + " in list " + this.curList;
                this.drawMode = 2;
                this.codePart = 5;
                return;
            case 5:
                this.note = "Insertion completed; total items = " + this.nPersons;
                this.drawMode = 1;
                this.codePart = 6;
                return;
            case 6:
                this.oldCurList = this.curList;
                this.curList = 0;
                this.topDisplayLine = 0;
                this.note = "Press any button";
                this.drawMode = 1;
                this.codePart = 1;
                return;
            default:
        }
    }

    public void find(boolean var1, int var2) {
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
                    this.note = "Will try to find item with key " + this.findKey;
                    this.codePart = 3;
                } else {
                    this.note = "CAN'T FIND: use key between 0 and " + 999;
                    this.codePart = 1;
                }
                break;
            case 3:
                this.oldCurList = this.curList;
                this.curList = this.hashFunction(this.findKey);
                this.topDisplayLine = this.curList;
                this.oldCurLink = this.curLink;
                this.curLink = 0;
                this.note = "Item with key " + this.findKey + " should be in list " + this.curList;
                this.codePart = 4;
                break;
            case 4:
                if (this.bucketArray[this.curList].nLinks == 0) {
                    this.note = "Can't find item with key " + this.findKey;
                    this.codePart = 6;
                } else if (this.bucketArray[this.curList].linkArray[this.curLink].persData.getHeight() == this.findKey) {
                    this.note = "Have found item with key " + this.findKey;
                    this.codePart = 6;
                } else if (this.curLink == this.bucketArray[this.curList].nLinks - 1) {
                    this.note = "Can't find item with key " + this.findKey;
                    this.codePart = 6;
                } else {
                    this.oldCurList = this.curList;
                    this.oldCurLink = this.curLink++;
                    this.note = "Looking for item with key " + this.findKey + " at link " + this.curLink;
                    this.codePart = 4;
                }
            case 5:
            default:
                break;
            case 6:
                this.oldCurList = this.curList;
                this.curList = 0;
                this.oldCurLink = this.curLink;
                this.curLink = 0;
                this.topDisplayLine = 0;
                this.note = "Press any button";
                this.codePart = 1;
        }

        this.drawMode = 1;
    }

    public void delete(boolean var1, int var2) {
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
                    this.note = "Will try to delete item with key " + this.delKey;
                    this.codePart = 3;
                } else {
                    this.note = "CAN'T FIND: use key between 0 and " + 999;
                    this.codePart = 1;
                }

                this.drawMode = 1;
                return;
            case 3:
                this.oldCurList = this.curList;
                this.curList = this.hashFunction(this.delKey);
                this.topDisplayLine = this.curList;
                this.oldCurLink = this.curLink;
                this.curLink = 0;
                this.note = "Item with key " + this.delKey + " should be in list " + this.curList;
                this.drawMode = 1;
                this.codePart = 4;
                return;
            case 4:
                if (this.bucketArray[this.curList].nLinks == 0) {
                    this.note = "Can't find item with key " + this.delKey;
                    this.codePart = 6;
                } else if (this.bucketArray[this.curList].linkArray[this.curLink].persData.getHeight() == this.delKey) {
                    this.note = "Have found item with key " + this.delKey;
                    this.codePart = 5;
                } else if (this.curLink == this.bucketArray[this.curList].nLinks - 1) {
                    this.note = "Can't find item with key " + this.delKey;
                    this.codePart = 6;
                } else {
                    this.oldCurList = this.curList;
                    this.oldCurLink = this.curLink++;
                    this.note = "Looking for item with key " + this.delKey + " at link " + this.curLink;
                    this.codePart = 4;
                }

                this.drawMode = 1;
                return;
            case 5:
                this.bucketArray[this.curList].linkArray[this.curLink] = null;
                int var3 = this.bucketArray[this.curList].nLinks;

                for (int var4 = this.curLink; var4 < var3 - 1; ++var4) {
                    this.bucketArray[this.curList].linkArray[var4] = this.bucketArray[this.curList].linkArray[var4 + 1];
                }

                --this.bucketArray[this.curList].nLinks;
                this.oldCurLink = this.curLink;
                int var10001;
                if (this.curLink > 0) {
                    int var10003 = this.curLink;
                    var10001 = var10003;
                    this.curLink = var10003 - 1;
                } else {
                    var10001 = 0;
                }

                this.curLink = var10001;
                this.note = "Deleted item with key " + this.delKey;
                this.drawMode = 2;
                this.codePart = 6;
                return;
            case 6:
                this.topDisplayLine = 0;
                this.oldCurList = this.curList;
                this.curList = 0;
                this.oldCurLink = this.curLink;
                this.curLink = 0;
                this.note = "Press any button";
                this.drawMode = 2;
                this.codePart = 1;
                return;
            default:
        }
    }

    public int hashFunction(int var1) {
        return var1 % this.totalBuckets;
    }

    public void drawLink(Graphics var1, int var2, int var3) {
        int var4 = 10 + 60 * var3;
        int var5 = 79 + 24 * (var2 - this.topDisplayLine);
        byte var6;
        if (var2 < 10) {
            var6 = 12;
        } else if (var2 < 100) {
            var6 = 6;
        } else {
            var6 = 0;
        }

        if (var3 == 0 && this.drawMode == 2) {
            var1.setColor(Color.black);
            var1.drawString(String.valueOf(var2), var4 + var6, var5 + 17 - 8);
        }

        var1.setColor(Color.black);
        var1.drawRect(var4 + 18 + 5, var5 - 5, 35, 17);
        int var8;
        int var9;
        if (this.bucketArray[var2].linkArray[var3] == null) {
            if (var3 == 0) {
                var1.setColor(Color.lightGray);
                var1.fill3DRect(var4 + 18 + 6, var5 - 4, 34, 16, true);
            }
        } else {
            int var7 = this.bucketArray[var2].linkArray[var3].persData.getHeight();
            var1.setColor(this.bucketArray[var2].linkArray[var3].persData.getColor());
            var1.fill3DRect(var4 + 18 + 6, var5 - 4, 34, 16, true);
            if (var7 < 10) {
                var6 = 12;
            } else if (var7 < 100) {
                var6 = 6;
            } else {
                var6 = 0;
            }

            var1.setColor(Color.black);
            var1.drawString(String.valueOf(var7), var4 + 18 + var6 + 15, var5 + 17 - 8);
            if (var3 != 0) {
                var8 = var4 + 22 - 1;
                var9 = var5 + 8 - 4;
                var1.drawLine(var8, var9, var8 - 22, var9);
                var1.drawLine(var8, var9, var8 - 7, var9 - 4);
                var1.drawLine(var8, var9, var8 - 7, var9 + 4);
            }
        }

        if (var2 == this.curList && var3 == this.curLink) {
            var1.setColor(Color.red);
        } else {
            var1.setColor(Color.lightGray);
        }

        var8 = var4 + 18 + 8 + 35;
        var9 = var5 - 2;
        var1.drawLine(var8, var9, var8 + 16, var9 - 10);
        var1.drawLine(var8, var9 + 1, var8 + 16, var9 - 9);
        var1.drawLine(var8, var9, var8 + 4, var9 - 9);
        var1.drawLine(var8 + 1, var9, var8 + 5, var9 - 9);
        var1.drawLine(var8, var9, var8 + 10, var9);
        var1.drawLine(var8, var9 + 1, var8 + 10, var9 + 1);
    }

    public void draw(Graphics var1) {
        if (this.drawMode == 1) {
            var1.setColor(Color.lightGray);
            var1.fillRect(10, 45, 320, 25);
            if (this.oldCurList >= this.topDisplayLine && this.oldCurList < this.topDisplayLine + 10) {
                this.drawLink(var1, this.oldCurList, this.oldCurLink);
            }

            if (this.curList >= this.topDisplayLine && this.curList < this.topDisplayLine + 10) {
                this.drawLink(var1, this.curList, this.curLink);
            }
        } else {
            if (this.drawMode == 2) {
                var1.setColor(Color.lightGray);
                var1.fillRect(0, 0, 440, 320);
            } else {
                var1.setColor(Color.lightGray);
                var1.fillRect(10, 45, 320, 25);
            }

            for (int var2 = 0; var2 < this.totalBuckets; ++var2) {
                if (var2 >= this.topDisplayLine && var2 < this.topDisplayLine + 10) {
                    this.drawLink(var1, var2, 0);

                    for (int var3 = 1; var3 < this.bucketArray[var2].nLinks && this.bucketArray[var2].linkArray[var3] != null; ++var3) {
                        this.drawLink(var1, var2, var3);
                    }
                }
            }
        }

        var1.setColor(Color.black);
        var1.drawString(this.note, 16, 64);
        this.drawMode = 2;
    }

    public void setDrawMode(int var1) {
        this.drawMode = var1;
    }
}
