/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    private void openWhatToEat(ActionEvent event) {
    }
    
}
