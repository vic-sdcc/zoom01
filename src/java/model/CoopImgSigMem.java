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
@Table(name = "coop_img_sig_mem")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CoopImgSigMem.findAll", query = "SELECT c FROM CoopImgSigMem c"),
    @NamedQuery(name = "CoopImgSigMem.findByImgSigMemId", query = "SELECT c FROM CoopImgSigMem c WHERE c.imgSigMemId = :imgSigMemId")})
public class CoopImgSigMem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "img_sig_mem_id")
    private Integer imgSigMemId;
    @JoinColumn(name = "mem_no", referencedColumnName = "mem_no")
    @ManyToOne
    private CoopMember memNo;
    @JoinColumn(name = "img_sig_id", referencedColumnName = "img_sig_id")
    @ManyToOne
    private CoopImgSig imgSigId;

    public CoopImgSigMem() {
    }

    public CoopImgSigMem(Integer imgSigMemId) {
        this.imgSigMemId = imgSigMemId;
    }

    public Integer getImgSigMemId() {
        return imgSigMemId;
    }

    public void setImgSigMemId(Integer imgSigMemId) {
        this.imgSigMemId = imgSigMemId;
    }

    public CoopMember getMemNo() {
        return memNo;
    }

    public void setMemNo(CoopMember memNo) {
        this.memNo = memNo;
    }

    public CoopImgSig getImgSigId() {
        return imgSigId;
    }

    public void setImgSigId(CoopImgSig imgSigId) {
        this.imgSigId = imgSigId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (imgSigMemId != null ? imgSigMemId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoopImgSigMem)) {
            return false;
        }
        CoopImgSigMem other = (CoopImgSigMem) object;
        if ((this.imgSigMemId == null && other.imgSigMemId != null) || (this.imgSigMemId != null && !this.imgSigMemId.equals(other.imgSigMemId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.CoopImgSigMem[ imgSigMemId=" + imgSigMemId + " ]";
    }
    
}
