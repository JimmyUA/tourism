package application.web.pages.handletour;

import application.entity.Tour;
import application.entity.Tourist;
import application.service.TourService;
import application.service.TouristService;
import application.web.pages.base.BasePage;
import application.web.pages.touristinfo.TouristInfoPage;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

@MountPath("/tour")
public class HandleTourPage extends BasePage {

	@SpringBean
	private TouristService touristService;

	@SpringBean
	private TourService tourService;
	private Tour tour;
	private TextField<String> mobileTextField;
	private Label touristLogin;
	private String mobile;

	private static final long serialVersionUID = 1L;


	@Override
	protected void onInitialize() {
		super.onInitialize();

		setUpNavigationLinks();

		setUpLoginLabel();

		setUpMobileTextField();

	}

	private void setUpMobileTextField() {
		mobileTextField = new TextField<String>("mobileTextField", Model.of(mobile));
		AjaxEventBehavior ajaxEventBehavior = getAjaxFocusOutBehavior();
		mobileTextField.add(ajaxEventBehavior);
		add(mobileTextField);
	}

	private AjaxEventBehavior getAjaxFocusOutBehavior() {
		return new AjaxEventBehavior("focusout") {
			@Override
			protected void onEvent(AjaxRequestTarget ajaxRequestTarget) {
				mobile = mobileTextField.getInput();
				Tourist luckyGuy = touristService.getByMobile(mobile);
				if (luckyGuy != null) {
					touristLogin.
							setDefaultModel(Model.of(luckyGuy.getLogin()))
							.setVisible(true);
					mobileTextField.setModelObject(mobile);
					ajaxRequestTarget.add(touristLogin, mobileTextField, HandleTourPage.this);
				}
			}
		};
	}

	private void setUpLoginLabel() {
		touristLogin = new Label("touristLogin", Model.of(""));
		touristLogin
				.setOutputMarkupId(true)
				.setVisible(false);
		add(touristLogin);
	}

	private void setUpNavigationLinks() {
		loginLink.setVisible(false);
		logoutLink.setVisible(true);
		cabinetLink.setVisible(true);
	}

	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);
		response.render(CssHeaderItem.forReference(new CssResourceReference(HandleTourPage.class, "style.css")));
	}

}
