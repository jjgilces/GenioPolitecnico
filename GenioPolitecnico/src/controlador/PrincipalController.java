/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author johan_p9pyxb1
 */
public class PrincipalController implements Initializable {

       @FXML
    private Button btnGo;

    @FXML
    void changeView(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/vista/Pagina.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setTitle("Genio Politecnico");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(PaginaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
