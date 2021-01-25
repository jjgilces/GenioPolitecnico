package estructura;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Johan
 */
public class BinaryTree {

    Node root;
    public BinaryTree() {
        root = new Node();
        root.setRight(null);
        root.setLeft(null);
    }

    public boolean isEmpty() {
        return root == null;
    }

    private Node searchNode(String data) {
        return searchNode(data, root);
    }

    private Node searchNode(String data, Node n) {
        if (n == null) {
            return n; //No preguntar con isEmpty 
        } else if (n.getInformacion().equals(data)) {
            return n;
        } else {
            Node nl = searchNode(data, n.getLeft());
            if (nl != null) {
                return nl;
            }
            return searchNode(data, n.getRight());
        }
    }

    public Node searchParent(String child) {
        return searchParent(child, root);
    }

    private Node searchParent(String child, Node n) {
        if (n == null) {
            return n;
        } else if ((n.getLeft() != null && n.getLeft().getInformacion().equals(child))
                || (n.getRight() != null && n.getRight().getInformacion().equals(child))) {
            return n;
        } else {
            Node nl = searchParent(child, n.getLeft());
            if (nl != null) {
                return nl;
            }
            return searchParent(child, n.getRight());
        }
    }
//para elementos repetidos


    public boolean insert(String child, String parent) {

        Node nchild = new Node(child);
        if (isEmpty() && parent == null) {
            root = nchild;
            return true;
        }
        Node np = searchNode(parent);
        if (np != null) {
            if (np.getLeft() == null) {
                np.setLeft(nchild);
                return true;
            } else if (np.getRight() == null) {
                np.setRight(nchild);
                return true;
            }
        }
        return false;
    }

    public boolean add(String child, String parent) {
        Node nchild = new Node(child);
        if (isEmpty() && parent == null) {
            root = nchild;
            return true;
        }
        Node np = searchNode(parent);
        Node nce = searchNode(child);
        if (nce == null && np != null) {
            if (np.getLeft() == null) {
                np.setLeft(nchild);
                return true;
            } else if (np.getRight() == null) {
                np.setRight(nchild);
                return true;
            }
        }
        return false;
    }

    public boolean remove(String child) {
        if (child == null || isEmpty()) {
            return false;
        }
        if (root.getInformacion().equals(child)) {
            root = null;
            return true;
        }
        Node np = searchParent(child);
        if (np != null) {
            if (np.getLeft() != null && np.getLeft().getInformacion().equals(child)) {
                np.setLeft(null);
            } else {
                np.setRight(null);
            }
            return true;
        }
        return false;
    }

    public int size() {
        return size(root);
    }

    public int size(Node n) {
        if (n == null) {
            return 0;
        }
        return 1 + size(n.getLeft()) + size(n.getRight());
    }

    public int height() {
        return height(root);
    }

    private int height(Node n) {
        if (n == null) {
            return 0;
        }
        return 1 + Math.max(height(n.getLeft()), height(n.getRight()));
    }

   
    
    public static void cargarArbol(BufferedReader b, Node nodo) throws IOException{
        String line=b.readLine();
        if(line!=null){
            nodo.setInformacion(line.substring(3));
            if(line.substring(1, 2).equals("P")){
                nodo.setLeft(new Node());
                nodo.setRight(new Node());
                cargarArbol(b,nodo.getLeft());
                cargarArbol(b,nodo.getRight());
            }
        }
        
    }

    public void preOrden() {
        preOrdenR(root);
    }

    private void preOrdenR(Node n) {
        if (n != null) {
            System.out.println(n);
            preOrden(n.getLeft());
            preOrden(n.getRight());
        }
    }
    
    public String preOrden(Node n) {
        if (n != null) {
            if (n.isHoja()) {
                return "#R " + n.getInformacion();
            }
            return "#P " + n.getInformacion()+ "\n"+ preOrden(n.getLeft()) + "\n" + preOrden(n.getRight()) ;
        }
        return " ";
    }
    
    public void preOrdenData() {
        String tree = preOrden(root);
        try ( BufferedWriter bw = new BufferedWriter(new FileWriter("src/data/datos.txt")) ) {
            bw.write(tree);
        } catch (IOException ex) {
            Logger.getLogger(BinaryTree.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void postOrden() {
        postOrden(root);
    }

    private void postOrden(Node n) {
        if (n != null) {
            postOrden(n.getLeft());
            postOrden(n.getRight());
            System.out.println(n);
        }
    }

    public void inOrden() {
        inOrden(root);
        System.out.println("");
    }

    private void inOrden(Node n) {
        if (n != null) {
            inOrden(n.getLeft());
            System.out.print(n.getInformacion());
            inOrden(n.getRight());
        }
    }

    public Node getRoot() {
        return root;
    }
    
    public void anchura() {
        if (!isEmpty()) {
            Queue<Node> cola = new LinkedList<>();
            cola.offer(root);
            while (!cola.isEmpty()) {
                Node n = cola.poll();
                System.out.print(n.getInformacion() + " ");
                if (n.getLeft() != null) {
                    cola.offer(n.getLeft());
                }
                if (n.getRight() != null) {
                    cola.offer(n.getRight());
                }
            }
        }
        System.out.println("");
    }


    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (o.getClass() != this.getClass()) {
            return false;
        }
        BinaryTree otro = (BinaryTree) o;
        return equals(root, otro.root);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + Objects.hashCode(this.root);
        return hash;
    }

    private boolean equals(Node parent1, Node parent2) {
        if (parent1 == null && parent2 == null) {
            return true;
        }
        if (parent1 == null || parent2 == null || !parent1.getInformacion().equals(parent2.getInformacion())) {
            return false;
        }
        return equals(parent1.getLeft(), parent2.getLeft()) && equals(parent1.getRight(), parent2.getRight());
    }
}

