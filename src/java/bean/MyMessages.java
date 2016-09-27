/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author mis05
 */
@ManagedBean
@RequestScoped
public class MyMessages implements Serializable {

    /**
     * Creates a new instance of MyMessages
     */
    public MyMessages() {
    }

    /*
     * methods
     */
    public void messages(String message) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        //facesContext.getExternalContext().getFlash().setKeepMessages(true);
        facesContext.addMessage(null, new FacesMessage(message + " member record successful.", ""));
    }

}
