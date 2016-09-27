/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mis05
 */
@Entity
@Table(name = "coop_member_applicant")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CoopMemberApplicant.findAll", query = "SELECT c FROM CoopMemberApplicant c"),
    @NamedQuery(name = "CoopMemberApplicant.findByMemAppNo", query = "SELECT c FROM CoopMemberApplicant c WHERE c.memAppNo = :memAppNo")})
public class CoopMemberApplicant implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "mem_app_no")
    private Integer memAppNo;
    @JoinColumn(name = "mem_no", referencedColumnName = "mem_no")
    @ManyToOne(optional = false)
    private CoopMember memNo;
    @JoinColumn(name = "applicant_no", referencedColumnName = "applicant_no")
    @ManyToOne(optional = false)
    private CoopApplicant applicantNo;

    public CoopMemberApplicant() {
    }

    public CoopMemberApplicant(Integer memAppNo) {
        this.memAppNo = memAppNo;
    }

    public Integer getMemAppNo() {
        return memAppNo;
    }

    public void setMemAppNo(Integer memAppNo) {
        this.memAppNo = memAppNo;
    }

    public CoopMember getMemNo() {
        return memNo;
    }

    public void setMemNo(CoopMember memNo) {
        this.memNo = memNo;
    }

    public CoopApplicant getApplicantNo() {
        return applicantNo;
    }

    public void setApplicantNo(CoopApplicant applicantNo) {
        this.applicantNo = applicantNo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (memAppNo != null ? memAppNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoopMemberApplicant)) {
            return false;
        }
        CoopMemberApplicant other = (CoopMemberApplicant) object;
        if ((this.memAppNo == null && other.memAppNo != null) || (this.memAppNo != null && !this.memAppNo.equals(other.memAppNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.CoopMemberApplicant[ memAppNo=" + memAppNo + " ]";
    }
    
}
