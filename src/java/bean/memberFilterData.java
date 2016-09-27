/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author vic
 */
@ManagedBean
@SessionScoped
public class memberFilterData {

    private String ouCodeID;
    private Character Status;
    private Date membershipDateFrom;
    private Date membershipDateTo;
    private String occupation;
    private Integer ageFrom;
    private Integer ageTo;
    private String compensation;
    private String personStat;
    private String membershipType;
    private String skill;
    private String rankPos;
    private String gender;
    private boolean current;

    /**
     * Creates a new instance of memberFilterData
     */
    public memberFilterData() {
    }

    public void beanclear() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("memberFilterData", null);
        FacesContext.getCurrentInstance().getViewRoot().getViewMap().clear();
    }

    public String getOuCodeID() {
        return ouCodeID;
    }

    public void setOuCodeID(String ouCodeID) {
        this.ouCodeID = ouCodeID;
    }

    public Character getStatus() {
        return Status;
    }

    public void setStatus(Character Status) {
        this.Status = Status;
    }

    public Date getMembershipDateFrom() {
        return membershipDateFrom;
    }

    public void setMembershipDateFrom(Date membershipDateFrom) {
        this.membershipDateFrom = membershipDateFrom;
    }

    public Date getMembershipDateTo() {
        return membershipDateTo;
    }

    public void setMembershipDateTo(Date membershipDateTo) {
        this.membershipDateTo = membershipDateTo;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public Integer getAgeFrom() {
        return ageFrom;
    }

    public void setAgeFrom(Integer ageFrom) {
        this.ageFrom = ageFrom;
    }

    public Integer getAgeTo() {
        return ageTo;
    }

    public void setAgeTo(Integer ageTo) {
        this.ageTo = ageTo;
    }

    public String getCompensation() {
        return compensation;
    }

    public void setCompensation(String compensation) {
        this.compensation = compensation;
    }

    public String getPersonStat() {
        return personStat;
    }

    public void setPersonStat(String personStat) {
        this.personStat = personStat;
    }

    public String getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getRankPos() {
        return rankPos;
    }

    public void setRankPos(String rankPos) {
        this.rankPos = rankPos;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean isCurrent() {
        return current;
    }

    public void setCurrent(boolean current) {
        this.current = current;
    }

}
