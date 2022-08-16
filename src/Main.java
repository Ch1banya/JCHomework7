import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        WeatherResponse wr = new WeatherResponse();
        try {
            wr.load();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.println("Прогноз на сегодня:");
        wr.today();

        System.out.println("\n");
        System.out.println("Прогноз на пять дней:");
        wr.fiveDays();
    }
}