package chap13.graph_n;

import java.awt.Color;

class vertex {
    public int x;
    public int y;
    public char label;
    public static char classChar;
    public Color color;
    public boolean wasVisited;

    public vertex(int var1, int var2) {
        this.x = var1;
        this.y = var2;
        this.label = classChar++;
        int var3 = 100 + (int)(Math.random() * 154.0D);
        int var4 = 100 + (int)(Math.random() * 154.0D);
        int var5 = 100 + (int)(Math.random() * 154.0D);
        this.color = new Color(var3, var4, var5);
        this.wasVisited = false;
    }
}
