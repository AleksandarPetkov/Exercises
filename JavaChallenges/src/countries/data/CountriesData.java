package countries.data;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class CountriesData {
    /**
     * Consume data from 'https://restcountries.com/v3.1/all' DIVIDE the countries by continents and return for each
     * continent THE 10 COUNTRIES with LARGEST POPULATION that have MORE THAN ONE official language.
     */
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
        JSONArray restApiData = new JSONArray(responseBody);
        StringBuilder outputData = new StringBuilder();

        for (int index = 0; index < restApiData.length(); index++) {
            JSONObject jsonObject = restApiData.getJSONObject(index);
            try {
                if(jsonObject.getJSONObject("languages").length() > 1){
                    String continentName = checkContinent(jsonObject);
                    Continent currentContinent = getContinent(continentName);
                    Country country = addCounty(jsonObject);

                    if (currentContinent != null) {
                        currentContinent.getCountries().add(country);
                    }
                }
            } catch (Exception e){
                System.out.println("INVALID DATA");
            }
        }

        for (Continent continent : continents) {
            outputData.append("Continent: ").append(continent.getName()).append(System.getProperty("line.separator"));

             continent.getCountries()
                    .stream()
                     .sorted(Comparator.comparing(Country::getPopulation).thenComparing(Country::getPopulation).reversed())
                     .limit(10)
                    .forEach(country -> {
                        outputData.append("   ").append(country.getName()).append(System.getProperty("line.separator"));
                        outputData.append("   ").append(country.getPopulation()).append(System.getProperty("line.separator"));
                    });
        }
        System.out.println(outputData);
        return outputData.toString();
    }

    private static Country addCounty(JSONObject jsonObject) {
        String countryName = jsonObject.getJSONObject("name").getString("official");
        int population = jsonObject.getInt("population");
        return new Country(countryName, population);
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
