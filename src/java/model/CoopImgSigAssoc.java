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
@Table(name = "coop_img_sig_assoc")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CoopImgSigAssoc.findAll", query = "SELECT c FROM CoopImgSigAssoc c"),
    @NamedQuery(name = "CoopImgSigAssoc.findByImgSigAssocId", query = "SELECT c FROM CoopImgSigAssoc c WHERE c.imgSigAssocId = :imgSigAssocId")})
public class CoopImgSigAssoc implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "img_sig_assoc_id")
    private Integer imgSigAssocId;
    @JoinColumn(name = "img_sig_id", referencedColumnName = "img_sig_id")
    @ManyToOne
    private CoopImgSig imgSigId;
    @JoinColumn(name = "associate_no", referencedColumnName = "associate_no")
    @ManyToOne
    private CoopAssociate associateNo;

    public CoopImgSigAssoc() {
    }

    public CoopImgSigAssoc(Integer imgSigAssocId) {
        this.imgSigAssocId = imgSigAssocId;
    }

    public Integer getImgSigAssocId() {
        return imgSigAssocId;
    }

    public void setImgSigAssocId(Integer imgSigAssocId) {
        this.imgSigAssocId = imgSigAssocId;
    }

    public CoopImgSig getImgSigId() {
        return imgSigId;
    }

    public void setImgSigId(CoopImgSig imgSigId) {
        this.imgSigId = imgSigId;
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
        hash += (imgSigAssocId != null ? imgSigAssocId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoopImgSigAssoc)) {
            return false;
        }
        CoopImgSigAssoc other = (CoopImgSigAssoc) object;
        if ((this.imgSigAssocId == null && other.imgSigAssocId != null) || (this.imgSigAssocId != null && !this.imgSigAssocId.equals(other.imgSigAssocId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.CoopImgSigAssoc[ imgSigAssocId=" + imgSigAssocId + " ]";
    }
    
}
