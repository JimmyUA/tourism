package application.web.pages.home;

import application.web.pages.BasePage;
import org.apache.wicket.model.Model;
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
	}





}
