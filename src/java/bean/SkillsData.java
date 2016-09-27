/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import model.CoopSkills;
import model.CoopSkillsAssoc;
import model.CoopSkillsMem;
import service.CoopSkillsAssocFacadeREST;
import service.CoopSkillsMemFacadeREST;

/**
 *
 * @author mis05
 */
@ManagedBean
@SessionScoped
public class SkillsData implements Serializable {

    /**
     * Creates a new instance of SkillsData
     */
    public SkillsData() {
        System.out.print("SkillsData constructed");
    }

    private ArrayList<String> stringAL;
    @PersistenceUnit
    EntityManagerFactory entityManagerFactory;
    private ArrayList<CoopSkills> skillsAL;
    private List<CoopSkills> skillsList;
    private DataModel<CoopSkills> skillsModel;
    @EJB
    private CoopSkillsAssocFacadeREST skillsAssocREST;
    private CoopSkillsAssoc skillsAssoc;
    private ArrayList<CoopSkillsAssoc> skillsAssocAL;
    @EJB
    private CoopSkillsMemFacadeREST skillsMemREST;
    private CoopSkillsMem skillsMem;
    private ArrayList<CoopSkillsMem> skillsMemAL;
    @ManagedProperty(value = "#{memberData}")
    private MemberData memberData;
    @ManagedProperty(value = "#{associateData}")
    private AssociateData associateData;
    @ManagedProperty(value = "#{membershipType}")
    private MembershipType membershipType;
    @ManagedProperty(value = "#{mode}")
    private Mode mode;

    /*
     * getter setter
     */
    public ArrayList<String> getStringAL() {
        if (stringAL == null) {
            stringAL = new ArrayList<>();
        }
        return stringAL;
    }

    public void setStringAL(ArrayList<String> stringAL) {
        this.stringAL = stringAL;
    }

    public ArrayList<CoopSkills> getSkillsAL() {
        if (skillsAL == null) {
            skillsAL = new ArrayList<>();
        }
        return skillsAL;
    }

    public void setSkillsAL(ArrayList<CoopSkills> skillsAL) {
        this.skillsAL = skillsAL;
    }

