package br.com.SolutionProd.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperRunManager;

/**
 * @author Henrique Santiago henriquesantiagofranco@gmail.com
 * @date 23/11/2018 - 22:46:36
 *
 */
public class ReportUtils implements Serializable {

	private static final long serialVersionUID = -1794790347153129672L;
	
	static final String contentType = "PDF";
	public static String contentDisposition = "inline";

	public ReportUtils() {
	}

	/**
	 * 
	 * @author Henrique Santiago henriquesantiagofranco@gmail.com
	 * @date 24/11/2018 - 00:42:49
	 *
	 * RN: Gera o relat�rio em PDF
	 *
	 * @param parametros
	 * @param nomeArquivoJasper
	 * @param nomeRelatorio
	 * @throws Exception
	 * @throws IOException
	 */
	public static void gerarRelatorio(Map<String, Object> parametros, String nomeArquivoJasper, String nomeRelatorio) throws Exception, IOException {
		//Sen�o for utilizado parametros,sera criado um objeto vazio, evitando erros de programa��o
		if (parametros == null || parametros.size() == 0) {
			parametros = new HashMap<>();
		}
		
		//Verifica se o jasper foi informado
		if(nomeArquivoJasper != null && !nomeArquivoJasper.isEmpty()) {
			if (!nomeArquivoJasper.contains(".jasper")) {
				throw new Exception("Arquivo n�o � v�lido! Verifique se � um arquivo Jasper!");
			}
			//Recupera o arquivo no diret�rio reports; exemplo: |---- /reports/custos.jasper ----|
			InputStream is =  FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/reports/" + nomeArquivoJasper);
			if (is == null) { //Se o arquivo n�o for encontrado lan�a exce��o
				throw new Exception("Arquivo n�o encontrado: " + nomeArquivoJasper);
			}
			
			/**
			 * Gera o arquivo pdf com o m�todo runReportToPdf, recebe como parametros o arquivo localizado 
			 * na a��o acima [arquivo (is)];
			 * 
			 * Passa o mapa de parametros preenchidos anteriormente;
			 * 
			 * Recupera a conex�o utilizada pelo hibernate e passa como parametro
			 */
			byte[] relatorio = JasperRunManager.runReportToPdf(is, parametros, HibernateUtil.getConexao()); 
			if (relatorio == null) {
				throw new Exception("N�o foi poss�vel gerar relat�rio, verifique o caminho do arquivo!");
			}
			
			
			FacesContext context = FacesContext.getCurrentInstance();
			HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();

			/**
			 * Se o contentDisposition for inline o relat�rio ser� exibido no browser;
			 * Se o contentDisposition for diferente de inline ser� feito o download do arquivo
			 */
			if (contentDisposition.equals("inline")) {
				response.setContentType("application/pdf");
				response.setHeader("Content-Disposition", "" + contentDisposition + "\"; filename=\"" + nomeRelatorio + "." + contentType + "");
			}else {
				response.setContentType("application/octet-stream");
				response.setHeader("Content-Disposition", "attachment;filename=\"" + nomeRelatorio + "." + contentType + "\"");
			}

			/**
			 * Seta os dados no response e completa a resposta renderizando a exibi��o do arquivo
			 */
			response.setContentLength(relatorio.length);
			response.getOutputStream().write(relatorio);
			response.getOutputStream().flush();
			response.getOutputStream().close();
			context.renderResponse();
			context.responseComplete();;
		}else {
			throw new Exception("Arquivo do relat�rio <Jasper> n�o foi informado!");
		}
	}
	
}
