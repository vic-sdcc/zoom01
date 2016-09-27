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
@Table(name = "coop_major_occu")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CoopMajorOccu.findAll", query = "SELECT c FROM CoopMajorOccu c"),
    @NamedQuery(name = "CoopMajorOccu.findByMajorCode", query = "SELECT c FROM CoopMajorOccu c WHERE c.majorCode = :majorCode"),
    @NamedQuery(name = "CoopMajorOccu.findByMajorName", query = "SELECT c FROM CoopMajorOccu c WHERE c.majorName = :majorName"),
    @NamedQuery(name = "CoopMajorOccu.findByMajorDefinition", query = "SELECT c FROM CoopMajorOccu c WHERE c.majorDefinition = :majorDefinition")})
public class CoopMajorOccu implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "major_code")
    private String majorCode;
    @Size(max = 2147483647)
    @Column(name = "major_name")
    private String majorName;
    @Size(max = 2147483647)
    @Column(name = "major_definition")
    private String majorDefinition;
    @OneToMany(mappedBy = "majorCode")
    private Collection<CoopSubMajorOccu> coopSubMajorOccuCollection;

    public CoopMajorOccu() {
    }

    public CoopMajorOccu(String majorCode) {
        this.majorCode = majorCode;
    }

    public String getMajorCode() {
        return majorCode;
    }

    public void setMajorCode(String majorCode) {
        this.majorCode = majorCode;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public String getMajorDefinition() {
        return majorDefinition;
    }

    public void setMajorDefinition(String majorDefinition) {
        this.majorDefinition = majorDefinition;
    }

    @XmlTransient
    public Collection<CoopSubMajorOccu> getCoopSubMajorOccuCollection() {
        return coopSubMajorOccuCollection;
    }

    public void setCoopSubMajorOccuCollection(Collection<CoopSubMajorOccu> coopSubMajorOccuCollection) {
        this.coopSubMajorOccuCollection = coopSubMajorOccuCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (majorCode != null ? majorCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoopMajorOccu)) {
            return false;
        }
        CoopMajorOccu other = (CoopMajorOccu) object;
        if ((this.majorCode == null && other.majorCode != null) || (this.majorCode != null && !this.majorCode.equals(other.majorCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.CoopMajorOccu[ majorCode=" + majorCode + " ]";
    }
    
}
