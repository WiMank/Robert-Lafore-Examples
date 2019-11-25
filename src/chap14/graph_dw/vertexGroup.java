package chap14.graph_dw;

import java.awt.Color;
import java.awt.Graphics;

class vertexGroup {
    private final int appletWidth = 440;
    private final int appletHeight = 325;
    private final int topMargin = 70;
    private final int leftMargin = 10;
    private final int textHeight = 13;
    private final int vertexDiameter = 22;
    private final int dirDistance = 18;
    private final int dirSize = 7;
    private final int weightBoxWidth = 17;
    private final int weightBoxHeight = 15;
    private final int noteBoxTop = 45;
    private final int noteBoxHeight = 25;
    private final int noteBoxWidth = 430;
    private final int visitBoxTop = 305;
    private final int visitBoxHeight = 25;
    private final int visitBoxWidth = 430;
    private final int charSpace = 40;
    private final int headerBoxTop = 280;
    private final int drawingBoxHeight = 207;
    private final int MAX_VERTS = 13;
    private final int MAX_WEIGHT = 99;
    private final int INFINITY = 1000000;
    private vertex[] vertexList;
    private disIs[][] adjMat;
    private int nVerts;
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
    private int startTree;
    private int currentSrc;
    private int currentDest;
    private int startToCurrent;
    private int loopCounter;
    private int displayVertex;
    private int displayEdge1;
    private int displayEdge2;
    private disP[] sPath;
    private boolean willUpdate;
    private int currentToFringe;
    private int startToFringe;

    public vertexGroup() {
        vertex.classChar = 'A';
        this.vertexList = new vertex[13];
        this.sPath = new disP[13];
        this.adjMat = new disIs[13][13];
        this.nVerts = 0;
        this.tree = new int[13];
        this.nTree = 0;

        for(int var1 = 0; var1 < 13; ++var1) {
            for(int var2 = 0; var2 < 13; ++var2) {
                this.adjMat[var1][var2] = new disIs(1000000, false);
            }
        }

        this.showAdjm = false;
        this.startingVertex = -1;
        this.note = "Double-click mouse to make vertex";
        this.drawMode = 4;
    }

