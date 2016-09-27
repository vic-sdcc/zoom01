/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import model.CoopAssociate;
import model.CoopMember;

/**
 *
 * @author vic
 */
@ManagedBean
@RequestScoped
public class generateData {

    private dateFormat dFormat;
    private Date date;
    @PersistenceUnit
    EntityManagerFactory emf;

    /**
     * Creates a new instance of generateData
     */
    public generateData() {

    }

    public List<CoopMember> memberList(memberFilterData memberValue, sortOrderString sortOrder) throws ParseException {
        String memberQuery = "";
        memberQuery += "SELECT c FROM CoopMember c WHERE c.memNo = c.memNo ";

        if (memberValue.getPersonStat() != null) {
            memberQuery += "and c.personStatus = " + memberValue.getPersonStat() + " ";
        }

        if (memberValue.getOuCodeID() != null) {
            memberQuery += "and c.ouCode.ouCode ='" + memberValue.getOuCodeID() + "' ";
        }

        if (memberValue.getStatus() != null) {
            memberQuery += "and c.memStatus ='" + memberValue.getStatus() + "' ";
        }

        if ((memberValue.getMembershipDateFrom() != null && memberValue.getMembershipDateTo() != null)
                || (memberValue.getMembershipDateFrom() != null && memberValue.getMembershipDateTo() == null)) {
            if (memberValue.getMembershipDateTo() == null) {
                //memberValue.setMembershipDateTo(getToday());
                memberQuery += "and c.memDate = '" + getdFormat().formatDate(memberValue.getMembershipDateFrom(), "yyyy-MM-dd") + "' ";
            } else {
                memberQuery += "and c.memDate >= '" + getdFormat().formatDate(memberValue.getMembershipDateFrom(), "yyyy-MM-dd") + "' "
                        + "and c.memDate <= '" + getdFormat().formatDate(memberValue.getMembershipDateTo(), "yyyy-MM-dd") + "' ";
            }
        }

        if (memberValue.getOccupation() != null) {
            memberQuery += "and c.preoccupation = '" + memberValue.getOccupation() + "' ";
        }

        if (memberValue.getRankPos() != null) {
            memberQuery += "and c.memNo IN (SELECT j.memNo.memNo FROM CoopEmplDtlMem j WHERE UPPER(j.emplDtlNum.emplRankPos.unitName) LIKE UPPER('%" + memberValue.getRankPos() + "%')) ";
        }

        if ((memberValue.getAgeFrom() != null && memberValue.getAgeTo() != null)
                || (memberValue.getAgeFrom() != null && memberValue.getAgeTo() == null)) {
            if (memberValue.getAgeTo() == null || memberValue.getAgeTo() == 0) {
                memberQuery += "and ((:currentDate-c.birthdate)/" + getdFormat().getMaxDateOfYear(getdFormat().dateMonth(getToday()), getdFormat().dateMonth(getToday())) + ") = " + memberValue.getAgeFrom() + " ";
            } else {
                //memberQuery += "and FUNC('to_char', c.birthdate, 'YYYY') >= '" + getdFormat().formatDate(getdFormat().addYear(getToday(), -memberValue.getAgeTo()), "YYYY") + "' "
                //    + "and FUNC('to_char', c.birthdate, 'YYYY')  <= '" + getdFormat().formatDate(getdFormat().addYear(getToday(), -memberValue.getAgeFrom()), "YYYY") + "' ";
                //memberQuery += "and c.birthdate >= '" + getdFormat().formatDate(getdFormat().addYear(getToday(), -memberValue.getAgeTo()), "yyyy-MM-dd") + "' "
                //        + "and c.birthdate <= '" + getdFormat().formatDate(getdFormat().addYear(getToday(), -memberValue.getAgeFrom()), "yyyy-MM-dd") + "' ";
                memberQuery += "and ((:currentDate-c.birthdate)/" + getdFormat().getMaxDateOfYear(getdFormat().dateMonth(getToday()), getdFormat().dateMonth(getToday())) + ") >= " + memberValue.getAgeFrom() + " "
                        + "and ((:currentDate-c.birthdate)/" + getdFormat().getMaxDateOfYear(getdFormat().dateMonth(getToday()), getdFormat().dateMonth(getToday())) + ") <= " + memberValue.getAgeTo() + " ";
            }
        }

        if (memberValue.getCompensation() != null) {
            memberQuery += "and c.memNo IN (SELECT n.memNo.memNo FROM CoopEmplDtlMem n WHERE n.emplDtlNum.compBracket = '" + memberValue.getCompensation() + "') ";
        }

        if (memberValue.getSkill() != null) {
            memberQuery += "and c.memNo IN (SELECT x.memNo.memNo FROM CoopSkillsMem x WHERE UPPER(x.skillsCode.skillsName) LIKE UPPER('%" + memberValue.getSkill() + "%')) ";
        }

        if (memberValue.getGender() != null) {
            memberQuery += "and c.gender = '" + memberValue.getGender() + "' ";
        }

        if (memberValue.isCurrent()) {
            memberQuery += "and c.statusId.memStatus <> 'W' and c.statusId.memStatus <> 'E' and c.statusId.memStatus <> 'D' and c.statusId.memStatus <> 'X' ";
        }

        memberQuery += "ORDER BY ";

        if (sortOrder.getOrderByStr().isEmpty()) {
            memberQuery += "c.memNo";
        } else {
            for (int i = 0; i != sortOrder.getOrderByStr().size(); i++) {
                memberQuery += sortOrder.getOrderByStr().get(i);
                if (sortOrder.getOrderByStr().size() - 1 != i) {
                    memberQuery += ", ";
                }
            }
        }

        if (!"Associate".equals(memberValue.getMembershipType())) {
            System.out.println("Reg query");
            //"SELECT c FROM CoopMember c WHERE FUNC('to_char', c.birthdate, 'YYYY') = '1994'"
            if (memberValue.getAgeFrom() != null) {
                return emf.createEntityManager().createQuery(memberQuery).setParameter("currentDate", getdFormat().parseStringDate(getdFormat().formatDate(getToday(), "yyyy-MM-dd"), "yyyy-MM-dd")).getResultList();
            } else {
                return emf.createEntityManager().createQuery(memberQuery).getResultList();
            }
        }
        return null;
    }

