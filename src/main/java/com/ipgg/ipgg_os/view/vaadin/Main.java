package com.ipgg.ipgg_os.view.vaadin;

import java.util.Locale;

import javax.servlet.annotation.WebServlet;

import com.ipgg.ipgg_os.model.common.OrdemServico;
import com.ipgg.ipgg_os.view.crudview.OrdemServicoFormViewForUpdate;
import com.ipgg.ipgg_os.view.crudview.PessoaFormInserirView;
import com.ipgg.ipgg_os.view.crudview.PessoaListView;
import com.ipgg.ipgg_os.view.crudview.PessoaListViewForDeletion;
import com.ipgg.ipgg_os.view.crudview.SistemaUsuarioFormViewForInsertion;
import com.ipgg.ipgg_os.view.crudview.SistemaUsuarioListView;
import com.ipgg.ipgg_os.view.crudview.SistemaUsuarioListViewForDeletion;
import com.ipgg.ipgg_os.view.crudview.SistemaUsuarioListViewForUpdate;
import com.ipgg.ipgg_os.view.vaadin.menu.MenuPrincipal;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * @author Clederson Cruz
 * 
 * Sistema web, construído em Java, para auxiliar na manutenção do prédio do
 * Instituto de Geriatria e Gerontologia - "José Ermírio Moraes" (IPGG -JEM)
 * através do controle de ordem de serviço.
 */
@Theme("meutema")
public class Main extends UI {

	public static String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath(); 
	public static Navigator navigator;
	
    @Override
    protected void init(VaadinRequest vaadinRequest) {    	
    	    	
    	VerticalLayout mainLayout = new VerticalLayout();
    	VerticalLayout menuLayout = new VerticalLayout();
    	VerticalLayout bodyLayout = new VerticalLayout();
    	
    	menuLayout.addComponent(MenuPrincipal.getInstance());
    	mainLayout.addComponent(menuLayout);
    	mainLayout.addComponent(bodyLayout);
    	
    	setContent(mainLayout);
    	
    	configNavigation(bodyLayout);
    	
    	getPage().setTitle("IPPG OS");    	
    	UI.getCurrent().setLocale(new Locale("pt-BR")); 
    	setSizeFull();    	

    }

	private void configNavigation(VerticalLayout bodyLayout) {
	
    	TelaSolicitacao telaSolicitacao = new TelaSolicitacao();
		TelaAvaliacao telaAvaliacao = new TelaAvaliacao();
		TelaFeedBack telaFeedBack = new TelaFeedBack();
		//TelaLogin telaLogin = new TelaLogin();
		TelaListaOS telaListaOS = new TelaListaOS();
		OrdemServicoFormViewForUpdate ordemServicoFormViewForUpdate = new OrdemServicoFormViewForUpdate();
		
		navigator = new Navigator(this, bodyLayout);
		navigator.addView("", telaListaOS);
		navigator.addView(TelaListaOS.VIEW_NAME, telaListaOS);
		navigator.addView(TelaSolicitacao.VIEW_NAME, telaSolicitacao);
		navigator.addView(TelaAvaliacao.VIEW_NAME, telaAvaliacao);
		navigator.addView(OrdemServicoFormViewForUpdate.VIEW_NAME, ordemServicoFormViewForUpdate);
		navigator.addView(TelaFeedBack.VIEW_NAME, telaFeedBack);
		navigator.addView(TelaListaOS.VIEW_NAME, telaListaOS);        
		navigator.addView(SistemaUsuarioFormViewForInsertion.VIEW_NAME, new SistemaUsuarioFormViewForInsertion());
		navigator.addView(SistemaUsuarioListViewForUpdate.VIEW_NAME, new SistemaUsuarioListViewForUpdate());
		navigator.addView(SistemaUsuarioListViewForDeletion.VIEW_NAME, new SistemaUsuarioListViewForDeletion());
		navigator.addView(SistemaUsuarioListView.VIEW_NAME, new SistemaUsuarioListView());
		navigator.addView(PessoaFormInserirView.VIEW_NAME, new PessoaFormInserirView(null, null));
		navigator.addView(PessoaListViewForDeletion.VIEW_NAME, new PessoaListViewForDeletion());
		navigator.addView(PessoaListView.VIEW_NAME, new PessoaListView());
	}
	

	public static void setValueInHttpSession(String attrName, Object value) {
		VaadinSession.getCurrent().setAttribute(attrName, value);
	}

	public static Object getValueFromHttpSession(String attrName) {
		return VaadinSession.getCurrent().getAttribute(attrName);
	}

    
    @WebServlet(urlPatterns = "/*", name = "MainWindowServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = Main.class, productionMode = false)
    public static class MainWindowServlet extends VaadinServlet {
    }
}
