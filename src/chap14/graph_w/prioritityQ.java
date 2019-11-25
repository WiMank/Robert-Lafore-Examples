package chap14.graph_w;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

class priorityQ {
    private final int SIZE = 20;
    private edge[] queArray = new edge[20];
    private int size = 0;

    public priorityQ() {
    }

    public void insert(edge var1) {
        int var2;
        for(var2 = 0; var2 < this.size && var1.distance < this.queArray[var2].distance; ++var2) {
        }

        for(int var3 = this.size - 1; var3 >= var2; --var3) {
            this.queArray[var3 + 1] = this.queArray[var3];
        }

        this.queArray[var2] = var1;
        ++this.size;
    }

    public edge removeMin() {
        return this.queArray[--this.size];
    }

    public void removeN(int var1) {
        for(int var2 = var1; var2 < this.size - 1; ++var2) {
            this.queArray[var2] = this.queArray[var2 + 1];
        }

        --this.size;
    }

    public edge peekMin() {
        return this.queArray[this.size - 1];
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public edge peekN(int var1) {
        return this.queArray[var1];
    }

    public int find(int var1) {
        for(int var2 = 0; var2 < this.size; ++var2) {
            if (this.queArray[var2].destVert == var1) {
                return var2;
            }
        }

        return -1;
    }
}
