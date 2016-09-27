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
@Table(name = "coop_other_source_of_income_mem")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CoopOtherSourceOfIncomeMem.findAll", query = "SELECT c FROM CoopOtherSourceOfIncomeMem c"),
    @NamedQuery(name = "CoopOtherSourceOfIncomeMem.findByOtherSoiMemNo", query = "SELECT c FROM CoopOtherSourceOfIncomeMem c WHERE c.otherSoiMemNo = :otherSoiMemNo")})
public class CoopOtherSourceOfIncomeMem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "other_soi_mem_no")
    private Integer otherSoiMemNo;
    @JoinColumn(name = "other_soi_no", referencedColumnName = "other_soi_no")
    @ManyToOne
    private CoopOtherSourceOfIncome otherSoiNo;
    @JoinColumn(name = "mem_no", referencedColumnName = "mem_no")
    @ManyToOne
    private CoopMember memNo;

    public CoopOtherSourceOfIncomeMem() {
    }

    public CoopOtherSourceOfIncomeMem(Integer otherSoiMemNo) {
        this.otherSoiMemNo = otherSoiMemNo;
    }

    public Integer getOtherSoiMemNo() {
        return otherSoiMemNo;
    }

    public void setOtherSoiMemNo(Integer otherSoiMemNo) {
        this.otherSoiMemNo = otherSoiMemNo;
    }

    public CoopOtherSourceOfIncome getOtherSoiNo() {
        return otherSoiNo;
    }

    public void setOtherSoiNo(CoopOtherSourceOfIncome otherSoiNo) {
        this.otherSoiNo = otherSoiNo;
    }

    public CoopMember getMemNo() {
        return memNo;
    }

    public void setMemNo(CoopMember memNo) {
        this.memNo = memNo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (otherSoiMemNo != null ? otherSoiMemNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoopOtherSourceOfIncomeMem)) {
            return false;
        }
        CoopOtherSourceOfIncomeMem other = (CoopOtherSourceOfIncomeMem) object;
        if ((this.otherSoiMemNo == null && other.otherSoiMemNo != null) || (this.otherSoiMemNo != null && !this.otherSoiMemNo.equals(other.otherSoiMemNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.CoopOtherSourceOfIncomeMem[ otherSoiMemNo=" + otherSoiMemNo + " ]";
    }
    
}
