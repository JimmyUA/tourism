package application.web.pages.touristinfo;

import application.Application;
import application.PersistenceConfig;
import application.SpringBeansCustomBeansConfig;
import application.web.security.WebSecurity;
import com.giffing.wicket.spring.boot.starter.app.WicketBootWebApplication;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebSecurity.class, PersistenceConfig.class, SpringBeansCustomBeansConfig.class, Application.class})
public class TouristInfoPageTest {
    @SpringBean
    private WicketBootWebApplication wicketBootWebApplication;

    private Application application = (Application) wicketBootWebApplication;;

    private WicketTester tester;

    @Before
    public void setUp() throws Exception {
        tester = new WicketTester();
        tester.startPage(TouristInfoPage.class);
    }


    @Test
    public void baseRender() throws Exception {

        tester.assertRenderedPage(TouristInfoPage.class);
    }

    @Test
    public void loginLabelShouldNotBeVisibleAtStart() throws Exception {
        tester.assertInvisible("loginLabel");
    }

    @Test
    public void loginLabelShouldBeVisibleAfterSubmitIfTouristIsFound() throws Exception {
        FormTester formTester = tester.newFormTester("form");
        formTester.submit("search");
        tester.assertVisible("loginLabel");
    }
}