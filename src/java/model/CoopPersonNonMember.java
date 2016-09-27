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
@Table(name = "coop_person_non_member")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CoopPersonNonMember.findAll", query = "SELECT c FROM CoopPersonNonMember c"),
    @NamedQuery(name = "CoopPersonNonMember.findByPersonNonMemberId", query = "SELECT c FROM CoopPersonNonMember c WHERE c.personNonMemberId = :personNonMemberId")})
public class CoopPersonNonMember implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "person_non_member_id")
    private Integer personNonMemberId;
    @JoinColumn(name = "person_id", referencedColumnName = "person_id")
    @ManyToOne
    private CoopPerson personId;
    @JoinColumn(name = "non_member_id", referencedColumnName = "non_member_id")
    @ManyToOne
    private CoopNonMember nonMemberId;

    public CoopPersonNonMember() {
    }

    public CoopPersonNonMember(Integer personNonMemberId) {
        this.personNonMemberId = personNonMemberId;
    }

    public Integer getPersonNonMemberId() {
        return personNonMemberId;
    }

    public void setPersonNonMemberId(Integer personNonMemberId) {
        this.personNonMemberId = personNonMemberId;
    }

    public CoopPerson getPersonId() {
        return personId;
    }

    public void setPersonId(CoopPerson personId) {
        this.personId = personId;
    }

    public CoopNonMember getNonMemberId() {
        return nonMemberId;
    }

    public void setNonMemberId(CoopNonMember nonMemberId) {
        this.nonMemberId = nonMemberId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (personNonMemberId != null ? personNonMemberId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoopPersonNonMember)) {
            return false;
        }
        CoopPersonNonMember other = (CoopPersonNonMember) object;
        if ((this.personNonMemberId == null && other.personNonMemberId != null) || (this.personNonMemberId != null && !this.personNonMemberId.equals(other.personNonMemberId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.CoopPersonNonMember[ personNonMemberId=" + personNonMemberId + " ]";
    }
    
}
