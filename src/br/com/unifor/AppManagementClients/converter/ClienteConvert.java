package br.com.unifor.AppManagementClients.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.com.unifor.AppManagementClients.model.Cliente;


public class ClienteConvert implements Converter{

	@Override 
	public Object getAsObject(FacesContext context, UIComponent component, String value) { 
		int index = value.indexOf(':');

		if (index != -1) { 
			return new Cliente(Integer.parseInt(value.substring(0, index)), value.substring(index + 1)); 
		} 
		return value; 
	} 

	@Override 
	public String getAsString(FacesContext context, UIComponent component, Object value) { 
		try { 
			Cliente optionItem = (Cliente) value; 
			return optionItem.getId() + ":" + optionItem.getNome(); 
		} catch (Exception e) { 
		} return (String) value; 
	} 

}