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
@Table(name = "coop_empl_dtl_mem")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CoopEmplDtlMem.findAll", query = "SELECT c FROM CoopEmplDtlMem c"),
    @NamedQuery(name = "CoopEmplDtlMem.findByEmplDtlMemId", query = "SELECT c FROM CoopEmplDtlMem c WHERE c.emplDtlMemId = :emplDtlMemId")})
public class CoopEmplDtlMem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "empl_dtl_mem_id")
    private Integer emplDtlMemId;
    @JoinColumn(name = "mem_no", referencedColumnName = "mem_no")
    @ManyToOne
    private CoopMember memNo;
    @JoinColumn(name = "empl_dtl_num", referencedColumnName = "empl_dtl_num")
    @ManyToOne
    private CoopEmplDtl emplDtlNum;

    public CoopEmplDtlMem() {
    }

    public CoopEmplDtlMem(Integer emplDtlMemId) {
        this.emplDtlMemId = emplDtlMemId;
    }

    public Integer getEmplDtlMemId() {
        return emplDtlMemId;
    }

    public void setEmplDtlMemId(Integer emplDtlMemId) {
        this.emplDtlMemId = emplDtlMemId;
    }

    public CoopMember getMemNo() {
        return memNo;
    }

    public void setMemNo(CoopMember memNo) {
        this.memNo = memNo;
    }

    public CoopEmplDtl getEmplDtlNum() {
        return emplDtlNum;
    }

    public void setEmplDtlNum(CoopEmplDtl emplDtlNum) {
        this.emplDtlNum = emplDtlNum;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (emplDtlMemId != null ? emplDtlMemId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoopEmplDtlMem)) {
            return false;
        }
        CoopEmplDtlMem other = (CoopEmplDtlMem) object;
        if ((this.emplDtlMemId == null && other.emplDtlMemId != null) || (this.emplDtlMemId != null && !this.emplDtlMemId.equals(other.emplDtlMemId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.CoopEmplDtlMem[ emplDtlMemId=" + emplDtlMemId + " ]";
    }
    
}
