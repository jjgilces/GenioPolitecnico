/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import javafx.scene.layout.VBox;
import estructura.BinaryTree;
import estructura.Node;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author User
 */
public class PaginaController implements Initializable {

    BinaryTree bt = new BinaryTree();

    public void dataArbol(BinaryTree bt) {
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader("src/data/datos.txt"));
            BinaryTree.cargarArbol(br, bt.getRoot());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PaginaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PaginaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private Node temp;

    @FXML
    private VBox cajaPreguntas;

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
        if (temp.getRight() == null) {
            response.setText("¿Era esta tu respuesta?");
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
        if (temp.getLeft() == null) {
            response.setText("¿Era esta tu respuesta?");
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
        response.setText("");
        btnFinalN.setOpacity(0);
        btnFinalY.setOpacity(0);
        Node padre = bt.searchParent(temp.getInformacion());
        final Node pregunta = new Node("", "pregunta");
        final Node respuesta = new Node("", "respuesta");
        cajaPreguntas.getChildren().clear();
        Label lr = new Label("¿En que animal estabas pensando?");
        TextField tr = new TextField();
        tr.setOpacity(0.5);
        Label lq = new Label("¿Como idenficamos a este animal?");
        TextField tq = new TextField();
        tq.setOpacity(0.5);
        tr.setMaxSize(100, 20);
        tq.setMaxSize(200, 20);
        Button btn = new Button("Submit");
        cajaPreguntas.getChildren().add(lr);
        cajaPreguntas.getChildren().add(tr);
        cajaPreguntas.getChildren().add(lq);
        cajaPreguntas.getChildren().add(tq);
        cajaPreguntas.getChildren().add(btn);
        cajaPreguntas.setAlignment(Pos.CENTER);
        System.out.println(tq.getWidth());
        cajaPreguntas.setPadding(new Insets(15, 15, 15, 15));
        btn.setOnAction((ActionEvent e) -> {
            TextField t = (TextField) cajaPreguntas.getChildren().get(3);
            TextField t1 = (TextField) cajaPreguntas.getChildren().get(1);
            try {

                if (textFieldNull(t) || textFieldNull(t1)) {
                    throw new IOException();
                }

                pregunta.setInformacion(t.getText());
                respuesta.setInformacion(t1.getText());
                bt.remove(temp.getInformacion());
                bt.add(pregunta.getInformacion(), padre.getInformacion());
                bt.add(respuesta.getInformacion(), pregunta.getInformacion());
                bt.add(temp.getInformacion(), pregunta.getInformacion());
                bt.preOrdenData();
            } catch (IOException ex) {
                Alert a = new Alert(AlertType.ERROR);
                a.setContentText("Porfavor ingrese datos que nos ayude a identificar el animal.");
                a.show();

            }
        });

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
        dataArbol(bt);
        temp = bt.getRoot();
        question.setText(temp.getInformacion());
    }

    public static boolean textFieldNull(TextField text) {
        return text.getText().isEmpty() && !text.getText().matches("[a-zA-Z]+");
    }

}
