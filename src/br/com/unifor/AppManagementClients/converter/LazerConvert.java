package br.com.unifor.AppManagementClients.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.unifor.AppManagementClients.model.Lazer;

@FacesConverter(forClass=Lazer.class) 
public class LazerConvert implements Converter{

	@Override 
	public Object getAsObject(FacesContext context, UIComponent component, String value) { 
		int index = value.indexOf(':');

		if (index != -1) { 
			return new Lazer(Integer.parseInt(value.substring(0, index)), value.substring(index + 1)); 
		} 
		return value; 
	} 

	@Override 
	public String getAsString(FacesContext context, UIComponent component, Object value) { 
		try { 
			Lazer optionItem = (Lazer) value; 
			return optionItem.getId() + ":" + optionItem.getDescricao(); 
		} catch (Exception e) { 
		} return (String) value; 
	} 
}