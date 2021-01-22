/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geniopolitecnico;

import estructura.BinaryTree;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Optional;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

/**
 *
 * @author johan_p9pyxb1
 */
public class GenioPolitecnico extends Application{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args); 
    }

    @Override
    public void start(Stage stage) throws Exception {
     Parent root = FXMLLoader.load(getClass().getResource("/vista/Principalview.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Genio Politecnico");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(e->{
            Optional<ButtonType> result = confirmation();
            if(result.get()==ButtonType.OK){
                Platform.exit();
            }else{
                e.consume();
        }});
    }
    
    
    public static Optional<ButtonType> confirmation(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("CONFIRMATION ALERT");
        alert.setHeaderText("CERRAR EL PROGRAMA");
        alert.setContentText("SI DA CLIC EN ACEPTAR, TERMINARÁ EL PROGRAMA \n ¿ESTÁ SEGURO QUE DESEA CONTINUAR?");
        return alert.showAndWait();    
    }
    
    
}
