package com.ipgg.ipgg_os.view.crudview;


import java.io.File;


import org.hibernate.Session;

import com.ipgg.ipgg_os.model.sistema.SistemaUsuario;
import com.ipgg.ipgg_os.persistence.GenericHibernateDAOImp;
import com.ipgg.ipgg_os.persistence.HibernateUtil;
import com.ipgg.ipgg_os.persistence.IGenericDAO;
import com.ipgg.ipgg_os.view.vaadin.Main;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewBeforeLeaveEvent;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;



public class SistemaUsuarioFormViewForInsertion extends FormLayout implements View {

		public final static String VIEW_NAME = "sistema_usuario_form_insertion";
		public final static String FORM_CAPTION = "Formulario: Inserir Usuario do Sistema";
		private SistemaUsuario sistUsuario;
		private IGenericDAO<SistemaUsuario,Long> pDAO;
		
		public SistemaUsuarioFormViewForInsertion() {
			
			this.sistUsuario = new SistemaUsuario();
			Binder<SistemaUsuario> pessBinder = new Binder<>(SistemaUsuario.class);
			pessBinder.setBean(this.sistUsuario);
			
			this.setCaption(SistemaUsuarioFormViewForInsertion.FORM_CAPTION);		
			
			
			GridLayout gridLayout = new GridLayout(2,3);//2 colunas e 3 linhas
			gridLayout.setWidth("80%");
			gridLayout.setHeight("70%");
			
			// Mostra imagem a foto da pessoa
			// TODO: criar uma maneira pro upload da imagem da foto da pessoa
			// TODO: maneira de inserir primeiro uma pessoa e depois um usuario correspondente
			FileResource resource = new FileResource(new File(Main.basepath + "/WEB-INF/images/defaultAccountImg.jpg"));			
			Image image = new Image("Foto", resource);		
			image.setWidth("90%");
			image.setHeight("300px");

	        //Primeira coluna,Primeira linha ate a Primeira coluna mesmo e terceira linha
			gridLayout.addComponent(image, 0, 0, 0, 2);
			gridLayout.setComponentAlignment(image, Alignment.MIDDLE_CENTER);
			
			
			//TextField para login
			TextField txtLogin = new TextField("Login");
			txtLogin.setWidth("80%");
			txtLogin.setIcon(VaadinIcons.USER);			
			txtLogin.setRequiredIndicatorVisible(true);			
			pessBinder.forField(txtLogin).bind("login");		
					
			gridLayout.addComponent(txtLogin, 1, 0, 1, 0);
			gridLayout.setComponentAlignment(txtLogin, Alignment.TOP_LEFT);

			
			//TextField para Senha
			PasswordField txtSenha = new PasswordField("Senha");
			txtSenha.setWidth("80%");
			txtSenha.setIcon(VaadinIcons.CODE);
			
			txtLogin.setRequiredIndicatorVisible(true);
			pessBinder.forField(txtSenha).bind("senha");
			
			gridLayout.addComponent(txtSenha, 1, 1, 1, 1);

			Button btnSalvar = new Button("Salvar");
			btnSalvar.addClickListener((e)->{
				try {
					pessBinder.writeBean(this.sistUsuario);
					Session session = HibernateUtil.getSessionFactory().openSession();
					this.pDAO.beginTransaction();
					this.pDAO.inserir(this.sistUsuario);
					this.pDAO.commit();
					
					new Notification("Operacao Concluida com Sucesso",
						    "Msg de Operac Concluid com sucess",
						    Notification.Type.HUMANIZED_MESSAGE, true)
						    .show(Page.getCurrent());
				} catch (ValidationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
	                
			gridLayout.addComponent(btnSalvar, 1, 2, 1, 2);

//			JsLabel fzlJsLabel = new JsLabel("asdfasdfafds");
//			gridLayout.addComponent(fzlJsLabel);

			this.addComponent(gridLayout);

		}

		@Override
		public void enter(ViewChangeEvent event) {
			Session session = HibernateUtil.getSessionFactory().openSession();
			this.pDAO = new GenericHibernateDAOImp<>(session, SistemaUsuario.class, Long.class);
			View.super.enter(event);
		}

		@Override
		public void beforeLeave(ViewBeforeLeaveEvent event) {
			this.pDAO.closeSession();
			this.pDAO = null;
			View.super.beforeLeave(event);
		}

	}
