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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mis05
 */
@Entity
@Table(name = "coop_occupation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CoopOccupation.findAll", query = "SELECT c FROM CoopOccupation c"),
    @NamedQuery(name = "CoopOccupation.findByOccupationCode", query = "SELECT c FROM CoopOccupation c WHERE c.occupationCode = :occupationCode"),
    @NamedQuery(name = "CoopOccupation.findByUnitName", query = "SELECT c FROM CoopOccupation c WHERE c.unitName = :unitName")})
public class CoopOccupation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "occupation_code")
    private String occupationCode;
    @Size(max = 2147483647)
    @Column(name = "unit_name")
    private String unitName;
    @OneToMany(mappedBy = "emplRankPos")
    private Collection<CoopEmplDtl> coopEmplDtlCollection;
    @JoinColumn(name = "unit_group_code", referencedColumnName = "unit_group_code")
    @ManyToOne
    private CoopUnitGroupOccu unitGroupCode;

    public CoopOccupation() {
    }

    public CoopOccupation(String occupationCode) {
        this.occupationCode = occupationCode;
    }

    public String getOccupationCode() {
        return occupationCode;
    }

    public void setOccupationCode(String occupationCode) {
        this.occupationCode = occupationCode;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    @XmlTransient
    public Collection<CoopEmplDtl> getCoopEmplDtlCollection() {
        return coopEmplDtlCollection;
    }

    public void setCoopEmplDtlCollection(Collection<CoopEmplDtl> coopEmplDtlCollection) {
        this.coopEmplDtlCollection = coopEmplDtlCollection;
    }

    public CoopUnitGroupOccu getUnitGroupCode() {
        return unitGroupCode;
    }

    public void setUnitGroupCode(CoopUnitGroupOccu unitGroupCode) {
        this.unitGroupCode = unitGroupCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (occupationCode != null ? occupationCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoopOccupation)) {
            return false;
        }
        CoopOccupation other = (CoopOccupation) object;
        if ((this.occupationCode == null && other.occupationCode != null) || (this.occupationCode != null && !this.occupationCode.equals(other.occupationCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.CoopOccupation[ occupationCode=" + occupationCode + " ]";
    }
    
}
