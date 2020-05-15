package br.com.SolutionProd.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

import br.com.SolutionProd.DAO.FitaDAO;
import br.com.SolutionProd.DAO.FornecedorDAO;
import br.com.SolutionProd.domain.Fita;
import br.com.SolutionProd.domain.Fornecedor;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;



@FacesConverter("fitasConverter")
public class FitasConverter implements Converter {
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent componente, String valor) {
		try{
			
			int codigo = Integer.parseInt(valor);
			FitaDAO dao = new FitaDAO();
			Fita fita =  dao.buscarPorCodigo(codigo);
			
			return fita;
			
		}catch(RuntimeException ex){
			return null;
		}
		
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent componente, Object objeto) {
		
try{
	
	Fita fita = (Fita) objeto;
	int codigo = fita.getId();
	String codigoS = String.valueOf(codigo);
	return codigoS;
	
		}catch(RuntimeException ex){
			return null;
		}
	}


}
