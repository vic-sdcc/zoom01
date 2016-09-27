/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mis05
 */
@Entity
@Table(name = "coop_member_user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CoopMemberUser.findAll", query = "SELECT c FROM CoopMemberUser c"),
    @NamedQuery(name = "CoopMemberUser.findByMemNo", query = "SELECT c FROM CoopMemberUser c WHERE c.memNo = :memNo"),
    @NamedQuery(name = "CoopMemberUser.findByUserId", query = "SELECT c FROM CoopMemberUser c WHERE c.userId = :userId")})
public class CoopMemberUser implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "mem_no")
    private String memNo;
    @Column(name = "user_id")
    private BigInteger userId;

    public CoopMemberUser() {
    }

    public CoopMemberUser(String memNo) {
        this.memNo = memNo;
    }

    public String getMemNo() {
        return memNo;
    }

    public void setMemNo(String memNo) {
        this.memNo = memNo;
    }

    public BigInteger getUserId() {
        return userId;
    }

    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (memNo != null ? memNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoopMemberUser)) {
            return false;
        }
        CoopMemberUser other = (CoopMemberUser) object;
        if ((this.memNo == null && other.memNo != null) || (this.memNo != null && !this.memNo.equals(other.memNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.CoopMemberUser[ memNo=" + memNo + " ]";
    }
    
}
