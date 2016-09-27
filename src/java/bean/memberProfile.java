/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 *
 * @author vic
 */
@ManagedBean
@SessionScoped
public class memberProfile implements Serializable {

    /**
     * Creates a new instance of memberProfile
     */
    public memberProfile() {
    }

    @PersistenceUnit
    public EntityManagerFactory entityManagerFactory;

    private String memNo, lastName, firstName, middleName, address;
    private Integer statusIdTemp;

    public String getMemNo() {
        return memNo;
    }

    public void setMemNo(String memNo) {
        this.memNo = memNo;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getStatusIdTemp() {
        return statusIdTemp;
    }

    public void setStatusIdTemp(Integer statusIdTemp) {
        this.statusIdTemp = statusIdTemp;
    }

}
