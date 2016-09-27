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
import org.primefaces.event.FlowEvent;

/**
 *
 * @author mis05
 */
@ManagedBean
@SessionScoped
public class Tabs implements Serializable {

    /**
     * Creates a new instance of Tabs
     */
    public Tabs() {
    }

    private boolean created, previous1 = true, previous2 = true, next1 = false, next2 = false, submit = true, img = true, btnSet1 = true;

    /*
     * getter setter
     */
    public boolean isCreated() {
        return created;
    }

    public void setCreated(boolean created) {
        this.created = created;
    }

    public boolean isPrevious1() {
        return previous1;
    }

    public void setPrevious1(boolean previous1) {
        this.previous1 = previous1;
    }

    public boolean isPrevious2() {
        return previous2;
    }

    public void setPrevious2(boolean previous2) {
        this.previous2 = previous2;
    }

    public boolean isNext1() {
        return next1;
    }

    public void setNext1(boolean next1) {
        this.next1 = next1;
    }

    public boolean isNext2() {
        return next2;
    }

    public void setNext2(boolean next2) {
        this.next2 = next2;
    }

    public boolean isSubmit() {
        return submit;
    }

    public void setSubmit(boolean submit) {
        this.submit = submit;
    }

    public boolean isImg() {
        return img;
    }

    public void setImg(boolean img) {
        this.img = img;
    }

    public boolean isBtnSet1() {
        return btnSet1;
    }

    public void setBtnSet1(boolean btnSet1) {
        this.btnSet1 = btnSet1;
    }

    /*
     * methods
     */
    public void beanclear() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("tabs", null);
    }

    public void takeFinalStep() {
        setCreated(true);
        setSubmit(true);
        setPrevious1(true);
        setNext1(false);
    }

    public String flow1(FlowEvent event) {
        if (event.getOldStep().equals("initialTab1")) {
            setPrevious1(false);
        }
        if (event.getNewStep().equals("initialTab1")) {
            setPrevious1(true);
        }
        if (event.getOldStep().equals("finalTab")) {
            setSubmit(true);
            setPrevious1(false);
            setNext1(false);
        }
        if (event.getNewStep().equals("finalTab")) {
            setSubmit(false);
            setPrevious1(false);
            setNext1(true);
        }
        if (event.getNewStep().equals("imagesTab")) {
            setNext1(true);
            setBtnSet1(false);
            setImg(false);
            setCreated(false);
        }
        return event.getNewStep();
    }

    public String flow2(FlowEvent event) {
        if (event.getOldStep().equals("initialTab2")) {
            setPrevious2(false);
        }
        if (event.getNewStep().equals("initialTab2")) {
            setPrevious2(true);
        }
        if (event.getOldStep().equals("skillsTab")) {
            setNext2(false);
        }
        if (event.getNewStep().equals("skillsTab")) {
            setNext2(true);
        }
        return event.getNewStep();
    }

}
