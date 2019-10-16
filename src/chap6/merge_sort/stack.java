package chap6.merge_sort;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

class stack {
    private int maxSize;
    private params[] stackArray;
    private int top;

    public stack(int var1) {
        this.maxSize = var1;
        this.stackArray = new params[this.maxSize];
        this.top = -1;
    }

    public void push(params var1) {
        this.stackArray[++this.top] = var1;
    }

    public params pop() {
        return this.stackArray[this.top--];
    }

    public params peek() {
        return this.stackArray[this.top];
    }

    public boolean isEmpty() {
        return this.top == -1;
    }
}

