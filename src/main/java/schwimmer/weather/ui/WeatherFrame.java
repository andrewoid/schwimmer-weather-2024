package schwimmer.weather.ui;

import com.andrewoid.ApiKey;
import hu.akarnokd.rxjava3.swing.SwingSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;
import schwimmer.weather.OpenWeatherMapService;
import schwimmer.weather.OpenWeatherMapServiceFactory;
import schwimmer.weather.json.current.CurrentWeather;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.net.MalformedURLException;
import java.net.URL;

public class WeatherFrame extends JFrame {

    private JTextField locationField = new JTextField();
    private JTextArea weatherResults = new JTextArea();

    private JLabel icon = new JLabel();

    private ApiKey apiKey = new ApiKey();

    private OpenWeatherMapService service = new OpenWeatherMapServiceFactory().getService();

    public WeatherFrame() {
        setSize(800, 600);
        setTitle("Open Weather Map");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(icon, BorderLayout.WEST);
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
                "Temperature: " + response.main.temperature +
                        "\nDescription: " + response.weather[0].description
        );
        String url = "https://openweathermap.org/img/wn/" + response.weather[0].icon + "@2x.png";
        try {
            icon.setIcon(new ImageIcon(new URL(url)));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        new WeatherFrame().setVisible(true);
    }

}
