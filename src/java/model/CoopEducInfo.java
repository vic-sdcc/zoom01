/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mis05
 */
@Entity
@Table(name = "coop_educ_info")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CoopEducInfo.findAll", query = "SELECT c FROM CoopEducInfo c"),
    @NamedQuery(name = "CoopEducInfo.findByEducInfoNo", query = "SELECT c FROM CoopEducInfo c WHERE c.educInfoNo = :educInfoNo"),
    @NamedQuery(name = "CoopEducInfo.findBySchoolName", query = "SELECT c FROM CoopEducInfo c WHERE c.schoolName = :schoolName"),
    @NamedQuery(name = "CoopEducInfo.findBySchoolLevel", query = "SELECT c FROM CoopEducInfo c WHERE c.schoolLevel = :schoolLevel"),
    @NamedQuery(name = "CoopEducInfo.findByCourse", query = "SELECT c FROM CoopEducInfo c WHERE c.course = :course"),
    @NamedQuery(name = "CoopEducInfo.findByGraduated", query = "SELECT c FROM CoopEducInfo c WHERE c.graduated = :graduated"),
    @NamedQuery(name = "CoopEducInfo.findByAcctno", query = "SELECT c FROM CoopEducInfo c WHERE c.acctno = :acctno"),
    @NamedQuery(name = "CoopEducInfo.findByYearLastAttended", query = "SELECT c FROM CoopEducInfo c WHERE c.yearLastAttended = :yearLastAttended"),
    @NamedQuery(name = "CoopEducInfo.findByEducInfoNum", query = "SELECT c FROM CoopEducInfo c WHERE c.educInfoNum = :educInfoNum")})
public class CoopEducInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "educ_info_no")
    private Integer educInfoNo;
    @Size(max = 2147483647)
    @Column(name = "school_name")
    private String schoolName;
    @Size(max = 2147483647)
    @Column(name = "school_level")
    private String schoolLevel;
    @Size(max = 2147483647)
    @Column(name = "course")
    private String course;
    @Column(name = "graduated")
    private Boolean graduated;
    @Size(max = 2147483647)
    @Column(name = "acctno")
    private String acctno;
    @Size(max = 2147483647)
    @Column(name = "year_last_attended")
    private String yearLastAttended;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "educ_info_num")
    private Integer educInfoNum;
    @OneToMany(mappedBy = "educInfoNum")
    private Collection<CoopEducInfoAssoc> coopEducInfoAssocCollection;
    @OneToMany(mappedBy = "educInfoNum")
    private Collection<CoopEducInfoMem> coopEducInfoMemCollection;

    public CoopEducInfo() {
    }

    public CoopEducInfo(Integer educInfoNum) {
        this.educInfoNum = educInfoNum;
    }

    public Integer getEducInfoNo() {
        return educInfoNo;
    }

    public void setEducInfoNo(Integer educInfoNo) {
        this.educInfoNo = educInfoNo;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSchoolLevel() {
        return schoolLevel;
    }

    public void setSchoolLevel(String schoolLevel) {
        this.schoolLevel = schoolLevel;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public Boolean getGraduated() {
        return graduated;
    }

    public void setGraduated(Boolean graduated) {
        this.graduated = graduated;
    }

    public String getAcctno() {
        return acctno;
    }

    public void setAcctno(String acctno) {
        this.acctno = acctno;
    }

    public String getYearLastAttended() {
        return yearLastAttended;
    }

    public void setYearLastAttended(String yearLastAttended) {
        this.yearLastAttended = yearLastAttended;
    }

    public Integer getEducInfoNum() {
        return educInfoNum;
    }

    public void setEducInfoNum(Integer educInfoNum) {
        this.educInfoNum = educInfoNum;
    }

    @XmlTransient
    public Collection<CoopEducInfoAssoc> getCoopEducInfoAssocCollection() {
        return coopEducInfoAssocCollection;
    }

    public void setCoopEducInfoAssocCollection(Collection<CoopEducInfoAssoc> coopEducInfoAssocCollection) {
        this.coopEducInfoAssocCollection = coopEducInfoAssocCollection;
    }

    @XmlTransient
    public Collection<CoopEducInfoMem> getCoopEducInfoMemCollection() {
        return coopEducInfoMemCollection;
    }

    public void setCoopEducInfoMemCollection(Collection<CoopEducInfoMem> coopEducInfoMemCollection) {
        this.coopEducInfoMemCollection = coopEducInfoMemCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (educInfoNum != null ? educInfoNum.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoopEducInfo)) {
            return false;
        }
        CoopEducInfo other = (CoopEducInfo) object;
        if ((this.educInfoNum == null && other.educInfoNum != null) || (this.educInfoNum != null && !this.educInfoNum.equals(other.educInfoNum))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.CoopEducInfo[ educInfoNum=" + educInfoNum + " ]";
    }
    
}
