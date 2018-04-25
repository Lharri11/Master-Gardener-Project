package model;

import com.google.gson.Gson;
import model.Pollinator;

import java.util.ArrayList;
import java.util.List;


public class JSONTest {

    public static void main (String[] args) {

        // Initialize a list of type DataObject
        List<Pollinator> pollinators = new ArrayList<>();
        pollinators.add(new Pollinator(0,"Honey Bee", "Bee"));
        pollinators.add(new Pollinator(0,"Bumblebee", "Bee"));
        pollinators.add(new Pollinator(0,"Monarch", "Butterfly"));

        // Convert the object to a JSON string
        String json = new Gson().toJson(pollinators);
        System.out.println(json);
    }
}
