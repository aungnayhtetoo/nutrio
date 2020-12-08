package controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.Food;
import model.Meal;
import model.Nutrient;

/**
 * FXML Controller class
 *
 * @author Aung Nay
 */
public class MealFoodViewController implements Initializable {

    @FXML
    private Button backBtn;
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
    private Label descriptionMenu;
    @FXML
    private Label descriptionFood;
    @FXML
    private Button mealBtn1;
    @FXML
    private Button mealBtn2;
    @FXML
    private Button mealBtn3;
    @FXML
    private Button mealBtn4;
    @FXML
    private ImageView mealPic1;
    @FXML
    private ImageView mealPic2;
    @FXML
    private ImageView mealPic3;
    @FXML
    private ImageView mealPic4;
    @FXML
    private Button foodBtn1;
    @FXML
    private Button foodBtn2;
    @FXML
    private Button foodBtn3;
    @FXML
    private Button foodBtn4;
    @FXML
    private ImageView foodPic1;
    @FXML
    private ImageView foodPic2;
    @FXML
    private ImageView foodPic3;
    @FXML
    private ImageView foodPic4;
    
    @FXML    
    ArrayList<ButtonPicture> foodList = new ArrayList<>();
    @FXML    
    ArrayList<ButtonPicture> mealList = new ArrayList<>();
    
    Scene previousScene;
    Nutrient selectedNutrient;
    private EntityManager manager;

    /**
     * Initializes the controller class.
     */
    
    // making a hashmap containing the image and the button for food and meal
    
    class ButtonPicture{
        Button btn;
        ImageView pic;
        
        ButtonPicture(Button btn, ImageView pic){
            this.btn = btn;
            this.pic = pic;
        }
        
        public void setBtn(Button btn) {
            this.btn = btn;
        }
        
        public Button getBtn(){
            return btn;
        }

        public ImageView getPic() {
            return pic;
        }

        public void setPic(ImageView pic) {
            this.pic = pic;
        }
    }
    public void setBtnList(){
        foodList.add(new ButtonPicture(foodBtn1, foodPic1));
        foodList.add(new ButtonPicture(foodBtn2, foodPic2));
        foodList.add(new ButtonPicture(foodBtn3, foodPic3));
        foodList.add(new ButtonPicture(foodBtn4, foodPic4));
        
        mealList.add(new ButtonPicture(mealBtn1, mealPic1));
        mealList.add(new ButtonPicture(mealBtn2, mealPic2));
        mealList.add(new ButtonPicture(mealBtn3, mealPic3));
        mealList.add(new ButtonPicture(mealBtn4, mealPic4));
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String database = "AungNayHtetOoFXMLPU";
        manager = (EntityManager)Persistence.createEntityManagerFactory(database).createEntityManager();
        setBtnList();
    }
    
    public void setBtn(){
        int sizeM = readMealList().size();
        int sizeF = readFoodList().size();
       
        while(sizeM-1 >= 0){
            mealList.get(sizeM-1).btn.setText(readMealList().get(sizeM-1).getMealName());
            sizeM -= 1;
        }
        
        while(sizeF-1 >= 0){
            foodList.get(sizeF-1).btn.setText(readFoodList().get(sizeF-1).getFoodName());
            sizeF -= 1;
        }
    }
    
    public void setPic(){
        int sizeM = readMealList().size();
        int sizeF = readFoodList().size();
        
        while(sizeM-1 >= 0){
            try{
                String imgName = "/resource/meal/" + readMealList().get(sizeM-1).getMealName().toLowerCase() + ".jpg";
                //System.out.println(imgName);
                Image nutrImg = new Image(getClass().getResourceAsStream(imgName));
                mealList.get(sizeM-1).pic.setImage(nutrImg);
            } catch(Exception ex){
                System.out.println(ex.getMessage());
            }
            sizeM -= 1;
        }
        
        while(sizeF-1 >= 0){
            try{
                String imgName = "/resource/food/" + readFoodList().get(sizeF-1).getFoodName().toLowerCase() + ".jpg";
                //System.out.println(imgName);
                Image nutrImg = new Image(getClass().getResourceAsStream(imgName));
                foodList.get(sizeF-1).pic.setImage(nutrImg);
            } catch(Exception ex){
                System.out.println(ex.getMessage());
            }
            sizeF -= 1;
        }
        
        
    }

    public void initData(Nutrient nutrient){
        this.selectedNutrient = nutrient;
        setBtn();
        setPic();
    }
    
    @FXML
    private void goBackScene(ActionEvent event) {
        Stage stage = (Stage)backBtn.getScene().getWindow();
        
        if(previousScene != null){
            stage.setScene(previousScene);
        }
    }
    
    public List<Meal> readMealList(){
        Query query = manager.createNamedQuery("Meal.findByNutrientId");
        
        query.setParameter("nutrientId", selectedNutrient.getNutrientId());
        
        List<Meal> list = query.getResultList();
        //System.out.println(list.toString());
        return list;
    }
    
    public List<Food> readFoodList(){
        Query query = manager.createNamedQuery("Food.findByNutrientId");
        
        query.setParameter("nutrientId", selectedNutrient.getNutrientId());
        
        List<Food> list = query.getResultList();
        //System.out.println(list.toString());
        return list;
    }

    @FXML
    private void getMenu(ActionEvent event) {
        System.out.println(event.getSource().toString());
    }

    @FXML
    private void getFood(ActionEvent event) {
    }
    
    public void setPreviousScene(Scene scene){
        previousScene = scene;
        backBtn.setDisable(false);
    }
    
    public void setHeader(String header, String header2){
        locationHeader1.setText(header);
        locationHeader2.setText(header2);
    }
}
