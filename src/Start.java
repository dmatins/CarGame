import java.util.ArrayList;
import java.util.Random;

public class Start{

    public static void main(String [] args){
        //creates a graph with random car data.
        Graph carGraph = createRandomGraph();

        ArrayList<Car> carList = (ArrayList<Car>) carGraph.getCars();
        ArrayList<Edge> path = (ArrayList<Edge>) carGraph.solve();
        float distance = carGraph.getDistanceTravelled();

        System.out.println("all cars: "+carList+ "\n");

        System.out.println("cars user takes: "+path);

        GenerateJSON generator = new GenerateJSON(carList, path, carGraph.getTime(), distance);
        generator.generate();

    }

    public static Graph createRandomGraph(){
        Random rand = new Random();
        //time
        float time = (float)(rand.nextInt(30)+ 10);
        //list of all cars
        ArrayList<Car> cars = new ArrayList<Car>();

        float currentPos = 0.0f;

        //Creates a random list of cars
        for(int i=0; i<100; i++) {
            float position = currentPos;
            currentPos+= ((float) rand.nextInt(50)) + 2.0f;
            float maxSpeed = ((float) rand.nextInt(120)) + 20.0f;
            float maxAccel = ((float) rand.nextInt(15)) + 5.0f;
            Car raceCar = new Car(i, position, maxSpeed, maxAccel);
            cars.add(raceCar);
        }

        return new Graph(cars, time);
    }




}