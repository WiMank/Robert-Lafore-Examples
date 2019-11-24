package chap13.graph_n;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.awt.Color;
import java.awt.Graphics;

class vertexGroup {
    private final int appletWidth = 440;
    private final int appletHeight = 300;
    private final int topMargin = 70;
    private final int leftMargin = 10;
    private final int textHeight = 13;
    private final int vertexDiameter = 22;
    private final int noteBoxTop = 45;
    private final int noteBoxHeight = 25;
    private final int noteBoxWidth = 350;
    private final int visitBoxTop = 280;
    private final int visitBoxHeight = 25;
    private final int visitBoxWidth = 430;
    private final int drawingBoxHeight = 207;
    private final int MAX_VERTS = 20;
    private vertex[] vertexList;
    private int[][] adjMat;
    private int nVerts;
    private int[][] adjMat2;
    private String note;
    private String oldNote;
    private int opMode;
    private int codePart;
    private int drawMode;
    private int xStartDrag;
    private int yStartDrag;
    private boolean madeVertex;
    private boolean showAdjm;
    private int startingVertex;
    private int currentVertex;
    private int displayVertex;
    private int displayEdge1;
    private int displayEdge2;
    private stack theStack;
    private Queue theQueue;
    private char[] visitArray;
    private int visitIndex;

    public vertexGroup() {
        vertex.classChar = 'A';
        this.vertexList = new vertex[20];
        this.adjMat = new int[20][20];
        this.nVerts = 0;

        for(int var1 = 0; var1 < 20; ++var1) {
            for(int var2 = 0; var2 < 20; ++var2) {
                this.adjMat[var1][var2] = 0;
            }
        }

        this.adjMat2 = new int[20][20];
        this.showAdjm = false;
        this.startingVertex = -1;
        this.theStack = new stack();
        this.theQueue = new Queue();
        this.visitArray = new char[20];
        this.visitIndex = 0;
        this.note = "Double-click mouse to make vertex";
        this.drawMode = 4;
    }

    public void makeVertex(int var1, int var2) {
        this.madeVertex = true;
        if (this.nVerts > 19) {
            this.note = "CAN'T INSERT more vertices";
        } else {
            this.vertexList[this.nVerts++] = new vertex(var1, var2);
            this.note = "Made vertex " + this.vertexList[this.nVerts - 1].label;
            this.displayVertex = this.nVerts - 1;
            this.drawMode = 2;
        }
    }

    public void startDrag(int var1, int var2) {
        this.xStartDrag = var1;
        this.yStartDrag = var2;
        int var3 = this.closeTo(var1, var2);
        this.startingVertex = var3;
        if (var3 < 0) {
            this.note = "To make edge, drag from vertex to vertex";
        } else {
            this.note = "Starting edge at vertex " + this.vertexList[var3].label;
        }

        this.drawMode = 1;
    }

    public void endDrag(int var1, int var2) {
        if (this.madeVertex) {
            this.madeVertex = false;
            this.drawMode = 2;
        } else {
            int var3 = this.closeTo(this.xStartDrag, this.yStartDrag);
            int var4 = this.closeTo(var1, var2);
            if (this.opMode > 0 && this.opMode < 4) {
                if (this.startingVertex == -1) {
                    this.note = "Click on a vertex";
                } else {
                    this.note = "You clicked on " + this.vertexList[this.startingVertex].label;
                }

                this.drawMode = 1;
            } else if (var3 >= 0 && var4 >= 0 && var3 != var4) {
                this.note = "Made edge from " + this.vertexList[var3].label + " to " + this.vertexList[var4].label;
                this.adjMat[var3][var4] = 1;
                this.adjMat[var4][var3] = 1;
                this.madeVertex = false;
                this.displayEdge1 = var3;
                this.displayEdge2 = var4;
                this.drawMode = 3;
            } else {
                this.note = "CAN'T MAKE EDGE";
                this.drawMode = 1;
            }
        }
    }

    public int closeTo(int var1, int var2) {
        byte var3 = 11;

        for(int var4 = 0; var4 < this.nVerts; ++var4) {
            if (Math.abs(this.vertexList[var4].x - var1) < var3 && Math.abs(this.vertexList[var4].y - var2) < var3) {
                return var4;
            }
        }

        return -1;
    }

    public void changeView() {
        if (this.showAdjm) {
            this.note = this.oldNote;
            this.showAdjm = false;
        } else {
            this.oldNote = this.note;
            if (this.nVerts == 0) {
                this.note = "No vertices; adjacency matrix is empty";
            } else {
                this.note = "Press View button again to show graph";
            }

            this.showAdjm = true;
            this.drawMode = 4;
        }
    }

