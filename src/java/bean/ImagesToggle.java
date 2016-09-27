/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author mis05
 */
@ManagedBean
@SessionScoped
public class ImagesToggle implements Serializable {

    /**
     * Creates a new instance of ImagesToggle
     */
    public ImagesToggle() {
    }

    private boolean img, sig, id, let;

    /*
     * getter setter
     */
    public boolean isImg() {
        return img;
    }

    public void setImg(boolean img) {
        this.img = img;
    }

    public boolean isSig() {
        return sig;
    }

    public void setSig(boolean sig) {
        this.sig = sig;
    }

    public boolean isId() {
        return id;
    }

    public void setId(boolean id) {
        this.id = id;
    }

    public boolean isLet() {
        return let;
    }

    public void setLet(boolean let) {
        this.let = let;
    }

    /*
     * methods
     */
    public void beanclear() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("imagesToggle", null);
    }

    public void imgToggle() {
        if (isImg()) {
            setImg(false);
        } else {
            setImg(true);
        }
    }

    public void sigToggle() {
        if (isSig()) {
            setSig(false);
        } else {
            setSig(true);
        }
    }

    public void idToggle() {
        if (isId()) {
            setId(false);
        } else {
            setId(true);
        }
    }

    public void letToggle() {
        if (isLet()) {
            setLet(false);
        } else {
            setLet(true);
        }
    }

}
