package br.com.SolutionProd.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.BarChartSeries;

import br.com.SolutionProd.DAO.CustoDAO;
import br.com.SolutionProd.DAO.MaterialDAO;
import br.com.SolutionProd.util.ReportUtils;

@SuppressWarnings("serial")
@ManagedBean(name = "GraficoMateriais")
@ViewScoped
public class GiroBean implements Serializable {

	 private BarChartModel barra; 
		
	 MaterialDAO cdao = new MaterialDAO();
	 
	 
    BarChartSeries serie2 = new BarChartSeries();
	public BarChartModel getBarra() {
		return barra;
	}
	public void setBarra(BarChartModel barra) {
		this.barra = barra;
	}
	
	 @PostConstruct
	    public void init() {
	        criarBarrasModelo();
	    }
	    
		public BigDecimal percentual(BigDecimal margem, BigDecimal custo) {
			BigDecimal cem=new BigDecimal(100);
			BigDecimal resultado= (margem.multiply(cem)).divide(custo);
			
				
			return resultado;
			
			
		}
	  
	public void criarBarrasModelo() {
		barra = new BarChartModel();
		for(int i = 0; i < cdao.listarMaiores().size() ; i++ ) {
			 BarChartSeries serie1 = new BarChartSeries();
			 serie1.setLabel(cdao.listarMaiores().get(i).getNome());
			serie1.set("top", cdao.listarMaiores().get(i).getQuantidade());
			barra.addSeries(serie1);
			
		} 
		barra.setTitle("Materiais mais utilizados este ano");
		barra.setLegendPosition("ne");
		
		
		Axis xAxis = barra.getAxis(AxisType.X);
		xAxis.setLabel("Materiais");
		
		Axis yAxis = barra.getAxis(AxisType.Y);
		yAxis.setLabel("Quantidade comprada em 2018");
		yAxis.setMin(300.0);
		yAxis.setMax(6000.0);
		
		
	}  
	
	
public void visualizarPDF() throws Exception {
		
		//Cria uma mapa que pode utilizado para enviar todos os parametros do relatório
		Map<String, Object> parametros = new HashMap<String, Object>();
		//parametros.put("logo",  ((ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext()).getRealPath("/reports/".concat("logo.png")));
	
		//Define se o relatório será exibido no browser ou será feito download do arquivo gerado
		ReportUtils.contentDisposition = "";
		
		//Método com 3 parametros, mapa de parametros, o jasper do relatório e o nome do relatório que desejar
		ReportUtils.gerarRelatorio(parametros, "MateriaisGiro.jasper", "relatório de lucro");
	}
	
	
	
}
