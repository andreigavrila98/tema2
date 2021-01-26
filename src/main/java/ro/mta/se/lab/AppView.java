package ro.mta.se.lab;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Class for the view of the App
 */
public class AppView {

    private AppView(){}

    private static AppView instance = null;

    private static ComboBox<String> cbCities;
    private static ComboBox<String> cbCountries;
    private static Button btn;


    public static AppView getView(){
        if(instance == null)
            instance = new AppView();

        return instance;
    }

    public ComboBox<String> getCbCities() {
        return cbCities;
    }

    public void setCbCities(ComboBox<String> cbCities) {
        AppView.cbCities = cbCities;
    }

    public ComboBox<String> getCbCountries() {
        return cbCountries;
    }

    public void setCbCountries(ComboBox<String> cbCountries) {
        AppView.cbCountries = cbCountries;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        AppView.btn = btn;
    }

    /**
     * Initialize the app view
     * @param primaryStage
     */
    public static void setScene(Stage primaryStage){

        primaryStage.setTitle("Hello World!");

        cbCountries         = new ComboBox<String>();
        cbCities            = new ComboBox<String>();

        btn = new Button();
        btn.setText("Get Weather Data");

        var uniqueCountries = new ArrayList<String>();
        var arr = MyUtils.inputData.getJSONArray("data");


        for(var item : arr) {
            var code = ((JSONObject)item).getString("countryCode");
            if(!uniqueCountries.contains(code))
                uniqueCountries.add(code);
        }

        //Add event for item change for the combobox
        cbCountries.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                var seldCountry = cbCountries.getSelectionModel().getSelectedItem();
                var cityList = new ArrayList<String>();

                for(var item : arr){
                    var countryCode = ((JSONObject)item).getString("countryCode");
                    var cityName    = ((JSONObject)item).getString("nm");

                    if(countryCode.equals(seldCountry))
                        cityList.add(cityName);
                }

                cbCities.setItems(FXCollections.observableArrayList(
                        cityList
                ));

                cbCities.getSelectionModel().selectFirst();
            }
        });

        //Populate country combobox
        cbCountries.setItems(FXCollections.observableArrayList(
                uniqueCountries
        ));
        cbCountries.getSelectionModel().selectFirst();


        //Set button click event
        btn.setOnAction(event -> {
            var wah = new WeatherApiHandler();
            try {
                var requestUrl = MyUtils.baseUrlBegin + cbCities.getSelectionModel().getSelectedItem() + ',' + cbCountries.getSelectionModel().getSelectedItem().toLowerCase(Locale.ROOT) + MyUtils.baseUrlEnd;
                var weatherData = wah.getWeather(new URI(requestUrl));

                System.out.println("Temp: "             + weatherData.getJSONObject("main").getBigDecimal("temp") + "K");
                System.out.println("Wind: deg-"         + weatherData.getJSONObject("wind").getBigDecimal("deg") + " speed-" + weatherData.getJSONObject("wind").getBigDecimal("speed"));
                System.out.println("Precipitation: "    + weatherData.getJSONArray("weather").getJSONObject(0).getString("description"));
                System.out.println("\n");


            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        });

        //Setting the view model

        var root = new VBox();
        var hBox = new HBox();

        root.setAlignment(Pos.CENTER);
        root.setSpacing(30);

        hBox.setSpacing(20);
        hBox.setAlignment(Pos.CENTER);

        hBox.getChildren().add(cbCountries);
        hBox.getChildren().add(cbCities);

        root.getChildren().add(hBox);
        root.getChildren().add(btn);

        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }
}
