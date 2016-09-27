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
@Table(name = "coop_associate_applicant")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CoopAssociateApplicant.findAll", query = "SELECT c FROM CoopAssociateApplicant c"),
    @NamedQuery(name = "CoopAssociateApplicant.findByAssocAppNo", query = "SELECT c FROM CoopAssociateApplicant c WHERE c.assocAppNo = :assocAppNo")})
public class CoopAssociateApplicant implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "assoc_app_no")
    private Integer assocAppNo;
    @JoinColumn(name = "associate_no", referencedColumnName = "associate_no")
    @ManyToOne
    private CoopAssociate associateNo;
    @JoinColumn(name = "applicant_no", referencedColumnName = "applicant_no")
    @ManyToOne
    private CoopApplicant applicantNo;

    public CoopAssociateApplicant() {
    }

    public CoopAssociateApplicant(Integer assocAppNo) {
        this.assocAppNo = assocAppNo;
    }

    public Integer getAssocAppNo() {
        return assocAppNo;
    }

    public void setAssocAppNo(Integer assocAppNo) {
        this.assocAppNo = assocAppNo;
    }

    public CoopAssociate getAssociateNo() {
        return associateNo;
    }

    public void setAssociateNo(CoopAssociate associateNo) {
        this.associateNo = associateNo;
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
        hash += (assocAppNo != null ? assocAppNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoopAssociateApplicant)) {
            return false;
        }
        CoopAssociateApplicant other = (CoopAssociateApplicant) object;
        if ((this.assocAppNo == null && other.assocAppNo != null) || (this.assocAppNo != null && !this.assocAppNo.equals(other.assocAppNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.CoopAssociateApplicant[ assocAppNo=" + assocAppNo + " ]";
    }
    
}
