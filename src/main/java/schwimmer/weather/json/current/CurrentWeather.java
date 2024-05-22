package schwimmer.weather.json.current;

import com.google.gson.annotations.SerializedName;

public class CurrentWeather {

    public long dt;

    public String dt_txt;

    @SerializedName("pop")
    public double rainProbability;

    public Main main;

    public Wind wind;

    public Weather weather[];

}
