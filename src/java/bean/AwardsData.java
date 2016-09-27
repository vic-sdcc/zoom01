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
import model.CoopAwards;
import model.CoopAwardsAssoc;
import model.CoopAwardsMem;
import service.CoopAwardsAssocFacadeREST;
import service.CoopAwardsFacadeREST;
import service.CoopAwardsMemFacadeREST;

/**
 *
 * @author mis05
 */
@ManagedBean
@SessionScoped
public class AwardsData implements Serializable {

    /**
     * Creates a new instance of AwardsData
     */
    public AwardsData() {
        System.out.print("AwardsData constructed");
    }

    @PersistenceUnit
    EntityManagerFactory entityManagerFactory;
    @EJB
    private CoopAwardsFacadeREST awardsREST;
    private CoopAwards awards;
    private ArrayList<CoopAwards> awardsAL;
    private List<CoopAwards> awardsList;
    private DataModel<CoopAwards> awardsModel;
    @EJB
    private CoopAwardsAssocFacadeREST awardsAssocREST;
    private CoopAwardsAssoc awardsAssoc;
    private ArrayList<CoopAwardsAssoc> awardsAssocAL;
    @EJB
    private CoopAwardsMemFacadeREST awardsMemREST;
    private CoopAwardsMem awardsMem;
    private ArrayList<CoopAwardsMem> awardsMemAL;
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
    public CoopAwards getAwards() {
        if (awards == null) {
            awards = new CoopAwards();
        }
        return awards;
    }

    public void setAwards(CoopAwards awards) {
        this.awards = awards;
    }

    public ArrayList<CoopAwards> getAwardsAL() {
        if (awardsAL == null) {
            awardsAL = new ArrayList<>();
        }
        return awardsAL;
    }

    public void setAwardsAL(ArrayList<CoopAwards> awardsAL) {
        this.awardsAL = awardsAL;
    }

    public List<CoopAwards> getAwardsList() {
        return awardsList;
    }

    public void setAwardsList(List<CoopAwards> awardsList) {
        this.awardsList = awardsList;
    }

    public DataModel<CoopAwards> getAwardsModel() {
        if (awardsModel == null) {
            awardsModel = new ListDataModel<>(getAwardsList());
        }
        return awardsModel;
    }

    public void setAwardsModel(DataModel<CoopAwards> awardsModel) {
        this.awardsModel = awardsModel;
    }

    public CoopAwardsAssoc getAwardsAssoc() {
        if (awardsAssoc == null) {
            awardsAssoc = new CoopAwardsAssoc();
        }
        return awardsAssoc;
    }

    public void setAwardsAssoc(CoopAwardsAssoc awardsAssoc) {
        this.awardsAssoc = awardsAssoc;
    }

    public ArrayList<CoopAwardsAssoc> getAwardsAssocAL() {
        if (awardsAssocAL == null) {
            awardsAssocAL = new ArrayList<>();
        }
        return awardsAssocAL;
    }

    public void setAwardsAssocAL(ArrayList<CoopAwardsAssoc> awardsAssocAL) {
        this.awardsAssocAL = awardsAssocAL;
    }

    public CoopAwardsMem getAwardsMem() {
        if (awardsMem == null) {
            awardsMem = new CoopAwardsMem();
        }
        return awardsMem;
    }

    public void setAwardsMem(CoopAwardsMem awardsMem) {
        this.awardsMem = awardsMem;
    }

    public ArrayList<CoopAwardsMem> getAwardsMemAL() {
        if (awardsMemAL == null) {
            awardsMemAL = new ArrayList<>();
        }
        return awardsMemAL;
    }

    public void setAwardsMemAL(ArrayList<CoopAwardsMem> awardsMemAL) {
        this.awardsMemAL = awardsMemAL;
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
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("awardsData", null);
    }

    public void clearAL() {
        getAwardsAL().clear();
        getAwardsAssocAL().clear();
        getAwardsMemAL().clear();
    }

    public void setAL() {
        getAwardsAL();
        getAwardsAssocAL();
        getAwardsMemAL();
    }

    public void newSet() {
        getAwardsAL().add(new CoopAwards());
        try {
            if (getMembershipType().getTypeValue().equals(0)) {
                getAwardsMemAL().add(new CoopAwardsMem());
            }
            if (getMembershipType().getTypeValue().equals(1)) {
                getAwardsAssocAL().add(new CoopAwardsAssoc());
            }
        } catch (Exception e) {
            System.out.print("AwardsData.java newSet() " + e);
        }
    }

    public void addAward() {
        if (getAwardsAL().size() < 20) {
            newSet();
        }
    }

    public void removeAward(int value) {
        getAwardsAL().remove(value);
        if (getMembershipType().getTypeValue().equals(0)) {
            getAwardsMemAL().remove(value);
        }
        if (getMembershipType().getTypeValue().equals(1)) {
            getAwardsAssocAL().remove(value);
        }
    }

