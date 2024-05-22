package schwimmer.weather;

import com.andrewoid.ApiKey;
import org.junit.jupiter.api.Test;
import schwimmer.weather.json.current.CurrentWeather;
import schwimmer.weather.json.forecast.ForecastWeather;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class OpenWeatherMapServiceTest {
    private ApiKey apiKey = new ApiKey();
    private OpenWeatherMapService service = new OpenWeatherMapServiceFactory().getService();

    @Test
    public void currentWeather() {
        // given

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
        assertNotNull(currentWeather.weather);
        assertNotNull(currentWeather.weather[0].description);
        assertNotNull(currentWeather.weather[0].icon);
        assertNotNull(currentWeather.weather[0].main);
    }

    @Test
    public void forecast() {
        // given

        // when
        ForecastWeather forecastWeather = service.forecast(
                apiKey.get(),
                "Passaic",
                "standard"
        ).blockingGet();

        // then
        assertNotNull(forecastWeather.list);
        assertNotEquals(0, forecastWeather.list[0].dt);
    }

}