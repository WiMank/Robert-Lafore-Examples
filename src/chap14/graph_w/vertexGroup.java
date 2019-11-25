package chap14.graph_w;

import java.awt.Color;
import java.awt.Graphics;

class vertexGroup {
    private final int appletWidth = 440;
    private final int appletHeight = 300;
    private final int topMargin = 70;
    private final int leftMargin = 10;
    private final int textHeight = 13;
    private final int vertexDiameter = 22;
    private final int weightBoxWidth = 17;
    private final int weightBoxHeight = 15;
    private final int noteBoxTop = 45;
    private final int noteBoxHeight = 25;
    private final int noteBoxWidth = 430;
    private final int visitBoxTop = 280;
    private final int visitBoxHeight = 25;
    private final int visitBoxWidth = 430;
    private final int drawingBoxHeight = 207;
    private final int MAX_VERTS = 16;
    private final int MAX_WEIGHT = 99;
    private final int INFINITY = 1000000;
    private vertex[] vertexList;
    private disIs[][] adjMat;
    private disIs[][] adjMat2;
    private int nVerts;
    private priorityQ thePQ;
    private int[] tree;
    private int nTree;
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
    private int currentVert;
    private int displayVertex;
    private int displayEdge1;
    private int displayEdge2;

    public vertexGroup() {
        vertex.classChar = 'A';
        this.vertexList = new vertex[16];
        this.adjMat = new disIs[16][16];
        this.adjMat2 = new disIs[16][16];
        this.nVerts = 0;
        this.thePQ = new priorityQ();
        this.tree = new int[16];
        this.nTree = 0;

        for(int var1 = 0; var1 < 16; ++var1) {
            for(int var2 = 0; var2 < 16; ++var2) {
                this.adjMat[var1][var2] = new disIs(1000000, false);
                this.adjMat2[var1][var2] = new disIs(1000000, false);
            }
        }

        this.showAdjm = false;
        this.startingVertex = -1;
        this.note = "Double-click mouse to make vertex";
        this.drawMode = 4;
    }

