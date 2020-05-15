package br.com.SolutionProd.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Ignore;
import org.junit.Test;

import br.com.SolutionProd.DAO.FornecedorDAO;
import br.com.SolutionProd.DAO.FuncionarioDAO;
import br.com.SolutionProd.DAO.UsuarioDAO;
import br.com.SolutionProd.domain.Fornecedor;
import br.com.SolutionProd.domain.Funcionario;
import br.com.SolutionProd.domain.Usuario;


public class UsuarioTest {

	

@Test

	public void salvar() throws ParseException{
		
	Usuario u = new Usuario();
	Funcionario f = new Funcionario();
	FuncionarioDAO fdao = new FuncionarioDAO();
	UsuarioDAO udao = new UsuarioDAO();
	
	f = fdao.buscarPorCodigo(1);
	u.setFuncionario(f);
	u.setSenhaSemCriptografia("666666");
	SimpleHash hash = new SimpleHash("md5", u.getSenhaSemCriptografia());
	u.setSenha(hash.toHex());
	u.setTipo('G');
	u.setAtivo(true);	
	udao.salvar(u);
		
	}

@Test
@Ignore
public void autenticar() {
	
	
	String senha = "666666";
	String nome = "Marcos";
	Usuario u = new Usuario();
	UsuarioDAO udao = new UsuarioDAO();
	u = udao.autenticar(nome, senha);
	System.out.println(u);
	
}

@Test
@Ignore
public void listar(){
	UsuarioDAO dao = new UsuarioDAO();
	List<Usuario> usuarios = dao.listar();
	
	for(Usuario usuario : usuarios){
		System.out.println(usuario);
		
		
	}
}





}
