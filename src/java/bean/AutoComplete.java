/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 *
 * @author vic
 */
@ManagedBean
@RequestScoped
public class AutoComplete implements Serializable {

    /**
     * Creates a new instance of AutoComplete
     */
    public AutoComplete() {
    }

    @PersistenceUnit
    EntityManagerFactory entityManagerFactory;
    @ManagedProperty(value = "#{personType}")
    private PersonType personType;

    /*
     * getter setter
     */
    public PersonType getPersonType() {
        if (personType == null) {
            personType = new PersonType();
        }
        return personType;
    }

    public void setPersonType(PersonType personType) {
        this.personType = personType;
    }

    /*
     * methods
     */
    public List<String> completeMemNoM(String query) {
        return entityManagerFactory.createEntityManager().createQuery("SELECT CONCAT('',m.memNo) FROM CoopMember m"
                + " WHERE CONCAT('',m.memNo) LIKE '" + query + "%' ORDER BY m.memNo").getResultList();
    }

    public List<String> completeMemNoA(String query) {
        return entityManagerFactory.createEntityManager().createQuery("SELECT CONCAT('',a.memNo) FROM CoopAssociate a"
                + " WHERE CONCAT('',a.memNo) LIKE '" + query + "%' ORDER BY a.memNo").getResultList();
    }

    public List<String> completeLastNameM(String query) {
        return entityManagerFactory.createEntityManager().createQuery("SELECT DISTINCT m.lastName FROM CoopMember m"
                + " WHERE UPPER(m.lastName) LIKE UPPER('" + query + "%') ORDER BY m.lastName").getResultList();
    }

    public List<String> completeLastNameA(String query) {
        return entityManagerFactory.createEntityManager().createQuery("SELECT DISTINCT a.lastName FROM CoopAssociate a"
                + " WHERE UPPER(a.lastName) LIKE UPPER('" + query + "%') ORDER BY a.lastName").getResultList();
    }

    public List<String> completeFirstNameM(String query) {
        return entityManagerFactory.createEntityManager().createQuery("SELECT DISTINCT m.firstName FROM CoopMember m"
                + " WHERE UPPER(m.firstName) LIKE UPPER('" + query + "%') ORDER BY m.firstName").getResultList();
    }

    public List<String> completeFirstNameA(String query) {
        return entityManagerFactory.createEntityManager().createQuery("SELECT DISTINCT a.firstName FROM CoopAssociate a"
                + " WHERE UPPER(a.firstName) LIKE UPPER('" + query + "%') ORDER BY a.firstName").getResultList();
    }

    public List<String> completeMiddleNameM(String query) {
        return entityManagerFactory.createEntityManager().createQuery("SELECT DISTINCT m.middleName FROM CoopMember m"
                + " WHERE UPPER(m.middleName) LIKE UPPER('" + query + "%') ORDER BY m.middleName").getResultList();
    }

    public List<String> completeMiddleNameA(String query) {
        return entityManagerFactory.createEntityManager().createQuery("SELECT DISTINCT a.middleName FROM CoopAssociate a"
                + " WHERE UPPER(a.middleName) LIKE UPPER('" + query + "%') ORDER BY a.middleName").getResultList();
    }

    public List<String> completeSkills(String query) {
        return entityManagerFactory.createEntityManager().createQuery("SELECT DISTINCT s.skillsName FROM CoopSkills s"
                + " WHERE UPPER(s.skillsName) LIKE UPPER('%" + query + "%') ORDER BY s.skillsName").getResultList();
    }

    public List<String> completeEmplRank(String query) {
        return entityManagerFactory.createEntityManager().createQuery("SELECT DISTINCT o.unitName FROM CoopOccupation o"
                + " WHERE UPPER(o.unitName) LIKE UPPER('%" + query + "%') ORDER BY o.unitName").getResultList();
    }

