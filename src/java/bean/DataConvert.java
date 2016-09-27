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
 * @author mis05
 */
@ManagedBean
@SessionScoped
public class DataConvert implements Serializable {

    /**
     * Creates a new instance of DataConvert
     */
    public DataConvert() {
    }

    @PersistenceUnit
    EntityManagerFactory entityManagerFactory;
    private String convertedString = "";

    /*
     * getter setter
     */
    public String getConvertedString() {
        return convertedString;
    }

    public void setConvertedString(String convertedString) {
        this.convertedString = convertedString;
    }

    /*
     * methods
     */
    public String residenceTypeConv(String value) {
        convertedString = "";
        if ("O".equals(value)) {
            convertedString = "OWNED";
        } else if ("R".equals(value)) {
            convertedString = "RENTED";
        } else if ("L".equals(value)) {
            convertedString = "LIVING WITH RELATIVES";
        } else if ("M".equals(value)) {
            convertedString = "MORTGAGE";
        } else if ("N".equals(value)) {
            convertedString = "NO DATA";
        }
        return convertedString;
    }

    public String genderConv(char value) {
        convertedString = "";
        if (value == 'M') {
            convertedString = "MALE";
        } else if (value == 'F') {
            convertedString = "FEMALE";
        }
        return convertedString;
    }

    public String civilStatusConv(String value) {
        convertedString = "";
        if ("S".equals(value)) {
            convertedString = "SINGLE";
        } else if ("M".equals(value)) {
            convertedString = "MARRIED";
        } else if ("P".equals(value)) {
            convertedString = "SEPARATED";
        } else if ("W".equals(value)) {
            convertedString = "WIDOW(ER)";
        } else if ("N".equals(value)) {
            convertedString = "NO DATA";
        }
        return convertedString;
    }

    public String personStatusConv(boolean value) {
        convertedString = "";
        if (value == true) {
            convertedString = "LIVING";
        } else if (value == false) {
            convertedString = "DECEASED";
        }
        return convertedString;
    }

    public String nationalityConv(boolean value) {
        convertedString = "";
        if (value == true) {
            convertedString = "FILIPINO";
        } else if (value == false) {
            convertedString = "NOT FILIPINO";
        }
        return convertedString;
    }

    public String contactTypeConv(char value) {
        convertedString = "";
        if (value == 'E') {
            convertedString = "EMAIL";
        } else if (value == 'M') {
            convertedString = "MOBILE";
        } else if (value == 'L') {
            convertedString = "LANDLINE";
        } else if (value == 'F') {
            convertedString = "FAX";
        } else if (value == 'N') {
            convertedString = "NO DATA";
        }
        return convertedString;
    }

    public String graduatedConv(boolean value) {
        convertedString = "";
        if (value == true) {
            convertedString = "YES";
        } else if (value == false) {
            convertedString = "NO";
        }
        return convertedString;
    }

    public String memStatusConv(int value) {
        convertedString = "";
        if (value == 1) {
            convertedString = "GOOD STANDING";
        } else if (value == 10) {
            convertedString = "PROBATIONARY";
        }
        return convertedString;
    }

    public String educConv(char value) {
        convertedString = "";
        if (value == 'P') {
            convertedString = "POST-GRADUATE";
        } else if (value == 'V') {
            convertedString = "VOCATIONAL";
        } else if (value == 'C') {
            convertedString = "COLLEGE";
        } else if (value == 'H') {
            convertedString = "HIGH SCHOOL";
        } else if (value == 'E') {
            convertedString = "ELEMENTARY";
        } else if (value == 'N') {
            convertedString = "NO DATA";
        }
        return convertedString;
    }

    public String pTypeConv(int value) {
        convertedString = "";
        if (value == 1) {
            convertedString = "REGULAR";
        } else if (value == 2) {
            convertedString = "ASSOCIATE";
        } else if (value == 3) {
            convertedString = "NON-MEMBER";
        }
        return convertedString;
    }

    public String ouConv(String value) {
        return entityManagerFactory.createEntityManager().createQuery("SELECT o.ouShortName FROM CoopOrgUnit o WHERE o.ouCode = '" + value + "'").getResultList().get(0).toString();
    }

    public String memStatConv(String value) {
        return entityManagerFactory.createEntityManager().createQuery("SELECT o.statusName FROM CoopMemberStatus o WHERE o.memStatus = '" + value + "'").getResultList().get(0).toString();
    }

}
