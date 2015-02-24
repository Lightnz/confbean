/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bonn.ooka.converter;

import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import org.bonn.ooka.conference.dao.JPADao;
import org.bonn.ooka.conference.dtos.Gutachter;

/**
 *
 * @author Fabian
 */
@Named("gutachterConverter")
@FacesConverter(value="gutachterConverter")
public class GutachterConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        List<Gutachter> gutachterList = (List<Gutachter>) context.getApplication().evaluateExpressionGet(context, "#{organizerController.gutachterListe}", List.class);
        
        for(Gutachter gutachter : gutachterList){
            if(gutachter.getId() == Integer.parseInt(value)){
                return gutachter;
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value instanceof Gutachter){
            return ""+((Gutachter)value).getId();
        }else{
            return null;
        }
    }
    
}
