package application.web.pages.handletour;

import application.service.TouristService;
import application.web.pages.base.BasePage;
import application.web.pages.touristinfo.TouristInfoPage;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

@MountPath("/tour")
public class HandleTourPage extends BasePage {

	@SpringBean
	private TouristService touristService;

	private RepeatingView repeater;
	private static final long serialVersionUID = 1L;


	@Override
	protected void onInitialize() {
		super.onInitialize();

		setUpNavigationLinks();

		repeater = new RepeatingView("repeater");
		add(repeater);

		touristService.getAllTouristsDB().forEach(tourist -> repeater.add(new Label(repeater.newChildId(), tourist.toString())));
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
