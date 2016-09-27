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
@Table(name = "coop_unit_group_occu")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CoopUnitGroupOccu.findAll", query = "SELECT c FROM CoopUnitGroupOccu c"),
    @NamedQuery(name = "CoopUnitGroupOccu.findByUnitGroupCode", query = "SELECT c FROM CoopUnitGroupOccu c WHERE c.unitGroupCode = :unitGroupCode"),
    @NamedQuery(name = "CoopUnitGroupOccu.findByUnitGroupName", query = "SELECT c FROM CoopUnitGroupOccu c WHERE c.unitGroupName = :unitGroupName"),
    @NamedQuery(name = "CoopUnitGroupOccu.findByUnitGroupDefinition", query = "SELECT c FROM CoopUnitGroupOccu c WHERE c.unitGroupDefinition = :unitGroupDefinition")})
public class CoopUnitGroupOccu implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "unit_group_code")
    private String unitGroupCode;
    @Size(max = 2147483647)
    @Column(name = "unit_group_name")
    private String unitGroupName;
    @Size(max = 2147483647)
    @Column(name = "unit_group_definition")
    private String unitGroupDefinition;
    @JoinColumn(name = "minor_group_code", referencedColumnName = "minor_group_code")
    @ManyToOne
    private CoopMinorGroupOccu minorGroupCode;
    @OneToMany(mappedBy = "unitGroupCode")
    private Collection<CoopOccupation> coopOccupationCollection;

    public CoopUnitGroupOccu() {
    }

    public CoopUnitGroupOccu(String unitGroupCode) {
        this.unitGroupCode = unitGroupCode;
    }

    public String getUnitGroupCode() {
        return unitGroupCode;
    }

    public void setUnitGroupCode(String unitGroupCode) {
        this.unitGroupCode = unitGroupCode;
    }

    public String getUnitGroupName() {
        return unitGroupName;
    }

    public void setUnitGroupName(String unitGroupName) {
        this.unitGroupName = unitGroupName;
    }

    public String getUnitGroupDefinition() {
        return unitGroupDefinition;
    }

    public void setUnitGroupDefinition(String unitGroupDefinition) {
        this.unitGroupDefinition = unitGroupDefinition;
    }

    public CoopMinorGroupOccu getMinorGroupCode() {
        return minorGroupCode;
    }

    public void setMinorGroupCode(CoopMinorGroupOccu minorGroupCode) {
        this.minorGroupCode = minorGroupCode;
    }

    @XmlTransient
    public Collection<CoopOccupation> getCoopOccupationCollection() {
        return coopOccupationCollection;
    }

    public void setCoopOccupationCollection(Collection<CoopOccupation> coopOccupationCollection) {
        this.coopOccupationCollection = coopOccupationCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (unitGroupCode != null ? unitGroupCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoopUnitGroupOccu)) {
            return false;
        }
        CoopUnitGroupOccu other = (CoopUnitGroupOccu) object;
        if ((this.unitGroupCode == null && other.unitGroupCode != null) || (this.unitGroupCode != null && !this.unitGroupCode.equals(other.unitGroupCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.CoopUnitGroupOccu[ unitGroupCode=" + unitGroupCode + " ]";
    }
    
}
