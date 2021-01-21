/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import estructura.BinaryTree;
import estructura.Node;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author User
 */
public class PaginaController implements Initializable {
    private BinaryTree bt = BinaryTree.loadData();
    private Node temp;
    
    @FXML
    private ImageView image;
    
    @FXML
    private Button btnyes;

    @FXML
    private Button btnno;    

    @FXML
    private Label question;

    
    @FXML
    private Label response;
    
    @FXML
    private Button btnFinalY;

    @FXML
    private Button btnFinalN;
    
    
    @FXML
    void nextOptionNo(ActionEvent event) {
        temp = temp.getRight();
        question.setText(temp.getInformacion()); 
        if (temp.getRight()==null) {
            response.setText("Era esta tu respuesta??");
            btnyes.setDisable(true);
            btnno.setDisable(true);
            btnFinalY.setOpacity(1);
            btnFinalN.setOpacity(1);
            btnFinalY.setText("Yes");
            btnFinalN.setText("No");
            
            
            
        }
    }

    @FXML
    void nextOptionYes(ActionEvent event) {
        temp = temp.getLeft();
        question.setText(temp.getInformacion());
        if (temp.getLeft()==null) {
            response.setText("Era esta tu respuesta??");
            btnyes.setDisable(true);
            btnno.setDisable(true);
            btnFinalY.setOpacity(1);
            btnFinalY.setText("Yes");
            btnFinalN.setOpacity(1);
            btnFinalN.setText("No");
        }
    }
    
     @FXML
    void responseNo(ActionEvent event) {
        
    }

    @FXML
    void responseYes(ActionEvent event) {
        try {
            response.setText("HE ADIVINADO");  
            Image ima = new Image(new FileInputStream("src/images/genio2.jpg"));
            image.setImage(ima);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PaginaController.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        temp =bt.getRoot();
        question.setText(temp.getInformacion());
    }    
    
}
