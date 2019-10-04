package chap2.array;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.awt.*;

class personGroup {
    private final int appletWidth = 440;
    private final int appletHeight = 320;
    private final int topMargin = 80;
    private final int leftMargin = 10;
    private final int textHeight = 13;
    private final int hF1 = 12;
    private final int hF2 = 6;
    private final int hF3 = 0;
    private final int vF = 8;
    private final int nColumns = 5;
    private final int nCellsPerCol = 12;
    private final int columnWidth = 85;
    private final int cellWidth = 35;
    private final int cellHeight = 17;
    private final int digits3Width = 18;
    private final int vertSeparation = 19;
    private final int horizSeparation = 60;
    private final int noteBoxTop = 55;
    private final int noteBoxHeight = 25;
    private final int noteBoxWidth = 325;
    private final int ASIZE = 60;
    private final int MAX_KEY = 999;
    private person[] personArray;
    private int totalCells;
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
    private int curIn;
    private int oldCurIn;
    private int lastDeletion;
    private int drawMode;
    private boolean dupsOK;
    private boolean isOKChangeDups;
    private boolean wasJustFound;
    private boolean madeDeletion;
    private int nDeleted;

    public personGroup(int var1) {
        this.totalCells = var1;
        this.personArray = new person[this.totalCells];
        this.curIn = this.oldCurIn = 0;
        this.nPersons = 0;
        this.codePart = 1;
        this.codePart2 = 1;
        this.drawMode = 2;
        this.dupsOK = false;
        this.note = "Press any button";
    }

    public void setDrawMode(int var1) {
        this.drawMode = var1;
    }

    public boolean getDupsStatus() {
        return this.dupsOK;
    }

    public boolean getChangeStatus() {
        return this.isOKChangeDups;
    }

    public void setDupsStatus(boolean var1) {
        if (this.isOKChangeDups && var1 != this.dupsOK) {
            this.dupsOK = var1;
        }

        if (!this.isOKChangeDups) {
            this.note = "To change duplication status, create array with New";
        }

        this.drawMode = 1;
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
                this.note = "Enter size of array to create";
                this.drawMode = 1;
                this.codePart = 2;
                this.oldCurIn = this.curIn;
                this.curIn = 0;
                return;
            case 2:
                if (var1 && var2 >= 0 && var2 <= 60) {
                    this.totalCells = var2;
                    this.note = "Will create empty array with " + this.totalCells + " cells";
                    this.codePart = 3;
                } else {
                    this.note = "ERROR: use size between 0 and " + 60;
                    this.codePart = 1;
                }

                this.drawMode = 1;
                return;
            case 3:
                this.note = "Select Duplicates OK, or No Dups";
                this.isOKChangeDups = true;
                this.drawMode = 1;
                this.codePart = 5;
                return;
            case 4:
            default:
                return;
            case 5:
                this.personArray = new person[this.totalCells];

                for (int var3 = 0; var3 < this.totalCells; ++var3) {
                    this.personArray[var3] = null;
                }

                this.nPersons = 0;
                this.note = "New array created; total items = " + this.nPersons;
                this.isOKChangeDups = false;
                this.oldCurIn = this.curIn;
                this.curIn = 0;
                this.drawMode = 2;
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
                if (var1 && var2 >= 0 && var2 <= this.totalCells) {
                    this.fillValue = var2;
                    this.note = "Will fill in " + this.fillValue + " items";
                    this.drawMode = 1;
                    this.codePart2 = 3;
                    return;
                }

                this.note = "ERROR: can't fill more than " + this.totalCells + " items";
                this.drawMode = 1;
                this.codePart2 = 1;
                return;
            case 3:
                this.nPersons = 0;
                this.doFill(this.fillValue);
                this.opMode = 2;
                this.note = "Fill completed; total items = " + this.nPersons;
                this.oldCurIn = this.curIn;
                this.curIn = 0;
                if (!this.dupsOK) {
                    this.checkDups();
                }

                this.drawMode = 2;
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
        for (int var2 = 0; var2 < this.totalCells; ++var2) {
            this.personArray[var2] = null;
        }

        this.oldCurIn = this.curIn;
        this.curIn = 0;
        this.codePart = 1;

        while (this.nPersons < var1) {
            this.insert(true, 1000);
            int var3 = (int) (Math.random() * 999.0D);
            if (!this.dupsOK) {
                while (this.getDuplicate(var3) != -1) {
                    var3 = (int) (Math.random() * 999.0D);
                }
            }

            this.insert(true, var3);

            while (this.codePart != 1) {
                this.insert(true, 1000);
            }
        }

    }

