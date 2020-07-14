/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stivenaguino.util;

import com.stivenaguino.model.Rol;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import org.primefaces.component.picklist.PickList;
import org.primefaces.model.DualListModel;

@FacesConverter("rolConverter")
public class RolConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return getObjectFromUIPickListComponent(component, value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object object) {
        String cadena;
        try {
            cadena = object == null ? "" : String.valueOf(((Rol) object).getIdRol());
        } catch (ClassCastException cce) {
            throw new ConverterException();
        }
        return cadena;
    }

    @SuppressWarnings("unchecked")
    private Rol getObjectFromUIPickListComponent(UIComponent component, String value) {
        final DualListModel<Rol> dualListModel;
        try {
            dualListModel = (DualListModel<Rol>) ((PickList) component).getValue();
            Rol rol = Utilities.nvl(
                    getObjectFromList(dualListModel.getSource(), Integer.valueOf(value)),
                    getObjectFromList(dualListModel.getTarget(), Integer.valueOf(value))
            );
            return rol;
        } catch (ClassCastException | NumberFormatException cce) {
            throw new ConverterException();
        }
    }

    private Rol getObjectFromList(final List<?> list, final Integer identifier) {
        for (final Object object : list) {
            final Rol rol = (Rol) object;
            if (rol.getIdRol().equals(identifier)) {
                return rol;
            }
        }
        return null;
    }

}
