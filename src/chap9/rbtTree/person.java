package chap9.rbtTree;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.awt.Color;

class person {
    private int height;
    private Color color;
    private boolean red;
    private int mark;

    public Color getColor() {
        return this.color;
    }

    public int getHeight() {
        return this.height;
    }

    public void setRed(boolean var1) {
        this.red = var1;
    }

    public boolean getRed() {
        return this.red;
    }

    public int getMark() {
        return this.mark;
    }

    public void setMark(int var1) {
        this.mark = var1;
    }

    public person(int var1, Color var2) {
        this.height = var1;
        this.color = var2;
        this.red = true;
        this.mark = 0;
    }
}
