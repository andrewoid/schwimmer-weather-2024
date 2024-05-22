package schwimmer.weather.json.current;

import com.google.gson.annotations.SerializedName;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class CurrentWeather {

    public long dt;

    @SerializedName("pop")
    public double rainProbability;

    public Main main;

    public Wind wind;

    public Weather weather[];

    public String getDateTime() {
        // Create an Instant from the epoch milliseconds
        Instant instant = Instant.ofEpochMilli(dt * 1000);

        // Define the local timezone
        ZoneId localZoneId = ZoneId.systemDefault();

        // Convert the Instant to a ZonedDateTime in the local timezone
        ZonedDateTime localDateTime = instant.atZone(localZoneId);

        // Define a formatter to format the ZonedDateTime to a string
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Format the local ZonedDateTime to a string
        return localDateTime.format(formatter);
    }

}