    public int getDuplicate(int var1) {
        for (int var2 = 0; var2 < this.totalCells; ++var2) {
            if (this.personArray[var2] != null && this.personArray[var2].getHeight() == var1) {
                return var2;
            }
        }

        return -1;
    }

    public void checkDups() {
        for (int var1 = 0; var1 < this.totalCells - 1; ++var1) {
            for (int var2 = var1 + 1; var2 < this.totalCells; ++var2) {
                if (this.personArray[var1] != null && this.personArray[var2] != null && this.personArray[var1].getHeight() == this.personArray[var2].getHeight()) {
                    this.note = "ERROR: " + var1 + " same as " + var2;
                    this.drawMode = 2;
                    return;
                }
            }
        }

    }

    public void insert(boolean var1, int var2) {
        if (this.opMode != 3) {
            this.opMode = 3;
            this.codePart = 1;
        }

        switch (this.codePart) {
            case 1:
                this.oldCurIn = this.curIn;
                this.curIn = 0;
                this.note = "Enter key of item to insert";
                this.drawMode = 1;
                this.codePart = 2;
                return;
            case 2:
                if (var1 && var2 >= 0 && var2 <= 999) {
                    if (this.nPersons >= this.totalCells) {
                        this.note = "CAN'T INSERT: array is full";
                        this.codePart = 6;
                    } else {
                        this.insKey = var2;
                        this.tempPers = this.makePerson(this.insKey);
                        this.note = "Will insert item with key " + this.insKey;
                        this.codePart = 4;
                    }
                } else {
                    this.note = "CAN'T INSERT: need key between 0 and " + 999;
                    this.codePart = 1;
                }

                this.drawMode = 1;
                return;
            case 3:
            default:
                return;
            case 4:
                this.oldCurIn = this.curIn;
                this.curIn = this.nPersons;
                this.personArray[this.curIn] = this.tempPers;
                ++this.nPersons;
                this.note = "Inserted item with key " + this.insKey + " at index " + this.curIn;
                this.drawMode = 1;
                this.codePart = 5;
                return;
            case 5:
                this.note = "Insertion completed; total items = " + this.nPersons;
                if (!this.dupsOK) {
                    this.checkDups();
                }

                this.drawMode = 2;
                this.codePart = 6;
                return;
            case 6:
                this.oldCurIn = this.curIn;
                this.curIn = 0;
                this.note = "Press any button";
                this.drawMode = 1;
                this.codePart = 1;
        }
    }

