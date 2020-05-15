package br.com.SolutionProd.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import br.com.SolutionProd.DAO.FornecedorDAO;
import br.com.SolutionProd.domain.Fornecedor;


@FacesConverter("fornecedoresConverter")
public class FornecedoresConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent componente, String valor) {
		try{
			
			int codigo = Integer.parseInt(valor);
			FornecedorDAO dao = new FornecedorDAO();
			Fornecedor fornecedor =  dao.buscarPorCodigo(codigo);
			
			return fornecedor;
			
		}catch(RuntimeException ex){
			return null;
		}
		
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent componente, Object objeto) {
		
try{
	
	Fornecedor fornecedor = (Fornecedor) objeto;
	int codigo = fornecedor.getId();
	String codigoS = String.valueOf(codigo);
	return codigoS;
	
		}catch(RuntimeException ex){
			return null;
		}
	}

}
