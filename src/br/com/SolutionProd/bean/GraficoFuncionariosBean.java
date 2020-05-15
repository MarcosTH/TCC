package br.com.SolutionProd.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartModel;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import br.com.SolutionProd.DAO.CustoDAO;
import br.com.SolutionProd.DAO.FuncionarioDAO;
import br.com.SolutionProd.DAO.ItemMaterialCustoDAO;
import br.com.SolutionProd.DAO.LoteDAO;
import br.com.SolutionProd.DAO.MaterialDAO;
import br.com.SolutionProd.DAO.ProdutoDAO;
import br.com.SolutionProd.domain.Custo;
import br.com.SolutionProd.domain.Funcionario;
import br.com.SolutionProd.domain.ItemMaterialCusto;
import br.com.SolutionProd.domain.Lote;
import br.com.SolutionProd.domain.Material;
import br.com.SolutionProd.domain.Produto;
import br.com.SolutionProd.util.JSFUtil;
import br.com.SolutionProd.util.ReportUtils;

@SuppressWarnings("serial")
@ManagedBean(name = "GraficoFuncionarios")
@ViewScoped
public class GraficoFuncionariosBean implements Serializable {

	private Funcionario funcionario;
	
	
	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	int somaLotef1;
	int somaLotef2;

    //Define um atributo do tipo lineModel onde podemos carregar
    //um gr�fico representado por linhas.
    private LineChartModel lineModel;    
    
    //Define um modelo que ir� armazenar as linhas do gr�fico 
    //e a s�rie de dados (pontos x,y).
    LineChartModel model = new LineChartModel();    
    
    //Define uma s�rie de dados (pontos x,y).
    LineChartSeries serie1 = new LineChartSeries();
    LineChartSeries serie2 = new LineChartSeries();
    LineChartSeries serie3 = new LineChartSeries();
    
    //Atrav�s deste m�todo get, o gr�fico ser� exibido.
    public LineChartModel getLineModel() {
        return lineModel;
    }
    
    //Executa este m�todo ap�s o Bean ser instanciado.
   @PostConstruct
    public void init() {
        criarLinhasModelo();
    }
    
    //Insere os dados dentro das cordenadas do modelo (a s�rie de dados).
    @SuppressWarnings("deprecation")
	private LineChartModel iniciarModeloLinear() {
        
        //Define um r�tulo na legenda para a s�rie de dados 1.
    	FuncionarioDAO fdao = new FuncionarioDAO();
    	Funcionario f1 = new Funcionario();
    	f1 = fdao.buscarPorCodigo(1);
    	Funcionario f2 = new Funcionario();
    	f2 = fdao.buscarPorCodigo(2);   	
    	Funcionario f3 = new Funcionario();
    	f3 = fdao.buscarPorCodigo(3);
    	
    	serie1.setLabel(f1.getNome());
    	serie2.setLabel(f2.getNome());
    	serie3.setLabel(f3.getNome());
    	
    	
        
        //Loop para inser��o de dados.
        
      LoteDAO ldao = new LoteDAO();
      serie1.getData().put(1, 200);
      serie1.getData().put(2, 100);
      serie1.getData().put(3, 300);
      serie1.getData().put(4, 300);
      serie1.getData().put(5, 400);
      serie1.getData().put(6, 150);
      serie1.getData().put(7, 200);
      serie1.getData().put(8, 200);
      serie1.getData().put(9, 300);
      serie1.getData().put(10, 400);
      
      serie2.getData().put(1, 200);
      serie2.getData().put(2, 300);
      serie2.getData().put(3, 300);
      serie2.getData().put(4, 400);
      serie2.getData().put(5, 200);
      serie2.getData().put(6, 100);
      serie2.getData().put(7, 400);
      serie2.getData().put(8, 300);
      serie2.getData().put(9, 350);
      serie2.getData().put(10, 390);
      
      serie3.getData().put(1, 100);
      serie3.getData().put(2, 120);
        
        //Insere a s�rie 1 dentro do modelo.
        model.addSeries(serie1);
        model.addSeries(serie2);
        model.addSeries(serie3);
      
        //Retorna o modelo com os dados para o atributo lineModel que por sua 
        //vez retorna para a tela atrav�s de seu m�todo get.  
        return model;
                
    } 
    
    //M�todo onde se cria as coordenadas do gr�fico e carrega o modelo,
    //tamb�m configura as cores, eixo x, y, etc.
    public void criarLinhasModelo() {
        
        //Armazena o modelo, permitindo que o m�todo get
        //retorne para a p�gina HTML.
        lineModel = iniciarModeloLinear();
        
        lineModel.setTitle(" Comparativo de Produtividade");    //Insere um t�tulo no gr�fico.
        lineModel.setLegendPosition("e");           //Define a posi��o da legenda.
        
        Axis yAxis = lineModel.getAxis(AxisType.Y); //Retorna o eixo Y.
        yAxis.setMin(0);                            //Eixo Y come�a com zero.
        yAxis.setMax(500);                           //Eixo Y termina em 50.
        yAxis.setTickFormat("%d");                  //Formata n�meros para inteiro.
        yAxis.setLabel("Quantidade");                 //Coloca um r�tulo no eixo Y.
        
        Axis xAxis = lineModel.getAxis(AxisType.X); //Retorna o eixo X.
        xAxis.setMin(1);                            //Eixo X come�a com zero.
        xAxis.setMax(12);                           //Eixo X termina em 12.
        xAxis.setTickFormat("%d");                  //Formata n�meros para inteiro.
        xAxis.setLabel("meses 2018");                    //Coloca um r�tulo no eixo Y.
        
    }                   
	 
	
	
	
public void visualizarPDF() throws Exception {
		
		//Cria uma mapa que pode utilizado para enviar todos os parametros do relat�rio
		Map<String, Object> parametros = new HashMap<String, Object>();
		//parametros.put("logo",  ((ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext()).getRealPath("/reports/".concat("logo.png")));
	
		//Define se o relat�rio ser� exibido no browser ou ser� feito download do arquivo gerado
	ReportUtils.contentDisposition = "";
		
		//M�todo com 3 parametros, mapa de parametros, o jasper do relat�rio e o nome do relat�rio que desejar
		ReportUtils.gerarRelatorio(parametros, "ProducaoMensal.jasper", "relat�rio de producao");
	}
	
	
	
	
	
	
	
	
	
	

}
