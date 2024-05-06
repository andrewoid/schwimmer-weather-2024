package schwimmer.weather;

import com.andrewoid.ApiKey;
import org.junit.jupiter.api.Test;
import schwimmer.weather.json.CurrentWeather;

import static org.junit.jupiter.api.Assertions.*;

class OpenWeatherMapServiceTest {

    @Test
    public void currentWeather() {
        // given
        ApiKey apiKey = new ApiKey();
        OpenWeatherMapService service = new OpenWeatherMapServiceFactory().getService();

        // when
        CurrentWeather currentWeather = service.currentWeather(
                apiKey.get(),
                "Passaic",
                "standard"
        ).blockingGet();

        // then
        assertNotEquals(0, currentWeather.main.temperature);
        assertNotEquals(0, currentWeather.main.humidity);
        assertNotEquals(0, currentWeather.main.pressure);
        assertNotEquals(0, currentWeather.wind.degrees);
        assertNotEquals(0, currentWeather.wind.gust);
        assertNotEquals(0, currentWeather.wind.speed);
    }

}