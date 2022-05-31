package model.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import model.Funcionario;

public class FuncionarioDAO {

	private BufferedReader br;
	private BufferedWriter bw;
	private String path = "./bd/funcionarios.csv";
	
	public ArrayList<Funcionario> ler() {
		ArrayList<Funcionario> linhas = new ArrayList<>();
		Funcionario f;
		try {
			br = new BufferedReader(new FileReader(path));
			String linha = br.readLine();
			while(linha != null) {
				f = new Funcionario(linha);
				linhas.add(f);
				linha = br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo não encontrado."+e);
		} catch (IOException e) {
			System.out.println(" Arquivo provavelmente está aberto."+e);
		}
		return linhas;
	}
	
	public void escrever(ArrayList<Funcionario> linhas) {
		try {
			bw = new BufferedWriter(new FileWriter(path));
			for (Funcionario f : linhas) {
				bw.write(f.toCSV());
			}
			bw.close();
		} catch (IOException e) {
			System.out.println(" Arquivo provavelmente está aberto."+e);
		}
	}
}