    public void makeVertex(int var1, int var2) {
        this.madeVertex = true;
        if (this.nVerts > 15) {
            this.note = "CAN'T INSERT more vertices";
        } else {
            this.vertexList[this.nVerts++] = new vertex(var1, var2);
            this.note = "Made vertex " + this.vertexList[this.nVerts - 1].label;
            this.displayVertex = this.nVerts - 1;
            this.opMode = 0;
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

    public void endDrag(boolean var1, int var2, int var3, int var4) {
        if (this.madeVertex) {
            this.madeVertex = false;
            this.drawMode = 2;
        } else {
            int var5 = this.closeTo(this.xStartDrag, this.yStartDrag);
            int var6 = this.closeTo(var3, var4);
            if (this.opMode > 0) {
                if (this.startingVertex == -1) {
                    this.note = "Click on a VERTEX";
                } else {
                    this.note = "You clicked on " + this.vertexList[this.startingVertex].label;
                }

                this.drawMode = 1;
            } else if (var5 >= 0 && var6 >= 0 && var5 != var6) {
                if (var1 && var2 >= 0 && var2 <= 99) {
                    this.note = "Made edge from " + this.vertexList[var5].label + " to " + this.vertexList[var6].label + " with weight " + var2;
                    this.adjMat[var5][var6].distance = var2;
                    this.adjMat[var6][var5].distance = var2;
                    this.madeVertex = false;
                    this.displayEdge1 = var5;
                    this.displayEdge2 = var6;
                    this.drawMode = 3;
                } else {
                    this.note = "CAN'T MAKE EDGE: Enter a weight (0 to " + 99 + ")";
                    this.drawMode = 1;
                }
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

    public void mst() {
        if (this.opMode != 1) {
            this.opMode = 1;
            this.codePart = 1;
        }

        int var2;
        int var3;
        switch(this.codePart) {
            case 1:
                this.saveState();
                this.note = "Single-click on vertex from which to start tree";
                this.startingVertex = -1;
                this.drawMode = 1;
                this.codePart = 2;
                return;
            case 2:
                if (this.startingVertex == -1) {
                    this.note = "INVALID VERTEX";
                    this.codePart = 1;
                } else {
                    this.note = "Starting tree from vertex " + this.vertexList[this.startingVertex].label;
                    this.currentVert = this.startingVertex;
                    this.codePart = 3;
                }

                this.drawMode = 1;
                return;
            case 3:
                this.tree[this.nTree++] = this.currentVert;
                this.vertexList[this.currentVert].isInTree = true;
                this.note = "Placed vertex " + this.vertexList[this.currentVert].label + " in tree";
                if (this.nTree == this.nVerts) {
                    this.codePart = 6;
                } else {
                    this.codePart = 4;
                }

                this.drawMode = 4;
                return;
            case 4:
                for(int var5 = 0; var5 < this.nVerts; ++var5) {
                    if (var5 != this.currentVert && !this.vertexList[var5].isInTree) {
                        var2 = this.adjMat[this.currentVert][var5].distance;
                        if (var2 != 1000000) {
                            this.putInPQ(var5, var2);
                        }
                    }
                }

                if (this.thePQ.size() > 0) {
                    this.note = "Placed vertices adj to " + this.vertexList[this.currentVert].label + " in priority queue";
                    this.codePart = 5;
                } else {
                    this.note = "Graph not connected";
                    this.codePart = 8;
                }

                this.drawMode = 1;
                return;
            case 5:
                edge var1 = this.thePQ.removeMin();
                this.currentVert = var1.destVert;
                var2 = var1.srcVert;
                this.adjMat[var2][this.currentVert].isInTree = true;
                this.adjMat[this.currentVert][var2].isInTree = true;
                this.note = "Removed minimum-distance edge " + this.vertexList[var1.srcVert].label + this.vertexList[var1.destVert].label + var1.distance + " from priority queue";
                this.drawMode = 1;
                this.codePart = 3;
                return;
            case 6:
                this.note = "Press again to delete unmarked edges";
                this.drawMode = 1;
                this.codePart = 7;
                return;
            case 7:
                for(var3 = 0; var3 < this.nVerts; ++var3) {
                    for(int var4 = 0; var4 < this.nVerts; ++var4) {
                        if (this.adjMat[var3][var4].distance != 1000000) {
                            if (!this.adjMat[var3][var4].isInTree) {
                                this.adjMat[var3][var4].distance = 1000000;
                            } else {
                                this.adjMat[var3][var4].isInTree = false;
                            }
                        }
                    }
                }

                this.note = "Minimum spanning tree; press again to restore tree";
                this.drawMode = 4;
                this.codePart = 8;
                return;
            case 8:
                this.restoreState();
                this.note = "Press any button";
                this.nTree = 0;

                for(var3 = 0; var3 < this.nVerts; ++var3) {
                    this.vertexList[var3].isInTree = false;
                }

                while(!this.thePQ.isEmpty()) {
                    this.thePQ.removeMin();
                }

                this.codePart = 1;
                this.opMode = 0;
                this.drawMode = 4;
                return;
            default:
        }
    }

    public void saveState() {
        for(int var1 = 0; var1 < this.nVerts; ++var1) {
            for(int var2 = 0; var2 < this.nVerts; ++var2) {
                this.adjMat2[var1][var2].distance = this.adjMat[var1][var2].distance;
                this.adjMat2[var1][var2].isInTree = this.adjMat[var1][var2].isInTree;
            }
        }

    }

    public void restoreState() {
        for(int var1 = 0; var1 < this.nVerts; ++var1) {
            for(int var2 = 0; var2 < this.nVerts; ++var2) {
                this.adjMat[var1][var2].distance = this.adjMat2[var1][var2].distance;
                this.adjMat[var1][var2].isInTree = this.adjMat2[var1][var2].isInTree;
            }
        }

    }

    public void putInPQ(int var1, int var2) {
        int var3 = this.thePQ.find(var1);
        edge var4;
        if (var3 != -1) {
            var4 = this.thePQ.peekN(var3);
            int var5 = var4.distance;
            if (var5 > var2) {
                this.thePQ.removeN(var3);
                edge var6 = new edge(this.currentVert, var1, var2);
                this.thePQ.insert(var6);
                return;
            }
        } else {
            var4 = new edge(this.currentVert, var1, var2);
            this.thePQ.insert(var4);
        }

    }

    public String dtoS(int var1) {
        return var1 == 1000000 ? "inf" : String.valueOf(var1);
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
            if (this.vertexList[var2].isInTree) {
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
            int var5;
            switch(this.drawMode) {
                case 2:
                    this.drawOneVertex(var1, this.displayVertex);
                    break;
                case 3:
                    var2 = this.adjMat[this.displayEdge1][this.displayEdge2].distance;
                    if (var2 < 1000000) {
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

                    for(var4 = 0; var4 < this.nVerts; ++var4) {
                        for(var5 = 0; var5 < this.nVerts; ++var5) {
                            var2 = this.adjMat[var4][var5].distance;
                            if (var2 < 1000000) {
                                this.drawEdge(var1, var4, var5);
                            }
                        }
                    }

                    for(var4 = 0; var4 < this.nVerts; ++var4) {
                        this.drawOneVertex(var1, var4);
                    }
            }

            var1.setColor(Color.lightGray);
            var1.fillRect(10, 45, 430, 25);
            var1.setColor(Color.black);
            var1.drawString(this.note, 16, 64);
            var1.setColor(Color.lightGray);
            var1.fillRect(10, 280, 430, 25);
            var1.setColor(Color.black);
            if (this.opMode == 1) {
                String var3 = "Tree: ";

                for(var4 = 0; var4 < this.nTree; ++var4) {
                    var3 = var3 + this.vertexList[this.tree[var4]].label + " ";
                }

                var1.drawString(var3, 10, 296);
                var3 = "PQ: ";

                for(var5 = 0; var5 < this.thePQ.size(); ++var5) {
                    var3 = var3 + this.vertexList[this.thePQ.peekN(var5).srcVert].label;
                    var3 = var3 + this.vertexList[this.thePQ.peekN(var5).destVert].label;
                    var3 = var3 + this.thePQ.peekN(var5).distance + " ";
                }

                var1.drawString(var3, 210, 296);
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
        if (this.adjMat[var2][var3].isInTree) {
            var1.drawLine(var4 + 1, var5, var6 + 1, var7);
            if (var6 - var4 > 0 != var7 - var5 > 0) {
                var1.drawLine(var4, var5 - 1, var6, var7 - 1);
            } else {
                var1.drawLine(var4, var5 + 1, var6, var7 + 1);
            }
        }

        int var8 = (var4 + var6) / 2 - 8;
        int var9 = (var5 + var7) / 2 - 7;
        var1.setColor(Color.white);
        var1.fillRect(var8, var9, 17, 15);
        var1.setColor(Color.black);
        var1.drawRect(var8, var9, 17, 15);
        int var10 = this.adjMat[var2][var3].distance;
        int var11 = var10 > 9 ? 2 : 6;
        var1.drawString(String.valueOf(var10), var8 + var11, var9 + 15 - 2);
    }

    public void drawAdjm(Graphics var1) {
        if (this.nVerts == 0) {
            this.note = "No vertices; adjacency matrix is empty";
        } else {
            byte var2 = 90;
            byte var3 = 106;
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
                    var1.drawString(this.dtoS(this.adjMat[var8][var7].distance), var2 + var7 * var4, var3 + var8 * var5);
                }
            }

        }
    }
}

