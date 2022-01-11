package countries.data;

import java.util.HashSet;
import java.util.Set;

public class Continent {
    private String name;
    private Set<Country> countries;

    public Continent(String name) {
        this.name = name;
        this.countries = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Country> getCountries() {
        return countries;
    }

    public void setCountries(Set<Country> countries) {
        this.countries = countries;
    }
}
