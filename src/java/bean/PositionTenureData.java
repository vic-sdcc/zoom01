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
import model.CoopMemOuPos;
import model.CoopOrgPosition;
import model.CoopPositionTenure;
import model.CoopTenure;
import service.CoopMemOuPosFacadeREST;
import service.CoopPositionTenureFacadeREST;

/**
 *
 * @author mis05
 */
@ManagedBean
@SessionScoped
public class PositionTenureData implements Serializable {

    /**
     * Creates a new instance of PositionTenure
     */
    public PositionTenureData() {
        System.out.print("PositionTenureData constructed");
    }

    private ArrayList<String> stringAL;
    @PersistenceUnit
    EntityManagerFactory entityManagerFactory;
    private ArrayList<CoopPositionTenure> positionTenureAL;
    private List<CoopPositionTenure> positionTenureList;
    private DataModel<CoopPositionTenure> positionTenureModel;
    private ArrayList<CoopTenure> tenureAL;
    private ArrayList<CoopMemOuPos> memOuPosAL;
    private ArrayList<CoopOrgPosition> orgPositionAL;
    @EJB
    private CoopPositionTenureFacadeREST positionTenureREST;
    private CoopPositionTenure positionTenure;
    @EJB
    private CoopMemOuPosFacadeREST memOuPosREST;
    private CoopMemOuPos memOuPos;
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

    public ArrayList<CoopPositionTenure> getPositionTenureAL() {
        if (positionTenureAL == null) {
            positionTenureAL = new ArrayList<>();
        }
        return positionTenureAL;
    }

    public void setPositionTenureAL(ArrayList<CoopPositionTenure> positionTenureAL) {
        this.positionTenureAL = positionTenureAL;
    }

    public List<CoopPositionTenure> getPositionTenureList() {
        return positionTenureList;
    }

    public void setPositionTenureList(List<CoopPositionTenure> positionTenureList) {
        this.positionTenureList = positionTenureList;
    }

    public DataModel<CoopPositionTenure> getPositionTenureModel() {
        if (positionTenureModel == null) {
            positionTenureModel = new ListDataModel<>(getPositionTenureList());
        }
        return positionTenureModel;
    }

    public void setPositionTenureModel(DataModel<CoopPositionTenure> positionTenureModel) {
        this.positionTenureModel = positionTenureModel;
    }

    public ArrayList<CoopTenure> getTenureAL() {
        if (tenureAL == null) {
            tenureAL = new ArrayList<>();
        }
        return tenureAL;
    }

    public void setTenureAL(ArrayList<CoopTenure> tenureAL) {
        this.tenureAL = tenureAL;
    }

    public ArrayList<CoopMemOuPos> getMemOuPosAL() {
        if (memOuPosAL == null) {
            memOuPosAL = new ArrayList<>();
        }
        return memOuPosAL;
    }

    public void setMemOuPosAL(ArrayList<CoopMemOuPos> memOuPosAL) {
        this.memOuPosAL = memOuPosAL;
    }

    public ArrayList<CoopOrgPosition> getOrgPositionAL() {
        if (orgPositionAL == null) {
            orgPositionAL = new ArrayList<>();
        }
        return orgPositionAL;
    }

    public void setOrgPositionAL(ArrayList<CoopOrgPosition> orgPositionAL) {
        this.orgPositionAL = orgPositionAL;
    }

    public CoopPositionTenure getPositionTenure() {
        if (positionTenure == null) {
            positionTenure = new CoopPositionTenure();
        }
        return positionTenure;
    }

    public void setPositionTenure(CoopPositionTenure positionTenure) {
        this.positionTenure = positionTenure;
    }

    public CoopMemOuPos getMemOuPos() {
        if (memOuPos == null) {
            memOuPos = new CoopMemOuPos();
        }
        return memOuPos;
    }

    public void setMemOuPos(CoopMemOuPos memOuPos) {
        this.memOuPos = memOuPos;
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
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("positionTenureData", null);
    }

    public void clearAL() {
        getPositionTenureAL().clear();
        getTenureAL().clear();
        getMemOuPosAL().clear();
        getOrgPositionAL().clear();
        getStringAL().clear();
    }

    public void setAL() {
        getPositionTenureAL();
        getTenureAL();
        getMemOuPosAL();
        getOrgPositionAL();
        getStringAL();
    }

    public void newSet() {
        getPositionTenureAL().add(new CoopPositionTenure());
        getTenureAL().add(new CoopTenure());
        getMemOuPosAL().add(new CoopMemOuPos());
        getOrgPositionAL().add(new CoopOrgPosition());
        getStringAL().add(new String());
    }

    public void addPositionTenure() {
        if (getPositionTenureAL().size() < 50) {
            newSet();
        }
    }

    public void removePositionTenure(int value) {
        getPositionTenureAL().remove(value);
        getTenureAL().remove(value);
        getMemOuPosAL().remove(value);
        getOrgPositionAL().remove(value);
        getStringAL().remove(value);
    }

    public void removeLoad() {
        try {
            if (getPositionTenureAL().get(0).getMemOuPosId() == null) {
                removePositionTenure(0);
            }
        } catch (Exception e) {
            System.out.print("PositionTenure.java removeLoad() " + e);
        }
    }

