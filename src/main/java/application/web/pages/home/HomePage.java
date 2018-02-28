package application.web.pages.home;

import application.web.pages.BasePage;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.resource.CssResourceReference;
import org.wicketstuff.annotation.mount.MountPath;


import com.giffing.wicket.spring.boot.context.scan.WicketHomePage;

@MountPath("/home")
@WicketHomePage
public class HomePage extends BasePage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Override
	protected void onInitialize() {
		super.onInitialize();

		loginLink.setVisible(false);
		logoutLink.setVisible(true);
		cabinetLink.setVisible(true);
	}


	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);
		response.render(CssHeaderItem.forReference(new CssResourceReference(HomePage.class, "style.css")));
	}

	private String getBackgroundBodyImagePath() {
		return "Seychelles.jpg";
	}

}
