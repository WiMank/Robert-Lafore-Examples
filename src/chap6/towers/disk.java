package chap6.towers;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.awt.Color;
import java.awt.Graphics;

class disk {
    private final int groundLevel = 300;
    private final int height = 20;
    public int width;
    public Color color;
    public String label;

    public disk(int var1, Color var2, String var3) {
        this.width = var1;
        this.color = var2;
        this.label = var3;
    }

    public void drawDisk(Graphics var1, int var2, int var3) {
        int var4 = var2 - this.width / 2;
        int var5 = 300 - (var3 + 1) * 20;
        var1.setColor(Color.black);
        var1.drawRect(var4, var5, this.width - 1, 19);
        var1.drawOval(var4 - 10, var5, 19, 19);
        var1.drawOval(var4 + this.width - 10 - 1, var5, 19, 19);
        var1.setColor(this.color);
        var1.fillRect(var4 + 1, var5 + 1, this.width - 2, 18);
        var1.fillOval(var4 - 10 + 1, var5 + 1, 18, 18);
        var1.fillOval(var4 + this.width - 10, var5 + 1, 18, 18);
        var1.setColor(Color.black);
        var1.drawString(this.label, var4, var5 + 10 + 4);
    }
}
