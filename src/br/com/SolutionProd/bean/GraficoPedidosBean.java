package br.com.SolutionProd.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.BarChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import br.com.SolutionProd.DAO.CustoDAO;
import br.com.SolutionProd.DAO.FornecedorDAO;
import br.com.SolutionProd.DAO.MaioresPedidosDAO;
import br.com.SolutionProd.DAO.NFDAO;
import br.com.SolutionProd.domain.Custo;
import br.com.SolutionProd.util.ReportUtils;

@SuppressWarnings("serial")
@ManagedBean(name = "GraficoPedidos")
@ViewScoped
public class GraficoPedidosBean implements Serializable {

	 private BarChartModel barra; 
	
	 MaioresPedidosDAO mdao = new MaioresPedidosDAO();
 
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
		for(int i = 0; i< mdao.listar().size() ; i++ ) {
			 BarChartSeries serie1 = new BarChartSeries();
			 serie1.setLabel(mdao.listar().get(i).getNome());
			serie1.set("Valor ", mdao.listar().get(i).getSoma());
			barra.addSeries(serie1);
			
		} 
		barra.setTitle("Maiores fornecedores");
		barra.setLegendPosition("ne");
		
		
		Axis xAxis = barra.getAxis(AxisType.X);
		xAxis.setLabel("Fornecedor");
		
		Axis yAxis = barra.getAxis(AxisType.Y);
		yAxis.setLabel("Volume comprado");
		yAxis.setMin(0.0);
		yAxis.setMax(30000.0);
		
		
	}  
	
	
public void visualizarPDF() throws Exception {
		
		//Cria uma mapa que pode utilizado para enviar todos os parametros do relatório
		Map<String, Object> parametros = new HashMap<String, Object>();
		//parametros.put("logo",  ((ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext()).getRealPath("/reports/".concat("logo.png")));
	
		//Define se o relatório será exibido no browser ou será feito download do arquivo gerado
		ReportUtils.contentDisposition = "";
		
		//Método com 3 parametros, mapa de parametros, o jasper do relatório e o nome do relatório que desejar
		ReportUtils.gerarRelatorio(parametros, "MaioresPedidos.jasper", "relatório de pedidos");
	}
	
	
	
	
	
	
	
	
}
