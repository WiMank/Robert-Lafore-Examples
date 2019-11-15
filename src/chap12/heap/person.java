package chap12.heap;

import java.awt.Color;

class person {
    private int height;
    private Color color;

    public Color getColor() {
        return this.color;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int var1) {
        this.height = var1;
    }

    public person(int var1, Color var2) {
        this.height = var1;
        this.color = var2;
    }
}
