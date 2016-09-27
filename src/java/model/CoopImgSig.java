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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mis05
 */
@Entity
@Table(name = "coop_img_sig")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CoopImgSig.findAll", query = "SELECT c FROM CoopImgSig c"),
    @NamedQuery(name = "CoopImgSig.findByImgSigId", query = "SELECT c FROM CoopImgSig c WHERE c.imgSigId = :imgSigId"),
    @NamedQuery(name = "CoopImgSig.findBySig", query = "SELECT c FROM CoopImgSig c WHERE c.sig = :sig")})
public class CoopImgSig implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "img_sig_id")
    private Integer imgSigId;
    @Lob
    @Column(name = "img")
    private byte[] img;
    @Size(max = 2147483647)
    @Column(name = "sig")
    private String sig;
    @OneToMany(mappedBy = "imgSigId")
    private Collection<CoopImgSigAssoc> coopImgSigAssocCollection;
    @OneToMany(mappedBy = "imgSigId")
    private Collection<CoopImgSigMem> coopImgSigMemCollection;

    public CoopImgSig() {
    }

    public CoopImgSig(Integer imgSigId) {
        this.imgSigId = imgSigId;
    }

    public Integer getImgSigId() {
        return imgSigId;
    }

    public void setImgSigId(Integer imgSigId) {
        this.imgSigId = imgSigId;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public String getSig() {
        return sig;
    }

    public void setSig(String sig) {
        this.sig = sig;
    }

    @XmlTransient
    public Collection<CoopImgSigAssoc> getCoopImgSigAssocCollection() {
        return coopImgSigAssocCollection;
    }

    public void setCoopImgSigAssocCollection(Collection<CoopImgSigAssoc> coopImgSigAssocCollection) {
        this.coopImgSigAssocCollection = coopImgSigAssocCollection;
    }

    @XmlTransient
    public Collection<CoopImgSigMem> getCoopImgSigMemCollection() {
        return coopImgSigMemCollection;
    }

    public void setCoopImgSigMemCollection(Collection<CoopImgSigMem> coopImgSigMemCollection) {
        this.coopImgSigMemCollection = coopImgSigMemCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (imgSigId != null ? imgSigId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoopImgSig)) {
            return false;
        }
        CoopImgSig other = (CoopImgSig) object;
        if ((this.imgSigId == null && other.imgSigId != null) || (this.imgSigId != null && !this.imgSigId.equals(other.imgSigId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.CoopImgSig[ imgSigId=" + imgSigId + " ]";
    }
    
}
