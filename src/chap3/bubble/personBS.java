package chap3.bubble;

import java.awt.Color;

class personBS {
    private int height;
    private Color color;

    public Color getColor() {
        return this.color;
    }

    public int getHeight() {
        return this.height;
    }

    public personBS(int var1, Color var2) {
        this.height = var1;
        this.color = var2;
    }
}