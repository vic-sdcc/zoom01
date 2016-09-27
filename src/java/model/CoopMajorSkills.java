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
@Table(name = "coop_major_skills")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CoopMajorSkills.findAll", query = "SELECT c FROM CoopMajorSkills c"),
    @NamedQuery(name = "CoopMajorSkills.findByMajorSkillsCode", query = "SELECT c FROM CoopMajorSkills c WHERE c.majorSkillsCode = :majorSkillsCode"),
    @NamedQuery(name = "CoopMajorSkills.findByMajorSkillsName", query = "SELECT c FROM CoopMajorSkills c WHERE c.majorSkillsName = :majorSkillsName")})
public class CoopMajorSkills implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "major_skills_code")
    private String majorSkillsCode;
    @Size(max = 2147483647)
    @Column(name = "major_skills_name")
    private String majorSkillsName;
    @OneToMany(mappedBy = "majorSkillsCode")
    private Collection<CoopSubMajorSkills> coopSubMajorSkillsCollection;

    public CoopMajorSkills() {
    }

    public CoopMajorSkills(String majorSkillsCode) {
        this.majorSkillsCode = majorSkillsCode;
    }

    public String getMajorSkillsCode() {
        return majorSkillsCode;
    }

    public void setMajorSkillsCode(String majorSkillsCode) {
        this.majorSkillsCode = majorSkillsCode;
    }

    public String getMajorSkillsName() {
        return majorSkillsName;
    }

    public void setMajorSkillsName(String majorSkillsName) {
        this.majorSkillsName = majorSkillsName;
    }

    @XmlTransient
    public Collection<CoopSubMajorSkills> getCoopSubMajorSkillsCollection() {
        return coopSubMajorSkillsCollection;
    }

    public void setCoopSubMajorSkillsCollection(Collection<CoopSubMajorSkills> coopSubMajorSkillsCollection) {
        this.coopSubMajorSkillsCollection = coopSubMajorSkillsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (majorSkillsCode != null ? majorSkillsCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoopMajorSkills)) {
            return false;
        }
        CoopMajorSkills other = (CoopMajorSkills) object;
        if ((this.majorSkillsCode == null && other.majorSkillsCode != null) || (this.majorSkillsCode != null && !this.majorSkillsCode.equals(other.majorSkillsCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.CoopMajorSkills[ majorSkillsCode=" + majorSkillsCode + " ]";
    }
    
}
