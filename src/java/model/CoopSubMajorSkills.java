/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "coop_sub_major_skills")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CoopSubMajorSkills.findAll", query = "SELECT c FROM CoopSubMajorSkills c"),
    @NamedQuery(name = "CoopSubMajorSkills.findBySubMajorSkillsCode", query = "SELECT c FROM CoopSubMajorSkills c WHERE c.subMajorSkillsCode = :subMajorSkillsCode"),
    @NamedQuery(name = "CoopSubMajorSkills.findBySubMajorSkillsName", query = "SELECT c FROM CoopSubMajorSkills c WHERE c.subMajorSkillsName = :subMajorSkillsName")})
public class CoopSubMajorSkills implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "sub_major_skills_code")
    private String subMajorSkillsCode;
    @Size(max = 2147483647)
    @Column(name = "sub_major_skills_name")
    private String subMajorSkillsName;
    @JoinColumn(name = "major_skills_code", referencedColumnName = "major_skills_code")
    @ManyToOne
    private CoopMajorSkills majorSkillsCode;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subMajorSkillsCode")
    private Collection<CoopSkills> coopSkillsCollection;

    public CoopSubMajorSkills() {
    }

    public CoopSubMajorSkills(String subMajorSkillsCode) {
        this.subMajorSkillsCode = subMajorSkillsCode;
    }

    public String getSubMajorSkillsCode() {
        return subMajorSkillsCode;
    }

    public void setSubMajorSkillsCode(String subMajorSkillsCode) {
        this.subMajorSkillsCode = subMajorSkillsCode;
    }

    public String getSubMajorSkillsName() {
        return subMajorSkillsName;
    }

    public void setSubMajorSkillsName(String subMajorSkillsName) {
        this.subMajorSkillsName = subMajorSkillsName;
    }

    public CoopMajorSkills getMajorSkillsCode() {
        return majorSkillsCode;
    }

    public void setMajorSkillsCode(CoopMajorSkills majorSkillsCode) {
        this.majorSkillsCode = majorSkillsCode;
    }

    @XmlTransient
    public Collection<CoopSkills> getCoopSkillsCollection() {
        return coopSkillsCollection;
    }

    public void setCoopSkillsCollection(Collection<CoopSkills> coopSkillsCollection) {
        this.coopSkillsCollection = coopSkillsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (subMajorSkillsCode != null ? subMajorSkillsCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoopSubMajorSkills)) {
            return false;
        }
        CoopSubMajorSkills other = (CoopSubMajorSkills) object;
        if ((this.subMajorSkillsCode == null && other.subMajorSkillsCode != null) || (this.subMajorSkillsCode != null && !this.subMajorSkillsCode.equals(other.subMajorSkillsCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.CoopSubMajorSkills[ subMajorSkillsCode=" + subMajorSkillsCode + " ]";
    }
    
}
