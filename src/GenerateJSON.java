import com.google.gson.stream.JsonWriter;

import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by David Matins on 11/22/15.
 *
 * This class is meant to take a list of cars and create a JSON map from them.
 */
public class GenerateJSON {

    private ArrayList<Car> allCars;
    private ArrayList<Edge> path;
    private float time;
    private float distance;

    /*
    ensure that the cars that are passed from allCars have the same positions in the arrays as their indexs indicate
    otherwise the graph will be incorrect and look weird
     */
    public GenerateJSON(ArrayList<Car> allCars, ArrayList<Edge> path, float time, float distance){
        this.allCars = allCars;
        this.path = path;
        this.time = time;
        this.distance = distance;
    }

    public void generate(){
        JsonWriter jsonWriter = null;
        try {
            jsonWriter = new JsonWriter(new FileWriter("src/carsTest.json"));
            jsonWriter.beginObject();
            jsonWriter.name("nodes");
            jsonWriter.beginArray();
            for (Car car: allCars) {
                jsonWriter.beginObject();
                jsonWriter.name("name");
                jsonWriter.value("car: "+car.getIndex() + "\nposition: "+car.getPosition()+"\nmax speed: "+car.getMaxSpeed()+"\nacceleration: "+car.getAcceleration());

                jsonWriter.name("color");
                if(car.isChosen()) {
                    jsonWriter.value("LightCoral");
                } else{
                    jsonWriter.value("LightSkyBlue");
                }
                jsonWriter.endObject();
                jsonWriter.flush();
            }
            jsonWriter.endArray();

            jsonWriter.name("links");
            jsonWriter.beginArray();
            StringBuffer carPath = new StringBuffer();
            ArrayList<StringBuffer> pathLookNice = new ArrayList<StringBuffer>();
            for (int i=0; i<path.size(); i++) {
                Edge edge = path.get(i);
                Car car1 = edge.getSource();
                Car car2 = edge.getDestination();

                jsonWriter.beginObject();
                jsonWriter.name("source");
                jsonWriter.value(car1.getIndex());
                jsonWriter.name("target");
                jsonWriter.value(car2.getIndex());
                jsonWriter.name("value");
                jsonWriter.value(edge.getWeight());
                jsonWriter.endObject();

                if (i==0){
                    carPath.append("car0");
                }
                if(i%5 ==0 ){
                    pathLookNice.add(carPath);
                    carPath = new StringBuffer();
                }
                carPath.append(" -> car" + car2.getIndex());
            }
            if(path.size()%5 != 0 ){
                pathLookNice.add(carPath);
            }
            jsonWriter.endArray();

            jsonWriter.name("info");
            jsonWriter.beginArray();

            jsonWriter.beginObject();
            jsonWriter.name("time");
            jsonWriter.value(time);

            jsonWriter.name("distance");
            jsonWriter.value(distance);

            jsonWriter.name("path");
            jsonWriter.beginArray();
            for (StringBuffer buff: pathLookNice) {
                jsonWriter.beginObject();
                jsonWriter.name("segment");
                jsonWriter.value(buff.toString());
                jsonWriter.endObject();
            }
            jsonWriter.endArray();
            jsonWriter.endObject();

            jsonWriter.endArray();

            jsonWriter.endObject();
        } catch (IOException e) {

        }finally{
            try {
                jsonWriter.close();
            } catch (IOException e) {

            }
        }
    }
}
