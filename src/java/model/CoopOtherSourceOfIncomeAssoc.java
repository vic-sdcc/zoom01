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
@Table(name = "coop_other_source_of_income_assoc")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CoopOtherSourceOfIncomeAssoc.findAll", query = "SELECT c FROM CoopOtherSourceOfIncomeAssoc c"),
    @NamedQuery(name = "CoopOtherSourceOfIncomeAssoc.findByOtherSoiAssocNo", query = "SELECT c FROM CoopOtherSourceOfIncomeAssoc c WHERE c.otherSoiAssocNo = :otherSoiAssocNo")})
public class CoopOtherSourceOfIncomeAssoc implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "other_soi_assoc_no")
    private Integer otherSoiAssocNo;
    @JoinColumn(name = "other_soi_no", referencedColumnName = "other_soi_no")
    @ManyToOne
    private CoopOtherSourceOfIncome otherSoiNo;
    @JoinColumn(name = "associate_no", referencedColumnName = "associate_no")
    @ManyToOne
    private CoopAssociate associateNo;

    public CoopOtherSourceOfIncomeAssoc() {
    }

    public CoopOtherSourceOfIncomeAssoc(Integer otherSoiAssocNo) {
        this.otherSoiAssocNo = otherSoiAssocNo;
    }

    public Integer getOtherSoiAssocNo() {
        return otherSoiAssocNo;
    }

    public void setOtherSoiAssocNo(Integer otherSoiAssocNo) {
        this.otherSoiAssocNo = otherSoiAssocNo;
    }

    public CoopOtherSourceOfIncome getOtherSoiNo() {
        return otherSoiNo;
    }

    public void setOtherSoiNo(CoopOtherSourceOfIncome otherSoiNo) {
        this.otherSoiNo = otherSoiNo;
    }

    public CoopAssociate getAssociateNo() {
        return associateNo;
    }

    public void setAssociateNo(CoopAssociate associateNo) {
        this.associateNo = associateNo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (otherSoiAssocNo != null ? otherSoiAssocNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoopOtherSourceOfIncomeAssoc)) {
            return false;
        }
        CoopOtherSourceOfIncomeAssoc other = (CoopOtherSourceOfIncomeAssoc) object;
        if ((this.otherSoiAssocNo == null && other.otherSoiAssocNo != null) || (this.otherSoiAssocNo != null && !this.otherSoiAssocNo.equals(other.otherSoiAssocNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.CoopOtherSourceOfIncomeAssoc[ otherSoiAssocNo=" + otherSoiAssocNo + " ]";
    }
    
}
