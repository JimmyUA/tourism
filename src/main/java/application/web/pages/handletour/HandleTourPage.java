package application.web.pages.handletour;

import application.entity.database.TouristBD;
import application.service.TouristService;
import application.web.pages.base.BasePage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@MountPath("/tour")
public class HandleTourPage extends BasePage {

	@SpringBean
	private TouristService touristService;

	private RepeatingView repeater;
	private static final long serialVersionUID = 1L;


	@Override
	protected void onInitialize() {
		super.onInitialize();

		loginLink.setVisible(false);
		logoutLink.setVisible(true);
		cabinetLink.setVisible(true);
	}
	
}