    public List<CoopSkills> getSkillsList() {
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

    public CoopSkillsAssoc getSkillsAssoc() {
        if (skillsAssoc == null) {
            skillsAssoc = new CoopSkillsAssoc();
        }
        return skillsAssoc;
    }

    public void setSkillsAssoc(CoopSkillsAssoc skillsAssoc) {
        this.skillsAssoc = skillsAssoc;
    }

    public ArrayList<CoopSkillsAssoc> getSkillsAssocAL() {
        if (skillsAssocAL == null) {
            skillsAssocAL = new ArrayList<>();
        }
        return skillsAssocAL;
    }

    public void setSkillsAssocAL(ArrayList<CoopSkillsAssoc> skillsAssocAL) {
        this.skillsAssocAL = skillsAssocAL;
    }

    public CoopSkillsMem getSkillsMem() {
        if (skillsMem == null) {
            skillsMem = new CoopSkillsMem();
        }
        return skillsMem;
    }

    public void setSkillsMem(CoopSkillsMem skillsMem) {
        this.skillsMem = skillsMem;
    }

    public ArrayList<CoopSkillsMem> getSkillsMemAL() {
        if (skillsMemAL == null) {
            skillsMemAL = new ArrayList<>();
        }
        return skillsMemAL;
    }

    public void setSkillsMemAL(ArrayList<CoopSkillsMem> skillsMemAL) {
        this.skillsMemAL = skillsMemAL;
    }

    public MemberData getMemberData() {
        if (memberData == null) {
            memberData = new MemberData();
        }
        return memberData;
    }

    public void setMemberData(MemberData memberData) {
        this.memberData = memberData;
    }

    public AssociateData getAssociateData() {
        if (associateData == null) {
            associateData = new AssociateData();
        }
        return associateData;
    }

    public void setAssociateData(AssociateData associateData) {
        this.associateData = associateData;
    }

    public MembershipType getMembershipType() {
        if (membershipType == null) {
            membershipType = new MembershipType();
        }
        return membershipType;
    }

    public void setMembershipType(MembershipType membershipType) {
        this.membershipType = membershipType;
    }

    public Mode getMode() {
        if (mode == null) {
            mode = new Mode();
        }
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    /*
     * methods
     */
    public void beanclear() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("skillsData", null);
    }

    public void clearAL() {
        getSkillsAL().clear();
        getSkillsAssocAL().clear();
        getSkillsMemAL().clear();
        getStringAL().clear();
    }

    public void setAL() {
        getSkillsAL();
        getSkillsAssocAL();
        getSkillsMemAL();
        getStringAL();
    }

    public void newSet() {
        getSkillsAL().add(new CoopSkills());
        getStringAL().add(new String());
        try {
            if (getMembershipType().getTypeValue().equals(0)) {
                getSkillsMemAL().add(new CoopSkillsMem());
            }
            if (getMembershipType().getTypeValue().equals(1)) {
                getSkillsAssocAL().add(new CoopSkillsAssoc());
            }
        } catch (Exception e) {
            System.out.print("SkillsData.java newSet() " + e);
        }
    }

    public void addSkills() {
        if (getSkillsAL().size() < 10) {
            newSet();
        }
    }

    public void removeSkills(int value) {
        getSkillsAL().remove(value);
        getStringAL().remove(value);
        if (getMembershipType().getTypeValue().equals(0)) {
            getSkillsMemAL().remove(value);
        }
        if (getMembershipType().getTypeValue().equals(1)) {
            getSkillsAssocAL().remove(value);
        }
    }

    public void removeLoad() {
        try {
            if (getSkillsAL().get(0).getSkillsCode() == null) {
                removeSkills(0);
            }
        } catch (Exception e) {
            System.out.print("SkillsData.java removeLoad() " + e);
        }
    }

    public void skillLoad() {
        if (getMode().isEditMode()) {
            selectedSkillLoad();
        }
        for (int i = 0; i != getSkillsAL().size(); i++) {
            getSkillsAL().get(i).setSkillsCode((String) entityManagerFactory.createEntityManager().createQuery("SELECT s.skillsCode FROM CoopSkills s WHERE s.skillsName = '" + getSkillsAL().get(i).getSkillsName() + "'").getResultList().get(0));
            //getSkillsAL().get(i).setSkillsCode((String) entityManagerFactory.createEntityManager().createQuery("SELECT s.skillsCode FROM CoopSkills s WHERE CONCAT(UPPER(s.skillsName),'(',UPPER(s.subMajorSkillsCode.majorSkillsCode.majorSkillsName),',',UPPER(s.subMajorSkillsCode.subMajorSkillsName),')') = '" + getStringAL().get(i) + "'").getResultList().get(0));
            if (getMembershipType().getTypeValue().equals(0)) {
                getSkillsMem().setMemNo(getMemberData().getMember());
                getSkillsMem().setSkillsCode(getSkillsAL().get(i));
                skillsMemREST.create(getSkillsMem());
            }
            if (getMembershipType().getTypeValue().equals(1)) {
                getSkillsAssoc().setAssociateNo(getAssociateData().getAssociate());
                getSkillsAssoc().setSkillsCode(getSkillsAL().get(i));
                skillsAssocREST.create(getSkillsAssoc());
            }
        }
    }

    public void selectedSkillLoad() {
        String t1 = "", t2 = "", t4 = "", endString = "";
        String d = null;
        CoopSkills am = null;
        CoopSkillsMem m = null;
        CoopSkillsAssoc a = null;
        Query CoopSkillsM = null, CoopSkillsA = null;
        try {
            if (getMembershipType().getTypeValue().equals(0)) {
                endString = "JOIN t2.skillsCode t1 "
                        + "JOIN t2.memNo t3 "
                        + "WHERE t3.memNo = '" + getMemberData().getMember().getMemNo() + "'";
                t1 = "SELECT t1 FROM CoopSkillsMem t2 " + endString;
                t2 = "SELECT t2 FROM CoopSkillsMem t2 " + endString;
                //t4 = "SELECT CONCAT(t2,'(',t5,',',t6,')') FROM CoopSkillsMem t2 " + endString;
                CoopSkillsM = entityManagerFactory.createEntityManager().createQuery(t1);
                setSkillsList(CoopSkillsM.getResultList());
                if (getSkillsList().size() > 0) {
                    for (int i = 0; i != getSkillsList().size(); i++) {
                        if (getMode().isEditMode()) {
                            m = (CoopSkillsMem) entityManagerFactory.createEntityManager().createQuery(t2).getResultList().get(0);
                            skillsMemREST.remove(m);
                        } else {
                            am = (CoopSkills) entityManagerFactory.createEntityManager().createQuery(t1).getResultList().get(i);
                            m = (CoopSkillsMem) entityManagerFactory.createEntityManager().createQuery(t2).getResultList().get(i);
                            //d = (String) entityManagerFactory.createEntityManager().createQuery(t4).getResultList().get(i);
                            if (i == 0) {
                                getSkillsMemAL().set(i, m);
                                getSkillsAL().set(i, am);
                                //getStringAL().set(i, d);
                            } else {
                                getSkillsMemAL().add(m);
                                getSkillsAL().add(am);
                                //getStringAL().add(d);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.print("SkillsData.java selectedSkillLoad()-1 " + e);
        }
        try {
            if (getMembershipType().getTypeValue().equals(1)) {
                endString = "JOIN t2.skillsCode t1 "
                        + "JOIN t2.associateNo t3 "
                        + "WHERE t3.associateNo = '" + getAssociateData().getAssociate().getAssociateNo() + "'";
                t1 = "SELECT t1 FROM CoopSkillsAssoc t2 " + endString;
                t2 = "SELECT t2 FROM CoopSkillsAssoc t2 " + endString;
                CoopSkillsA = entityManagerFactory.createEntityManager().createQuery(t1);
                setSkillsList(CoopSkillsA.getResultList());
                if (getSkillsList().size() > 0) {
                    for (int i = 0; i != getSkillsList().size(); i++) {
                        if (getMode().isEditMode()) {
                            a = (CoopSkillsAssoc) entityManagerFactory.createEntityManager().createQuery(t2).getResultList().get(0);
                            skillsAssocREST.remove(a);
                        } else {
                            am = (CoopSkills) entityManagerFactory.createEntityManager().createQuery(t1).getResultList().get(i);
                            a = (CoopSkillsAssoc) entityManagerFactory.createEntityManager().createQuery(t2).getResultList().get(i);
                            if (i == 0) {
                                getSkillsAssocAL().set(i, a);
                                getSkillsAL().set(i, am);
                            } else {
                                getSkillsAssocAL().add(a);
                                getSkillsAL().add(am);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.print("SkillsData.java selectedSkillLoad()-2 " + e);
        }
    }

}
