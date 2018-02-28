package application.web.pages.base;

import application.web.pages.login.LoginPage;
import org.apache.wicket.Application;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.Session;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.OnChangeAjaxBehavior;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.resource.CssResourceReference;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class BasePage extends WebPage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MarkupContainer defaultModal;
	protected List<String> localeNames;
	protected DropDownChoice<String> localeDDC;
	protected Image logoImage;
	protected Link<String> loginLink;
	protected Link<String> cabinetLink;
	protected Link<String> logoutLink;


	@Override
	protected void onInitialize() {
		super.onInitialize();
		createLocalesDCC();
		createHeaderLinks();
	}


	private void createHeaderLinks() {
		loginLink = getLoginLink();
		logoutLink = getLogoutLink();
		cabinetLink = getCabinetLink();

		loginLink.add(new Label("label", new ResourceModel("loginLink")));
		logoutLink.add(new Label("label", new ResourceModel("logoutLink"))).setVisible(false);
		cabinetLink.add(new Label("label", new ResourceModel("cabinetLink"))).setVisible(false);

		add(loginLink, logoutLink, cabinetLink);

	}

	private Link<String> getLoginLink() {
		return new Link<String>("loginLink", new ResourceModel("loginLink")) {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(LoginPage.class);
			}
		};
	}

	private Link<String> getLogoutLink() {
		return new Link<String>("logoutLink") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(LoginPage.class);
			}
		};
	}

	private Link<String> getCabinetLink() {
		return new Link<String>("cabinetLink") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(LoginPage.class);
			}
		};
	}


	private void createLocalesDCC() {
		List<Locale> locales = Arrays.asList(Locale.ENGLISH, new Locale("ru"));
		Form ddcForm = new Form("ddcForm");
		final DropDownChoice<Locale> localeDDCSelection = new DropDownChoice<Locale>("changeLocale",
				getDDCModel(), locales, getLocalesChoiseRenderer());
		localeDDCSelection.add(new OnChangeAjaxBehavior() {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				target.add(getPage());
			}
			
			
			
		});
		localeDDCSelection.setModelObject(Session.get().getLocale());
		ddcForm.add(localeDDCSelection);
		add(ddcForm);
	}
	
	private IModel<Locale> getDDCModel(){
		return new IModel<Locale>() {

			private static final long serialVersionUID = 1L;

			@Override
			public Locale getObject() {
				return getSession().getLocale();
			}
			
			@Override
			public void setObject(Locale object) {
				if (object != null) {
					getSession().setLocale(object);
				}
			}
		};
	}

	private IChoiceRenderer<Locale> getLocalesChoiseRenderer() {
		return new IChoiceRenderer<Locale>() {

			private static final long serialVersionUID = 1L;

			@Override
			public Object getDisplayValue(Locale object) {
				String displayValue = object.getDisplayLanguage(object);
				return displayValue.substring(0, 1).toUpperCase() + displayValue.substring(1);
			}

			@Override
			public String getIdValue(Locale object, int index) {
				return object.getDisplayLanguage();
			}

			@Override
			public Locale getObject(String id, IModel<? extends List<? extends Locale>> choices) {
				List<? extends Locale> _choices = choices.getObject();
				for (int index = 0; index < _choices.size(); index++) {
					// Get next choice
					final Locale choice = _choices.get(index);
					if (getIdValue(choice, index).equals(id)) {
						return choice;
					}
				}
				return null;
			}
		};
	}
	
	public void replaceDefaultModal(ModalWindow newModal){
		defaultModal.replaceWith(newModal);
		defaultModal = newModal;
		defaultModal.setOutputMarkupId(true);
	}
	
	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);
		
		response.render(JavaScriptHeaderItem.forReference(getApplication().getJavaScriptLibrarySettings().getJQueryReference()));
		response.render(JavaScriptHeaderItem.forReference(getApplication().getJavaScriptLibrarySettings().getWicketEventReference()));
		response.render(JavaScriptHeaderItem.forReference(getApplication().getJavaScriptLibrarySettings().getWicketAjaxReference()));

		response.render(CssHeaderItem.forReference(new CssResourceReference(BasePage.class, "../css/style.css")));
	}
	
	@Override
	protected void onBeforeRender() {
		Application.get().getMarkupSettings().setStripWicketTags(true);
		super.onBeforeRender();
	}



}