    public void dfs() {
        if (this.opMode != 1) {
            this.opMode = 1;
            this.codePart = 1;
        }

        int var1;
        switch(this.codePart) {
            case 1:
                this.note = "Single-click on vertex from which to start search";
                this.startingVertex = -1;
                this.drawMode = 1;
                this.codePart = 2;
                return;
            case 2:
                if (this.startingVertex == -1) {
                    this.note = "INVALID VERTEX";
                    this.drawMode = 1;
                    this.codePart = 1;
                } else {
                    this.note = "Starting search from vertex " + this.vertexList[this.startingVertex].label;

                    while(!this.theStack.isEmpty()) {
                        this.theStack.pop();
                    }

                    this.vertexList[this.startingVertex].wasVisited = true;
                    this.visitArray[this.visitIndex++] = this.vertexList[this.startingVertex].label;
                    this.theStack.push(this.startingVertex);
                    this.codePart = 3;
                }

                this.displayVertex = this.startingVertex;
                this.drawMode = 2;
                return;
            case 3:
                if (!this.theStack.isEmpty()) {
                    var1 = this.getAdjUnvisitedVertex(this.theStack.peek());
                    if (var1 == -1) {
                        this.theStack.pop();
                        if (!this.theStack.isEmpty()) {
                            int var2 = this.theStack.peek();
                            this.note = "Will check vertices adjacent to " + this.vertexList[var2].label;
                        } else {
                            this.note = "No more vertices with unvisited neighbors";
                        }

                        this.drawMode = 1;
                    } else {
                        this.note = "Visited vertex " + this.vertexList[var1].label;
                        this.vertexList[var1].wasVisited = true;
                        this.visitArray[this.visitIndex++] = this.vertexList[var1].label;
                        this.theStack.push(var1);
                        this.displayVertex = var1;
                        this.drawMode = 2;
                    }

                    this.codePart = 3;
                    return;
                }

                this.note = "Press again to reset search";
                this.codePart = 4;
                this.drawMode = 1;
                return;
            case 4:
                this.visitIndex = 0;

                for(var1 = 0; var1 < this.nVerts; ++var1) {
                    this.vertexList[var1].wasVisited = false;
                }

                this.note = "Press any button";
                this.codePart = 1;
                this.opMode = 0;
                this.drawMode = 4;
                return;
            default:
        }
    }

    public int getAdjUnvisitedVertex(int var1) {
        for(int var2 = 0; var2 < this.nVerts; ++var2) {
            if (this.adjMat[var1][var2] == 1 && !this.vertexList[var2].wasVisited) {
                return var2;
            }
        }

        return -1;
    }

    public void bfs() {
        if (this.opMode != 2) {
            this.opMode = 2;
            this.codePart = 1;
        }

        switch(this.codePart) {
            case 1:
                this.note = "Single-click on vertex from which to start search";
                this.startingVertex = -1;
                this.drawMode = 1;
                this.codePart = 2;
                return;
            case 2:
                if (this.startingVertex == -1) {
                    this.note = "Invalid vertex";
                    this.drawMode = 1;
                    this.codePart = 1;
                    return;
                }

                this.note = "Will start from vertex " + this.vertexList[this.startingVertex].label;

                while(!this.theQueue.isEmpty()) {
                    this.theQueue.remove();
                }

                this.vertexList[this.startingVertex].wasVisited = true;
                this.visitArray[this.visitIndex++] = this.vertexList[this.startingVertex].label;
                this.theQueue.insert(this.startingVertex);
                this.displayVertex = this.startingVertex;
                this.drawMode = 2;
                this.codePart = 3;
                return;
            case 3:
                if (!this.theQueue.isEmpty()) {
                    this.currentVertex = this.theQueue.remove();
                    this.note = "Will check vertices adjacent to " + this.vertexList[this.currentVertex].label;
                    this.codePart = 4;
                } else {
                    this.note = "Press again to reset search";
                    this.codePart = 5;
                }

                this.drawMode = 1;
                return;
            case 4:
                int var1 = this.getAdjUnvisitedVertex(this.currentVertex);
                if (var1 == -1) {
                    this.note = "No more unvisited vertices adjacent to " + this.vertexList[this.currentVertex].label;
                    this.drawMode = 1;
                    this.codePart = 3;
                    return;
                }

                this.note = "Visited vertex " + this.vertexList[var1].label;
                this.vertexList[var1].wasVisited = true;
                this.visitArray[this.visitIndex++] = this.vertexList[var1].label;
                this.theQueue.insert(var1);
                this.displayVertex = var1;
                this.drawMode = 2;
                this.codePart = 4;
                return;
            case 5:
                this.visitIndex = 0;

                for(int var2 = 0; var2 < this.nVerts; ++var2) {
                    this.vertexList[var2].wasVisited = false;
                }

                this.note = "Press any button";
                this.codePart = 1;
                this.opMode = 0;
                this.drawMode = 4;
                return;
            default:
        }
    }

