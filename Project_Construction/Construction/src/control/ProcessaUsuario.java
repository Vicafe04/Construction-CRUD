package control;

import java.util.ArrayList;

import model.Usuario;
import model.dao.UsuarioDAO;
import model.Cripto;

public class ProcessaUsuario {
	
	public static ArrayList<Usuario> usuarios = new ArrayList<>();
	public static String UserConnected;
	private static UsuarioDAO ud = new UsuarioDAO();

	public static void abrir() {
		usuarios = ud.ler();
		if(usuarios.size() == 0) {
			usuarios.add(new Usuario("adm",Cripto.encripta("adm")));
		}
	}

	public static void salvar() {
		ud.escrever(usuarios);
	}
	
	public static int checarLogin(String email) {
		int retorno = -1;
		for (int i = 0; i < usuarios.size(); i++) {
			if (usuarios.get(i).getLogin().equals(email)) {
				return i;
			}
		}
		return retorno;
	}

	public static boolean checarSenha(int indice, String senha) {
		if (usuarios.get(indice).getSenha().equals(senha)) {
			UserConnected = usuarios.get(indice).getLogin();
			return true;
		}
		return false;
	}
}
