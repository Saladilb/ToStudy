import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//Задача -
    /*
    1. Создать класс City с полями название и население

    2. Создать список из 5 городов
    3. С помощью Streams API создать список из
    названий городов, в которых численность
    населения больше миллиона
     */

public class Main {
    private List<City> arrayOfCity = new ArrayList<>();

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    private void run() {
        arrayOfCity.add(new City("Kazan", 2000000));
        arrayOfCity.add(new City("Moskow", 50000000));
        arrayOfCity.add(new City("Almet", 700000));
        arrayOfCity.add(new City("Chelni", 1100000));
        arrayOfCity.add(new City("Elab", 500000));
        arrayOfCity.add(new City("Piter", 3000000));

        List<String> result = arrayOfCity.stream().filter(city -> city.population > 1000000).map(City::getCity).collect(Collectors.toList());

            System.out.println(result);

    }

    private class City {
        int population;
        String city;

        public City(String cityName, int population) {
            this.city = cityName;
            this.population = population;
        }

        public int getPopulation() {
            return population;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public void setPopulation(int population) {
            this.population = population;
        }
    }

}