    public List<CoopAssociate> associateList(memberFilterData memberValue, sortOrderString sortOrder) throws ParseException {
        String memberQuery = "";
        memberQuery += "SELECT c FROM CoopAssociate c WHERE c.associateNo = c.associateNo ";

        if (memberValue.getPersonStat() != null) {
            memberQuery += "and c.personStatus = " + memberValue.getPersonStat() + " ";
        }

        if (memberValue.getOuCodeID() != null) {
            memberQuery += "and c.ouCode.ouCode ='" + memberValue.getOuCodeID() + "' ";
        }

        if (memberValue.getStatus() != null) {
            memberQuery += "and c.memStatus ='" + memberValue.getStatus() + "' ";
        }

        if ((memberValue.getMembershipDateFrom() != null && memberValue.getMembershipDateTo() != null)
                || (memberValue.getMembershipDateFrom() != null && memberValue.getMembershipDateTo() == null)) {
            if (memberValue.getMembershipDateTo() == null) {
                //memberValue.setMembershipDateTo(getToday());
                memberQuery += "and c.memDate = '" + getdFormat().formatDate(memberValue.getMembershipDateFrom(), "yyyy-MM-dd") + "' ";
            } else {
                memberQuery += "and c.memDate >= '" + getdFormat().formatDate(memberValue.getMembershipDateFrom(), "yyyy-MM-dd") + "' "
                        + "and c.memDate <= '" + getdFormat().formatDate(memberValue.getMembershipDateTo(), "yyyy-MM-dd") + "' ";
            }
        }

        if (memberValue.getOccupation() != null) {
            memberQuery += "and c.preoccupation = '" + memberValue.getOccupation() + "' ";
        }

        if (memberValue.getRankPos() != null) {
            memberQuery += "and c.associateNo IN (SELECT j.associateNo.associateNo FROM CoopEmplDtlAssoc j WHERE UPPER(j.emplDtlNum.emplRankPos.unitName) LIKE UPPER('%" + memberValue.getRankPos() + "%')) ";
        }

        if ((memberValue.getAgeFrom() != null && memberValue.getAgeTo() != null)
                || (memberValue.getAgeFrom() != null && memberValue.getAgeTo() == null)) {
            if (memberValue.getAgeTo() == null || memberValue.getAgeTo() == 0) {
                memberQuery += "and ((:currentDate-c.birthdate)/" + getdFormat().getMaxDateOfYear(getdFormat().dateMonth(getToday()), getdFormat().dateMonth(getToday())) + ") = " + memberValue.getAgeFrom() + " ";
            } else {
                //memberQuery += "and FUNC('to_char', c.birthdate, 'YYYY') >= '" + getdFormat().formatDate(getdFormat().addYear(getToday(), -memberValue.getAgeTo()), "YYYY") + "' "
                //    + "and FUNC('to_char', c.birthdate, 'YYYY')  <= '" + getdFormat().formatDate(getdFormat().addYear(getToday(), -memberValue.getAgeFrom()), "YYYY") + "' ";
                //memberQuery += "and c.birthdate >= '" + getdFormat().formatDate(getdFormat().addYear(getToday(), -memberValue.getAgeTo()), "yyyy-MM-dd") + "' "
                //        + "and c.birthdate <= '" + getdFormat().formatDate(getdFormat().addYear(getToday(), -memberValue.getAgeFrom()), "yyyy-MM-dd") + "' ";
                memberQuery += "and ((:currentDate-c.birthdate)/" + getdFormat().getMaxDateOfYear(getdFormat().dateMonth(getToday()), getdFormat().dateMonth(getToday())) + ") >= " + memberValue.getAgeFrom() + " "
                        + "and ((:currentDate-c.birthdate)/" + getdFormat().getMaxDateOfYear(getdFormat().dateMonth(getToday()), getdFormat().dateMonth(getToday())) + ") <= " + memberValue.getAgeTo() + " ";
            }
        }

        if (memberValue.getCompensation() != null) {
            memberQuery += "and c.associateNo IN (SELECT n.associateNo.associateNo FROM CoopEmplDtlAssoc n WHERE n.emplDtlNum.compBracket = '" + memberValue.getCompensation() + "') ";
        }

        if (memberValue.getSkill() != null) {
            memberQuery += "and c.associateNo IN (SELECT x.associateNo.associateNo FROM CoopSkillsAssoc x WHERE UPPER(x.skillsCode.skillsName) LIKE UPPER('%" + memberValue.getSkill() + "%')) ";
        }

        if (memberValue.getGender() != null) {
            memberQuery += "and c.gender = '" + memberValue.getGender() + "' ";
        }

        if (memberValue.isCurrent()) {
            memberQuery += "and c.statusId.memStatus <> 'W' and c.statusId.memStatus <> 'E' and c.statusId.memStatus <> 'D' and c.statusId.memStatus <> 'X' ";
        }

        memberQuery += "ORDER BY ";

        if (sortOrder.getOrderByStr().isEmpty()) {
            memberQuery += "c.memNo";
        } else {
            for (int i = 0; i != sortOrder.getOrderByStr().size(); i++) {
                memberQuery += sortOrder.getOrderByStr().get(i);
                if (sortOrder.getOrderByStr().size() - 1 != i) {
                    memberQuery += ", ";
                }
            }
        }

        if (!"Regular".equals(memberValue.getMembershipType())) {
            System.out.println("Assoc query");
            //"SELECT c FROM CoopMember c WHERE FUNC('to_char', c.birthdate, 'YYYY') = '1994'"
            if (memberValue.getAgeFrom() != null) {
                return emf.createEntityManager().createQuery(memberQuery).setParameter("currentDate", getdFormat().parseStringDate(getdFormat().formatDate(getToday(), "yyyy-MM-dd"), "yyyy-MM-dd")).getResultList();
            } else {
                return emf.createEntityManager().createQuery(memberQuery).getResultList();
            }
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

    public Date getToday() {
        if (date == null) {
            date = new Date();
        }
        return date;
    }

    public void setToday(Date date) {
        this.date = date;
    }
}