    public void find(boolean var1, int var2) {
        if (this.opMode != 4) {
            this.opMode = 4;
            this.codePart = 1;
        }

        switch (this.codePart) {
            case 1:
                this.oldCurIn = this.curIn;
                this.curIn = 0;
                this.note = "Enter key of item to find";
                this.codePart = 2;
                break;
            case 2:
                if (var1 && var2 >= 0 && var2 <= 999) {
                    this.findKey = var2;
                    this.note = "Looking for item with key " + this.findKey;
                    this.codePart = 3;
                } else {
                    this.note = "ERROR: use key between 0 and " + 999;
                    this.codePart = 1;
                }
                break;
            case 3:
                if (this.curIn >= this.nPersons) {
                    this.note = "Can't locate item with key " + this.findKey;
                    this.codePart = 6;
                } else if (this.personArray[this.curIn].getHeight() == this.findKey) {
                    this.note = "Have found item with key " + this.findKey;
                    this.wasJustFound = true;
                    if (this.dupsOK) {
                        this.codePart = 4;
                    } else {
                        this.codePart = 6;
                    }
                } else {
                    this.oldCurIn = this.curIn++;
                    this.note = "Checking next cell; index = " + this.curIn;
                    this.codePart = 3;
                }
                break;
            case 4:
                if (this.wasJustFound) {
                    this.oldCurIn = this.curIn++;
                }

                if (this.curIn >= this.nPersons) {
                    this.note = "No additional items with key " + this.findKey;
                    this.codePart = 6;
                } else if (this.personArray[this.curIn].getHeight() == this.findKey) {
                    this.note = "Have found additional item with key " + this.findKey + " at index " + this.curIn;
                    this.wasJustFound = true;
                    this.codePart = 4;
                } else {
                    if (!this.wasJustFound) {
                        this.oldCurIn = this.curIn++;
                    }

                    this.wasJustFound = false;
                    this.note = "Checking for additional matches; index = " + this.curIn;
                    this.codePart = 4;
                }
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
        if (this.opMode != 5) {
            this.opMode = 5;
            this.codePart = 1;
        }

        switch (this.codePart) {
            case 1:
                this.oldCurIn = this.curIn;
                this.curIn = 0;
                this.lastDeletion = -1;
                this.nDeleted = 0;
                this.note = "Enter key of item to delete";
                this.codePart = 2;
                break;
            case 2:
                if (var1 && var2 >= 0 && var2 <= 999) {
                    this.delKey = var2;
                    this.note = "Looking for item with key " + this.delKey;
                    this.codePart = 3;
                } else {
                    this.note = "ERROR: use key between 0 and " + 999;
                    this.codePart = 1;
                }
                break;
            case 3:
                if (this.curIn >= this.nPersons) {
                    if (this.lastDeletion == -1) {
                        this.note = "No item with key " + this.delKey + " found";
                        this.codePart = 5;
                    } else {
                        this.note = "No additional items with key " + this.delKey + " found";
                        this.codePart = 6;
                    }
                } else if (this.personArray[this.curIn].getHeight() == this.delKey) {
                    this.personArray[this.curIn] = null;
                    this.note = "Have found and deleted item with key " + this.delKey;
                    this.lastDeletion = this.curIn;
                    if (this.dupsOK) {
                        this.nDeleted = 1;
                        this.codePart = 10;
                    } else {
                        this.codePart = 4;
                    }
                } else {
                    this.oldCurIn = this.curIn++;
                    this.note = "Checking index = " + this.curIn + " for item";
                    this.codePart = 3;
                }
                break;
            case 4:
                if (this.curIn < this.nPersons - 1) {
                    this.oldCurIn = this.curIn++;
                    this.personArray[this.curIn - 1] = this.personArray[this.curIn];
                    this.personArray[this.curIn] = null;
                    this.note = "Shifted item from " + this.curIn + " to " + (this.curIn - 1);
                    this.codePart = 4;
                } else {
                    --this.nPersons;
                    this.note = "Shifting completed. Total items = " + this.nPersons;
                    this.oldCurIn = this.curIn;
                    this.curIn = this.nPersons - 1;
                    if (this.dupsOK) {
                        this.curIn = this.lastDeletion;
                        this.codePart = 3;
                    } else {
                        this.codePart = 6;
                    }
                }
                break;
            case 5:
                this.note = "Deletion not completed";
                this.codePart = 6;
                break;
            case 6:
                this.oldCurIn = this.curIn;
                this.curIn = 0;
                this.note = "Press any button";
                this.codePart = 1;
            case 7:
            case 8:
            case 9:
            default:
                break;
            case 10:
                this.oldCurIn = this.curIn;
                this.curIn += this.nDeleted;
                this.note = "Will shift item " + this.nDeleted + " spaces";
                this.codePart = 11;
                break;
            case 11:
                if (this.curIn < this.nPersons) {
                    this.personArray[this.curIn - this.nDeleted] = this.personArray[this.curIn];
                    this.personArray[this.curIn] = null;
                    this.note = "Shifted item from " + this.curIn + " to " + (this.curIn - this.nDeleted);
                    this.oldCurIn = this.curIn;
                    this.curIn -= this.nDeleted;
                    this.codePart = 12;
                } else {
                    this.nPersons -= this.nDeleted;
                    this.note = "Shifts complete; no more items to delete";
                    this.codePart = 6;
                }
                break;
            case 12:
                if (this.personArray[this.curIn].getHeight() == this.delKey) {
                    ++this.nDeleted;
                    this.personArray[this.curIn] = null;
                    this.note = "Have deleted additional item with key " + this.delKey;
                    this.lastDeletion = this.curIn;
                    this.oldCurIn = this.curIn;
                    this.curIn += this.nDeleted;
                } else {
                    this.note = "Item at " + this.curIn + " is not a duplicate";
                    this.oldCurIn = this.curIn;
                    this.curIn += this.nDeleted + 1;
                }

                this.codePart = 11;
        }

        this.drawMode = 1;
    }

    public void drawPerson(Graphics var1, int var2) {
        if (var2 <= this.totalCells - 1) {
            int var3 = 10 + 85 * (var2 / 12);
            int var4 = 89 + 17 * (var2 % 12);
            byte var5;
            if (var2 < 10) {
                var5 = 12;
            } else if (var2 < 100) {
                var5 = 6;
            } else {
                var5 = 0;
            }

            if (this.drawMode == 2) {
                var1.setColor(Color.black);
                var1.drawString(String.valueOf(var2), var3 + var5, var4 + 17 - 8);
            }

            var1.setColor(Color.black);
            var1.drawRect(var3 + 18 + 5, var4 - 5, 35, 17);
            if (this.personArray[var2] == null) {
                var1.setColor(Color.lightGray);
                var1.fill3DRect(var3 + 18 + 6, var4 - 4, 34, 16, true);
            } else {
                int var6 = this.personArray[var2].getHeight();
                var1.setColor(this.personArray[var2].getColor());
                var1.fill3DRect(var3 + 18 + 6, var4 - 4, 34, 16, true);
                if (var6 < 10) {
                    var5 = 12;
                } else if (var6 < 100) {
                    var5 = 6;
                } else {
                    var5 = 0;
                }

                var1.setColor(Color.black);
                var1.drawString(String.valueOf(var6), var3 + 18 + var5 + 15, var4 + 17 - 8);
            }

            if (var2 == this.curIn) {
                var1.setColor(Color.red);
            } else {
                var1.setColor(Color.lightGray);
            }

            int var7 = var3 + 18 + 8 + 35;
            int var8 = var4 + 8 - 4;
            var1.drawLine(var7, var8, var7 + 20, var8);
            var1.drawLine(var7, var8 + 1, var7 + 20, var8 + 1);
            var1.drawLine(var7, var8, var7 + 5, var8 - 5);
            var1.drawLine(var7, var8 + 1, var7 + 5, var8 - 4);
            var1.drawLine(var7, var8, var7 + 5, var8 + 5);
            var1.drawLine(var7, var8 + 1, var7 + 5, var8 + 6);
        }
    }

    public void draw(Graphics var1) {
        if (this.drawMode == 1) {
            var1.setColor(Color.lightGray);
            var1.fillRect(10, 55, 325, 25);
            var1.setColor(Color.black);
            var1.drawString(this.note, 16, 74);
            this.drawPerson(var1, this.oldCurIn);
            this.drawPerson(var1, this.curIn);
            this.drawMode = 2;
        } else {
            int var2;
            if (this.drawMode == 2) {
                var1.setColor(Color.lightGray);
                var1.fillRect(0, 0, 440, 320);

                for (var2 = 0; var2 < this.totalCells; ++var2) {
                    this.drawPerson(var1, var2);
                }

                var1.setColor(Color.black);
                var1.drawString(this.note, 16, 74);
            } else {
                var1.setColor(Color.lightGray);
                var1.fillRect(10, 55, 325, 25);

                for (var2 = 0; var2 < this.totalCells; ++var2) {
                    this.drawPerson(var1, var2);
                }

                var1.setColor(Color.black);
                var1.drawString(this.note, 16, 74);
            }
        }

        this.drawMode = 2;
    }
}
