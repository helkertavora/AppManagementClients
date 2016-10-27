package br.com.unifor.AppManagementClients.converter;

import java.util.Iterator;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.primefaces.component.picklist.PickList;
import org.primefaces.model.DualListModel;

import br.com.unifor.AppManagementClients.model.Lazer;

@FacesConverter(value = "pickListConvert") 
public class PickListConvert implements Converter{

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) { 
		Lazer ret = null; 
		if (arg1 instanceof PickList) { 
			Object dualList = ((PickList) arg1).getValue(); 
			DualListModel<Lazer> dl = (DualListModel<Lazer>) dualList; 
			for (Iterator iterator = dl.getSource().iterator(); iterator 
					.hasNext();) { 
				Object o = iterator.next(); 
				String id = (new StringBuilder()).append( 
						((Lazer) o).getId()).toString(); 
				if (arg2.equals(id)) { 
					ret = (Lazer)o; 
					break; 
				} 
			} 

			if (ret == null) { 
				for (Iterator iterator1 = dl.getTarget().iterator(); iterator1 
						.hasNext();) { 
					Object o = iterator1.next(); 
					String id = (new StringBuilder()).append( 
							((Lazer) o).getId()).toString(); 
					if (arg2.equals(id)) { 
						ret = (Lazer)o; 
						break; 
					} 
				} 

			} 
		} 
		return ret; 
	} 

	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) { 
		String str = ""; 
		if (arg2 instanceof Lazer) 
			str = ((Lazer) arg2).getId().toString(); 
		return str; 
	} 
}