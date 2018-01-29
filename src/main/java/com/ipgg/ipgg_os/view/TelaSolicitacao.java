package com.ipgg.ipgg_os.view;

import com.vaadin.navigator.View;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

public class TelaSolicitacao extends FormLayout implements View {
	private TextField nos;
	private ComboBox<String> solicitante;
	private TextField gerencia;
	private TextField diretoria;
	private ComboBox<String> local;
	private ComboBox<String> servico;
	private ComboBox<String> tipoServico;
	private TextField bemPatrimonial;
	private TextField nPatrimonio;
	private TextArea descricaoServico;
	private ComboBox<String> grauNecessidade;
	private TextField rechamado;
	private TextField osAnterior;
	private Button confirmar;
	private Button cancelar;

	public TelaSolicitacao() {
		nos = new TextField("Nº O.S.");
		solicitante = new ComboBox<>("Solicitante Autorizado");
		gerencia = new TextField("Gerência");
		diretoria = new TextField("Diretoria");
		local = new ComboBox<>("Local");
		servico = new ComboBox<>("Serviço Solicitado");
		tipoServico = new ComboBox<>("Tipo de Serviço");
		bemPatrimonial = new TextField("Bem Patrimonial");
		nPatrimonio = new TextField("Nº de Patrimônio");
		descricaoServico = new TextArea("Descrição do Serviço a ser realizado");
		grauNecessidade = new ComboBox<>("Grau de necessidade");
		rechamado = new TextField("Rechamado");
		osAnterior = new TextField("O.S. anterior");

		confirmar = new Button("Confirmar");
		cancelar = new Button("Cancelar");

		HorizontalLayout footer = new HorizontalLayout();

		nos.setEnabled(false);
		osAnterior.setEnabled(false);

		addComponent(nos);
		addComponent(solicitante);
		addComponent(gerencia);
		addComponent(diretoria);
		addComponent(local);
		addComponent(servico);
		addComponent(tipoServico);
		addComponent(tipoServico);
		addComponent(nPatrimonio);
		addComponent(descricaoServico);
		addComponent(grauNecessidade);
		addComponent(rechamado);
		addComponent(osAnterior);

		footer.addComponent(confirmar);
		footer.addComponent(cancelar);
		addComponent(footer);
	}
}