    public void removeLoad() {
        try {
            if (getAwardsAL().get(0).getAwardTitle() == null) {
                removeAward(0);
            }
        } catch (Exception e) {
            System.out.print("AwardsData.java removeLoad() " + e);
        }
    }

    public void awardsLoad() {
        if (getMode().isEditMode()) {
            selectedAwardsLoad();
        }
        for (int i = 0; i != getAwardsAL().size(); i++) {
            getAwards().setAwardTitle(getAwardsAL().get(i).getAwardTitle());
            getAwards().setAwardsDate(getAwardsAL().get(i).getAwardsDate());
            getAwards().setAwardDetails(getAwardsAL().get(i).getAwardDetails());
            awardsREST.create(getAwards());
            if (getMembershipType().getTypeValue().equals(0)) {
                getAwardsMem().setMemNo(getMemberData().getMember());
                getAwardsMem().setAccAwdNum(getAwards());
                awardsMemREST.create(getAwardsMem());
            }
            if (getMembershipType().getTypeValue().equals(1)) {
                getAwardsAssoc().setAssociateNo(getAssociateData().getAssociate());
                getAwardsAssoc().setAccAwdNum(getAwards());
                awardsAssocREST.create(getAwardsAssoc());
            }
        }
    }

    public void selectedAwardsLoad() {
        String t1 = "", t2 = "", endString = "";
        CoopAwards am = null;
        CoopAwardsMem m = null;
        CoopAwardsAssoc a = null;
        Query CoopAwardsM = null, CoopAwardsA = null;
        try {
            if (getMembershipType().getTypeValue().equals(0)) {
                endString = "JOIN t2.accAwdNum t1 "
                        + "JOIN t2.memNo t3 "
                        + "WHERE t3.memNo = '" + getMemberData().getMember().getMemNo() + "'";
                t1 = "SELECT t1 FROM CoopAwardsMem t2 " + endString;
                t2 = "SELECT t2 FROM CoopAwardsMem t2 " + endString;
                CoopAwardsM = entityManagerFactory.createEntityManager().createQuery(t1);
                setAwardsList(CoopAwardsM.getResultList());
                if (getAwardsList().size() > 0) {
                    for (int i = 0; i != getAwardsList().size(); i++) {
                        if (getMode().isEditMode()) {
                            am = (CoopAwards) entityManagerFactory.createEntityManager().createQuery(t1).getResultList().get(0);
                            m = (CoopAwardsMem) entityManagerFactory.createEntityManager().createQuery(t2).getResultList().get(0);
                            awardsMemREST.remove(m);
                            awardsREST.remove(am);
                        } else {
                            am = (CoopAwards) entityManagerFactory.createEntityManager().createQuery(t1).getResultList().get(i);
                            m = (CoopAwardsMem) entityManagerFactory.createEntityManager().createQuery(t2).getResultList().get(i);
                            if (i == 0) {
                                getAwardsMemAL().set(i, m);
                                getAwardsAL().set(i, am);
                            } else {
                                getAwardsMemAL().add(m);
                                getAwardsAL().add(am);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.print("AwardsData.java selectedAwardsLoad()-1 " + e);
        }
        try {
            if (getMembershipType().getTypeValue().equals(1)) {
                endString = "JOIN t2.accAwdNum t1 "
                        + "JOIN t2.associateNo t3 "
                        + "WHERE t3.associateNo = '" + getAssociateData().getAssociate().getAssociateNo() + "'";
                t1 = "SELECT t1 FROM CoopAwardsAssoc t2 " + endString;
                t2 = "SELECT t2 FROM CoopAwardsAssoc t2 " + endString;
                CoopAwardsA = entityManagerFactory.createEntityManager().createQuery(t1);
                setAwardsList(CoopAwardsA.getResultList());
                if (getAwardsList().size() > 0) {
                    for (int i = 0; i != getAwardsList().size(); i++) {
                        if (getMode().isEditMode()) {
                            am = (CoopAwards) entityManagerFactory.createEntityManager().createQuery(t1).getResultList().get(0);
                            a = (CoopAwardsAssoc) entityManagerFactory.createEntityManager().createQuery(t2).getResultList().get(0);
                            awardsAssocREST.remove(a);
                            awardsREST.remove(am);
                        } else {
                            am = (CoopAwards) entityManagerFactory.createEntityManager().createQuery(t1).getResultList().get(i);
                            a = (CoopAwardsAssoc) entityManagerFactory.createEntityManager().createQuery(t2).getResultList().get(i);
                            if (i == 0) {
                                getAwardsAssocAL().set(i, a);
                                getAwardsAL().set(i, am);
                            } else {
                                getAwardsAssocAL().add(a);
                                getAwardsAL().add(am);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.print("AwardsData.java selectedAwardsLoad()-2 " + e);
        }
        removeLoad();
    }

}
