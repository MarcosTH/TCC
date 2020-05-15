package br.com.SolutionProd.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.SolutionProd.DAO.FornecedorDAO;
import br.com.SolutionProd.DAO.UsuarioDAO;
import br.com.SolutionProd.domain.Fornecedor;
import br.com.SolutionProd.domain.Usuario;

@FacesConverter("usuarioConverter")
public class UsuarioConverter implements Converter {

	

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent componente, String valor) {
		try{
			
			int codigo = Integer.parseInt(valor);
			UsuarioDAO dao = new UsuarioDAO();
			Usuario usuario =  dao.buscarPorCodigo(codigo);
			
			return usuario;
			
		}catch(RuntimeException ex){
			return null;
		}
		
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent componente, Object objeto) {
		
try{
	
	Usuario usuario = (Usuario) objeto;
	int codigo = usuario.getId();
	String codigoS = String.valueOf(codigo);
	return codigoS;
	
		}catch(RuntimeException ex){
			return null;
		}
	}

	
	
}
