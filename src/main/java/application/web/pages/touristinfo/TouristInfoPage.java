package application.web.pages.touristinfo;

import application.entity.Tourist;
import application.service.TouristService;
import application.web.pages.base.BasePage;
import application.web.pages.home.HomePage;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.Arrays;
import java.util.List;


public class TouristInfoPage extends BasePage{

    private TextField<String> touristSearch;
    protected Tourist tourist;
    private Form<Void> form;
    private List<Label> labels;
    private List<Label> values;

    @SpringBean
    private TouristService touristService;

    @Override
    protected void onInitialize() {
        super.onInitialize();
        setUpNavigationLinks();
        addSearchForm();
        setLabels();
        setValueContainers();
    }

    private void addSearchForm() {
        form = new Form<Void>("form");
        form.setOutputMarkupId(true);

        touristSearch = new TextField<>("touristSearch", Model.of(""));
        Button search = getSearchButton();

        add(form);
        form.add(touristSearch);
        form.add(search);
    }

    private void setUpNavigationLinks() {
        loginLink.setVisible(false);
        logoutLink.setVisible(true);
        cabinetLink.setVisible(true);

    }

    private void setValueContainers() {
        Label loginValue = new Label("loginValue",Model.of());
        Label mobileValue = new Label("mobileValue",Model.of());
        Label emailValue = new Label("emailValue",Model.of());
        Label bonusValue = new Label("bonusValue",Model.of());
        Label usedBonusValue = new Label("usedBonusValue",Model.of());
        Label parentLoginValue = new Label("parentLoginValue",Model.of());
        Label infoValue = new Label("infoValue",Model.of());

        values = Arrays.asList(loginValue, mobileValue, emailValue, bonusValue, usedBonusValue, parentLoginValue, infoValue);
        values.forEach(value -> value.setVisible(false).setOutputMarkupId(true));
        add(values.stream().toArray(Label[]::new));
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

        add(labels.stream().toArray(Label[]::new));
    }

    private Button getSearchButton() {
        return new Button("search", new ResourceModel("searchButton")) {
            @Override
            public void onSubmit() {
                String touristMobile = touristSearch.getDefaultModelObjectAsString();
                tourist = touristService.getByMobile(touristMobile);
                labels.forEach(label -> label.setVisible(true));
                mapValues();
                values.forEach(value -> value.setVisible(true));
            }
        };
    }

    private void mapValues() {
        values.get(0).setDefaultModel(Model.of(" " + tourist.getLogin()));
        values.get(1).setDefaultModel(Model.of(" " + tourist.getMobile()));
        values.get(2).setDefaultModel(Model.of(" " + tourist.getEmail()));
        values.get(3).setDefaultModel(Model.of(" " + tourist.getBonusAmount()/100.0));
        values.get(4).setDefaultModel(Model.of(" " + tourist.getUsedBonuses()/100.0));
        Tourist parent = tourist.getParent();
        if (parent == null){
            parent = new Tourist();
            parent.setLogin("Denis");
        }

        values.get(5).setDefaultModel(Model.of(" " + parent.getLogin()));
        values.get(6).setDefaultModel(Model.of(" " + tourist.getTouristInfoId()));
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);
        response.render(CssHeaderItem.forReference(new CssResourceReference(TouristInfoPage.class, "style.css")));
    }
}
