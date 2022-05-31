package model;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class OrdemServico {
	private int idOS;
	private String descricao, endereco;
	private Date dataInicio, dataFim;
	private Date horaInicio, horaFim;
	private double valorOS;
	
	private final Locale BRASIL = new Locale("pt", "BR");
	private DecimalFormat decf = new DecimalFormat("#.00");
	private SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	private SimpleDateFormat hdf = new SimpleDateFormat("HH:mm");
	
	public OrdemServico(int idOS) {
		this.idOS = idOS;
	}

	public OrdemServico(int idOS, String descricao, String endereco, String dataInicio, String dataFim, String horaInicio,
			String horaFim, double valorOS) {
		this.idOS = idOS;
		this.descricao = descricao;
		this.endereco = endereco;
		try {
			this.dataInicio = df.parse(dataInicio);
			this.dataFim = df.parse(dataFim);
			this.horaInicio = hdf.parse(horaInicio);
			this.horaFim = hdf.parse(horaFim);
		} catch (ParseException e) {
			System.out.println(e);
		}
		this.valorOS = valorOS;
	}

	public OrdemServico(String linha) {
		this.idOS = Integer.parseInt(linha.split(";")[0]);
		this.descricao = linha.split(";")[1];
		this.endereco = linha.split(";")[2];
		try {
			this.dataInicio = df.parse(linha.split(";")[3]);
			this.dataFim = df.parse(linha.split(";")[4]);
			this.horaInicio = hdf.parse(linha.split(";")[5]);
			this.horaFim = hdf.parse(linha.split(";")[6]);
			this.valorOS = Double.parseDouble(linha.split(";")[7]);
		} catch (ParseException e) {
			System.out.println(e);
		}
	}

	public int getIdOS() {
		return idOS;
	}
	
	public String getIdOS(String s) {
		return String.format("%d", idOS);
	}

	public void setIdOS(int idOS) {
		this.idOS = idOS;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Date getDataInicio() {
		return dataInicio;
	}
	
	public String getDataInicio(String s) {
		return df.format(dataInicio);
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}
	
	public String getDataFim(String s) {
		return df.format(dataFim);
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Date getHoraInicio() {
		return horaInicio;
	}
	
	public String getHoraInicio(String s) {
		return hdf.format(horaInicio);
	}

	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Date getHoraFim() {
		return horaFim;
	}
	
	public String getHoraFim(String s) {
		return hdf.format(horaInicio);
	}

	public void setHoraFim(Date horaFim) {
		this.horaFim = horaFim;
	}

	public double getValorOS() {
		return valorOS;
	}

	public String getValorOS(String s) {
		return String.format("%.2f", valorOS);
	}
	
	public void setValorOS(double valorOS) {
		this.valorOS = valorOS;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idOS);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrdemServico other = (OrdemServico) obj;
		return idOS == other.idOS;
	}

	@Override
	public String toString() {
		return idOS + "\t" + descricao + "\t" + endereco + "\t" + df.format(dataInicio) + "\t" + 
	df.format(dataFim) + "\t" + hdf.format(horaInicio) + "\t" + hdf.format(horaFim) + "\t" + String.format("%.2f", valorOS) + "\n";
	}
	
	public String toCSV() {
		return idOS + ";" + descricao + ";" + endereco + ";" + df.format(dataInicio) + ";" 
	+ df.format(dataFim) + ";" + hdf.format(horaInicio) + ";" + hdf.format(horaFim) + ";" + String.format("%.2f", valorOS) + "\r\n";
	}
}
