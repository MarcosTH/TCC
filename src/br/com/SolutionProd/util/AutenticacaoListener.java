package br.com.SolutionProd.util;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.omnifaces.util.Faces;

import br.com.SolutionProd.bean.AutenticacaoBean;
import br.com.SolutionProd.domain.Usuario;




public class AutenticacaoListener implements PhaseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1019300610778861130L;

	/**
	 * 
	 */
	

	@Override
	public void afterPhase(PhaseEvent arg0) {
		
		String paginaAtual = Faces.getViewId();
		
		
		boolean pagAut = paginaAtual.contains("Autenticacao.xhtml");
		
		if(!pagAut){
			AutenticacaoBean autenticacaoBean = Faces.getSessionAttribute("AutenticacaoMB");
		
			if(autenticacaoBean == null){
				Faces.navigate("/pages/Autenticacao.xhtml");
				return;
			}
			
			Usuario usuario = autenticacaoBean.getUsuarioLogado();
				
			
			if(usuario == null){
				Faces.navigate("/pages/autenticacao.xhtml");
				return;
			}
		
		}
		
		
	}

	@Override
	public void beforePhase(PhaseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PhaseId getPhaseId() {
		// TODO Auto-generated method stub
		return PhaseId.RESTORE_VIEW;
	}

}
