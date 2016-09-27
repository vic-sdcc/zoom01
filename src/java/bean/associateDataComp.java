/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.CoopAssociate;

/**
 *
 * @author vic
 */
@ManagedBean
@SessionScoped
public class associateDataComp extends memberProfile implements Serializable {

    /**
     * Creates a new instance of associateDataComp
     */
    public associateDataComp() {
    }

    private List<CoopAssociate> memProfileList;
    private Integer indexList;

    public void beanclear() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("associateDataComp", null);
        FacesContext.getCurrentInstance().getViewRoot().getViewMap().clear();
    }

    public void memberComplete() {
        String query = "";
        setMemProfileList(null);
        if (super.getMemNo() != null || super.getLastName() != null || super.getFirstName() != null || super.getMiddleName() != null) {
            try {
                query = "SELECT a FROM CoopAssociate a WHERE a.memNo = a.memNo ";
                if (super.getMemNo() != null) {
                    query += "AND a.memNo ='" + super.getMemNo() + "' ";
                }
                if (super.getLastName() != null) {
                    query += "AND a.lastName ='" + super.getLastName() + "' ";
                }
                if (super.getFirstName() != null) {
                    query += "AND a.firstName ='" + super.getFirstName() + "' ";
                }
                if (super.getMiddleName() != null) {
                    query += "AND a.middleName ='" + super.getMiddleName() + "' ";
                }
                query += "ORDER BY a.memNo";
                setMemProfileList(entityManagerFactory.createEntityManager().createQuery(query).getResultList());
            } catch (Exception e) {
                super.setMemNo(null);
                super.setLastName(null);
                super.setFirstName(null);
                super.setLastName(null);
            }
        }
    }

    public List<CoopAssociate> getMemProfileList() {
        if (memProfileList == null) {
            memProfileList = new ArrayList<>();
        }
        return memProfileList;
    }

    public void setMemProfileList(List<CoopAssociate> memProfileList) {
        this.memProfileList = memProfileList;
    }

    public Integer getIndexList() {
        if (indexList == null) {
            indexList = 0;
        }
        return indexList;
    }

    public void setIndexList(Integer indexList) {
        this.indexList = indexList;
    }
}