    public void tree() {
        if (this.opMode != 3) {
            this.opMode = 3;
            this.codePart = 1;
        }

        int var1;
        int var2;
        switch(this.codePart) {
            case 1:
                this.saveState();
                this.note = "Single-click on vertex from which to start tree";
                this.startingVertex = -1;
                this.codePart = 2;
                this.drawMode = 1;
                return;
            case 2:
                if (this.startingVertex == -1) {
                    this.note = "INVALID VERTEX";
                    this.drawMode = 1;
                    this.codePart = 1;
                    return;
                }

                while(!this.theStack.isEmpty()) {
                    this.theStack.pop();
                }

                this.vertexList[this.startingVertex].wasVisited = true;
                this.visitArray[this.visitIndex++] = this.vertexList[this.startingVertex].label;
                this.theStack.push(this.startingVertex);
                this.note = "Starting tree from vertex " + this.vertexList[this.startingVertex].label;
                this.displayVertex = this.startingVertex;
                this.drawMode = 2;
                this.codePart = 3;
                return;
            case 3:
                if (!this.theStack.isEmpty()) {
                    this.currentVertex = this.theStack.peek();
                    var1 = this.getAdjUnvisitedVertex(this.currentVertex);
                    if (var1 == -1) {
                        this.theStack.pop();
                        if (!this.theStack.isEmpty()) {
                            var2 = this.theStack.peek();
                            this.note = "Will check vertices adjacent to " + this.vertexList[var2].label;
                        } else {
                            this.note = "No more vertices with unvisited neighbors";
                        }

                        this.drawMode = 1;
                    } else {
                        this.note = "Visited vertex " + this.vertexList[var1].label;
                        this.vertexList[var1].wasVisited = true;
                        this.visitArray[this.visitIndex++] = this.vertexList[var1].label;
                        this.theStack.push(var1);
                        this.adjMat[this.currentVertex][var1] = 2;
                        this.adjMat[var1][this.currentVertex] = 2;
                        this.displayVertex = var1;
                        this.displayEdge1 = this.currentVertex;
                        this.displayEdge2 = var1;
                        this.drawMode = 3;
                    }

                    this.codePart = 3;
                    return;
                }

                this.note = "Press again to delete unmarked edges";
                this.codePart = 4;
                return;
            case 4:
                for(var1 = 0; var1 < this.nVerts; ++var1) {
                    for(var2 = 0; var2 < this.nVerts; ++var2) {
                        if (this.adjMat[var1][var2] == 1) {
                            this.adjMat[var1][var2] = 0;
                        } else if (this.adjMat[var1][var2] == 2) {
                            this.adjMat[var1][var2] = 1;
                        }
                    }
                }

                this.note = "Minimum spanning tree; press again to restore tree";
                this.codePart = 5;
                return;
            case 5:
                this.restoreState();
                this.visitIndex = 0;

                for(var1 = 0; var1 < this.nVerts; ++var1) {
                    this.vertexList[var1].wasVisited = false;
                }

                this.note = "Press any button";
                this.codePart = 1;
                this.opMode = 0;
                return;
            default:
        }
    }

    public void warningNew() {
        this.note = "ARE YOU SURE? Press again to clear old graph";
    }

    public void saveState() {
        for(int var1 = 0; var1 < this.nVerts; ++var1) {
            for(int var2 = 0; var2 < this.nVerts; ++var2) {
                this.adjMat2[var1][var2] = this.adjMat[var1][var2];
            }
        }

    }

    public void restoreState() {
        for(int var1 = 0; var1 < this.nVerts; ++var1) {
            for(int var2 = 0; var2 < this.nVerts; ++var2) {
                this.adjMat[var1][var2] = this.adjMat2[var1][var2];
            }
        }

    }

    public void drawOneVertex(Graphics var1, int var2) {
        if (var2 >= 0) {
            Color var3 = this.vertexList[var2].color;
            char var4 = this.vertexList[var2].label;
            int var5 = this.vertexList[var2].x - 11;
            int var6 = this.vertexList[var2].y - 11;
            var1.setColor(var3);
            var1.fillOval(var5, var6, 22, 22);
            var1.setColor(Color.black);
            var1.drawOval(var5, var6, 22, 22);
            if (this.vertexList[var2].wasVisited) {
                var1.setColor(Color.red);
                var1.drawOval(var5 - 3, var6 - 3, 28, 28);
            }

            var1.drawString(String.valueOf(var4), var5 + 8, var6 + 22 - 5);
        }
    }

