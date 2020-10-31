package gui.panel;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import gui.listener.JTreeSelectionListener;
import util.*;
import gui.frame.DetailDialog;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.*;
import java.io.IOException;
import java.util.ArrayList;

public class MainPanel extends JPanel {
    private MainPanel(){
        /*建树*/
        String[] src=IOUtil.read("data.txt");
        DefaultMutableTreeNode root=new DefaultMutableTreeNode("家谱");
        ArrayList<Node> nodes=new ArrayList<Node>();
        int count=Integer.valueOf(src[0]);
        String name,birthplace,vocation,birthday,deathday;
        boolean sex;
        int height;
        int sigma=0;
        for(int i=0;i<count;i++){
            sigma++;name=src[sigma];
            sigma++;sex=Boolean.valueOf(src[sigma]);
            sigma++;birthplace=src[sigma];
            sigma++;birthday=src[sigma];
            sigma++;deathday=src[sigma];
            sigma++;height=Integer.valueOf(src[sigma]);
            sigma++;vocation=src[sigma];
            Node newperson=new Node(name,sex,birthplace, birthday,deathday,height,vocation);
            newperson.setUserObject(name);
            sigma++;
            if(Boolean.valueOf(src[sigma])==true) {
                sigma++;
                name = src[sigma];
                sigma++;
                sex = Boolean.valueOf(src[sigma]);
                sigma++;
                birthplace = src[sigma];
                sigma++;
                birthday = src[sigma];
                sigma++;
                deathday = src[sigma];
                sigma++;
                height = Integer.valueOf(src[sigma]);
                sigma++;
                vocation = src[sigma];
                Node spouse = new Node(name, sex, birthplace, birthday, deathday, height, vocation);
                spouse.setUserObject(name);
                newperson.marry(spouse);
            }
            nodes.add(newperson);
        }
        sigma++;int relationcount=Integer.valueOf(src[sigma]);
        boolean[] ischild=new boolean[count];
        for(int i=0;i<ischild.length;i++)ischild[i]=false;
        for(int i=0;i<relationcount;i++){
            sigma++;int u=Integer.valueOf(src[sigma]);
            sigma++;int v=Integer.valueOf(src[sigma]);
            ischild[v]=true;
            nodes.get(u).add(nodes.get(v));
        }
        for(int i=0;i<ischild.length;i++){
            if(ischild[i]==false)root.add(nodes.get(i));
        }
        JTree genealogy=new JTree(root);
        genealogy.setOpaque(false);
        JScrollPane genealogysp=new JScrollPane(genealogy);
        /*建树*/
        /*设置监听*/
        genealogy.addTreeSelectionListener(new JTreeSelectionListener());
        /*设置监听*/
        //设置拖拽
        DragSource dragSource=DragSource.getDefaultDragSource();
        dragSource.createDefaultDragGestureRecognizer(genealogy, DnDConstants.ACTION_COPY_OR_MOVE, new DragGestureListener() {
            @Override
            public void dragGestureRecognized(DragGestureEvent dge) {
                TreePath path = genealogy.getSelectionPath();
                if ((path == null) || (path.getPathCount() <= 1)) {
                    return;
                }
                NodeTransferable transferable = new NodeTransferable(path);
                dge.startDrag(DragSource.DefaultCopyDrop, transferable, new DragSourceListener() {
                        @Override
                        public void dragEnter(DragSourceDragEvent dsde) {

                        }

                        @Override
                        public void dragOver(DragSourceDragEvent dsde) {

                        }

                        @Override
                        public void dropActionChanged(DragSourceDragEvent dsde) {

                        }

                        @Override
                        public void dragExit(DragSourceEvent dse) {

                        }

                        @Override
                        public void dragDropEnd(DragSourceDropEvent dsde) {

                        }
                });
            }
        });
        DropTarget dropTarget=new DropTarget(genealogy, new DropTargetListener() {
            @Override
            public void dragEnter(DropTargetDragEvent dtde) {

            }

            @Override
            public void dragOver(DropTargetDragEvent dtde) {

            }

            @Override
            public void dropActionChanged(DropTargetDragEvent dtde) {

            }

            @Override
            public void dragExit(DropTargetEvent dte) {

            }

            @Override
            public void drop(DropTargetDropEvent dtde) {
                Point pt = dtde.getLocation();
                DropTargetContext dtc = dtde.getDropTargetContext();
                JTree tree = (JTree) dtc.getComponent();
                TreePath parentpath = tree.getClosestPathForLocation(pt.x, pt.y);
                DefaultMutableTreeNode parent = (DefaultMutableTreeNode) parentpath.getLastPathComponent();
                if (parentpath==null) {
                    //防止移至空地
                    dtde.rejectDrop();
                    return;
                }
                boolean flag=false;
                try {
                    Transferable tr = dtde.getTransferable();
                    DataFlavor[] flavors = tr.getTransferDataFlavors();
                    for (int i = 0; i < flavors.length; i++) {
                        if (tr.isDataFlavorSupported(flavors[i])) {
                            dtde.acceptDrop(dtde.getDropAction());
                            TreePath p = (TreePath) tr.getTransferData(flavors[i]);
                            DefaultMutableTreeNode node = null;
                            DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
                            node=(DefaultMutableTreeNode) genealogy.getLastSelectedPathComponent();
                            if (parent.getUserObject().toString().equals(node.getUserObject().toString())) {
                                flag=true;
                                return;
                            }
                            node = (DefaultMutableTreeNode) p.getLastPathComponent();
                            model.insertNodeInto(node, parent, 0);
                            node=(DefaultMutableTreeNode) genealogy.getLastSelectedPathComponent();
                            node.removeFromParent();
                            genealogy.updateUI();
                            dtde.dropComplete(true);
                            return;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    dtde.rejectDrop();
                }
                if(flag==true){
                    dtde.rejectDrop();
                    return;
                }
            }
        });
        //设置拖拽
        this.add(genealogysp);
    }
    private static MainPanel instance=new MainPanel();
    public static MainPanel getInstance(){return instance;}
}
