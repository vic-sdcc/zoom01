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
@Table(name = "coop_minor_group_occu")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CoopMinorGroupOccu.findAll", query = "SELECT c FROM CoopMinorGroupOccu c"),
    @NamedQuery(name = "CoopMinorGroupOccu.findByMinorGroupCode", query = "SELECT c FROM CoopMinorGroupOccu c WHERE c.minorGroupCode = :minorGroupCode"),
    @NamedQuery(name = "CoopMinorGroupOccu.findByMinorGroupName", query = "SELECT c FROM CoopMinorGroupOccu c WHERE c.minorGroupName = :minorGroupName"),
    @NamedQuery(name = "CoopMinorGroupOccu.findByMinorGroupDefinition", query = "SELECT c FROM CoopMinorGroupOccu c WHERE c.minorGroupDefinition = :minorGroupDefinition")})
public class CoopMinorGroupOccu implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "minor_group_code")
    private String minorGroupCode;
    @Size(max = 2147483647)
    @Column(name = "minor_group_name")
    private String minorGroupName;
    @Size(max = 2147483647)
    @Column(name = "minor_group_definition")
    private String minorGroupDefinition;
    @OneToMany(mappedBy = "minorGroupCode")
    private Collection<CoopUnitGroupOccu> coopUnitGroupOccuCollection;
    @JoinColumn(name = "sub_major_group_code", referencedColumnName = "sub_major_code")
    @ManyToOne
    private CoopSubMajorOccu subMajorGroupCode;

    public CoopMinorGroupOccu() {
    }

    public CoopMinorGroupOccu(String minorGroupCode) {
        this.minorGroupCode = minorGroupCode;
    }

    public String getMinorGroupCode() {
        return minorGroupCode;
    }

    public void setMinorGroupCode(String minorGroupCode) {
        this.minorGroupCode = minorGroupCode;
    }

    public String getMinorGroupName() {
        return minorGroupName;
    }

    public void setMinorGroupName(String minorGroupName) {
        this.minorGroupName = minorGroupName;
    }

    public String getMinorGroupDefinition() {
        return minorGroupDefinition;
    }

    public void setMinorGroupDefinition(String minorGroupDefinition) {
        this.minorGroupDefinition = minorGroupDefinition;
    }

    @XmlTransient
    public Collection<CoopUnitGroupOccu> getCoopUnitGroupOccuCollection() {
        return coopUnitGroupOccuCollection;
    }

    public void setCoopUnitGroupOccuCollection(Collection<CoopUnitGroupOccu> coopUnitGroupOccuCollection) {
        this.coopUnitGroupOccuCollection = coopUnitGroupOccuCollection;
    }

    public CoopSubMajorOccu getSubMajorGroupCode() {
        return subMajorGroupCode;
    }

    public void setSubMajorGroupCode(CoopSubMajorOccu subMajorGroupCode) {
        this.subMajorGroupCode = subMajorGroupCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (minorGroupCode != null ? minorGroupCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoopMinorGroupOccu)) {
            return false;
        }
        CoopMinorGroupOccu other = (CoopMinorGroupOccu) object;
        if ((this.minorGroupCode == null && other.minorGroupCode != null) || (this.minorGroupCode != null && !this.minorGroupCode.equals(other.minorGroupCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.CoopMinorGroupOccu[ minorGroupCode=" + minorGroupCode + " ]";
    }
    
}
