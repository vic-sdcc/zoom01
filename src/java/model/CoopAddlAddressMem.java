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
@Table(name = "coop_addl_address_mem")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CoopAddlAddressMem.findAll", query = "SELECT c FROM CoopAddlAddressMem c"),
    @NamedQuery(name = "CoopAddlAddressMem.findByAddlAddressMemId", query = "SELECT c FROM CoopAddlAddressMem c WHERE c.addlAddressMemId = :addlAddressMemId")})
public class CoopAddlAddressMem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "addl_address_mem_id")
    private Integer addlAddressMemId;
    @JoinColumn(name = "mem_no", referencedColumnName = "mem_no")
    @ManyToOne
    private CoopMember memNo;
    @JoinColumn(name = "addl_address_num", referencedColumnName = "addl_address_num")
    @ManyToOne
    private CoopAddlAddress addlAddressNum;

    public CoopAddlAddressMem() {
    }

    public CoopAddlAddressMem(Integer addlAddressMemId) {
        this.addlAddressMemId = addlAddressMemId;
    }

    public Integer getAddlAddressMemId() {
        return addlAddressMemId;
    }

    public void setAddlAddressMemId(Integer addlAddressMemId) {
        this.addlAddressMemId = addlAddressMemId;
    }

    public CoopMember getMemNo() {
        return memNo;
    }

    public void setMemNo(CoopMember memNo) {
        this.memNo = memNo;
    }

    public CoopAddlAddress getAddlAddressNum() {
        return addlAddressNum;
    }

    public void setAddlAddressNum(CoopAddlAddress addlAddressNum) {
        this.addlAddressNum = addlAddressNum;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (addlAddressMemId != null ? addlAddressMemId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoopAddlAddressMem)) {
            return false;
        }
        CoopAddlAddressMem other = (CoopAddlAddressMem) object;
        if ((this.addlAddressMemId == null && other.addlAddressMemId != null) || (this.addlAddressMemId != null && !this.addlAddressMemId.equals(other.addlAddressMemId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.CoopAddlAddressMem[ addlAddressMemId=" + addlAddressMemId + " ]";
    }
    
}