    /*
     public List<String> completeSkills(String query) {
     return entityManagerFactory.createEntityManager().createQuery("SELECT DISTINCT CONCAT(s.skillsName,'(',s.subMajorSkillsCode.majorSkillsCode.majorSkillsName,',',s.subMajorSkillsCode.subMajorSkillsName,')') FROM CoopSkills s"
     + " WHERE CONCAT(UPPER(s.skillsName),'(',UPPER(s.subMajorSkillsCode.majorSkillsCode.majorSkillsName),',',UPPER(s.subMajorSkillsCode.subMajorSkillsName),')') LIKE UPPER('" + query + "%')").getResultList();
     }

     public List<String> completeEmplRank(String query) {
     return entityManagerFactory.createEntityManager().createQuery("SELECT DISTINCT CONCAT(o.unitName,'(',o.unitGroupCode.minorGroupCode.subMajorGroupCode.majorCode.majorName,',',o.unitGroupCode.minorGroupCode.subMajorGroupCode.subMajorName,',',o.unitGroupCode.minorGroupCode.minorGroupName,',',o.unitGroupCode.unitGroupName,')') FROM CoopOccupation o"
     + " WHERE CONCAT(UPPER(o.unitName),'(',UPPER(o.unitGroupCode.minorGroupCode.subMajorGroupCode.majorCode.majorName),',',UPPER(o.unitGroupCode.minorGroupCode.subMajorGroupCode.subMajorName),',',UPPER(o.unitGroupCode.minorGroupCode.minorGroupName),',',UPPER(o.unitGroupCode.unitGroupName),')') LIKE UPPER('" + query + "%')").getResultList();
     }
     */
    
    public List<String> completeOrgPos(String query) {
        return entityManagerFactory.createEntityManager().createQuery("SELECT DISTINCT CONCAT(op.ouCode.ouShortName,'-',op.positionId.positionName) FROM CoopOrgPosition op"
                + " WHERE CONCAT(UPPER(op.ouCode.ouShortName),'-',UPPER(op.positionId.positionName)) LIKE UPPER('" + query + "%')").getResultList();
    }

    public List<String> completePersonLastName(String query) {
        String pln = "SELECT DISTINCT pp.lastName FROM ";
        if (getPersonType().getAl().get(getPersonType().getTv()).getPersonTypeId().equals(1)) {
            pln += "CoopPersonMem p JOIN p.memNo pp WHERE UPPER(pp.lastName) LIKE UPPER('" + query + "%') ";
        }
        if (getPersonType().getAl().get(getPersonType().getTv()).getPersonTypeId().equals(2)) {
            pln += "CoopPersonAssociate p JOIN p.associateNo pp WHERE UPPER(pp.lastName) LIKE UPPER('" + query + "%') ";
        }
        if (getPersonType().getAl().get(getPersonType().getTv()).getPersonTypeId().equals(3)) {
            pln += "CoopPersonNonMember p JOIN p.nonMemberId pp WHERE UPPER(pp.lastName) LIKE UPPER('" + query + "%') ";
        }
        pln += "ORDER BY pp.lastName";
        return entityManagerFactory.createEntityManager().createQuery(pln).getResultList();
    }

    public List<String> completePersonFirstName(String query) {
        String pfn = "SELECT DISTINCT pp.firstName FROM ";
        if (getPersonType().getAl().get(getPersonType().getTv()).getPersonTypeId().equals(1)) {
            pfn += "CoopPersonMem p JOIN p.memNo pp WHERE UPPER(pp.firstName) LIKE UPPER('" + query + "%') ";
        }
        if (getPersonType().getAl().get(getPersonType().getTv()).getPersonTypeId().equals(2)) {
            pfn += "CoopPersonAssociate p JOIN p.associateNo pp WHERE UPPER(pp.firstName) LIKE UPPER('" + query + "%') ";
        }
        if (getPersonType().getAl().get(getPersonType().getTv()).getPersonTypeId().equals(3)) {
            pfn += "CoopPersonNonMember p JOIN p.nonMemberId pp WHERE UPPER(pp.firstName) LIKE UPPER('" + query + "%') ";
        }
        pfn += "ORDER BY pp.firstName";
        return entityManagerFactory.createEntityManager().createQuery(pfn).getResultList();
    }

    public List<String> completePersonMiddleName(String query) {
        String pmn = "SELECT DISTINCT pp.middleName FROM ";
        if (getPersonType().getAl().get(getPersonType().getTv()).getPersonTypeId().equals(1)) {
            pmn += "CoopPersonMem p JOIN p.memNo pp WHERE UPPER(pp.middleName) LIKE UPPER('" + query + "%') ";
        }
        if (getPersonType().getAl().get(getPersonType().getTv()).getPersonTypeId().equals(2)) {
            pmn += "CoopPersonAssociate p JOIN p.associateNo pp WHERE UPPER(pp.middleName) LIKE UPPER('" + query + "%') ";
        }
        if (getPersonType().getAl().get(getPersonType().getTv()).getPersonTypeId().equals(3)) {
            pmn += "CoopPersonNonMember p JOIN p.nonMemberId pp WHERE UPPER(pp.middleName) LIKE UPPER('" + query + "%') ";
        }
        pmn += "ORDER BY pp.middleName";
        return entityManagerFactory.createEntityManager().createQuery(pmn).getResultList();
    }

}
