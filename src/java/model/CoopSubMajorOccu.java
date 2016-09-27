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
@Table(name = "coop_sub_major_occu")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CoopSubMajorOccu.findAll", query = "SELECT c FROM CoopSubMajorOccu c"),
    @NamedQuery(name = "CoopSubMajorOccu.findBySubMajorCode", query = "SELECT c FROM CoopSubMajorOccu c WHERE c.subMajorCode = :subMajorCode"),
    @NamedQuery(name = "CoopSubMajorOccu.findBySubMajorName", query = "SELECT c FROM CoopSubMajorOccu c WHERE c.subMajorName = :subMajorName"),
    @NamedQuery(name = "CoopSubMajorOccu.findBySubMajorDefinition", query = "SELECT c FROM CoopSubMajorOccu c WHERE c.subMajorDefinition = :subMajorDefinition")})
public class CoopSubMajorOccu implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "sub_major_code")
    private String subMajorCode;
    @Size(max = 2147483647)
    @Column(name = "sub_major_name")
    private String subMajorName;
    @Size(max = 2147483647)
    @Column(name = "sub_major_definition")
    private String subMajorDefinition;
    @JoinColumn(name = "major_code", referencedColumnName = "major_code")
    @ManyToOne
    private CoopMajorOccu majorCode;
    @OneToMany(mappedBy = "subMajorGroupCode")
    private Collection<CoopMinorGroupOccu> coopMinorGroupOccuCollection;

    public CoopSubMajorOccu() {
    }

    public CoopSubMajorOccu(String subMajorCode) {
        this.subMajorCode = subMajorCode;
    }

    public String getSubMajorCode() {
        return subMajorCode;
    }

    public void setSubMajorCode(String subMajorCode) {
        this.subMajorCode = subMajorCode;
    }

    public String getSubMajorName() {
        return subMajorName;
    }

    public void setSubMajorName(String subMajorName) {
        this.subMajorName = subMajorName;
    }

    public String getSubMajorDefinition() {
        return subMajorDefinition;
    }

    public void setSubMajorDefinition(String subMajorDefinition) {
        this.subMajorDefinition = subMajorDefinition;
    }

    public CoopMajorOccu getMajorCode() {
        return majorCode;
    }

    public void setMajorCode(CoopMajorOccu majorCode) {
        this.majorCode = majorCode;
    }

    @XmlTransient
    public Collection<CoopMinorGroupOccu> getCoopMinorGroupOccuCollection() {
        return coopMinorGroupOccuCollection;
    }

    public void setCoopMinorGroupOccuCollection(Collection<CoopMinorGroupOccu> coopMinorGroupOccuCollection) {
        this.coopMinorGroupOccuCollection = coopMinorGroupOccuCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (subMajorCode != null ? subMajorCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoopSubMajorOccu)) {
            return false;
        }
        CoopSubMajorOccu other = (CoopSubMajorOccu) object;
        if ((this.subMajorCode == null && other.subMajorCode != null) || (this.subMajorCode != null && !this.subMajorCode.equals(other.subMajorCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.CoopSubMajorOccu[ subMajorCode=" + subMajorCode + " ]";
    }
    
}
