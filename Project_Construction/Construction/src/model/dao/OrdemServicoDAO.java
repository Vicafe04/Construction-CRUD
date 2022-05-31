package model.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import model.OrdemServico;

public class OrdemServicoDAO {

	private BufferedReader br;
	private BufferedWriter bw;
	private String path = "./bd/ordensServicos.csv";
	
	public ArrayList<OrdemServico> ler() {
		ArrayList<OrdemServico> linhas = new ArrayList<>();
		OrdemServico os;
		try {
			br = new BufferedReader(new FileReader(path));
			String linha = br.readLine();
			while(linha != null) {
				os = new OrdemServico(linha);
				linhas.add(os);
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
	
	public void escrever(ArrayList<OrdemServico> linhas) {
		try {
			bw = new BufferedWriter(new FileWriter(path));
			for (OrdemServico os : linhas) {
				bw.write(os.toCSV());
			}
			bw.close();
		} catch (IOException e) {
			System.out.println(" Arquivo provavelmente está aberto."+e);
		}
	}
}
