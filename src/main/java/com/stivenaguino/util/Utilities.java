package com.stivenaguino.util;

import com.stivenaguino.emuns.Severity;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;

public class Utilities {

    public static <T> T nvl(T value, T alternateValue) {
        return (value == null) ? alternateValue : value;
    }

    public static void resetComponent(String component) {
        PrimeFaces.current().resetInputs(component);
    }

    public static <T> T getUserSession() {
        return (T) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(Constants.USER_SESSION);
    }

    public static void showMessage(String title, String content, Severity severity) {
        switch (severity) {
            case INFO:
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, title, content));
                break;
            case WARN:
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, title, content));
                break;
            case ERROR:
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, title, content));
                break;
            case FATAL:
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_FATAL, title, content));
                break;
        }

    }
}
