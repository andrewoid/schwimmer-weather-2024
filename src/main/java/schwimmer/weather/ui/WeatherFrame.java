package schwimmer.weather.ui;

import com.andrewoid.ApiKey;
import hu.akarnokd.rxjava3.swing.SwingSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;
import schwimmer.weather.OpenWeatherMapService;
import schwimmer.weather.OpenWeatherMapServiceFactory;
import schwimmer.weather.json.CurrentWeather;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.BorderLayout;

public class WeatherFrame extends JFrame {

    private JTextField locationField = new JTextField();
    private JTextArea weatherResults = new JTextArea();

    private ApiKey apiKey = new ApiKey();

    private OpenWeatherMapService service = new OpenWeatherMapServiceFactory().getService();

    public WeatherFrame() {
        setSize(800, 600);
        setTitle("Open Weather Map");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        locationField.addActionListener(e -> {
            // tells Rx to request the data on a background Thread
            service.currentWeather(apiKey.get(), locationField.getText(), "imperial")
                    .subscribeOn(Schedulers.io())
                    // tells Rx to handle the response on Swing's main Thread
                    .observeOn(SwingSchedulers.edt())
                    //.observeOn(AndroidSchedulers.mainThread()) // Instead use this on Android only
                    .subscribe(
                            this::handleResponse,
                            Throwable::printStackTrace);
        });
        panel.add(locationField, BorderLayout.NORTH);
        weatherResults.setEditable(false);
        panel.add(weatherResults, BorderLayout.CENTER);

        setContentPane(panel);
    }

    private void handleResponse(CurrentWeather response) {
        weatherResults.setText(
                "Temperature: " + response.main.temperature
        );
    }

    public static void main(String[] args) {
        new WeatherFrame().setVisible(true);
    }

}
