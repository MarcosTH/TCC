package br.com.SolutionProd.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.SolutionProd.DAO.FitaDAO;
import br.com.SolutionProd.DAO.ProdutoDAO;
import br.com.SolutionProd.domain.Fita;
import br.com.SolutionProd.domain.Produto;

@FacesConverter("produtoConverter")
public class ProdutoConverter implements Converter {
	
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent componente, String valor) {
		try{
			
			int codigo = Integer.parseInt(valor);
			ProdutoDAO dao = new ProdutoDAO();
			Produto produto =  dao.buscarPorCodigo(codigo);
			
			return produto;
			
		}catch(RuntimeException ex){
			return null;
		}
		
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent componente, Object objeto) {
		
try{
	
	Produto produto = (Produto) objeto;
	int codigo = produto.getId();
	String codigoS = String.valueOf(codigo);
	return codigoS;
	
		}catch(RuntimeException ex){
			return null;
		}
	}



}
