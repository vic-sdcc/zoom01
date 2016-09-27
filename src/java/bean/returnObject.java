/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.portlet.RenderRequest;
import model.CoopEducInfo;
import model.CoopMember;
import model.CoopMemberStatus;
import model.CoopOrgUnit;
import model.CoopSkills;

/**
 *
 * @author vic
 */
@ManagedBean
@RequestScoped
public class returnObject implements Serializable {

    @PersistenceUnit
    EntityManagerFactory emf;

    public returnObject() {
        //CoopProspect q = (CoopProspect) emf.createEntityManager().createQuery("SELECT c FROM CoopProspect c").getResultList().get(0);
        //FacesContext context = FacesContext.getCurrentInstance();
        //context.addMessage(null, new FacesMessage("Successful", "New data has been created."));
    }

    public List<CoopOrgUnit> ouList() {
        return emf.createEntityManager().createQuery("SELECT o FROM CoopOrgUnit o WHERE o.ouShortName LIKE 'PT%' AND o.dateDissolved IS NULL OR o.ouShortName = 'NO DATA' ORDER BY o.ouShortName").getResultList();
        //return emf.createEntityManager().createQuery("SELECT c FROM CoopOrgUnit c WHERE c.ouShortName LIKE 'PT%'").getResultList();
    }
    
    public CoopOrgUnit ouCode(String ouCode) {
        return (CoopOrgUnit) emf.createEntityManager().createQuery("SELECT o FROM CoopOrgUnit o WHERE o.ouCode ='" + ouCode + "'").getResultList().get(0);
        //return emf.createEntityManager().createQuery("SELECT c FROM CoopOrgUnit c WHERE c.ouShortName LIKE 'PT%'").getResultList();
    }

    public List<CoopMemberStatus> memStatList() {
        return emf.createEntityManager().createQuery("SELECT c FROM CoopMemberStatus c").getResultList();
    }

    public List<String> memOccupationList() {
        return emf.createEntityManager().createQuery("SELECT DISTINCT(c.preoccupation) FROM CoopMember c WHERE c.preoccupation IS NOT NULL").getResultList();
    }

    public List<CoopMember> memList(memberDataComp memData) {
        try {
            if (memData.getMemNo() != null || memData.getLastName() != null || memData.getFirstName() != null || memData.getMiddleName() != null) {
                String query = "SELECT c FROM CoopMember c WHERE c.memNo = c.memNo ";
                if (memData.getMemNo() != null) {
                    query += "AND c.memNo ='" + memData.getMemNo() + "' ";
                }
                if (memData.getLastName() != null) {
                    query += "AND c.lastName ='" + memData.getLastName() + "' ";
                }
                if (memData.getFirstName() != null) {
                    query += "AND c.firstName ='" + memData.getFirstName() + "' ";
                }
                if (memData.getMiddleName() != null) {
                    query += "AND c.middleName ='" + memData.getMiddleName() + "' ";
                }
                query += "ORDER BY c.memNo";
                return emf.createEntityManager().createQuery(query).getResultList();
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public String initCap(String line) {
        return Character.toUpperCase(line.charAt(0)) + line.substring(1).toLowerCase();
    }

    public String skillList(List<CoopSkills> list) {
        String str = "";

        if (list.size() > 0) {
            str = list.get(0).getSkillsName();
        }

        if (list.size() > 1) {
            for (int i = 1; i != list.size(); i++) {
                str += ", ";
                str += list.get(i).getSkillsName();
            }
        }

        return str;
    }

    public String fullname(String pprefix, String lastName, String firstName, String middleName, String suffix) {
        String str = "";

        if (pprefix != null) {
            str += pprefix + " ";
        }
        if (firstName != null) {
            str += firstName + " ";
        }
        if (middleName != null) {
            str += middleName + " ";
        }
        if (lastName != null) {
            str += lastName + " ";
        }
        if (suffix != null) {
            str += suffix;
        }

        return str;
    }

    public String educInfo(CoopEducInfo educInfo) {
        String str = "";

        //School Name
        if (educInfo.getSchoolName() != null) {
            str += educInfo.getSchoolName();
        } else {
            str += "-";
        }
        str += ";";

        //School Level
        if (educInfo.getSchoolLevel() != null) {
            str += educInfo.getSchoolLevel();
        } else {
            str += "-";
        }
        str += ";";

        //Course
        if (educInfo.getCourse() != null) {
            str += educInfo.getCourse();
        } else {
            str += "-";
        }
        str += ";";

        //Graduated
        if (educInfo.getGraduated() != null) {
            if (educInfo.getGraduated()) {
                str += "GRADUATED";
            } else {
                str += "UNDER-GRADUATE";
            }
        } else {
            str += "UNDER-GRADUATE";
        }
        str += ";";

        //Last year attended
        if (educInfo.getYearLastAttended() != null) {
            str += educInfo.getYearLastAttended();
        } else {
            str += "-";
        }

        return str;
    }

    public String liferayUser() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        RenderRequest renderRequest = (RenderRequest) facesContext.getExternalContext().getRequest();
        Map userInfo = (Map) renderRequest.getAttribute(RenderRequest.USER_INFO);
        return userInfo.get("liferay.user.id").toString();
    }

    public String memberUser(String liferayUserId) {
        try {
            CoopMember member = (CoopMember) emf.createEntityManager().createQuery("SELECT c FROM CoopMember c, CoopMemberUser d "
                    + "WHERE c.memNo = d.memNo and d.userId =" + liferayUserId + "").getResultList().get(0);
            return fullname(member.getPPrefix(), member.getLastName(), member.getFirstName(), member.getMiddleName(), member.getSuffix());
        } catch (Exception e) {
        }
        return "";
    }
    
    public String getPercentage(float value) {
        return String.format("%.2f", value*100) + "%";
    }
}