    public void positionTenureLoad() {
        if (getMode().isEditMode()) {
            selectedPositionTenureLoad();
        }
        for (int i = 0; i != getPositionTenureAL().size(); i++) {
            getOrgPositionAL().get(i).setOrgPosId((Integer) entityManagerFactory.createEntityManager().createQuery("SELECT orpo.orgPosId FROM CoopOrgPosition orpo WHERE CONCAT(UPPER(orpo.ouCode.ouShortName),'-',UPPER(orpo.positionId.positionName)) = '" + getStringAL().get(i) + "'").getResultList().get(0));
            if (getMembershipType().getTypeValue().equals(0)) {
                getMemOuPos().setMemNo(getMemberData().getMember());
                getMemOuPos().setAcctno(getMemberData().getMember().getScAcctno());
                getMemOuPos().setOrgPosId(getOrgPositionAL().get(i));
                memOuPosREST.create(getMemOuPos());

                getPositionTenure().setMemOuPosId(getMemOuPos());
                getPositionTenure().setTenureId(getTenureAL().get(i));
                getPositionTenure().setTermStart(getPositionTenureAL().get(i).getTermStart());
                System.out.print("hello world " + getPositionTenureAL().get(i).getTermEnd());
                getPositionTenure().setTermEnd(getPositionTenureAL().get(i).getTermEnd());
                positionTenureREST.create(getPositionTenure());
            }
            if (getMembershipType().getTypeValue().equals(1)) {

            }
        }
    }

    public void selectedPositionTenureLoad() {
        String t1 = "", t2 = "", t3 = "", t4 = "", t5 = "", t6 = "", t8 = "", endString = "";
        CoopPositionTenure a = null;
        CoopTenure b = null;
        CoopMemOuPos c = null;
        String d = null;
        CoopOrgPosition e = null;
        Query CoopPositionTenureM = null;
        try {
            if (getMembershipType().getTypeValue().equals(0)) {
                endString = "JOIN t2.tenureId t1 "
                        + "JOIN t2.memOuPosId.memNo.memNo t3 "
                        + "JOIN t1.tenureId t4 "
                        + "JOIN t2.memOuPosId t5 "
                        + "JOIN t2.memOuPosId.orgPosId.ouCode.ouShortName t6 "
                        + "JOIN t2.memOuPosId.orgPosId.positionId.positionName t7 "
                        + "JOIN t2.memOuPosId.orgPosId t8 "
                        + "WHERE t3 = '" + getMemberData().getMember().getMemNo() + "'";
                /*
                 endString = "JOIN t2.tenureId t1 "
                 + "JOIN t2.memOuPosId.memNo.memNo t3 "
                 + "JOIN t1.tenureId t4 "
                 + "JOIN t2.memOuPosId t5 "
                 + "JOIN t2.memOuPosId.orgPosId t6 "
                 + "WHERE t2.termEnd IS NULL "
                 + "OR t2.termEnd"
                 + "AND t3 = '" + getMemberData().getMember().getMemNo() + "'";

                 WHERE c.termStart <= '" + getdFormat().formatDate(new Date(), "yyyy-MM-dd") + "' "
                 + "AND c.termEnd >= '" + getdFormat().formatDate(new Date(), "yyyy-MM-dd") + "' "
                 */
                t1 = "SELECT t1 FROM CoopPositionTenure t2 " + endString;
                t2 = "SELECT t2 FROM CoopPositionTenure t2 " + endString;
                t4 = "SELECT t4 FROM CoopPositionTenure t2 " + endString;
                t5 = "SELECT t5 FROM CoopPositionTenure t2 " + endString;
                t6 = "SELECT CONCAT(t6,'-',t7) FROM CoopPositionTenure t2 " + endString;
                t8 = "SELECT t8 FROM CoopPositionTenure t2 " + endString;
                CoopPositionTenureM = entityManagerFactory.createEntityManager().createQuery(t2);
                setPositionTenureList(CoopPositionTenureM.getResultList());
                if (getPositionTenureList().size() > 0) {
                    for (int i = 0; i != getPositionTenureList().size(); i++) {
                        if (getMode().isEditMode()) {
                            a = (CoopPositionTenure) entityManagerFactory.createEntityManager().createQuery(t2).getResultList().get(0);
                            c = (CoopMemOuPos) entityManagerFactory.createEntityManager().createQuery(t5).getResultList().get(0);
                            positionTenureREST.remove(a);
                            memOuPosREST.remove(c);
                        } else {
                            a = (CoopPositionTenure) entityManagerFactory.createEntityManager().createQuery(t2).getResultList().get(i);
                            b = (CoopTenure) entityManagerFactory.createEntityManager().createQuery(t1).getResultList().get(i);
                            c = (CoopMemOuPos) entityManagerFactory.createEntityManager().createQuery(t5).getResultList().get(i);
                            d = (String) entityManagerFactory.createEntityManager().createQuery(t6).getResultList().get(i);
                            e = (CoopOrgPosition) entityManagerFactory.createEntityManager().createQuery(t8).getResultList().get(i);
                            if (i == 0) {
                                getPositionTenureAL().set(i, a);
                                getTenureAL().set(i, b);
                                getMemOuPosAL().set(i, c);
                                getStringAL().set(i, d);
                                getOrgPositionAL().set(i, e);
                            } else {
                                getPositionTenureAL().add(a);
                                getTenureAL().add(b);
                                getMemOuPosAL().add(c);
                                getStringAL().add(d);
                                getOrgPositionAL().add(e);
                            }
                        }
                    }
                }
            }
        } catch (Exception exception) {
            System.out.print("PositionTenureData.java selectedPositionTenureLoad()-1 " + exception);
        }
        try {
            if (getMembershipType().getTypeValue().equals(1)) {

            }
        } catch (Exception exception) {
            System.out.print("PositionTenureData.java selectedPositionTenureLoad()-2 " + exception);
        }
    }

}
