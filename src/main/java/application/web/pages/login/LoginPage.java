package application.web.pages.login;


import application.service.TourismUserDetailService;
import application.web.html.panel.FeedbackPanel;
import application.web.pages.BasePage;
import application.web.pages.home.HomePage;
import com.giffing.wicket.spring.boot.context.scan.WicketSignInPage;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.wicketstuff.annotation.mount.MountPath;

/**
 * Default login page.
 * 
 * @author Marc Giffing
 *
 */
@MountPath("/login")
@WicketSignInPage
public class LoginPage extends BasePage {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	FeedbackPanel feedbackPanel;
	RequiredTextField<String> userNameField;
	PasswordTextField passwordTextField;
	Button submitBtn;
	Label loginFormTitle;

	public LoginPage(PageParameters parameters) {
		super(parameters);

		if (((AbstractAuthenticatedWebSession) getSession()).isSignedIn()) {
			continueToOriginalDestination();
		}
		add(getLoginForm("loginForm"));
		getTitle().setDefaultModel(new ResourceModel("loginPageTitle"));
	}
	
	private Form getLoginForm(String id) {
		Form form = new Form(id);
		
		loginFormTitle = new Label("loginFormTitle", new ResourceModel("loginFormTitle"));
		feedbackPanel = new FeedbackPanel("feedback");
		userNameField = new RequiredTextField<String>("username", Model.of(""));
		passwordTextField = new PasswordTextField("password", Model.of(""));
		submitBtn = new Button("submitBtn", new ResourceModel("loginBtn")) {

			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {
				AuthenticatedWebSession session = AuthenticatedWebSession.get();
				if (session.signIn(userNameField.getModelObject(), passwordTextField.getModelObject())) {
					setResponsePage(HomePage.class);
				} else {
					error("Login failed");
				}
			}
		};
		form.add(feedbackPanel,
				userNameField.add(new AttributeAppender("placeholder", new ResourceModel("usernamePlaceholder"))),
				passwordTextField.add(new AttributeAppender("placeholder", new ResourceModel("passwordPlaceholder"))),
				submitBtn, loginFormTitle);
		return form;
		
	}
	
	@Override
		public void renderHead(IHeaderResponse response) {
			response.render(CssHeaderItem.forReference(new CssResourceReference(BasePage.class, "../css/authorize.css")));
			super.renderHead(response);
		}
}
