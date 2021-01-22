/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructura;

/**
 *
 * @author CltControl
 */
public class Node {
    private String informacion;
    private Node getLeft;
    private Node right;
    private String type;
    private boolean isLeft;
  
    
    public Node(String informacion,String type){
        this.informacion=informacion;
        this.type = type;
    }

    public Node(String informacion) {
        this.informacion=informacion;
                
    }
    public Node(){
        this.informacion = null;
        this.type = null;
    }
    
    public void readLine(String informacion){
        if (informacion.startsWith("#P")) this.type="pregunta";
        else this.type = "respuesta";
        this.informacion = informacion.substring(3);
    }
    
    

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    

    /**
     * @return the informacion
     */
    public String getInformacion() {
        return informacion;
    }

    /**
     * @param informacion the informacion to set
     */
    public void setInformacion(String informacion) {
        this.informacion = informacion;
    }

    @Override
    public String toString() {
        return "Node{" + "informacion=" + informacion + '}';
    }

    /**
     * @return the getLeft
     */
    public Node getLeft() {
        this.isLeft=true;
        return getLeft;
    }

    /**
     * @param getLeft the getLeft to set
     */
    public void setLeft(Node getLeft) {
        this.getLeft = getLeft;
    }

    public boolean isIsLeft() {
        return isLeft;
    }

    /**
     * @return the right
     */
    public Node getRight() {
        this.isLeft=false;
        return right;
    }

    /**
     * @param right the right to set
     */
    public void setRight(Node right) {
        this.right = right;
    }
    public boolean isHoja(){
        return (getLeft==null && right==null);
    }
}