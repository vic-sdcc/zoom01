/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import model.CoopSkills;

/**
 *
 * @author mis05
 */
@ManagedBean
@RequestScoped
public class SkillsList implements Serializable {

    /**
     * Creates a new instance of SkillsList
     */
    public SkillsList() {
    }

    @PersistenceUnit
    EntityManagerFactory entityManagerFactory;
    private List<CoopSkills> skillsList;
    private DataModel<CoopSkills> skillsModel;

    /*
     * getter setter
     */
    public List<CoopSkills> getSkillsList() {
        skillsList = entityManagerFactory.createEntityManager().createQuery("SELECT s FROM CoopSkills s ORDER BY s.skillsName").getResultList();
        return skillsList;
    }

    public void setSkillsList(List<CoopSkills> skillsList) {
        this.skillsList = skillsList;
    }

    public DataModel<CoopSkills> getSkillsModel() {
        if (skillsModel == null) {
            skillsModel = new ListDataModel<>(getSkillsList());
        }
        return skillsModel;
    }

    public void setSkillsModel(DataModel<CoopSkills> skillsModel) {
        this.skillsModel = skillsModel;
    }

}
