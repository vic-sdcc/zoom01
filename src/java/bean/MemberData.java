/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import com.liferay.portal.service.OrganizationLocalServiceUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import model.CoopMember;
import model.Organization;
import service.CoopMemberUserFacadeREST;
import service.CoopOrgUnitFacadeREST;

/**
 *
 * @author mis05
 */
@ManagedBean
@SessionScoped
public class MemberData implements Serializable {

    /**
     * Creates a new instance of MemberData
     */
    public MemberData() {
        newSet();
    }

    @PersistenceUnit
    EntityManagerFactory entityManagerFactory;
    @EJB
    private CoopMemberUserFacadeREST coopMemberUserFacadeREST;
    @EJB
    private CoopOrgUnitFacadeREST coopOrgUnitFacadeREST;
    private CoopMember member, selectedMember;
    private List<CoopMember> memberList, memberList2, memberViewList;
    private DataModel<CoopMember> memberModel;
    @ManagedProperty(value = "#{currentDate}")
    private Date currentDate;
    @ManagedProperty(value = "#{orgUnitData}")
    private OrgUnitData orgUnitData;
    @ManagedProperty(value = "#{memStatusData}")
    private MemStatusData memStatusData;
    @ManagedProperty(value = "#{applicantData}")
    private ApplicantData applicantData;
    @ManagedProperty(value = "#{memberProfile}")
    private memberProfile memberProfile;
    private String memIdNo, scAcctNo;

    /*
     * getter setter
     */
    public CoopMember getMember() {
        if (member == null) {
            member = new CoopMember();
        }
        return member;
    }

    public void setMember(CoopMember member) {
        this.member = member;
    }

    public CoopMember getSelectedMember() {
        if (selectedMember == null) {
            selectedMember = new CoopMember();
        }
        return selectedMember;
    }

    public void setSelectedMember(CoopMember selectedMember) {
        this.selectedMember = selectedMember;
    }

    public List<CoopMember> getMemberList() {
        memberList = entityManagerFactory.createEntityManager().createQuery("SELECT m FROM CoopMember m ORDER BY m.memNo").getResultList();
        return memberList;
    }

    public void setMemberList(List<CoopMember> memberList) {
        this.memberList = memberList;
    }

    public List<CoopMember> getMemberList2() {
        memberList2 = entityManagerFactory.createEntityManager().createQuery("SELECT m FROM CoopMember m ORDER BY m.scAcctno").getResultList();
        return memberList2;
    }

    public void setMemberList2(List<CoopMember> memberList2) {
        this.memberList2 = memberList2;
    }

    public List<CoopMember> getMemberViewList() {
        if (memberViewList == null) {
            memberViewList = new ArrayList<>();
        }
        return memberViewList;
    }

    public void setMemberViewList(List<CoopMember> memberViewList) {
        this.memberViewList = memberViewList;
    }

    public DataModel<CoopMember> getMemberModel() {
        if (memberModel == null) {
            memberModel = new ListDataModel<>(getMemberList());
        }
        return memberModel;
    }

    public void setMemberModel(DataModel<CoopMember> memberModel) {
        this.memberModel = memberModel;
    }

    public Date getCurrentDate() {
        if (currentDate == null) {
            currentDate = new Date();
        }
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public OrgUnitData getOrgUnitData() {
        if (orgUnitData == null) {
            orgUnitData = new OrgUnitData();
        }
        return orgUnitData;
    }

    public void setOrgUnitData(OrgUnitData orgUnitData) {
        this.orgUnitData = orgUnitData;
    }

    public MemStatusData getMemStatusData() {
        if (memStatusData == null) {
            memStatusData = new MemStatusData();
        }
        return memStatusData;
    }

    public void setMemStatusData(MemStatusData memStatusData) {
        this.memStatusData = memStatusData;
    }

    public ApplicantData getApplicantData() {
        if (applicantData == null) {
            applicantData = new ApplicantData();
        }
        return applicantData;
    }

    public void setApplicantData(ApplicantData applicantData) {
        this.applicantData = applicantData;
    }

    public memberProfile getMemberProfile() {
        if (memberProfile == null) {
            memberProfile = new memberProfile();
        }
        return memberProfile;
    }

    public void setMemberProfile(memberProfile memberProfile) {
        this.memberProfile = memberProfile;
    }

    public String getMemIdNo() {
        return memIdNo;
    }

    public void setMemIdNo(String memIdNo) {
        this.memIdNo = memIdNo;
    }

    public String getScAcctNo() {
        return scAcctNo;
    }

    public void setScAcctNo(String scAcctNo) {
        this.scAcctNo = scAcctNo;
    }

    /*
     * methods
     */
    public void beanclear() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("memberData", null);
    }

