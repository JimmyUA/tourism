package application.web.pages.home;

import application.web.pages.base.BasePage;
import application.web.pages.handletour.HandleTourPage;
import application.web.pages.login.LoginPage;
import application.web.pages.touristinfo.TouristInfoPage;
import com.giffing.wicket.spring.boot.context.scan.WicketHomePage;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.SharedResourceReference;
import org.wicketstuff.annotation.mount.MountPath;

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


        Link<String> handleTour = getHandleTourLink();

        Image image = new Image("img", new SharedResourceReference(HomePage.class, "plane.png"));
        handleTour.add(image);
        add(handleTour);


        Link<String> touristInfo = getTouristInfoLink();

        Image touristImage = new Image("touristImage", new SharedResourceReference(HomePage.class, "diver.png"));

        touristInfo.add(touristImage);
        add(touristInfo);
    }

    private Link<String> getTouristInfoLink() {
        return new Link<String>("tourist") {
            @Override
            public void onClick() {
                setResponsePage(TouristInfoPage.class);
            }
        };
    }

    private Link<String> getHandleTourLink() {
        return new Link<String>("handleTour") {
            @Override
            public void onClick() {
                setResponsePage(HandleTourPage.class);
            }
        };
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
