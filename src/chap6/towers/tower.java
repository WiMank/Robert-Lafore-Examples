package chap6.towers;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.awt.Color;
import java.awt.Graphics;

class tower {
    private final int groundLevel = 300;
    private final int diskHeight = 20;
    private final int maxDiskWidth = 120;
    private final int towerWidth = 15;
    private final int towerTop = 80;
    private final int towerHeight = 220;
    public int nDisks;
    public int xCtr;
    public char label;
    public disk[] diskArray;
    public int arrayTop;

    public tower(int var1, char var2, int var3) {
        this.nDisks = var3;
        this.xCtr = var1;
        this.label = var2;
        this.diskArray = new disk[this.nDisks];
        this.arrayTop = -1;
    }

    public void insertDisk(disk var1) {
        this.diskArray[++this.arrayTop] = var1;
    }

    public disk removeDisk() {
        return this.diskArray[this.arrayTop--];
    }

    public disk peekDisk() {
        return this.diskArray[this.arrayTop];
    }

    public boolean isEmpty() {
        return this.arrayTop == -1;
    }

    public boolean isFull() {
        return this.arrayTop == this.nDisks - 1;
    }

    public void drawTower(Graphics var1, int var2, int var3) {
        if (var2 == 1) {
            if (var3 == 1) {
                this.eraseDisk(var1, this.arrayTop + 1);
                return;
            }

            if (var3 == 2) {
                this.diskArray[this.arrayTop].drawDisk(var1, this.xCtr, this.arrayTop);
                return;
            }
        } else {
            var1.setColor(Color.black);
            int var4 = this.xCtr - 7;
            var1.fillRect(var4, 80, 15, 220);
            var1.setColor(Color.white);
            var1.drawString(String.valueOf(this.label), var4 + 4, 95);

            for(int var5 = 0; var5 <= this.arrayTop && this.diskArray[var5] != null; ++var5) {
                this.diskArray[var5].drawDisk(var1, this.xCtr, var5);
            }
        }

    }

    public void eraseDisk(Graphics var1, int var2) {
        int var3 = this.xCtr - 60 - 10;
        int var4 = 300 - (var2 + 1) * 20;
        short var5 = 140;
        var1.setColor(Color.lightGray);
        var1.fillRect(var3, var4, var5, 20);
        var1.setColor(Color.black);
        int var6 = this.xCtr - 7;
        var1.fillRect(var6, var4, 15, 20);
    }
}
