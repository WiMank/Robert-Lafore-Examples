package chap4.queue;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.awt.Color;
import java.awt.Graphics;

class personGroup {
    private final int appletWidth = 440;
    private final int appletHeight = 300;
    private final int topMargin = 80;
    private final int leftMargin = 10;
    private final int centerX = 175;
    private final int textHeight = 13;
    private final int hF1 = 12;
    private final int hF2 = 6;
    private final int hF3 =0;
    private final int vF = 8;
    private final int cellWidth = 35;
    private final int cellHeight = 17;
    private final int blueShaft = 60;
    private final int redShaft = 15;
    private final int digits3Width = 18;
    private final int noteBoxTop = 55;
    private final int noteBoxHeight = 25;
    private final int noteBoxWidth = 325;
    private final int ASIZE = 10;
    private final int INIT_NUM = 4;
    private final int MAX_KEY = 999;
    private person[] queArray = new person[10];
    private int nPersons;
    private person tempPers;
    private String note;
    private int insKey;
    private String returnString;
    private int codePart;
    private int opMode;
    private int front;
    private int rear;
    private int oldFront;
    private int oldRear;
    private int drawMode;

    public personGroup() {
        this.front = this.oldFront = 0;
        this.rear = this.oldRear = -1;
        this.nPersons = 0;
        this.codePart = 1;
        this.drawMode = 2;
        this.note = "Press any button";
    }

    public person makePerson(int var1) {
        int var2 = 100 + (int)(Math.random() * 154.0D);
        int var3 = 100 + (int)(Math.random() * 154.0D);
        int var4 = 100 + (int)(Math.random() * 154.0D);
        Color var5 = new Color(var2, var3, var4);
        return new person(var1, var5);
    }

    public void newStack() {
        if (this.opMode != 1) {
            this.opMode = 1;
            this.codePart = 1;
        }

        switch(this.codePart) {
            case 1:
                this.note = "Will create new queue";
                this.drawMode = 1;
                this.codePart = 2;
                return;
            case 2:
                this.queArray = new person[10];

                for(int var1 = 0; var1 < 10; ++var1) {
                    this.queArray[var1] = null;
                }

                this.nPersons = 0;
                this.oldFront = this.front;
                this.front = 0;
                this.oldRear = this.rear;
                this.rear = -1;
                this.note = "New queue created";
                this.drawMode = 2;
                this.codePart = 3;
                return;
            case 3:
                this.note = "Press any button";
                this.drawMode = 1;
                this.codePart = 1;
                return;
            default:
        }
    }

    public void doFill() {
        int var1;
        for(var1 = 0; var1 < 10; ++var1) {
            this.queArray[var1] = null;
        }

        for(var1 = 0; var1 < 4; ++var1) {
            int var2 = (int)(Math.random() * 999.0D);
            this.tempPers = this.makePerson(var2);
            this.queArray[var1] = this.tempPers;
        }

        this.nPersons = 4;
        this.oldRear = this.rear;
        this.rear = this.nPersons - 1;
        this.front = 0;
    }

    public void insert(boolean var1, int var2) {
        if (this.opMode != 2) {
            this.opMode = 2;
            this.codePart = 1;
        }

        switch(this.codePart) {
            case 1:
                this.note = "Enter key of item to insert";
                this.drawMode = 1;
                this.codePart = 2;
                return;
            case 2:
                if (var1 && var2 >= 0 && var2 <= 999) {
                    if (this.nPersons > 9) {
                        this.note = "CAN'T INSERT: queue is full";
                        this.codePart = 4;
                    } else {
                        this.insKey = var2;
                        this.note = "Will insert item with key " + this.insKey;
                        this.tempPers = this.makePerson(this.insKey);
                        this.codePart = 3;
                    }
                } else {
                    this.note = "CAN'T INSERT: need key between 0 and " + 999;
                    this.codePart = 1;
                }

                this.drawMode = 1;
                return;
            case 3:
                this.oldRear = this.rear;
                if (this.rear >= 9) {
                    this.rear = -1;
                }

                ++this.rear;
                ++this.nPersons;
                this.queArray[this.rear] = this.tempPers;
                this.note = "Inserted item with key " + this.insKey;
                this.drawMode = 1;
                this.codePart = 4;
                return;
            case 4:
                this.note = "Press any button";
                this.drawMode = 1;
                this.codePart = 1;
                return;
            default:
        }
    }

