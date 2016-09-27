/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import model.CoopAssociate;
import model.CoopMember;

/**
 *
 * @author vic
 */
@ManagedBean
@SessionScoped
public class memberCount implements Serializable {

    @PersistenceUnit
    EntityManagerFactory emf;

    private Integer current;
    private Integer total;

    public memberCount() {

    }

    public void initVal() {
        setCurrent(regularList(true).size() + associateList(true).size());
        setTotal(regularList(false).size() + associateList(false).size());
    }

    public List<CoopMember> regularList(boolean current) {
        if (current) {
            return emf.createEntityManager().createQuery("SELECT c FROM CoopMember c WHERE c.memStatus <> 'W' and c.memStatus <> 'E' and c.memStatus <> 'D'").getResultList();
        } else {
            return emf.createEntityManager().createQuery("SELECT c FROM CoopMember c").getResultList();
        }
    }

    public List<CoopAssociate> associateList(boolean current) {
        if (current) {
            return emf.createEntityManager().createQuery("SELECT c FROM CoopAssociate c WHERE c.memStatus <> 'W' and c.memStatus <> 'E' and c.memStatus <> 'D'").getResultList();
        } else {
            return emf.createEntityManager().createQuery("SELECT c FROM CoopAssociate c").getResultList();
        }
    }

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

}
