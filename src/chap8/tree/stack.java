package chap8.tree;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

class stack {
    private int[] st = new int[20];
    private int top = -1;

    public stack() {
    }

    public void push(int var1) {
        this.st[++this.top] = var1;
    }

    public int pop() {
        return this.st[this.top--];
    }

    public boolean isEmpty() {
        return this.top == -1;
    }
}

