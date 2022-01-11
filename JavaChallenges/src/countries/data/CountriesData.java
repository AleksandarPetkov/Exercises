package countries.data;

import com.sun.tools.javac.Main;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

public class CountriesData {
    private static Set<Continent> continents = new HashSet<>();

    public static void main(String[] args) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://restcountries.com/v3.1/all")).build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(CountriesData::calculateRestApiData)
                .join();
    }

    public static String calculateRestApiData(String responseBody){
        JSONArray data = new JSONArray(responseBody);
        StringBuilder stringBuilder = new StringBuilder();

        for (int index = 0; index < data.length(); index++) {
            JSONObject jsonObject = data.getJSONObject(index);
            try {
                if(jsonObject.getJSONObject("languages").length() > 1){
                    String continentName = checkContinent(jsonObject);
                    Continent currentContinent = getContinent(continentName);

                    String countryName = jsonObject.getJSONObject("name").getString("official");
                    int population = jsonObject.getInt("population");
                    Country country = new Country(countryName, population);

                    if (currentContinent != null) {
                        currentContinent.getCountries().add(country);
                    }
                }
            } catch (Exception e){
                System.out.println("INVALID DATA");
            }
        }

        for (Continent continent : continents) {
            stringBuilder.append("Continent: ").append(continent.getName()).append(System.getProperty("line.separator"));
             continent.getCountries()
                    .stream()
                     .sorted(Comparator.comparing(Country::getPopulation).thenComparing(Country::getPopulation).reversed())
                    .forEach(country -> {
                        stringBuilder.append("   "+ country.getName()).append(System.getProperty("line.separator"));
                        stringBuilder.append("   "+ country.getPopulation()).append(System.getProperty("line.separator"));
                    });
        }
        System.out.println(stringBuilder);
        return stringBuilder.toString();
    }

    private static Continent getContinent(String continentName) {
        for (Continent continent : continents) {
            if (continent.getName().equals(continentName)){
                return continent;
            }
        }
        return null;
    }

    private static String checkContinent(JSONObject jsonObject) {
        JSONArray continentsArray = jsonObject.getJSONArray("continents");
        String continentName = "";

        for (int i = 0; i < continentsArray.length(); i++) {
            continentName = (String) continentsArray.get(i);

            if (continents.size() == 0){
                continents.add(new Continent(continentName));
            } else {
                boolean isContinentExist= true;
                for (Continent continent : continents) {
                    if (continent.getName().equals(continentName)){
                        isContinentExist = false;
                        break;
                    }
                }
                if (isContinentExist){
                    continents.add(new Continent(continentName));
                }
            }
        }
        return continentName;
    }
}
