/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author vic
 */
@ManagedBean
@SessionScoped
public class dateStringParse implements Serializable {

    /**
     * Creates a new instance of dateStringParse
     */
    public dateStringParse() {
    }

    private Date dateReturn;
    private static final String[] formats = {
        "yyyy-MM-dd'T'HH:mm:ss'Z'", "yyyy-MM-dd'T'HH:mm:ssZ",
        "yyyy-MM-dd'T'HH:mm:ss", "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
        "yyyy-MM-dd'T'HH:mm:ss.SSSZ", "yyyy-MM-dd HH:mm:ss",
        "MM/dd/yyyy HH:mm:ss", "MM/dd/yyyy'T'HH:mm:ss.SSS'Z'",
        "MM/dd/yyyy'T'HH:mm:ss.SSSZ", "MM/dd/yyyy'T'HH:mm:ss.SSS",
        "MM/dd/yyyy'T'HH:mm:ssZ", "MM/dd/yyyy'T'HH:mm:ss",
        "yyyy:MM:dd HH:mm:ss", "yyyyMMdd",
        "yyyy-MM-dd", "EE MMM dd HH:mm:ss z yyyy", "MMM dd yyyy"};

    public Date getDateReturn() {
        if (dateReturn == null) {
            dateReturn = new Date();
        }
        return dateReturn;
    }

    public void setDateReturn(Date dateReturn) {
        this.dateReturn = dateReturn;
    }

    public Date parse(String d) {
        if (d != null) {
            for (String parse : formats) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(parse, Locale.ENGLISH);
                try {
                    return simpleDateFormat.parse(d);
                } catch (ParseException e) {
                }
            }
        }
        return new Date();
    }

}