    public String remove() {
        if (this.opMode != 3) {
            this.opMode = 3;
            this.codePart = 1;
        }

        switch(this.codePart) {
            case 1:
                if (this.nPersons == 0) {
                    this.note = "CAN'T REMOVE: queue is empty";
                    this.codePart = 3;
                } else {
                    this.note = "Will remove item from front of queue";
                    this.codePart = 2;
                }

                this.returnString = "";
                this.drawMode = 1;
                break;
            case 2:
                this.returnString = String.valueOf(this.queArray[this.front].getHeight());
                this.queArray[this.front] = null;
                --this.nPersons;
                this.oldFront = this.front++;
                if (this.front > 9) {
                    this.front = 0;
                }

                this.note = "Item removed; value returned in Number";
                this.drawMode = 1;
                this.codePart = 3;
                break;
            case 3:
                this.note = "Press any button";
                this.drawMode = 1;
                this.codePart = 1;
        }

        return this.returnString;
    }

    public String peek() {
        if (this.opMode != 4) {
            this.opMode = 4;
            this.codePart = 1;
        }

        switch(this.codePart) {
            case 1:
                if (this.nPersons == 0) {
                    this.note = "CAN'T PEEK: queue is empty";
                    this.codePart = 3;
                } else {
                    this.note = "Will peek at item at front of queue";
                    this.codePart = 2;
                }

                this.returnString = "";
                this.drawMode = 1;
                break;
            case 2:
                this.returnString = String.valueOf(this.queArray[this.front].getHeight());
                this.note = "Value returned in Number";
                this.drawMode = 1;
                this.codePart = 3;
                break;
            case 3:
                this.note = "Press any button";
                this.drawMode = 1;
                this.codePart = 1;
        }

        return this.returnString;
    }

    public void drawPerson(Graphics var1, int var2) {
        short var3 = 175;
        int var4 = 300 - (40 + 17 * var2);
        if (var2 >= 0 && var2 < 10) {
            byte var5;
            if (var2 < 10) {
                var5 = 12;
            } else if (var2 < 100) {
                var5 = 6;
            } else {
                var5 = 0;
            }

            var1.setColor(Color.black);
            var1.drawString(String.valueOf(var2), var3 + var5, var4 + 17 - 8);
            var1.setColor(Color.black);
            var1.drawRect(var3 + 18 + 5, var4 - 5, 35, 17);
            if (this.queArray[var2] == null) {
                var1.setColor(Color.lightGray);
                var1.fill3DRect(var3 + 18 + 6, var4 - 4, 34, 16, true);
            } else {
                int var6 = this.queArray[var2].getHeight();
                var1.setColor(this.queArray[var2].getColor());
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
        }

        int var7 = var3 + 18 + 8 + 35;
        int var8 = var4 + 8 - 4;
        var1.setColor(Color.lightGray);
        var1.drawString("Front", var7 + 15 + 3, var8 + 5);
        var1.drawString("Rear", var7 + 60 + 3, var8 + 5);
        this.drawArrow(var1, 60, var7, var8);
        if (var2 == this.rear) {
            var1.setColor(Color.blue);
            var1.drawString("Rear", var7 + 60 + 3, var8 + 5);
            this.drawArrow(var1, 60, var7, var8);
        }

        if (var2 == this.front) {
            var1.setColor(Color.red);
            var1.drawString("Front", var7 + 15 + 3, var8 + 5);
            this.drawArrow(var1, 15, var7, var8);
        }

    }

    public void drawArrow(Graphics var1, int var2, int var3, int var4) {
        var1.drawLine(var3, var4, var3 + var2, var4);
        var1.drawLine(var3, var4 + 1, var3 + var2, var4 + 1);
        var1.drawLine(var3, var4, var3 + 5, var4 - 5);
        var1.drawLine(var3, var4 + 1, var3 + 5, var4 - 4);
        var1.drawLine(var3, var4, var3 + 5, var4 + 5);
        var1.drawLine(var3, var4 + 1, var3 + 5, var4 + 6);
    }

    public void draw(Graphics var1) {
        if (this.drawMode == 1) {
            var1.setColor(Color.lightGray);
            var1.fillRect(10, 55, 325, 25);
            var1.setColor(Color.black);
            var1.drawString(this.note, 16, 74);
            this.drawPerson(var1, this.oldFront);
            this.drawPerson(var1, this.front);
            this.drawPerson(var1, this.oldRear);
            this.drawPerson(var1, this.rear);
            this.drawMode = 2;
        } else {
            var1.setColor(Color.lightGray);
            var1.fillRect(0, 0, 440, 300);
            var1.setColor(Color.black);
            var1.drawString(this.note, 16, 74);

            for(int var2 = 0; var2 < 10; ++var2) {
                this.drawPerson(var1, var2);
            }

            this.drawPerson(var1, this.rear);
        }
    }
}