    public void draw(Graphics var1) {
        if (this.showAdjm) {
            var1.setColor(Color.yellow);
            var1.fillRect(0, 0, 440, 300);
            var1.setColor(Color.black);
            var1.drawString(this.note, 16, 64);
            this.drawAdjm(var1);
        } else {
            int var2;
            int var4;
            switch(this.drawMode) {
                case 2:
                    this.drawOneVertex(var1, this.displayVertex);
                    break;
                case 3:
                    var2 = this.adjMat[this.displayEdge1][this.displayEdge2];
                    if (var2 > 0) {
                        this.drawEdge(var1, this.displayEdge1, this.displayEdge2, var2);
                    }

                    this.drawOneVertex(var1, this.displayEdge1);
                    this.drawOneVertex(var1, this.displayEdge2);
                    break;
                case 4:
                    var1.setColor(Color.lightGray);
                    var1.fillRect(0, 0, 440, 300);
                    var1.setColor(Color.black);
                    var1.drawRect(2, 71, 436, 207);

                    int var3;
                    for(var3 = 0; var3 < this.nVerts; ++var3) {
                        for(var4 = 0; var4 < this.nVerts; ++var4) {
                            var2 = this.adjMat[var3][var4];
                            if (var2 > 0) {
                                this.drawEdge(var1, var3, var4, var2);
                            }
                        }
                    }

                    for(var3 = 0; var3 < this.nVerts; ++var3) {
                        this.drawOneVertex(var1, var3);
                    }
            }

            var1.setColor(Color.lightGray);
            var1.fillRect(10, 45, 350, 25);
            var1.setColor(Color.black);
            var1.drawString(this.note, 16, 64);
            var1.setColor(Color.lightGray);
            var1.fillRect(10, 280, 430, 25);
            var1.setColor(Color.black);
            if (this.opMode > 0 && this.opMode < 4) {
                String var7 = "Visits: ";

                for(var4 = 0; var4 < this.visitIndex; ++var4) {
                    var7 = var7 + this.visitArray[var4] + " ";
                }

                var1.drawString(var7, 16, 296);
                int var5;
                int var6;
                if (this.opMode != 1 && this.opMode != 3) {
                    var7 = "Queue (f-->r): ";
                    if (!this.theQueue.isEmpty()) {
                        for(var5 = 0; var5 < this.theQueue.size(); ++var5) {
                            var6 = this.theQueue.peekN(var5);
                            var7 = var7 + this.vertexList[var6].label + " ";
                        }
                    }
                } else {
                    var7 = "Stack (b-->t): ";

                    for(var5 = 0; var5 < this.theStack.size(); ++var5) {
                        var6 = this.theStack.peekN(var5);
                        var7 = var7 + this.vertexList[var6].label + " ";
                    }
                }

                var1.drawString(var7, 260, 296);
            }

            this.drawMode = 4;
        }
    }

    private void drawEdge(Graphics var1, int var2, int var3, int var4) {
        int var5 = this.vertexList[var2].x;
        int var6 = this.vertexList[var2].y;
        int var7 = this.vertexList[var3].x;
        int var8 = this.vertexList[var3].y;
        var1.drawLine(var5, var6, var7, var8);
        if (var4 != 1) {
            var1.drawLine(var5 + 1, var6, var7 + 1, var8);
            if (var7 - var5 > 0 != var8 - var6 > 0) {
                var1.drawLine(var5, var6 - 1, var7, var8 - 1);
                return;
            }

            var1.drawLine(var5, var6 + 1, var7, var8 + 1);
        }

    }

    public void drawAdjm(Graphics var1) {
        if (this.nVerts == 0) {
            this.note = "No vertices; adjacency matrix is empty";
        } else {
            byte var2 = 95;
            byte var3 = 110;
            byte var4 = 27;
            byte var5 = 18;
            var1.drawLine(var2 - var4, var3 - var5 + 4, var2 + this.nVerts * var4 - var4 + 8, var3 - var5 + 4);
            var1.drawLine(var2 - var4 / 2 + 2, var3 - 2 * var5 + 8, var2 - var4 / 2 + 2, var3 + (this.nVerts - 1) * var5);

            for(int var6 = 0; var6 < this.nVerts; ++var6) {
                var1.drawString(String.valueOf(this.vertexList[var6].label), var2 - var4, var3 + var6 * var5);
            }

            for(int var7 = 0; var7 < this.nVerts; ++var7) {
                var1.drawString(String.valueOf(this.vertexList[var7].label), var2 + var7 * var4, var3 - var5);

                for(int var8 = 0; var8 < this.nVerts; ++var8) {
                    var1.drawString(String.valueOf(this.adjMat[var8][var7]), var2 + var7 * var4, var3 + var8 * var5);
                }
            }

        }
    }
}