    public void newSet() {
        getMember().setMemDate(getCurrentDate());
        getMember().setStatusDate(getCurrentDate());
        getMember().setPersonStatus(true);
        getMember().setNationality(true);
        getMember().setMemberType(getApplicantData().getApplicant().getApplicantType());
        getMemStatusData().getMemberStatus().setStatusId(1);
        getMember().setStatusId(getMemStatusData().getMemberStatus());
        getMember().setMemStatRem("NEW MEMBER");
        getMember().setOwnedBusiness(false);
    }

    public String generateMemIdNo() {
        memIdNo = String.format("%06d", getMemberModel().getRowCount() + 4);
        memIdNo = (getCurrentDate().getYear() + 1900) + "-" + memIdNo;
        return memIdNo;
    }

    public String generateScAcctNo() {
        scAcctNo = getMemberList2().get(getMemberList2().size() - 1).getScAcctno();
        String baseStr = scAcctNo.substring(5), midStr = scAcctNo.substring(3, 5), blockStr = scAcctNo.substring(0, 2);
        Integer baseInt, midInt, blockInt;
        //entry point 000 - 499
        //value produced 001 - 500
        if (Integer.parseInt(baseStr) >= 000 && Integer.parseInt(baseStr) < 500) {
            if (Integer.parseInt(baseStr) == 000) {
                blockInt = Integer.parseInt(blockStr) + 1;
                blockStr = blockInt.toString();
            }
            baseInt = Integer.parseInt(baseStr) + 1;
            baseStr = String.format("%03d", baseInt);
        } //entry point 500 - 998
        //value produced 501 - 999
        else if (Integer.parseInt(baseStr) >= 500 && Integer.parseInt(baseStr) < 999) {
            if (Integer.parseInt(baseStr) == 500) {
                blockInt = Integer.parseInt(blockStr) + 1;
                blockStr = blockInt.toString();
            }
            baseInt = Integer.parseInt(baseStr) + 1;
            baseStr = String.format("%03d", baseInt);
        } else if (Integer.parseInt(baseStr) == 999) {
            baseStr = "000";
            midInt = Integer.parseInt(midStr) + 1;
            midStr = midInt.toString();
        }
        scAcctNo = blockStr + "-" + midStr + baseStr;
        return scAcctNo;
    }

    public void memberLoad() {
        getMember().setMemDate(getMember().getMemDate());
        getMember().setMemNo(generateMemIdNo());
        getMember().setMemIdNo(generateMemIdNo());
        getMember().setScAcctno(generateScAcctNo());
        getMember().setOuCode(getApplicantData().getApplicant().getOuCode());
        getMember().setStatusDate(getMember().getStatusDate());
        getMemStatusData().getMemberStatus().setStatusId(1);
        getMember().setStatusId(getMemStatusData().getMemberStatus());
        getMember().setMemStatRem(getMember().getMemStatRem());
        getMember().setMemberType(getApplicantData().getApplicant().getApplicantType());
        getMember().setPPrefix(getMember().getPPrefix());
        getMember().setLastName(getApplicantData().getApplicant().getLastName());
        getMember().setFirstName(getApplicantData().getApplicant().getFirstName());
        getMember().setMiddleName(getApplicantData().getApplicant().getMiddleName());
        getMember().setSuffix(getMember().getSuffix());
        getMember().setNickname(getMember().getNickname());
        getMember().setHeight(getMember().getHeight());
        getMember().setWeight(getMember().getWeight());
        getMember().setBlood(getMember().getBlood());
        getMember().setGender(getApplicantData().getApplicant().getGender());
        getMember().setCivilStatus(getApplicantData().getApplicant().getCivilStatus().toString());
        getMember().setPersonStatus(getMember().getPersonStatus());
        getMember().setBirthdate(getApplicantData().getApplicant().getBirthdate());
        getMember().setBirthplace(getMember().getBirthplace());
        getMember().setReligion(getMember().getReligion());
        getMember().setNationality(getApplicantData().getApplicant().getNationality());
        getMember().setTaxIdNo(getMember().getTaxIdNo());
        getMember().setPagIbig(getMember().getPagIbig());
        getMember().setPhilhealth(getMember().getPhilhealth());
        getMember().setSss(getMember().getSss());
        getMember().setGsis(getMember().getGsis());
        getMember().setResidenceType(getMember().getResidenceType());
        getMember().setStreet(getApplicantData().getApplicant().getStreet());
        getMember().setBarangay(getApplicantData().getApplicant().getBarangay());
        getMember().setCityMun(getApplicantData().getApplicant().getCityMun());
        getMember().setRegion(getApplicantData().getApplicant().getRegion());
        getMember().setProvince(getApplicantData().getApplicant().getProvince());
        getMember().setZipCode(getMember().getZipCode());
        getMember().setEmail(getApplicantData().getApplicant().getEmail());
        getMember().setContactNumber(getApplicantData().getApplicant().getContactNumber());
        getMember().setPreoccupation(getApplicantData().getApplicant().getOccupation());
        getMember().setOwnedBusiness(getMember().getOwnedBusiness());
        getMember().setNotifyName(getMember().getNotifyName());
        getMember().setNotifyAddress(getMember().getNotifyAddress());
        getMember().setNotifyPhone(getMember().getNotifyPhone());
        getMember().setNotifyRelation(getMember().getNotifyRelation());
    }

