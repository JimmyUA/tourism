package application.web.pages.login;

import application.PersistenceConfig;
import application.SpringBeansCustomBeansConfig;
import application.Application;
import application.web.security.WebSecurity;
import com.giffing.wicket.spring.boot.starter.app.WicketBootWebApplication;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebSecurity.class, PersistenceConfig.class, SpringBeansCustomBeansConfig.class, Application.class})
public class LoginPageTest {
    @Autowired
    private WicketBootWebApplication wicketBootWebApplication;

    private Application application = (Application) wicketBootWebApplication;;

    private WicketTester tester;

    @Before
    public void setUp() throws Exception {
        tester = new WicketTester();
        tester.startPage(LoginPage.class);
    }


    @Test
    public void baseRender() throws Exception {

        tester.assertRenderedPage(LoginPage.class);
    }
}