    public void makeVertex(int var1, int var2) {
        this.madeVertex = true;
        if (this.nVerts > 12) {
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

    public void path() {
        if (this.opMode != 1) {
            this.opMode = 1;
            this.codePart = 1;
        }

        int var1;
        int var2;
        int var3;
        switch(this.codePart) {
            case 1:
                this.note = "Single-click on starting vertex";
                this.startingVertex = -1;
                this.drawMode = 1;
                this.codePart = 2;
                return;
            case 2:
                if (this.startingVertex == -1) {
                    this.note = "INVALID VERTEX";
                    this.codePart = 1;
                } else {
                    this.startTree = this.startingVertex;
                    this.note = "Starting from vertex " + this.vertexList[this.startTree].label;
                    this.codePart = 3;
                }

                this.drawMode = 1;
                return;
            case 3:
                this.vertexList[this.startTree].isInTree = true;
                this.tree[0] = this.startTree;
                this.nTree = 1;
                this.note = "Added vertex " + this.vertexList[this.startTree].label + " to tree";
                this.displayVertex = this.startTree;
                this.drawMode = 2;
                this.codePart = 4;
                return;
            case 4:
                for(var1 = 0; var1 < this.nVerts; ++var1) {
                    var2 = this.adjMat[this.startTree][var1].distance;
                    disP var7 = new disP(this.startTree, var2);
                    this.sPath[var1] = var7;
                }

                this.note = "Copied row " + this.vertexList[this.startTree].label + " from adjacency matrix to shortest-path array";
                this.drawMode = 1;
                this.codePart = 5;
                return;
            case 5:
                var1 = 1000000;
                var2 = 0;

                for(var3 = 0; var3 < this.nVerts; ++var3) {
                    if (!this.vertexList[var3].isInTree) {
                        int var8 = this.sPath[var3].distance;
                        if (var8 < var1) {
                            var1 = var8;
                            var2 = var3;
                        }
                    }
                }

                if (var1 == 1000000) {
                    this.note = "One or more vertices are UNREACHABLE";
                    this.codePart = 12;
                } else {
                    this.currentDest = var2;
                    this.currentSrc = this.sPath[var2].parentVert;
                    this.startToCurrent = this.sPath[var2].distance;
                    this.note = "Minimum distance from " + this.vertexList[this.startTree].label + " is " + this.startToCurrent + ", to vertex " + this.vertexList[this.currentDest].label;
                    this.codePart = 6;
                }

                this.drawMode = 1;
                return;
            case 6:
                this.vertexList[this.currentDest].isInTree = true;
                this.tree[this.nTree++] = this.currentDest;
                this.note = "Added vertex " + this.vertexList[this.currentDest].label + " to tree";
                this.adjMat[this.currentSrc][this.currentDest].isInTree = true;
                this.displayEdge1 = this.currentSrc;
                this.displayEdge2 = this.currentDest;
                if (this.nTree == this.nVerts) {
                    this.codePart = 12;
                } else {
                    this.loopCounter = 0;
                    this.codePart = 7;
                }

                this.drawMode = 3;
                return;
            case 7:
                this.note = "Will adjust values in shortest-path array";
                this.drawMode = 1;
                this.codePart = 8;
                return;
            case 8:
                while(this.loopCounter < this.nVerts && this.vertexList[this.loopCounter].isInTree) {
                    ++this.loopCounter;
                }

                if (this.loopCounter == this.nVerts) {
                    this.note = "Finished all entries in shortest-path array";
                    this.codePart = 5;
                    return;
                }

                this.note = "Will compare distances for column " + this.vertexList[this.loopCounter].label;
                this.codePart = 9;
                this.drawMode = 1;
                return;
            case 9:
                this.currentToFringe = this.adjMat[this.currentDest][this.loopCounter].distance;
                this.startToFringe = this.startToCurrent + this.currentToFringe;
                var3 = this.sPath[this.loopCounter].distance;
                String var4;
                if (this.startToFringe < var3) {
                    var4 = " less than ";
                    this.willUpdate = true;
                } else {
                    var4 = " greater than or equal to ";
                    this.willUpdate = false;
                }

                this.note = "To " + this.vertexList[this.loopCounter].label + ":  " + this.vertexList[this.startTree].label + " to " + this.vertexList[this.currentDest].label + " (" + this.dtoS(this.startToCurrent) + ") plus edge " + this.vertexList[this.currentDest].label + this.vertexList[this.loopCounter].label + " (" + this.dtoS(this.currentToFringe) + ") " + var4 + this.vertexList[this.startTree].label + " to " + this.vertexList[this.loopCounter].label + " (" + this.dtoS(var3) + ")";
                this.codePart = 10;
                this.drawMode = 1;
                return;
            case 10:
                if (this.willUpdate) {
                    this.sPath[this.loopCounter].parentVert = this.currentDest;
                    this.sPath[this.loopCounter].distance = this.startToFringe;
                    this.note = "Updated array column " + this.vertexList[this.loopCounter].label;
                } else {
                    this.note = "No need to update array column " + this.vertexList[this.loopCounter].label;
                }

                this.drawMode = 1;
                this.codePart = 11;
                return;
            case 11:
                if (++this.loopCounter < this.nVerts) {
                    this.note = "Will examine next non-tree column";
                    this.codePart = 8;
                } else {
                    this.note = "Done all entries in shortest-path array";
                    this.codePart = 5;
                }

                this.drawMode = 1;
                return;
            case 12:
                this.note = "All shortest paths from " + this.vertexList[this.startTree].label + " found; distances in array";
                this.codePart = 13;
                this.drawMode = 1;
                return;
            case 13:
                this.note = "Press again to reset paths";
                this.codePart = 14;
                this.drawMode = 1;
                return;
            case 14:
                this.nTree = 0;

                for(int var5 = 0; var5 < this.nVerts; ++var5) {
                    this.vertexList[var5].isInTree = false;

                    for(int var6 = 0; var6 < this.nVerts; ++var6) {
                        this.adjMat[var5][var6].isInTree = false;
                    }
                }

                this.note = "Press any button";
                this.codePart = 1;
                this.opMode = 0;
                this.drawMode = 4;
                return;
            default:
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
            var1.fillRect(0, 0, 440, 325);
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
                    var2 = this.adjMat[this.displayEdge1][this.displayEdge2].distance;
                    if (var2 < 1000000) {
                        this.drawEdge(var1, this.displayEdge1, this.displayEdge2);
                    }

                    this.drawOneVertex(var1, this.displayEdge1);
                    this.drawOneVertex(var1, this.displayEdge2);
                    break;
                case 4:
                    var1.setColor(Color.lightGray);
                    var1.fillRect(0, 0, 440, 325);
                    var1.setColor(Color.black);
                    var1.drawRect(2, 71, 436, 207);

                    for(var4 = 0; var4 < this.nVerts; ++var4) {
                        for(int var5 = 0; var5 < this.nVerts; ++var5) {
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
            var1.fillRect(10, 305, 430, 25);
            var1.setColor(Color.black);
            if (this.opMode == 1 && this.codePart > 4) {
                for(var4 = 0; var4 < this.nVerts; ++var4) {
                    String var7 = this.dtoS(this.sPath[var4].distance);
                    String var3 = var7 + "(";
                    int var6 = this.sPath[var4].parentVert;
                    var3 = var3 + this.vertexList[var6].label + ")";
                    var1.setColor(Color.black);
                    var1.drawString(var3, 10 + 40 * var4, 321);
                    var1.drawLine(10 + 40 * var4, 303, 10 + 40 * (var4 + 1), 303);
                    var3 = String.valueOf(this.vertexList[var4].label);
                    if (this.vertexList[var4].isInTree) {
                        var1.setColor(Color.red);
                    } else {
                        var1.setColor(Color.black);
                    }

                    var1.drawString(var3, 10 + 40 * var4, 296);
                }
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

        this.drawDirection(var1, var4, var5, var6, var7);
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
