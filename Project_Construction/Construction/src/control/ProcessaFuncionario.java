package control;

import java.util.ArrayList;

import model.Funcionario;
import model.dao.FuncionarioDAO;

public class ProcessaFuncionario {

	public static ArrayList<Funcionario> funcionarios = new ArrayList<>();
	private static FuncionarioDAO fd = new FuncionarioDAO();
	
	public static void abrir() {
		funcionarios = fd.ler();
		if(funcionarios.size() == 0) {
			funcionarios.add(new Funcionario(1, "Roberto Souza", "Pedreiro", "03/10/1996", 60.50f));
		}
	}
	
	public static void salvar() {
		fd.escrever(funcionarios);
	}
}
