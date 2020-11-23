/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.Nutrient;
import model.Recipe;

/**
 * FXML Controller class
 *
 * @author Aung Nay
 */
public class NutrientViewController implements Initializable {

    private EntityManager manager;
    
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button nutrBtn1;

    @FXML
    private Button nutrBtn2;

    @FXML
    private Button nutrBtn3;

    @FXML
    private Button nutrBtn6;

    @FXML
    private Button nutrBtn5;

    @FXML
    private Button nutrBtn4;
    
    ArrayList<Button> btnList = new ArrayList<>();

    @FXML
    void getNutr(ActionEvent event) {
        Button btn = (Button)event.getSource();
        System.out.println(btn.getText());
    }
    
    public void setNutrientLabel(){
        btnList.add(nutrBtn1);
        btnList.add(nutrBtn2);
        btnList.add(nutrBtn3);
        btnList.add(nutrBtn4);
        btnList.add(nutrBtn5);
        btnList.add(nutrBtn6);
        
        for(int i = 0; i < btnList.size(); i++){
            btnList.get(i).setText(readById(i+1).getNutrientName());
        }
    }
    
    public List<Nutrient> readNutrient(){
        Query query = manager.createNamedQuery("Nutrient.findAll");
        
        //query.setParameter("nutrientId", repId);
        
        List<Nutrient> nutList = query.getResultList();
        //System.out.println(recipes.size());
        return nutList;
    }
    
    public Nutrient readById(int id){
        Query query = manager.createNamedQuery("Nutrient.findByNutrientId");
        
        query.setParameter("nutrientId", id);
        Nutrient nut = (Nutrient)query.getSingleResult();
        if(nut != null){
            System.out.println(nut.toString());
        }
        return nut;
    }

    

    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        String database = "AungNayHtetOoFXMLPU";
        manager = (EntityManager)Persistence.createEntityManagerFactory(database).createEntityManager();
        setNutrientLabel();
    }
}
