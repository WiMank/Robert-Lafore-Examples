package chap14.graph_w;

class edge {
    public int srcVert;
    public int destVert;
    public int distance;

    public edge(int var1, int var2, int var3) {
        this.srcVert = var1;
        this.destVert = var2;
        this.distance = var3;
    }
}
