/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author vic
 */
@ManagedBean
@SessionScoped
public class yearsList implements Serializable {

    /**
     * Creates a new instance of yearsList
     */
    public yearsList() {
    }

    private ArrayList yearsAL;
    @ManagedProperty(value = "#{currentDate}")
    private Date currentDate;

    public ArrayList getYearsAL() {
        if (yearsAL == null) {
            yearsAL = new ArrayList();
        }
        for (int i = (getCurrentDate().getYear() + 1900); i > 1949; i--) {
            getYearsAL().add(i);
        }
        return yearsAL;
    }

    public void setYearsAL(ArrayList yearsAL) {
        this.yearsAL = yearsAL;
    }

    public Date getCurrentDate() {
        if (currentDate == null) {
            currentDate = new Date();
        }
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

}
