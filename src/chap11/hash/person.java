package chap11.hash;

import java.awt.Color;

class person {
    private int height;
    private Color color;

    public person(int var1, Color var2) {
        this.height = var1;
        this.color = var2;
    }

    public Color getColor() {
        return this.color;
    }

    public int getHeight() {
        return this.height;
    }
}
