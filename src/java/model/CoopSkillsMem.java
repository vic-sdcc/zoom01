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
@Table(name = "coop_skills_mem")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CoopSkillsMem.findAll", query = "SELECT c FROM CoopSkillsMem c"),
    @NamedQuery(name = "CoopSkillsMem.findBySkillsMemId", query = "SELECT c FROM CoopSkillsMem c WHERE c.skillsMemId = :skillsMemId"),
    @NamedQuery(name = "CoopSkillsMem.findByMemSkillNum", query = "SELECT c FROM CoopSkillsMem c WHERE c.memSkillNum = :memSkillNum"),
    @NamedQuery(name = "CoopSkillsMem.findByTempSkills", query = "SELECT c FROM CoopSkillsMem c WHERE c.tempSkills = :tempSkills"),
    @NamedQuery(name = "CoopSkillsMem.findByAcctno", query = "SELECT c FROM CoopSkillsMem c WHERE c.acctno = :acctno")})
public class CoopSkillsMem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "skills_mem_id")
    private Integer skillsMemId;
    @Column(name = "mem_skill_num")
    private Integer memSkillNum;
    @Size(max = 2147483647)
    @Column(name = "temp_skills")
    private String tempSkills;
    @Size(max = 2147483647)
    @Column(name = "acctno")
    private String acctno;
    @JoinColumn(name = "skills_code", referencedColumnName = "skills_code")
    @ManyToOne
    private CoopSkills skillsCode;
    @JoinColumn(name = "mem_no", referencedColumnName = "mem_no")
    @ManyToOne
    private CoopMember memNo;

    public CoopSkillsMem() {
    }

    public CoopSkillsMem(Integer skillsMemId) {
        this.skillsMemId = skillsMemId;
    }

    public Integer getSkillsMemId() {
        return skillsMemId;
    }

    public void setSkillsMemId(Integer skillsMemId) {
        this.skillsMemId = skillsMemId;
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

    public String getAcctno() {
        return acctno;
    }

    public void setAcctno(String acctno) {
        this.acctno = acctno;
    }

    public CoopSkills getSkillsCode() {
        return skillsCode;
    }

    public void setSkillsCode(CoopSkills skillsCode) {
        this.skillsCode = skillsCode;
    }

    public CoopMember getMemNo() {
        return memNo;
    }

    public void setMemNo(CoopMember memNo) {
        this.memNo = memNo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (skillsMemId != null ? skillsMemId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoopSkillsMem)) {
            return false;
        }
        CoopSkillsMem other = (CoopSkillsMem) object;
        if ((this.skillsMemId == null && other.skillsMemId != null) || (this.skillsMemId != null && !this.skillsMemId.equals(other.skillsMemId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.CoopSkillsMem[ skillsMemId=" + skillsMemId + " ]";
    }
    
}
