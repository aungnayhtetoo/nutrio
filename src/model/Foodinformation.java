/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Aung Nay
 */
@Entity
@Table(name = "FOODINFORMATION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Foodinformation.findAll", query = "SELECT f FROM Foodinformation f")
    , @NamedQuery(name = "Foodinformation.findByFoodInfoId", query = "SELECT f FROM Foodinformation f WHERE f.foodInfoId = :foodInfoId")
    , @NamedQuery(name = "Foodinformation.findByFoodId", query = "SELECT f FROM Foodinformation f WHERE f.foodId = :foodId")})
public class Foodinformation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "FOOD_INFO_ID")
    private Integer foodInfoId;
    @Basic(optional = false)
    @Column(name = "FOOD_ID")
    private int foodId;
    @Lob
    @Column(name = "FOOD_INFORMATION")
    private String foodInformation;

    public Foodinformation() {
    }

    public Foodinformation(Integer foodInfoId) {
        this.foodInfoId = foodInfoId;
    }

    public Foodinformation(Integer foodInfoId, int foodId) {
        this.foodInfoId = foodInfoId;
        this.foodId = foodId;
    }

    public Integer getFoodInfoId() {
        return foodInfoId;
    }

    public void setFoodInfoId(Integer foodInfoId) {
        this.foodInfoId = foodInfoId;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public String getFoodInformation() {
        return foodInformation;
    }

    public void setFoodInformation(String foodInformation) {
        this.foodInformation = foodInformation;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (foodInfoId != null ? foodInfoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Foodinformation)) {
            return false;
        }
        Foodinformation other = (Foodinformation) object;
        if ((this.foodInfoId == null && other.foodInfoId != null) || (this.foodInfoId != null && !this.foodInfoId.equals(other.foodInfoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Foodinformation[ foodInfoId=" + foodInfoId + " ]";
    }
    
}