    public String withdrawnGenerateMemIdNo(String m) {
        memIdNo = m;
        String temp = m.substring(5);
        memIdNo = (getCurrentDate().getYear() + 1900) + "-" + temp;
        return memIdNo;
    }

    public void editOu() {
        getOrgUnitData().getOrgUnit().setOuCode(getMember().getOuCode().getOuCode());
        getMember().setOuCode(getOrgUnitData().getOrgUnit());
        try {
            for (Integer x = 0; x != OrganizationLocalServiceUtil.getUserOrganizations(
                    coopMemberUserFacadeREST.find(getMember().getMemNo()).getUserId().longValue()).size(); x++) {
                //Delete organization with name that starts with "PT" 
                if (OrganizationLocalServiceUtil.getOrganization(OrganizationLocalServiceUtil.getUserOrganizations(coopMemberUserFacadeREST.find(
                        getMember().getMemNo()).getUserId().longValue()).get(x).getOrganizationId()).getName().startsWith("PT")) {
                    OrganizationLocalServiceUtil.deleteUserOrganization(
                            coopMemberUserFacadeREST.find(getMember().getMemNo()).getUserId().longValue(),
                            OrganizationLocalServiceUtil.getUserOrganizations(
                                    coopMemberUserFacadeREST.find(getMember().getMemNo()).getUserId().longValue()).get(x).getOrganizationId());
                }
            }
        } catch (Exception e) {
            System.out.println("Delete Organization: " + e);
        }
        try {
            Organization q = (Organization) entityManagerFactory.createEntityManager().createQuery("SELECT c FROM Organization c "
                    + "WHERE c.name ='" + coopOrgUnitFacadeREST.find(getMember().getOuCode().getOuCode()).getOuShortName() + "'").getResultList().get(0);
            OrganizationLocalServiceUtil.addUserOrganization(
                    coopMemberUserFacadeREST.find(getMember().getMemNo()).getUserId().longValue(), q.getOrganizationid());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void editMemberStatus() {
        if (!(getMemberProfile().getStatusIdTemp()
                .equals(getMember().getStatusId().getStatusId()))) {
            getMemStatusData().getMemberStatus().setStatusId(getMember().getStatusId().getStatusId());
            getMember().setStatusId(getMemStatusData().getMemberStatus());
            getMember().setMemStatRem(getMember().getMemStatRem());
            getMember().setStatusDate(getCurrentDate());
            if (getMemberProfile().getStatusIdTemp() == 4
                    && (getMember().getStatusId().getStatusId() == 1
                    || getMember().getStatusId().getStatusId() == 2
                    || getMember().getStatusId().getStatusId() == 3
                    || getMember().getStatusId().getStatusId() == 6
                    || getMember().getStatusId().getStatusId() == 7
                    || getMember().getStatusId().getStatusId() == 9
                    || getMember().getStatusId().getStatusId() == 11)) {
                getMember().setMemIdNo(withdrawnGenerateMemIdNo(getMember().getMemIdNo()));
                getMemStatusData().getMemberStatus().setStatusId(getMember().getStatusId().getStatusId());
                getMember().setStatusId(getMemStatusData().getMemberStatus());
                getMember().setMemStatRem(getMember().getMemStatRem());
                getMember().setScAcctno(generateScAcctNo());
            }
        }
    }

}
