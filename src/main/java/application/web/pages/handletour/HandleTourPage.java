package application.web.pages.handletour;

import application.entity.Tour;
import application.entity.Tourist;
import application.service.TourService;
import application.service.TouristService;
import application.web.pages.base.BasePage;
import application.web.pages.touristinfo.TouristInfoPage;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;
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
	private Tourist luckyGuy;
	private TextField<String> mobileTextField;
	private TextField<String> costTextField;
	private Label touristLogin;
	private String mobile;

	private static final long serialVersionUID = 1L;


	@Override
	protected void onInitialize() {
		super.onInitialize();

		setUpNavigationLinks();
		setUpStaticLabels();
		setUpLoginLabel();
		setUpTextFields();
		Form<Void> form = new Form<Void>("form");
		add(form);

		AjaxButton submit = new AjaxButton("submit") {
			@Override
			protected void onSubmit(AjaxRequestTarget target) {
				tour = new Tour();
				tour.setLuckyGuy(luckyGuy);
				Double enteredCost = Double.valueOf(costTextField.getInput());
				Double cost = (enteredCost * 100);
				tour.setCost(cost.intValue());
				tour.setTourDetailsId(1);  //TODO should be changed to real implementation
				tourService.saveTour(tour);
				//TODO add notification and maybe dialog asking about possibility to use bonus
			}
		};

		form.add(submit);
	}

	private void setUpTextFields() {
		setUpMobileTextField();
		setUpCostTextField();
	}

	private void setUpCostTextField() {
		costTextField = new TextField<>("costTextField", Model.of());
		add(costTextField);
	}

	private void setUpStaticLabels() {
		Label mobileLabel = new Label("mobileLabel", new ResourceModel("mobileLabel"));
		add(mobileLabel);
		Label tourCostLabel = new Label("tourCostNumber", new ResourceModel("tourCostNumber"));
		add(tourCostLabel);

	}

	private void setUpMobileTextField() {
		mobileTextField = new TextField<>("mobileTextField", Model.of(mobile));
		AjaxEventBehavior ajaxEventBehavior = getAjaxFocusOutBehavior();
		mobileTextField.add(ajaxEventBehavior);
		add(mobileTextField);
	}

	private AjaxEventBehavior getAjaxFocusOutBehavior() {
		return new AjaxEventBehavior("focusout") {
			@Override
			protected void onEvent(AjaxRequestTarget ajaxRequestTarget) {
				mobile = mobileTextField.getInput();
				luckyGuy = touristService.getByMobile(mobile);
				if (luckyGuy != null) {
					touristLogin.
							setDefaultModel(Model.of(luckyGuy.getLogin()))
							.setVisible(true);
					mobileTextField.setModelObject(mobile);
				} else {
					touristLogin.setDefaultModel(new ResourceModel("noTourist"));
				}
				ajaxRequestTarget.add(touristLogin, mobileTextField, HandleTourPage.this);
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
