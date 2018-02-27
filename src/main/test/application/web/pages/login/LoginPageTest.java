package application.web.pages.login;

import application.PersistenceConfig;
import application.SpringBeansCustomBeansConfig;
import application.WicketApplication;
import application.web.pages.BasePage;
import application.web.security.WebSecurity;
import com.giffing.wicket.spring.boot.starter.app.WicketBootWebApplication;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebSecurity.class, PersistenceConfig.class, SpringBeansCustomBeansConfig.class, WicketApplication.class})
public class LoginPageTest {
    @Autowired
    private WicketBootWebApplication wicketBootWebApplication;

    private WicketApplication application = (WicketApplication) wicketBootWebApplication;;

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