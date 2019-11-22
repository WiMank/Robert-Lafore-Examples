package chap13.graph_d;

import java.awt.Color;
import java.awt.Graphics;

class vertexGroup {
    private final int appletWidth = 440;
    private final int appletHeight = 300;
    private final int topMargin = 70;
    private final int leftMargin = 10;
    private final int textHeight = 13;
    private final int vertexDiameter = 22;
    private final int dirDistance = 18;
    private final int dirSize = 7;
    private final int noteBoxTop = 45;
    private final int noteBoxHeight = 25;
    private final int noteBoxWidth = 350;
    private final int visitBoxTop = 280;
    private final int visitBoxHeight = 25;
    private final int visitBoxWidth = 430;
    private final int drawingBoxHeight = 207;
    private final int MAX_VERTS = 20;
    private vertex[] vertexList;
    private vertex[] vertexList2;
    private int[][] adjMat;
    private int[][] adjMat2;
    private int nVerts;
    private int nVerts2;
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
    private char[] visitArray;
    private int visitIndex;

    public vertexGroup() {
        vertex.classChar = 'A';
        this.vertexList = new vertex[20];
        this.vertexList2 = new vertex[20];
        this.adjMat = new int[20][20];
        this.adjMat2 = new int[20][20];
        this.nVerts = 0;

        for(int var1 = 0; var1 < 20; ++var1) {
            for(int var2 = 0; var2 < 20; ++var2) {
                this.adjMat[var1][var2] = -1;
            }
        }

        this.showAdjm = false;
        this.startingVertex = -1;
        this.visitArray = new char[20];
        this.visitIndex = 0;
        this.note = "Double-click mouse to make vertex";
        this.drawMode = 4;
    }

    public void makeVertex(int var1, int var2) {
        this.madeVertex = true;
        if (this.nVerts > 19) {
            this.note = "Can't insert more vertices";
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
                this.madeVertex = false;
                this.displayEdge1 = var3;
                this.displayEdge2 = var4;
                this.drawMode = 3;
            } else {
                this.note = "CAN'T MAKE EDGE: Drag from vertex to vertex";
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

    public void topo() {
        if (this.opMode != 1) {
            this.opMode = 1;
            this.codePart = 1;
        }

        switch(this.codePart) {
            case 1:
                this.saveState();
                this.note = "Will perform topological sort";
                this.drawMode = 1;
                this.codePart = 2;
                return;
            case 2:
                if (this.nVerts > 0) {
                    this.currentVertex = this.noSuccessors();
                    if (this.currentVertex == -1) {
                        this.note = "Cannot sort graph with cycles";
                        this.drawMode = 1;
                        this.codePart = 4;
                    } else {
                        this.note = "Will remove vertex " + this.vertexList[this.currentVertex].label;
                        this.drawMode = 1;
                        this.codePart = 3;
                    }
                } else {
                    this.note = "Sort is complete; will restore graph";
                    this.codePart = 4;
                }

                this.drawMode = 1;
                return;
            case 3:
                for(int var1 = this.visitIndex - 1; var1 >= 0; --var1) {
                    this.visitArray[var1 + 1] = this.visitArray[var1];
                }

                this.visitArray[0] = this.vertexList[this.currentVertex].label;
                ++this.visitIndex;
                this.note = "Added vertex " + this.vertexList[this.currentVertex].label + " at start of sorted list";
                this.deleteVertex(this.currentVertex);
                this.drawMode = 4;
                this.codePart = 2;
                return;
            case 4:
                this.restoreState();
                this.note = "Will reset sort";
                this.drawMode = 4;
                this.codePart = 5;
                return;
            case 5:
                this.note = "Press any button";
                this.visitIndex = 0;
                this.codePart = 1;
                this.opMode = 0;
                this.drawMode = 1;
                return;
            default:
        }
    }

    public int noSuccessors() {
        for(int var2 = 0; var2 < this.nVerts; ++var2) {
            boolean var1 = false;

            for(int var3 = 0; var3 < this.nVerts; ++var3) {
                if (this.adjMat[var2][var3] > 0) {
                    var1 = true;
                    break;
                }
            }

            if (!var1) {
                return var2;
            }
        }

        return -1;
    }

    public void deleteVertex(int var1) {
        if (var1 == this.nVerts - 1) {
            --this.nVerts;
        } else {
            for(int var2 = var1; var2 < this.nVerts - 1; ++var2) {
                this.vertexList[var2] = this.vertexList[var2 + 1];
            }

            for(int var3 = var1; var3 < this.nVerts - 1; ++var3) {
                this.moveRowUp(var3, this.nVerts);
            }

            for(int var4 = var1; var4 < this.nVerts - 1; ++var4) {
                this.moveColLeft(var4, this.nVerts - 1);
            }

            --this.nVerts;
        }
    }

    private void moveRowUp(int var1, int var2) {
        for(int var3 = 0; var3 < var2; ++var3) {
            this.adjMat[var1][var3] = this.adjMat[var1 + 1][var3];
        }

    }

    private void moveColLeft(int var1, int var2) {
        for(int var3 = 0; var3 < var2; ++var3) {
            this.adjMat[var3][var1] = this.adjMat[var3][var1 + 1];
        }

    }

    public void saveState() {
        for(int var1 = 0; var1 < this.nVerts; ++var1) {
            this.vertexList2[var1] = this.vertexList[var1];
        }

        for(int var2 = 0; var2 < this.nVerts; ++var2) {
            for(int var3 = 0; var3 < this.nVerts; ++var3) {
                this.adjMat2[var2][var3] = this.adjMat[var2][var3];
            }
        }

        this.nVerts2 = this.nVerts;
    }

    public void restoreState() {
        for(int var1 = 0; var1 < this.nVerts2; ++var1) {
            this.vertexList[var1] = this.vertexList2[var1];
        }

        for(int var2 = 0; var2 < this.nVerts2; ++var2) {
            for(int var3 = 0; var3 < this.nVerts2; ++var3) {
                this.adjMat[var2][var3] = this.adjMat2[var2][var3];
            }
        }

        this.nVerts = this.nVerts2;
    }

    public void warningNew() {
        this.note = "ARE YOU SURE? Press again to clear old graph";
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
            if (this.vertexList[var2].inTheTree) {
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
                    if (var2 >= 0) {
                        this.drawEdge(var1, this.displayEdge1, this.displayEdge2);
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
                            if (var2 >= 0) {
                                this.drawEdge(var1, var3, var4);
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
                String var5 = "List: ";

                for(var4 = 0; var4 < this.visitIndex; ++var4) {
                    var5 = var5 + this.visitArray[var4] + " ";
                }

                var1.drawString(var5, 16, 296);
            }

            this.drawMode = 4;
        }
    }

    private void drawEdge(Graphics var1, int var2, int var3) {
        int var4 = this.vertexList[var2].x;
        int var5 = this.vertexList[var2].y;
        int var6 = this.vertexList[var3].x;
        int var7 = this.vertexList[var3].y;
        var1.setColor(Color.black);
        var1.drawLine(var4, var5, var6, var7);
        this.drawDirection(var1, var4, var5, var6, var7);
    }

    public void drawDirection(Graphics var1, int var2, int var3, int var4, int var5) {
        int var6 = var2 - var4;
        int var7 = var3 - var5;
        int var8 = (int)Math.sqrt((double)(var6 * var6 + var7 * var7));
        byte var9 = 18;
        int var10 = var6 * var9 / var8;
        int var11 = var7 * var9 / var8;
        var1.fillOval(var4 + var10 - 3, var5 + var11 - 3, 7, 7);
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

