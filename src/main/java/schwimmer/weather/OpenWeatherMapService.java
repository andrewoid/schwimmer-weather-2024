package schwimmer.weather;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;
import schwimmer.weather.json.current.CurrentWeather;
import schwimmer.weather.json.forecast.ForecastWeather;

/**
 * https://openweathermap.org/current
 */
public interface OpenWeatherMapService {

    @GET("/data/2.5/weather")
    Single<CurrentWeather> currentWeather(
            @Query("appid") String appId,
            @Query("q") String city,
            @Query("units") String units
    );

    @GET("/data/2.5/forecast")
    Single<ForecastWeather> forecast(
            @Query("appid") String appId,
            @Query("q") String city,
            @Query("units") String units
    );

}
