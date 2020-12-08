/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Nutrient;

/**
 * FXML Controller class
 *
 * @author Aung Nay
 */
public class NutrientInfoController implements Initializable {
    
  
    @FXML
    private Button backBtn;

    @FXML
    private Button toEatBtn;

    @FXML
    private Text nurtInfo;

    @FXML
    private ImageView nurtImage;

    @FXML
    private Circle icon2;

    @FXML
    private Circle icon3;

    @FXML
    private Circle icon1;

    @FXML
    private Circle icon4;

    @FXML
    private Button locationHeader3;

    @FXML
    private Button locationHeader2;

    @FXML
    private Button locationHeader1;

    @FXML
    private Button locationHeader4;

    @FXML
    private Label nutrientTitle;

    @FXML
    private Label description;

    
    Scene previousScene;
    Nutrient selectedNutrient;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void setPreviousScene(Scene scene){
        previousScene = scene;
        backBtn.setDisable(false);
    }
    
    public void setHeader(String header){
        locationHeader1.setText(header);
    }
    
    void initData(Nutrient selectedNutrient) {
        this.selectedNutrient = selectedNutrient;
        nutrientTitle.setText(selectedNutrient.getNutrientName());
        description.setText(selectedNutrient.getDescription());
        locationHeader2.setText(selectedNutrient.getNutrientName());
        icon2.setFill(Color.web("#979797"));
        
        try{
            String imgName = "/resource/nutrient/" + selectedNutrient.getNutrientName().toLowerCase() + ".jpg";
            Image nutrImg = new Image(getClass().getResourceAsStream(imgName));
            nurtImage.setImage(nutrImg);
        } catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
    @FXML
    private void goBackScene(ActionEvent event) {
        Stage stage = (Stage)backBtn.getScene().getWindow();
        
        if(previousScene != null){
            stage.setScene(previousScene);
        }
    }

    @FXML
    private void openWhatToEat(ActionEvent event) throws IOException {
        Nutrient nutrientSelected = selectedNutrient;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MealFoodView.fxml"));
        
        try{        
            System.out.println(nutrientSelected.toString());
            Parent detailModelView = loader.load();
            Scene tableViewScene = new Scene(detailModelView);

            MealFoodViewController controller = loader.getController();
            controller.initData(nutrientSelected);
            
            controller.setHeader(locationHeader1.getText(), locationHeader2.getText());

            Scene currentScene = ((Node) event.getSource()).getScene();
            controller.setPreviousScene(currentScene);

            Stage stage = (Stage) currentScene.getWindow();
            stage.setScene(tableViewScene);
            stage.show();
        } catch(NullPointerException  e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error Button Malfunctioning");
            alert.setHeaderText("Nutrient not found in system");
            alert.setContentText("Please check the database");
            alert.showAndWait();
        }
    }
    
}
