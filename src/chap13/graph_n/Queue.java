package chap13.graph_n;

class Queue {
    private final int SIZE = 20;
    private int[] queArray = new int[20];
    private int front = 0;
    private int rear = -1;

    public Queue() {
    }

    public void insert(int var1) {
        if (this.rear == 19) {
            this.rear = -1;
        }

        this.queArray[++this.rear] = var1;
    }

    public int remove() {
        int var1 = this.queArray[this.front++];
        if (this.front == 20) {
            this.front = 0;
        }

        return var1;
    }

    public boolean isEmpty() {
        return this.rear + 1 == this.front || this.front + 20 - 1 == this.rear;
    }

    public int size() {
        return this.rear >= this.front ? this.rear - this.front + 1 : 20 - this.front + this.rear + 1;
    }

    public int peekN(int var1) {
        if (this.rear >= this.front) {
            return this.queArray[this.front + var1];
        } else {
            return var1 < 20 - this.front ? this.queArray[this.front + var1] : this.queArray[var1 - (20 - this.front)];
        }
    }
}
