package application.web.pages.touristinfo;

import application.entity.Tourist;
import application.service.TouristService;
import application.web.pages.base.BasePage;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.spring.injection.annot.SpringBean;


public class TouristInfoPage extends BasePage{

    private TextField<String> touristSearch;
    private Tourist tourist;

    @SpringBean
    private TouristService touristService;

    @Override
    protected void onInitialize() {
        super.onInitialize();
        setUpNavigationLinks();
    }

    private void setUpNavigationLinks() {
        loginLink.setVisible(false);
        logoutLink.setVisible(true);
        cabinetLink.setVisible(true);

        Form<Void> form = new Form<Void>("form");

        touristSearch = new TextField<>("touristSearch");
        AjaxButton search = new AjaxButton("search") {
            @Override
            protected void onSubmit(AjaxRequestTarget target) {
                String touristMobile = touristSearch.getDefaultModelObjectAsString();

                tourist = touristService.getByMobile(touristMobile);
            }
        };

        add(form);
        form.add(touristSearch);
        form.add(search);
    }


}
