/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author mis05
 */
@ManagedBean
@RequestScoped
public class CustomDate implements Serializable {

    /**
     * Creates a new instance of CustomDate
     */
    public CustomDate() {
    }

    @ManagedProperty(value = "#{currentDate}")
    private Date currentDate;

    /*
     * getter setter
     */
    public Date getCurrentDate() {
        if (currentDate == null) {
            currentDate = new Date();
        }
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    /*
     * methods
     */
    public String start() {
        return "1900";
    }

    public String end() {
        return (getCurrentDate().getYear() + 1900) + "";
    }

    public String endBirthdate() {
        return ((getCurrentDate().getYear() - 18) + 1900) + "";
    }

    public String endFive() {
        return ((getCurrentDate().getYear() + 5) + 1900) + "";
    }

}
