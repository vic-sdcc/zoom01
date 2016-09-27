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
@Table(name = "coop_empl_dtl_assoc")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CoopEmplDtlAssoc.findAll", query = "SELECT c FROM CoopEmplDtlAssoc c"),
    @NamedQuery(name = "CoopEmplDtlAssoc.findByEmplDtlAssocId", query = "SELECT c FROM CoopEmplDtlAssoc c WHERE c.emplDtlAssocId = :emplDtlAssocId")})
public class CoopEmplDtlAssoc implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "empl_dtl_assoc_id")
    private Integer emplDtlAssocId;
    @JoinColumn(name = "empl_dtl_num", referencedColumnName = "empl_dtl_num")
    @ManyToOne
    private CoopEmplDtl emplDtlNum;
    @JoinColumn(name = "associate_no", referencedColumnName = "associate_no")
    @ManyToOne
    private CoopAssociate associateNo;

    public CoopEmplDtlAssoc() {
    }

    public CoopEmplDtlAssoc(Integer emplDtlAssocId) {
        this.emplDtlAssocId = emplDtlAssocId;
    }

    public Integer getEmplDtlAssocId() {
        return emplDtlAssocId;
    }

    public void setEmplDtlAssocId(Integer emplDtlAssocId) {
        this.emplDtlAssocId = emplDtlAssocId;
    }

    public CoopEmplDtl getEmplDtlNum() {
        return emplDtlNum;
    }

    public void setEmplDtlNum(CoopEmplDtl emplDtlNum) {
        this.emplDtlNum = emplDtlNum;
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
        hash += (emplDtlAssocId != null ? emplDtlAssocId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoopEmplDtlAssoc)) {
            return false;
        }
        CoopEmplDtlAssoc other = (CoopEmplDtlAssoc) object;
        if ((this.emplDtlAssocId == null && other.emplDtlAssocId != null) || (this.emplDtlAssocId != null && !this.emplDtlAssocId.equals(other.emplDtlAssocId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.CoopEmplDtlAssoc[ emplDtlAssocId=" + emplDtlAssocId + " ]";
    }
    
}
