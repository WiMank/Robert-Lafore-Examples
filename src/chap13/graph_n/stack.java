package chap13.graph_n;

class stack {
    private final int SIZE = 20;
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

    public int peek() {
        return this.st[this.top];
    }

    public boolean isEmpty() {
        return this.top == -1;
    }

    public int size() {
        return this.top + 1;
    }

    public int peekN(int var1) {
        return this.st[var1];
    }
}
