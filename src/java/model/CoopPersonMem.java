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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mis05
 */
@Entity
@Table(name = "coop_person_mem")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CoopPersonMem.findAll", query = "SELECT c FROM CoopPersonMem c"),
    @NamedQuery(name = "CoopPersonMem.findByPersonMemId", query = "SELECT c FROM CoopPersonMem c WHERE c.personMemId = :personMemId")})
public class CoopPersonMem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "person_mem_id")
    private Integer personMemId;
    @JoinColumn(name = "person_id", referencedColumnName = "person_id")
    @ManyToOne
    private CoopPerson personId;
    @JoinColumn(name = "mem_no", referencedColumnName = "mem_no")
    @ManyToOne
    private CoopMember memNo;

    public CoopPersonMem() {
    }

    public CoopPersonMem(Integer personMemId) {
        this.personMemId = personMemId;
    }

    public Integer getPersonMemId() {
        return personMemId;
    }

    public void setPersonMemId(Integer personMemId) {
        this.personMemId = personMemId;
    }

    public CoopPerson getPersonId() {
        return personId;
    }

    public void setPersonId(CoopPerson personId) {
        this.personId = personId;
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
        hash += (personMemId != null ? personMemId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoopPersonMem)) {
            return false;
        }
        CoopPersonMem other = (CoopPersonMem) object;
        if ((this.personMemId == null && other.personMemId != null) || (this.personMemId != null && !this.personMemId.equals(other.personMemId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.CoopPersonMem[ personMemId=" + personMemId + " ]";
    }
    
}
