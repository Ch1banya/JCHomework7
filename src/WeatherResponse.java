import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherResponse {
    String url = "http://dataservice.accuweather.com/forecasts/v1/daily/5day/295212?apikey=V3gwcyf5ol53VdF7SN0uTiF2KnaTKmJm&language=ru-ru&metric=true";
    String city = "Санкт-Петербург";
    JSONObject json;

    public void load() throws IOException {
        URL accuWeatherUrl = new URL(url);
        HttpURLConnection urlConnection = (HttpURLConnection) accuWeatherUrl.openConnection();
        String line;
        StringBuilder response = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))) {
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        }

        json = new JSONObject(response.toString());
    }

    public void today() {
        JSONObject forecast = json.getJSONArray("DailyForecasts").getJSONObject(0);
        String text = forecast.getJSONObject("Day").getString("IconPhrase");
        float tmin = forecast.getJSONObject("Temperature").getJSONObject("Minimum").getFloat("Value");
        float tmax = forecast.getJSONObject("Temperature").getJSONObject("Maximum").getFloat("Value");
        System.out.println(text + ". " +tmin + "°C - " + tmax + "°C");
    }

    public void fiveDays() {
        weather(0);
        weather(1);
        weather(2);
        weather(3);
        weather(4);
    }

    public void weather(int day) {
        JSONObject forecast = json.getJSONArray("DailyForecasts").getJSONObject(day);
        String text = forecast.getJSONObject("Day").getString("IconPhrase");
        String date = forecast.getString("Date");
        float tmin = forecast.getJSONObject("Temperature").getJSONObject("Minimum").getFloat("Value");
        float tmax = forecast.getJSONObject("Temperature").getJSONObject("Maximum").getFloat("Value");
        System.out.println("|В городе " + city + " на дату " + date + " ожидается " + text + ", температура от " + tmin + "°C до " + tmax + "°C" + " |");
    }
}
