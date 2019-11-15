package chap10.tree234;

class node {
    private final int ORDER = 4;
    private int numPers;
    private node parent = null;
    private node[] children = new node[4];
    private person[] personArray = new person[3];
    private boolean weAreCurrentNode;

    public node() {
        for(int var1 = 0; var1 < 3; ++var1) {
            this.personArray[var1] = null;
        }

        for(int var2 = 0; var2 < 4; ++var2) {
            this.children[var2] = null;
        }

        this.numPers = 0;
        this.weAreCurrentNode = false;
    }

    public void connectChild(int var1, node var2) {
        this.children[var1] = var2;
        if (var2 != null) {
            var2.parent = this;
        }

    }

    public node getChild(int var1) {
        return this.children[var1];
    }

    public int getChildNumber() {
        for(int var1 = 0; var1 < this.parent.getNumPers() + 1; ++var1) {
            if (this.parent.getChild(var1) == this) {
                return var1;
            }
        }

        return -1;
    }

    public node getParent() {
        return this.parent;
    }

    public boolean isLeaf() {
        return this.children[0] == null;
    }

    public int getNumPers() {
        return this.numPers;
    }

    public void putp(person var1, int var2) {
        this.personArray[var2] = var1;
    }

    public person getp(int var1) {
        return this.personArray[var1];
    }

    public void clear() {
        for(int var1 = 0; var1 < 3; ++var1) {
            this.personArray[var1] = null;
        }

        this.numPers = 0;
    }

    public boolean isFull() {
        return this.numPers == 3;
    }

    public int findItem(double var1) {
        for(int var3 = 0; var3 < 3 && this.personArray[var3] != null; ++var3) {
            if ((double)this.personArray[var3].getHeight() == var1) {
                return var3;
            }
        }

        return -1;
    }

    public void insertPers(person var1) {
        if (this.numPers == 0) {
            this.personArray[0] = var1;
            ++this.numPers;
        } else {
            int var3 = var1.getHeight();

            int var2;
            for(var2 = 0; var2 < this.numPers; ++var2) {
                int var4 = this.getp(var2).getHeight();
                if (var3 < var4) {
                    for(int var5 = this.numPers - 1; var5 >= var2; --var5) {
                        this.personArray[var5 + 1] = this.personArray[var5];
                    }

                    this.personArray[var2] = var1;
                    ++this.numPers;
                    return;
                }
            }

            this.personArray[var2] = var1;
            ++this.numPers;
        }
    }

    public boolean areWeCurrentNode() {
        return this.weAreCurrentNode;
    }

    public void setCurrentNode(boolean var1) {
        this.weAreCurrentNode = var1;
    }
}
