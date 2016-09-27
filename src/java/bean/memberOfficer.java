/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import model.CoopMember;

/**
 *
 * @author vic
 */
@ManagedBean
@SessionScoped
public class memberOfficer implements Serializable {

    /**
     * Creates a new instance of memberOfficer
     */
    public memberOfficer() {
    }

    @PersistenceUnit
    EntityManagerFactory entityManagerFactory;
    private dateFormat dFormat;

    public CoopMember selectedOfficer(String ouCode) {
        try {
            return (CoopMember) entityManagerFactory.createEntityManager().createQuery("SELECT c.memOuPosId.memNo FROM "
                    + "CoopPositionTenure c WHERE c.termStart <= '" + getdFormat().formatDate(new Date(), "yyyy-MM-dd") + "' "
                    + "AND c.termEnd >= '" + getdFormat().formatDate(new Date(), "yyyy-MM-dd") + "' "
                    + "AND c.memOuPosId.orgPosId.positionId.positionId = 51 "
                    + "AND c.memOuPosId.orgPosId.ouCode.ouCode ='" + ouCode + "'").getResultList().get(0);
        } catch (Exception e) {
            System.out.print(e);
        }
        return null;
    }

    public dateFormat getdFormat() {
        if (dFormat == null) {
            dFormat = new dateFormat();
        }
        return dFormat;
    }

    public void setdFormat(dateFormat dFormat) {
        this.dFormat = dFormat;
    }

}
