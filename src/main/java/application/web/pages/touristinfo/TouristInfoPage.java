package application.web.pages.touristinfo;

import application.entity.Tourist;
import application.service.TouristService;
import application.web.pages.base.BasePage;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class TouristInfoPage extends BasePage{

    private TextField<String> touristSearch;
    protected Tourist tourist;
    private Form<Void> form;
    private List<Label> labels;

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

        form = new Form<Void>("form");
        form.setOutputMarkupId(true);

        setLabels();

        touristSearch = new TextField<>("touristSearch", Model.of(""));
        AjaxButton search = getSearchButton();

        add(form);
        form.add(touristSearch);
        form.add(search);
    }

    private void setLabels() {

        Label login = new Label("loginLabel", new ResourceModel("loginLabel"));
        Label mobile = new Label("mobileLabel", new ResourceModel("mobileLabel"));
        Label email = new Label("emailLabel", new ResourceModel("emailLabel"));
        Label bonus = new Label("bonusLabel", new ResourceModel("bonusLabel"));
        Label usedBonus = new Label("usedBonusLabel", new ResourceModel("usedBonusLabel"));
        Label parentLogin = new Label("parentLoginLabel", new ResourceModel("parentLoginLabel"));
        Label info = new Label("infoLabel", new ResourceModel("infoLabel"));

        labels = Arrays.asList(login, mobile, email, bonus, usedBonus, parentLogin, info);

        labels.forEach(label -> label.setVisible(false).setOutputMarkupId(true));

        add(login, mobile, email, bonus, usedBonus, parentLogin, info);
    }

    private AjaxButton getSearchButton() {
        return new AjaxButton("search") {
            @Override
            protected void onSubmit(AjaxRequestTarget target) {
                String touristMobile = touristSearch.getDefaultModelObjectAsString();

                tourist = touristService.getByMobile(touristMobile);
                labels.forEach(label -> label.setVisible(true));
                target.add(labels.stream().toArray(Label[]::new));

            }
        };
    }


}
