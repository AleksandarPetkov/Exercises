import data.Car;
import data.Engine;
import data.Wheel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Replacing Loops with flatMap(), filter -> anyMatch()
 */
public class StreamNestedLooping {

    private static List<Car> cars;
    private static List<Wheel> wheels;

    public static void main(String[] args) {
        populateData();

        //  Example 1
        List<Car> filteredCars = new ArrayList<>();

        // Solution Using Loops
        for (Car car : cars) {
            for (Wheel wheel : wheels) {
                if (car.getColor().equals(wheel.getColor()) && wheel.getWorking()) {
                    filteredCars.add(car);
                    break;
                }
            }
        }

        //Solution Using Stream API (Iterate between two object car nad wheel)
        //Defining Predicate
        Predicate<Car> carCheck = car -> wheels.stream().anyMatch(wheel -> car.getColor().equals(wheel.getColor()) && wheel.getWorking());
        filteredCars = cars.stream()
                .filter(carCheck)
//                .filter(car -> wheels  (Directly write the Predicate)
//                        .stream()
//                        .anyMatch(wheel -> car.getColor().equals(wheel.getColor()) && wheel.getWorking()))
                .collect(Collectors.toList());

        filteredCars.forEach(c -> System.out.println(c.getColor()));

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // Example 2 (Nested iteration using stream() and Stream.of() for Strings)
        List<String> carsM = cars.stream()  //Stream<Ca>
                .flatMap(car -> car.getEngine().stream()) //Stream<Engine>
                .flatMap(engine -> Stream.of(engine.getBrand())) //Stream<String>
                .filter(s -> s.startsWith("M"))
                .distinct()
                .collect(Collectors.toList());

        carsM.forEach(System.out::println);
    }

    private static void populateData() {
        cars = Arrays.asList(new Car("blue"), new Car("red"), new Car("purple"), new Car("green"));

        List<Engine> engines = Arrays.asList(new Engine("Mercedes"), new Engine("Mazda"),
                new Engine("Audi"), new Engine("Mini"), new Engine("BMW"));

        cars.forEach(c -> c.setEngine(engines));

        cars.forEach(c -> c.setParts(Arrays.asList("Wheel", "Turbo", "Val")));

        wheels = Arrays.asList(new Wheel("red", true), new Wheel("red", false),
                new Wheel("blue", true), new Wheel("blue", false));
    }
}
