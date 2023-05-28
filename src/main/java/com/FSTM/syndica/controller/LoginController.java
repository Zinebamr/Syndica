package com.FSTM.syndica.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.FSTM.syndica.DAL.SyndicDal;
import com.FSTM.syndica.Model.Syndic;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class LoginController implements Initializable {
    private Syndic syndic;
    private SyndicDal syndicDal = new SyndicDal();

    private double x = 0;
    private double y = 0;
    @FXML
    private Button loginBtn;

    @FXML
    private AnchorPane main_form;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    public void loginAdmin(){
        try{
            Alert alert;

            if(username.getText().isEmpty() || password.getText().isEmpty()){
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            }else{
                if(password.getText().equals("admin")  && username.getText().equals("admin")){
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Login");
                    alert.showAndWait();
                    loginBtn.getScene().getWindow().hide();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard-view.fxml"));
                    Parent dashboardRoot = loader.load();
                    Stage stage = new Stage();
                    Scene scene = new Scene(dashboardRoot);

                    dashboardRoot.setOnMousePressed((MouseEvent event) ->{
                        x = event.getSceneX();
                        y = event.getSceneY();
                    });

                    dashboardRoot.setOnMouseDragged((MouseEvent event) ->{
                        stage.setX(event.getScreenX() - x);
                        stage.setY(event.getScreenY() - y);
                    });

                    stage.initStyle(StageStyle.TRANSPARENT);
                    stage.setScene(scene);
                    stage.show();

                }else{
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Wrong Username/Password");
                    alert.showAndWait();
                }
            }

        }catch(Exception e){e.printStackTrace();}

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}