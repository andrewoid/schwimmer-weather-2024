package schwimmer.weather.json;

import com.google.gson.annotations.SerializedName;

public class Main {
    @SerializedName("temp")
    public double temperature;
    public double humidity;
    public double pressure;
}
