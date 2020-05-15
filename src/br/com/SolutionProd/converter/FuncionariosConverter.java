package br.com.SolutionProd.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.SolutionProd.DAO.FitaDAO;
import br.com.SolutionProd.DAO.FuncionarioDAO;
import br.com.SolutionProd.domain.Fita;
import br.com.SolutionProd.domain.Funcionario;

@FacesConverter("funcionariosConverter")
public class FuncionariosConverter implements Converter {
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent componente, String valor) {
		try{
			
			int codigo = Integer.parseInt(valor);
			FuncionarioDAO dao = new FuncionarioDAO();
			Funcionario fun =  dao.buscarPorCodigo(codigo);
			
			return fun;
			
		}catch(RuntimeException ex){
			return null;
		}
		
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent componente, Object objeto) {
		
try{
	
	Funcionario fun = (Funcionario) objeto;
	int codigo = fun.getId();
	String codigoS = String.valueOf(codigo);
	return codigoS;
	
		}catch(RuntimeException ex){
			return null;
		}
	}

}
