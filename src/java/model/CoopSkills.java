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
@Table(name = "coop_skills")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CoopSkills.findAll", query = "SELECT c FROM CoopSkills c"),
    @NamedQuery(name = "CoopSkills.findBySkillsCode", query = "SELECT c FROM CoopSkills c WHERE c.skillsCode = :skillsCode"),
    @NamedQuery(name = "CoopSkills.findBySkillsName", query = "SELECT c FROM CoopSkills c WHERE c.skillsName = :skillsName")})
public class CoopSkills implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "skills_code")
    private String skillsCode;
    @Size(max = 2147483647)
    @Column(name = "skills_name")
    private String skillsName;
    @OneToMany(mappedBy = "skillsCode")
    private Collection<CoopSkillsAssoc> coopSkillsAssocCollection;
    @JoinColumn(name = "sub_major_skills_code", referencedColumnName = "sub_major_skills_code")
    @ManyToOne(optional = false)
    private CoopSubMajorSkills subMajorSkillsCode;
    @OneToMany(mappedBy = "skillsCode")
    private Collection<CoopSkillsMem> coopSkillsMemCollection;

    public CoopSkills() {
    }

    public CoopSkills(String skillsCode) {
        this.skillsCode = skillsCode;
    }

    public String getSkillsCode() {
        return skillsCode;
    }

    public void setSkillsCode(String skillsCode) {
        this.skillsCode = skillsCode;
    }

    public String getSkillsName() {
        return skillsName;
    }

    public void setSkillsName(String skillsName) {
        this.skillsName = skillsName;
    }

    @XmlTransient
    public Collection<CoopSkillsAssoc> getCoopSkillsAssocCollection() {
        return coopSkillsAssocCollection;
    }

    public void setCoopSkillsAssocCollection(Collection<CoopSkillsAssoc> coopSkillsAssocCollection) {
        this.coopSkillsAssocCollection = coopSkillsAssocCollection;
    }

    public CoopSubMajorSkills getSubMajorSkillsCode() {
        return subMajorSkillsCode;
    }

    public void setSubMajorSkillsCode(CoopSubMajorSkills subMajorSkillsCode) {
        this.subMajorSkillsCode = subMajorSkillsCode;
    }

    @XmlTransient
    public Collection<CoopSkillsMem> getCoopSkillsMemCollection() {
        return coopSkillsMemCollection;
    }

    public void setCoopSkillsMemCollection(Collection<CoopSkillsMem> coopSkillsMemCollection) {
        this.coopSkillsMemCollection = coopSkillsMemCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (skillsCode != null ? skillsCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoopSkills)) {
            return false;
        }
        CoopSkills other = (CoopSkills) object;
        if ((this.skillsCode == null && other.skillsCode != null) || (this.skillsCode != null && !this.skillsCode.equals(other.skillsCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.CoopSkills[ skillsCode=" + skillsCode + " ]";
    }
    
}
