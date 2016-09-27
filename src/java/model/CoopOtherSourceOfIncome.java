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
@Table(name = "coop_other_source_of_income")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CoopOtherSourceOfIncome.findAll", query = "SELECT c FROM CoopOtherSourceOfIncome c"),
    @NamedQuery(name = "CoopOtherSourceOfIncome.findByOtherSoiNo", query = "SELECT c FROM CoopOtherSourceOfIncome c WHERE c.otherSoiNo = :otherSoiNo"),
    @NamedQuery(name = "CoopOtherSourceOfIncome.findBySoiType", query = "SELECT c FROM CoopOtherSourceOfIncome c WHERE c.soiType = :soiType"),
    @NamedQuery(name = "CoopOtherSourceOfIncome.findByRegularity", query = "SELECT c FROM CoopOtherSourceOfIncome c WHERE c.regularity = :regularity"),
    @NamedQuery(name = "CoopOtherSourceOfIncome.findBySource", query = "SELECT c FROM CoopOtherSourceOfIncome c WHERE c.source = :source"),
    @NamedQuery(name = "CoopOtherSourceOfIncome.findByAmount", query = "SELECT c FROM CoopOtherSourceOfIncome c WHERE c.amount = :amount")})
public class CoopOtherSourceOfIncome implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "other_soi_no")
    private Integer otherSoiNo;
    @Size(max = 2147483647)
    @Column(name = "soi_type")
    private String soiType;
    @Size(max = 2147483647)
    @Column(name = "regularity")
    private String regularity;
    @Size(max = 2147483647)
    @Column(name = "source")
    private String source;
    @Size(max = 2147483647)
    @Column(name = "amount")
    private String amount;
    @OneToMany(mappedBy = "otherSoiNo")
    private Collection<CoopOtherSourceOfIncomeAssoc> coopOtherSourceOfIncomeAssocCollection;
    @OneToMany(mappedBy = "otherSoiNo")
    private Collection<CoopOtherSourceOfIncomeMem> coopOtherSourceOfIncomeMemCollection;

    public CoopOtherSourceOfIncome() {
    }

    public CoopOtherSourceOfIncome(Integer otherSoiNo) {
        this.otherSoiNo = otherSoiNo;
    }

    public Integer getOtherSoiNo() {
        return otherSoiNo;
    }

    public void setOtherSoiNo(Integer otherSoiNo) {
        this.otherSoiNo = otherSoiNo;
    }

    public String getSoiType() {
        return soiType;
    }

    public void setSoiType(String soiType) {
        this.soiType = soiType;
    }

    public String getRegularity() {
        return regularity;
    }

    public void setRegularity(String regularity) {
        this.regularity = regularity;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @XmlTransient
    public Collection<CoopOtherSourceOfIncomeAssoc> getCoopOtherSourceOfIncomeAssocCollection() {
        return coopOtherSourceOfIncomeAssocCollection;
    }

    public void setCoopOtherSourceOfIncomeAssocCollection(Collection<CoopOtherSourceOfIncomeAssoc> coopOtherSourceOfIncomeAssocCollection) {
        this.coopOtherSourceOfIncomeAssocCollection = coopOtherSourceOfIncomeAssocCollection;
    }

    @XmlTransient
    public Collection<CoopOtherSourceOfIncomeMem> getCoopOtherSourceOfIncomeMemCollection() {
        return coopOtherSourceOfIncomeMemCollection;
    }

    public void setCoopOtherSourceOfIncomeMemCollection(Collection<CoopOtherSourceOfIncomeMem> coopOtherSourceOfIncomeMemCollection) {
        this.coopOtherSourceOfIncomeMemCollection = coopOtherSourceOfIncomeMemCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (otherSoiNo != null ? otherSoiNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoopOtherSourceOfIncome)) {
            return false;
        }
        CoopOtherSourceOfIncome other = (CoopOtherSourceOfIncome) object;
        if ((this.otherSoiNo == null && other.otherSoiNo != null) || (this.otherSoiNo != null && !this.otherSoiNo.equals(other.otherSoiNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.CoopOtherSourceOfIncome[ otherSoiNo=" + otherSoiNo + " ]";
    }
    
}
