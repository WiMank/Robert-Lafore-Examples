package chap13.graph_d;

import java.applet.Applet;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GraphD extends Applet implements Runnable, ActionListener, MouseListener {
    private Thread runner;
    private vertexGroup theVertexGroup;
    private boolean wasClearPressed = false;
    private Button newButton;
    private Button topoButton;
    private Button viewButton;

    public void init() {
        this.addMouseListener(this);
        this.setLayout(new FlowLayout());
        Panel var1 = new Panel();
        this.add(var1);
        var1.setLayout(new FlowLayout());
        Panel var2 = new Panel();
        var1.add(var2);
        var2.setLayout(new FlowLayout(0));
        this.newButton = new Button("New");
        var2.add(this.newButton);
        this.newButton.addActionListener(this);
        this.topoButton = new Button("Topo");
        var2.add(this.topoButton);
        this.topoButton.addActionListener(this);
        this.viewButton = new Button("View");
        var2.add(this.viewButton);
        this.viewButton.addActionListener(this);
        this.theVertexGroup = new vertexGroup();
        this.repaint();
    }

    public void start() {
        if (this.runner == null) {
            this.runner = new Thread(this);
            this.runner.start();
        }

    }

    public void stop() {
        if (this.runner != null) {
            this.runner.stop();
            this.runner = null;
        }

    }

    public void paint(Graphics var1) {
        this.theVertexGroup.draw(var1);
    }

    public void update(Graphics var1) {
        this.paint(var1);
    }

    public void actionPerformed(ActionEvent var1) {
        if (var1.getSource() == this.newButton) {
            if (this.wasClearPressed) {
                this.wasClearPressed = false;
                this.theVertexGroup = new vertexGroup();
            } else {
                this.wasClearPressed = true;
                this.theVertexGroup.warningNew();
            }
        }

        if (var1.getSource() == this.topoButton) {
            this.theVertexGroup.topo();
            this.wasClearPressed = false;
        }

        if (var1.getSource() == this.viewButton) {
            this.theVertexGroup.changeView();
            this.wasClearPressed = false;
        }

        this.repaint();

        try {
            Thread.sleep(10L);
        } catch (InterruptedException var2) {
        }
    }

    public void run() {
        // $FF: Couldn't be decompiled
    }

    public void mousePressed(MouseEvent var1) {
        int var2 = var1.getX();
        int var3 = var1.getY();
        this.wasClearPressed = false;
        if (var1.getClickCount() == 1) {
            this.theVertexGroup.startDrag(var2, var3);
        } else if (var1.getClickCount() == 2) {
            this.theVertexGroup.makeVertex(var2, var3);
        }

        this.repaint();
    }

    public void mouseReleased(MouseEvent var1) {
        int var2 = var1.getX();
        int var3 = var1.getY();
        this.theVertexGroup.endDrag(var2, var3);
        this.repaint();
    }

    public void mouseClicked(MouseEvent var1) {
    }

    public void mouseEntered(MouseEvent var1) {
    }

    public void mouseExited(MouseEvent var1) {
    }

    public GraphD() {
    }
}
