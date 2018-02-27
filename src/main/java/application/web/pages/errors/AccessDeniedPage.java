package application.web.pages.errors;

import javax.servlet.http.HttpServletResponse;

import application.web.pages.BasePage;
import org.apache.wicket.request.http.WebResponse;
import org.wicketstuff.annotation.mount.MountPath;

import com.giffing.wicket.spring.boot.context.scan.WicketAccessDeniedPage;

@MountPath("problem")
@WicketAccessDeniedPage
public class AccessDeniedPage extends BasePage {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void setHeaders(final WebResponse response)
	{
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
	}
	
	@Override
	public boolean isErrorPage()
	{
		return true;
	}

	@Override
	public boolean isVersioned()
	{
		return false;
	}
	
}
