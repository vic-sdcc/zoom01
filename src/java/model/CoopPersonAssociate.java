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
@Table(name = "coop_person_associate")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CoopPersonAssociate.findAll", query = "SELECT c FROM CoopPersonAssociate c"),
    @NamedQuery(name = "CoopPersonAssociate.findByPersonAssociateId", query = "SELECT c FROM CoopPersonAssociate c WHERE c.personAssociateId = :personAssociateId")})
public class CoopPersonAssociate implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "person_associate_id")
    private Integer personAssociateId;
    @JoinColumn(name = "person_id", referencedColumnName = "person_id")
    @ManyToOne
    private CoopPerson personId;
    @JoinColumn(name = "associate_no", referencedColumnName = "associate_no")
    @ManyToOne
    private CoopAssociate associateNo;

    public CoopPersonAssociate() {
    }

    public CoopPersonAssociate(Integer personAssociateId) {
        this.personAssociateId = personAssociateId;
    }

    public Integer getPersonAssociateId() {
        return personAssociateId;
    }

    public void setPersonAssociateId(Integer personAssociateId) {
        this.personAssociateId = personAssociateId;
    }

    public CoopPerson getPersonId() {
        return personId;
    }

    public void setPersonId(CoopPerson personId) {
        this.personId = personId;
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
        hash += (personAssociateId != null ? personAssociateId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoopPersonAssociate)) {
            return false;
        }
        CoopPersonAssociate other = (CoopPersonAssociate) object;
        if ((this.personAssociateId == null && other.personAssociateId != null) || (this.personAssociateId != null && !this.personAssociateId.equals(other.personAssociateId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.CoopPersonAssociate[ personAssociateId=" + personAssociateId + " ]";
    }
    
}
