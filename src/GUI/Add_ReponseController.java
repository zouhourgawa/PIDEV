/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import API.sendSMS;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.RotateTransition;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import Model.Reclamation;
import Model.Reponse;

import org.controlsfx.control.Notifications;
import Services.ServiceReclam;
import Services.ServiceRep;

/**
 * FXML Controller class
 *
 * @author HP
 */

public class Add_ReponseController implements Initializable {
private TextField labelTF;
    private TextField contenuTF;
    private TextField respTF;
 

    /**
     * Initializes the controller class.
     */
    ServiceRep srep = new ServiceRep();
    
  
    
    private AnchorPane paneMain;
    private javafx.stage.Stage stage;
    private Scene scene;
    private Parent root;
    private Reponse rep;
    @FXML
    private Circle C1;
    @FXML
    private TextField reponse;
    @FXML
    private TextField date;
    @FXML
    private ComboBox<Reclamation> claim;

    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ObservableList<Reclamation> postslist = srep.getpostsList();
            try {

            } catch (Exception ex) {
            }
            claim.setItems(postslist);

            System.out.println(postslist);
            // TODO

        } catch (SQLException ex) {
        }
//        ToggleSwitch button =new ToggleSwitch();
//    
//        SimpleBooleanProperty isON=button.switchOnProperty();
//        isON.addListener((observable,oldValue,newValue) ->{
//        
//        
//        
//        
//        if(newValue){
//        
//        button.getScene().getRoot().getStylesheets().add(getClass().getResource("styles.css").toString());
//            System.out.println("adding css dark");
// }else {        button.getScene().getRoot().getStylesheets().remove(getClass().getResource("styles.css").toString());
//            System.out.println("removing css dark");
//
//        }
//        });
//        
////        paneMain.getChildren().add(button); 
//    }    

    }

    @FXML
    private void addcmnt(ActionEvent event) {
        ServiceRep spost = new ServiceRep();
        System.out.println(reponse.getText().length());
        Rotate(C1);
        if (reponse.getText().length() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur de saisie !");
            alert.setContentText("Vous navez pas saisie le nom");
            alert.show();

        } else {
           
           // System.out.println(p.toString());
         //   if (ValidateFields()) {
       // if (ValidateFields2()) {
                // if (ValidateFields3(reponse.getText())) {
                     
                         int rec   =  claim.getSelectionModel().getSelectedItem().getId();
                 
                        spost.ajouterReponse(new Reponse( reponse.getText(), date.getText(),rec));
                         Reponse  p= new Reponse( "momo", "2020-02-07",55);

                         System.out.println(p);
                       
                        sendSMS sm= new sendSMS();
                        sm.sendSMS();
                        
                        
                        
                        
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);//
                        alert.setTitle("AJOUTER PUBLICATION!");
                        alert.setHeaderText("information!");
                        alert.setContentText("PUBLICATION A ETE AJOUTEE AVEC SUCCES!");
                        alert.showAndWait();
                        

                  // }
           //   }
           // }
        }
    }

    public void GettextVal(String txtval) {
        System.out.println("text value from one controller - " + txtval);
    }

//    @FXML
//    private void returcmnt(ActionEvent event) {
//        try {
//            root = FXMLLoader.load(getClass().getResource("commentgrid.fxml"));
//            stage = (javafx.stage.Stage) ((Node) event.getSource()).getScene().getWindow();
//            scene = new Scene(root);
//            stage.setScene(scene);
//            stage.show();
//        } catch (IOException ex) {
//        }
//        Notifications notificationBuilder = Notifications.create()
//                .title("retour vers commentaires").text("retour envoyé avec succès").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
//                .position(Pos.CENTER_LEFT)
//                .onAction(new EventHandler<ActionEvent>() {
//                    public void handle(ActionEvent event) {
//                        System.out.println("clicked ON ");
//                    }
//                });
//        notificationBuilder.darkStyle();
//        notificationBuilder.show();
//
//    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

//   void setData(Comment comment) {
//        this.comment = comment;
//        cntTF.setText(comment.getContenu());
//        repTF.setText(String.valueOf(comment.getResp()));
//        labTF.setText(comment.getLabel());
//    
//  
//        
//}
    private boolean ValidateFields() {
        //Date myDate = Date.valueOf(datePK.getValue().toString());
        if (reponse.getText().isEmpty() | date.getText().isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate Fields");
            alert.setHeaderText(null);
            alert.setContentText("Please enter into the fields");
            alert.showAndWait();
            return false;
        }
        return true;
    }

//    private boolean ValidateFields2() {
//        ServiceReclam bs = new ServiceReclam();
//        if (Integer.parseInt(bs.calculer_nbp(cntTF.getText())) != 0) {
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.setTitle("Validate Fields");
//            alert.setHeaderText(null);
//            alert.setContentText("Please enter a non existant course name");
//            // bs.calculer_nbseance(Add_Nom_Co.getText())
//            //alert.setContentText(bs.calculer_nbseance(Add_Nom_Co.getText()));
//            alert.showAndWait();
//            return false;
//        }
//        return true;
//    }

    public void Rotate(Circle c) {
        RotateTransition rt = new RotateTransition(Duration.millis(3000), c);
        rt.setByAngle(360);
        rt.setCycleCount(5);
        rt.setAutoReverse(true);

        rt.play();

    }

    @FXML
    private void g(KeyEvent event) {

    }

    private boolean ValidateFields3(String S) {
        ServiceRep sc = new Services.ServiceRep();

        String word = "shit";
        
        String result = "";
        // Creating the censor which is an asterisks
        // "*" text of the length of censor word
        String stars = "";
        for (int i = 0; i < word.length(); i++) {
            stars += '*';
        }

        // Iterating through our list
        // of extracted words
        int index = 0;
        String[] word_list = S.split("\\s+");
        for (String i : word_list) {
            if (i.compareTo(word) == 0) // changing the censored word to
            // created asterisks censor
            {
                word_list[index] = stars;
            }
            index++;
        }

        // join the words
        for (String i : word_list) {
            result += i + ' ';
        }

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("bad word");
        alert.setHeaderText(null);
        alert.setContentText(""+result);
        alert.showAndWait();
        return false;
    }
    }


