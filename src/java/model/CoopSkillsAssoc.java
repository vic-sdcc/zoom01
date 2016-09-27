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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mis05
 */
@Entity
@Table(name = "coop_skills_assoc")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CoopSkillsAssoc.findAll", query = "SELECT c FROM CoopSkillsAssoc c"),
    @NamedQuery(name = "CoopSkillsAssoc.findBySkillsAssocId", query = "SELECT c FROM CoopSkillsAssoc c WHERE c.skillsAssocId = :skillsAssocId"),
    @NamedQuery(name = "CoopSkillsAssoc.findByMemSkillNum", query = "SELECT c FROM CoopSkillsAssoc c WHERE c.memSkillNum = :memSkillNum"),
    @NamedQuery(name = "CoopSkillsAssoc.findByTempSkills", query = "SELECT c FROM CoopSkillsAssoc c WHERE c.tempSkills = :tempSkills")})
public class CoopSkillsAssoc implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "skills_assoc_id")
    private Integer skillsAssocId;
    @Column(name = "mem_skill_num")
    private Integer memSkillNum;
    @Size(max = 2147483647)
    @Column(name = "temp_skills")
    private String tempSkills;
    @JoinColumn(name = "skills_code", referencedColumnName = "skills_code")
    @ManyToOne
    private CoopSkills skillsCode;
    @JoinColumn(name = "associate_no", referencedColumnName = "associate_no")
    @ManyToOne
    private CoopAssociate associateNo;

    public CoopSkillsAssoc() {
    }

    public CoopSkillsAssoc(Integer skillsAssocId) {
        this.skillsAssocId = skillsAssocId;
    }

    public Integer getSkillsAssocId() {
        return skillsAssocId;
    }

    public void setSkillsAssocId(Integer skillsAssocId) {
        this.skillsAssocId = skillsAssocId;
    }

    public Integer getMemSkillNum() {
        return memSkillNum;
    }

    public void setMemSkillNum(Integer memSkillNum) {
        this.memSkillNum = memSkillNum;
    }

    public String getTempSkills() {
        return tempSkills;
    }

    public void setTempSkills(String tempSkills) {
        this.tempSkills = tempSkills;
    }

    public CoopSkills getSkillsCode() {
        return skillsCode;
    }

    public void setSkillsCode(CoopSkills skillsCode) {
        this.skillsCode = skillsCode;
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
        hash += (skillsAssocId != null ? skillsAssocId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoopSkillsAssoc)) {
            return false;
        }
        CoopSkillsAssoc other = (CoopSkillsAssoc) object;
        if ((this.skillsAssocId == null && other.skillsAssocId != null) || (this.skillsAssocId != null && !this.skillsAssocId.equals(other.skillsAssocId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.CoopSkillsAssoc[ skillsAssocId=" + skillsAssocId + " ]";
    }
    
}
