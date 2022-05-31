package model;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class Funcionario {
	private int IdF;
	private String nome, funcao;
	private Date dataNascimento;
	private double cargaHoraria, valorHora;
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private final Locale BRASIL = new Locale("pt", "BR");
	private DecimalFormat df = new DecimalFormat("#.00");
	
	public Funcionario(int IdF) {
		this.IdF = IdF;
	}

	public Funcionario(int IdF, String nome, String funcao, String dataNascimento, double cargaHoraria) {
		df.setCurrency(Currency.getInstance(BRASIL));
		this.IdF = IdF;
		this.nome = nome;
		this.funcao = funcao;
		try {
			this.dataNascimento = sdf.parse(dataNascimento);
		} catch (ParseException e) {
			System.out.println(e);
		}
		this.cargaHoraria = cargaHoraria;
		this.valorHora = valorHora;
	}

	public Funcionario(String linha) {
		df.setCurrency(Currency.getInstance(BRASIL));
		this.IdF = Integer.parseInt(linha.split(";")[0]);
		this.nome = linha.split(";")[1];
		this.funcao = linha.split(";")[2];
		try {
			this.dataNascimento = sdf.parse(linha.split(";")[3]);
			this.valorHora = Double.parseDouble(linha.split(";")[4]);
			this.cargaHoraria = Double.parseDouble(linha.split(";")[5]);
		} catch (ParseException e) {
			System.out.println(e);
		}
	}

	public int getIdF() {
		return IdF;
	}
	
	public String getIdF(String s) {
		return String.format("%d", IdF);
	}

	public void setIdF(int IdF) {
		this.IdF = IdF;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}
	
	public Date getDataNascimento() {
		return dataNascimento;
	}
	
	public String getDataNascimento(String s) {
		return sdf.format(dataNascimento);
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public double getcargaHoraria() {
		return cargaHoraria;
	}
	
	public String getcargaHoraria(String s) {
		return String.format("%.2f", cargaHoraria);
	}

	public void setcargaHoraria(double cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}
	
	public double getvalorHora() {
		return valorHora;
	}
	
	public String getvalorHora(String s) {
		return String.format("%.2f", valorHora);
	}

	public void setvalorHora(double valorHora) {
		this.valorHora = valorHora;
	}
	
	@SuppressWarnings("deprecation")
	public int calcIdade() {
		return new Date().getYear() - dataNascimento.getYear();
	}

	@Override
	public int hashCode() {
		return Objects.hash(IdF);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Funcionario other = (Funcionario) obj;
		return IdF == other.IdF;
	}

	@Override
	public String toString() {
		return IdF + "\t" + nome + "\t" + funcao + "\t" + calcIdade() + ";" + String.format("%.2f", valorHora) + ";" + String.format("%.2f", cargaHoraria) + "\n";
	}
	
	public String toCSV() {
		return IdF + ";" + nome + ";" + funcao + ";" + sdf.format(dataNascimento)
				+ ";" + String.format("%.2f", valorHora)+ ";" + String.format("%.2f", cargaHoraria) + "\r\n";
	}
}
