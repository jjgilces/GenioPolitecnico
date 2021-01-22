package estructura;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Johan
 */
public class BinaryTree {
//    public static Node<E> prevNode = null;

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

    private Node searchParent(String child) {
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

    /*public E max(){
        return max(root);
    }
    
    private E max(Node<E> n){
        if(n==null)return null;
        else if(n.right==null)return n.data;
        else return max(n.right); 
    }*/
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

    /*public E min(){
        return min(root);
    }
    
    private E min(Node<E> n){
        if(n==null)return null;
        else if(n.left==null)return n.data;
        else return min(n.left); 
    }*/

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
        preOrden(root);
    }

    private void preOrden(Node n) {
        if (n != null) {
            System.out.println(n);
            preOrden(n.getLeft());
            preOrden(n.getRight());
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
    
    public static BinaryTree loadData() {
        BinaryTree bt = new BinaryTree();
        bt.add("Es mamifero?",null );
        bt.add("Es animal domestico?", "Es mamifero?");
        bt.add("Perro","Es animal domestico?");
        bt.add("Es de granja?","Es animal domestico?");
        bt.add("Oveja","Es de granja?");
        bt.add("Elefante","Es de granja?");
        bt.add("Es un ave?", "Es mamifero?");
        bt.add("Un pinguino","Es un ave?");
        bt.add("Vive en el oceano?","Es un ave?");
        bt.add("Un calamar","Vive en el oceano?");
        bt.add("Una araña","Vive en el oceano?");
        return  bt;
    }
   
    /*public boolean isMirror(BinaryTree<E> otherTree) {
        if (otherTree == null) {
            return false;
        }
        return isMirror(root, otherTree.root);
    }

    private boolean isMirror(Node<E> n, Node<E> Other) {
        if (n == null && Other == null) {
            return true;
        }
        if ((n == null && Other != null) || (n != null && Other == null) || !n.data.equals(Other.data)) {
            return false;
        }
        return isMirror(n.left, Other.right) && isMirror(n.right, Other.left);
    }*/
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

    /*public void setLeft(BinaryTree<E> tree) {
        this.root.left=tree.root;
    }
    public void setRight(BinaryTree<E> tree) {
        this.root.right=tree.root;
    }
    public boolean esHoja() {
        return this.root.left == null && this.root.right == null && !this.isEmpty();
    }*/
 /*public String decision(List<String> lado) {
        Node<String> resultado = decision(new LinkedList<>(lado), root);
        return resultado != null ? resultado.data : "Incertidumbre";
    }
    
    private Node<String> decision(Queue<String> lado, Node<E> n) {
        String l = "";
        if (!lado.isEmpty()) {
            l = lado.poll();
        }
        if (n == null) {
            return null;
        } else if (l.equals("YES")) {
            return decision(lado, n.right);
        } else if (l.equals("NO")) {
            return decision(lado, n.left);
        } else if (n.left == null && n.right == null) {
            return (Node<String>) n;
        }
        return null;
    }
    
    
    public static String codificarMorse(List<String> codigos, BinaryTree<String> arbol){
        return arbol.codificarMorse(codigos);
    }
    private String codificarMorse(List<String> codigos){
         Node<String> q=(Node<String>) this.root;
         String cadena="";
        for(String c: codigos){  
            if(c.equals(".")) q = q.right;
            else   q = q.left; 
            if(q==null) return cadena;
            if(!q.data.equals("") && q!=root){
                cadena += q.data;
                q = (Node<String>) this.root;
            }  
        }
        return cadena;
    }
    
    //Ejercicio 3
    public static BinaryTree<String> crearArbolMorse(HashMap<String, List<String>> mapa){
        BinaryTree<String> morse = new BinaryTree<>();
        morse.add("INCIO");     
        mapa.entrySet().forEach((e) -> {
            System.out.println(e);
            morse.addMorse(e.getKey(), e.getValue());
        });
           
        return morse;
    }
      public boolean add(E child) {
        Node<E> nc = new Node<>(child);
        if (!isEmpty())   return false;   
        root = nc;
        return true; 
      }
      public boolean addMorse(String letra, List<String> camino){
           Node nd = root;
           if(letra==null) return false;
            for (int i=0; i<camino.size();i++) {
                String signo= camino.get(i);
                if (".".equals(signo)) {
                    if (nd.right == null) nd.right = new Node<>("");
                    if (i == camino.size()-1)  { nd.right.data = letra ;return true; }
                    nd = nd.right;
                }
                else if (signo.equals("-")) {
                    if (nd.left == null)nd.left = new Node<>("");
                    if (i == camino.size()-1) {nd.left.data = letra; return true;}
                    nd = nd.left;
                }            
            }
            return false;
      }

    public List<E> equiposDerrotadosFase(String fase, HashMap<String,Integer> fases){
        if(fase == null)return null;
        int numfase = fases.get(fase);
        return equiposDerrotadosFase(numfase,root,0);
    }
    
    private List<E> equiposDerrotadosFase(int numFase,Node<E> n , int actLevel){
        List<E> lista = new LinkedList<>();
        if(n.right==null)return lista;
        if(actLevel>=numFase)lista.add(n.right.data);
        lista.addAll(equiposDerrotadosFase(numFase,n.left,actLevel+1));
        lista.addAll(equiposDerrotadosFase(numFase,n.right,actLevel+1));
        return lista;
    }

    public static BinaryTree<Integer> createHeapTree(int inicio, int level){
        if(inicio < 0 || level < 1)          return new BinaryTree<>();
        return createHeapTree(1, level, inicio);
    }
    
    private static BinaryTree<Integer> createHeapTree(int current, int level, int i){
        BinaryTree<Integer> tree = new BinaryTree<>();
        tree.add(i);
        if(current < level){
            tree.setLeft(createHeapTree(current +1, level, i*2+1));
            tree.setRight(createHeapTree(current +1, level, i*2 +2));
        }
        return tree;
    }


     public List<E> equiposEliminados(E seleccion) {
       if(seleccion==null)return null;
       Node<E> n = searchNode(seleccion);
       List<E> lista = new LinkedList<>() ;
        return elementosDerecha(n, lista);
    }

    private List<E> elementosDerecha(Node<E> n,List<E> lista) {
        if(n==null) return lista;
        if (n.right != null) {   
            lista.add(n.right.data);
            elementosDerecha(n.left,lista);
            return lista;
        }     
        return lista;
    }
    
    public boolean isBST(Comparator<E> f)  { 
        return isBST(root,min(root),max(root),f); 
    } 
    
    boolean isBST(Node<E> node, E min, E max,Comparator<E> f )   { 
        if (node == null) 
            return true; 
        if (f.compare(min,node.data)>0 || f.compare(max,node.data)<0) 
            return false; 
        return (isBST(node.left, min, node.data,f) && 
                isBST(node.right, node.data, max,f)); 
    }

    public void CrearMundial(){
        root= new Node("FRA");
        root.left= new Node("FRA");
        root.right= new Node("CRO");
        root.left.left=new Node("FRA");
        root.left.right= new Node("BEL");
        root.right.left= new Node("CRO");
        root.right.right= new Node("ING");
        root.left.left.left=new Node("FRA");
        root.left.left.right=new Node("URU");
        root.left.right.left= new Node("BEL");
        root.left.right.right= new Node("BRA");
        root.right.left.left= new Node("CRO");
        root.right.left.right= new Node("RUS");
        root.right.right.left= new Node("ING");
        root.right.right.right= new Node("SUE");
        root.left.left.left.left=new Node("FRA");
        root.left.left.left.right=new Node("ARG");
        root.left.left.right.left=new Node("URU");
        root.left.left.right.right=new Node("POR");
        root.left.right.left.left= new Node("BEL");
        root.left.right.left.right= new Node("JAP");
        root.left.right.right.left= new Node("BRA");
        root.left.right.right.right= new Node("MEX");
        root.right.left.left.left= new Node("CRO");
        root.right.left.left.right= new Node("DIN");
        root.right.left.right.left= new Node("RUS");
        root.right.left.right.right= new Node("ESP");
        root.right.right.left.left= new Node("ING");
        root.right.right.right.left= new Node("SUE");
        root.right.right.left.right= new Node("COL");
        root.right.right.right.right= new Node("SUI");
    }
    public List<E> getValuesFromLevels(int lvl1, int lvl2) {
        List<E> lista = new LinkedList<>();
        if (lvl1 < 1 || lvl1 > lvl2)        return lista;
        if(lvl2<lvl1) return lista;
        return getValuesFromLevels(lvl1, lvl2+1, root, 1);
    }

    private List<E> getValuesFromLevels(int lvl1, int lvl2, Node<E> n, int lvlAct) {
        List<E> lista = new LinkedList<>();
        if (n == null) {
            return lista;
        }
        if (lvlAct >= lvl1 & lvlAct < lvl2) {
            lista.add(n.data);
        }
        if (lvlAct <= lvl2) {
            lista.addAll(getValuesFromLevels(lvl1, lvl2, n.left, lvlAct + 1));
            lista.addAll(getValuesFromLevels(lvl1, lvl2, n.right, lvlAct + 1));
        }
        return lista;
    }*/
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

/*
   public boolean add(E child, E parent, String prueba) {
        Node<E> nc = searchNode(child);
        if (nc != null) {
            return false;
        }
        nc = new Node<>(child);
        if (parent == null && root == null) {
            root = nc;
            return true;
        }
        Node<E> np = searchNode(parent);
        if (np != null) {
            if (prueba == "d" && np.left == null) {
                np.left = nc;
                return true;
            } else if (prueba == "i" && np.right == null) {
                np.right = nc;
                return true;
            }
        }
        return false;
    }

/* public int countLeavesIterative() {
        Stack<BinaryTree<E>> stack = new Stack();
        int count = 0;
        if (this.isEmpty()) {
            return count;
        } else {
            stack.push(this);
            while (!stack.empty()) {
                BinaryTree<E> subtree = stack.pop();
                if (subtree.isLeaf()) {
                    count++;
                } else {
                    if (subtree.root.left != null) {
                        stack.push(subtree.root.left);
                    }
                    if (subtree.root.right != null) {
                        stack.push(subtree.root.right);
                    }
                }  
            }
        }
        return count;
    }
         public int countLeavesRecursive() {
        if (this.isEmpty()) {
            return 0;
        } else if (this.isLeaf()) {
            return 1;
        } else {
            int totalLeaves = 0;            
            if (this.root.left != null) {
                totalLeaves += this.root.left.countLeavesRecursive();
            }
            if (this.root.right != null) {
                totalLeaves += this.root.right.countLeavesRecursive();
            }
            return totalLeaves;
        }
    }
//    public Map<E,Integer> getLevels(List<E> claves){
//        Map<E, Integer> mapa = new HashMap<>();
//        getLevels(mapa, root, 1);
//        Map<E, Integer> mapaReturn = new HashMap<>();
//        for(E e: claves){
//            mapaReturn.put(e, mapa.get(e));
//        }
//        return mapaReturn;
//    }
    
//    private void getLevels(Map<E,Integer> mapa, Node<E> q, int lvlActual){
//        if(q != null){
//            mapa.put(q.data, lvlActual);
//            getLevels(mapa, q.left, lvlActual+1);
//            getLevels(mapa, q.right, lvlActual+1);
//        }
//    }
//    private boolean isBST(Node<E> n,Comparator<E> f){
//        if (n != null) {
//                inOrden(n.left);
//                return isBST(root,f);
//                inOrden(n.right);
//         }
//        
//    }
// 
//    private boolean isBST(Node<E> n,Node<E> l, Node<E> r,Comparator<E> f)   { 
//        if(n==null ) return true;
//        if (l!=null && f.compare(n.data, l.data)<0)  return false;  
//        if (r != null && f.compare(n.data, r.data)>0 )     return false; 
//        return isBST(root.left,l,n,f) &&    isBST(root.right,n,r,f);  
//    } 
 */