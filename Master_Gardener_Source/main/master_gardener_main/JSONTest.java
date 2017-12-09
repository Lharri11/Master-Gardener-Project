package master_gardener_main;

import com.google.gson.Gson;
import java.util.List;
import java.util.ArrayList;
import com.google.gson.reflect.TypeToken;
import model.Pollinator;

import java.lang.reflect.Type;


public class JSONTest {

    public static void main (String[] args) {

        // Initialize a list of type DataObject
        List<Pollinator> pollinators = new ArrayList<>();
        pollinators.add(new Pollinator("Honey Bee", "Bee"));
        pollinators.add(new Pollinator("Bumblebee", "Bee"));
        pollinators.add(new Pollinator("Monarch", "Butterfly"));

        // Convert the object to a JSON string
        String json = new Gson().toJson(pollinators);
        System.out.println(json);
    }
}
