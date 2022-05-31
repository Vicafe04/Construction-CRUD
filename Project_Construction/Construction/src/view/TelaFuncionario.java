package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import control.ProcessaFuncionario;
import model.Funcionario;

public class TelaFuncionario extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel painel;
	private JLabel idF, funcao, nome, cargaHoraria, dataNascimento, valorHora;
	private JTextField tfIdF, tfNome, tfCargaHoraria, tfDataNascimento, tfValorHora;
	private JTextArea verResultados;
	private JScrollPane rolagem;
	private JComboBox<String> cbFuncao;
	private JButton create, read, update, delete;
	private String texto = "";
	
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private final Locale BRASIL = new Locale("pt", "BR");
	private DecimalFormat df = new DecimalFormat("#.00");
	private int autoId = ProcessaFuncionario.funcionarios.get(ProcessaFuncionario.funcionarios.size()-1).getIdF() + 1;

	

	TelaFuncionario () {
		setTitle("Construction Funcionarios");
		setBounds(150, 170, 800, 600);
		painel = new JPanel();
		painel.setBackground(new Color(255, 233, 213));
		setContentPane(painel);
		setLayout(null);

		idF = new JLabel("Id:");
		idF.setBounds(20, 20, 120, 30);
		painel.add(idF);
		funcao = new JLabel("Função:");
		funcao.setBounds(20, 130, 120, 30);
		painel.add(funcao);
		nome = new JLabel("Nome:");
		nome.setBounds(20, 55, 120, 30);
		painel.add(nome);
		
		cargaHoraria = new JLabel("Carga Horaria:");
		cargaHoraria.setBounds(20, 165, 120, 30);
		painel.add(cargaHoraria);
		dataNascimento = new JLabel("Nascimento:");
		dataNascimento.setBounds(20, 95, 120, 30);
		painel.add(dataNascimento);
		valorHora = new JLabel("Valor por Hora:");
		valorHora.setBounds(20, 200, 120, 30);
		painel.add(valorHora);
		tfIdF = new JTextField(String.format("%d", autoId));
		tfIdF.setEditable(false);
		tfIdF.setBounds(140, 25, 40, 30);
		painel.add(tfIdF);
		cbFuncao = new JComboBox<String>(new String[] { "Eletricista", "Construtor", "Detetizador", "Cortador de grama" });
		cbFuncao.setBounds(140, 130, 255, 30);
		painel.add(cbFuncao);
		tfNome = new JTextField();
		tfNome.setBounds(140, 60, 255, 30);
		painel.add(tfNome);
		tfCargaHoraria = new JTextField();
		tfCargaHoraria.setBounds(140, 165, 255, 30);
		painel.add(tfCargaHoraria);
		tfDataNascimento = new JTextField();
		tfDataNascimento.setBounds(140, 95, 255, 30);
		painel.add(tfDataNascimento);
		tfValorHora = new JTextField();
		tfValorHora.setBounds(140, 200, 255, 30);
		painel.add(tfValorHora);
		verResultados = new JTextArea();
		verResultados.setEditable(false);
		verResultados.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		preencherAreaDeTexto();
		rolagem = new JScrollPane(verResultados);
		rolagem.setBounds(20, 340, 740, 200);
		painel.add(rolagem);

		create = new JButton("Cadastrar");
		read = new JButton("Buscar");
		update = new JButton("Atualizar");
		delete = new JButton("Excluir");
		create.setBounds(545, 55, 110, 50);
		read.setBounds(425, 55, 110, 50);
		update.setBounds(425, 110, 110, 50);
		delete.setBounds(545, 110, 110, 50);
		update.setEnabled(false);
		delete.setEnabled(false);
		painel.add(create);
		painel.add(read);
		painel.add(update);
		painel.add(delete);

		// Ouvir os eventos dos Botoes, ComboBox e outros
		
		create.addActionListener(this);
		read.addActionListener(this);
		update.addActionListener(this);
		delete.addActionListener(this);

	}
	
	private void preencherAreaDeTexto() {
		texto = ""; // Limpar a área de texto antes de preenher
		for (Funcionario f : ProcessaFuncionario.funcionarios) {
			texto += f.toString();
		}
		verResultados.setText(texto);
	}
	
	private void cadastrar() {
		if (tfIdF.getText().length() != 0 
			&& tfNome.getText().length() != 0 
			&& tfDataNascimento.getText().length() != 0 
			&& tfValorHora.getText().length() != 0
			&& tfCargaHoraria.getText().length() != 0) {

			// Converter o peso no formato Brasileiro usando virgula como decimal
			df.setCurrency(Currency.getInstance(BRASIL));
			double valorHora;
			try {
				valorHora = Double.parseDouble(df.parse(tfValorHora.getText()).toString());
			} catch (ParseException e) {
				System.out.println(e);
				valorHora = 0;
			}

			ProcessaFuncionario.funcionarios.add(new Funcionario(autoId, cbFuncao.getSelectedItem().toString(), tfNome.getText(),
					tfCargaHoraria, valorHora, tfDataNascimento.getText()));
			autoId++;
			preencherAreaDeTexto();
			limparCampos();
		} else {
			JOptionPane.showMessageDialog(this, "Favor preencher todos os campos.");
		}
	}

	private void limparCampos() {
		tfNome.setText(null);
		tfIdF.setText(null);
		tfDataNascimento.setText(null);
		tfValorHora.setText(null);
		tfCargaHoraria.setText(null);
		create.setEnabled(true);
		update.setEnabled(false);
		delete.setEnabled(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}

	public static void main(String[] args) {
		ProcessaFuncionario.abrir();
		new TelaFuncionario().setVisible(true);
	}
	
	
}