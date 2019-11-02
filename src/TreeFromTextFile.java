
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author yeexu_qt
 */
public class TreeFromTextFile {
    private BufferedReader in;
    private LineNumberReader ln;
    private String line;    //value of a line in the text file
    private String root;    //value to be used for the root Node of our JTree                         
    private String filename = "BookList.txt";
    private String encoding = "UTF-8";
    private DefaultMutableTreeNode top;
    private JTree tree;

    public TreeFromTextFile() {
        getRootNode();
        top = new DefaultMutableTreeNode(root);
        createNodes(top);

        //Create a tree that allows one selection at a time.
        tree = new JTree(top);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

    }

    //this method reads the file and prints all the lines to standard output
    //for testing purposes
    public void readFile() {
        try {
            //in = new BufferedReader(new FileReader("Path\\To\\File.txt"));
            in = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(filename), encoding));

            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    //this method reads the first line in the text file and assigns it 
    //to the root variable which will be used for the root node of our JTree
    private void getRootNode() {
        try {
            //in = new BufferedReader(new FileReader("Path\\To\\File.txt"));
            in = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(filename), encoding));
            ln = new LineNumberReader(in);

            if (ln.getLineNumber() == 0) {
                root = ln.readLine();
                //System.out.println(root);
            }

            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int countOccurrences(String haystack, char needle) {
        int count = 0;
        for (int i = 0; i < haystack.length(); i++) {
            if (haystack.charAt(i) == needle) {
                count++;
            }
        }
        return count;
    }

    //create the Nodes
    private void createNodes(DefaultMutableTreeNode top) {
        DefaultMutableTreeNode category = null;     // Level 1 in Hierarchy
        DefaultMutableTreeNode subCategory = null;  // Level 2 in Hierarchy
        DefaultMutableTreeNode leaf = null;         // Level 3 in Hierarchy    

        try {
            //in = new BufferedReader(new FileReader("Path\\To\\File.txt"));
            in = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(filename), encoding));

            while ((line = in.readLine()) != null) {
                if (countOccurrences(line, '\t') == 1) {
                    category = new DefaultMutableTreeNode(line);
                    top.add(category);
                } else if (countOccurrences(line, '\t') == 2) {
                    subCategory = new DefaultMutableTreeNode(line);
                    category.add(subCategory);
                } else if (countOccurrences(line, '\t') == 3) {
                    leaf = new DefaultMutableTreeNode(line);
                    subCategory.add(leaf);
                } //continue the else...if - if you have more levels
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

        public JTree getTree() {
            return tree;
        }

}
