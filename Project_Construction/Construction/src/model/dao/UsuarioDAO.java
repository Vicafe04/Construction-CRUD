package model.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import model.Usuario;

public class UsuarioDAO {

	private BufferedReader br;
	private BufferedWriter bw;
	private String path = "./bd/usuarios.csv";
	
	public ArrayList<Usuario> ler() {
		ArrayList<Usuario> linhas = new ArrayList<>();
		Usuario u;
		try {
			br = new BufferedReader(new FileReader(path));
			String linha = br.readLine();
			while(linha != null) {
				u = new Usuario(linha);
				linhas.add(u);
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
	
	public void escrever(ArrayList<Usuario> linhas) {
		try {
			bw = new BufferedWriter(new FileWriter(path));
			for (Usuario u : linhas) {
				bw.write(u.toCSV());
			}
			bw.close();
		} catch (IOException e) {
			System.out.println(" Arquivo provavelmente está aberto."+e);
		}
	}
}
