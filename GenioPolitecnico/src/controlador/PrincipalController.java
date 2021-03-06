/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import static geniopolitecnico.GenioPolitecnico.confirmation;
import static geniopolitecnico.GenioPolitecnico.principal;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author johan_p9pyxb1
 */
public class PrincipalController implements Initializable {
    public static Stage juego;
    @FXML
    void changeView(ActionEvent event) {
        principal.close();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/vista/Pagina.fxml"));
            ventanaJuego(root);
        } catch (IOException ex) {
            Logger.getLogger(PaginaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }

    
    public static void ventanaJuego(Parent root){        
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setTitle("Genio Politecnico");
            stage.setScene(scene);
            stage.show();
            juego=stage;
            stage.setOnCloseRequest(e->{
            Optional<ButtonType> result = confirmation();
            if(result.get()==ButtonType.OK){
                Platform.exit();
            }else{
                e.consume();
        }});
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
