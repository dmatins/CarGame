import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by David Matins on 11/22/15.
 *
 * Holds all elements in graph: cars, all edges between cars and their weights
 */
public class Graph {
    private final List<Car> cars;
    private final List<Edge> allEdges;
    private final float time;
    private float distance = 0.0f;
    DijkstraAlgorithmSolver solver;

    public Graph(List<Car> cars, float time) {
        this.cars = cars;
        allEdges = new ArrayList<Edge>();
        //adds edges to allEdges list
        createEdges(cars, allEdges);
        this.time = time;

        solver = new DijkstraAlgorithmSolver(cars, allEdges, time);
    }

    //Creates all edges from a given set of cars.
    private List<Edge> createEdges(List<Car> cars, List<Edge> edges){
        for(int i=0;i<cars.size();i++){
            for(int j=i+1; j<cars.size();j++){
                Car source = cars.get(i);
                Car destination = cars.get(j);
                String id = (source.getIndex()+"->"+destination.getIndex());
                float weight = calculateTimeToNextCar(source, destination);
                edges.add(new Edge(id, source, destination, weight));
            }
        }
        return edges;
    }

    protected static float calculateTimeToNextCar(Car source, Car destination){
        float distBetween = destination.getPosition() - source.getPosition();
        float accel = source.getAcceleration();
        float maxSpeed = source.getMaxSpeed();

        float timeTillTopSpeed = maxSpeed / accel;
        float distanceTravelledTillTopSpeed = ( accel * timeTillTopSpeed * timeTillTopSpeed ) / 2.0f;
        float centerDistanceAtMaxSpeed = distBetween - (distanceTravelledTillTopSpeed * 2.0f);

        float timeTaken;

        if(2.0f * distanceTravelledTillTopSpeed > distBetween){
            //not enough distance between cars to reach top speed
            timeTaken = (float) (2.0f * (Math.sqrt(distBetween / accel)));
        } else{
            //at least enough distance to reach top speed
            timeTaken = (timeTillTopSpeed * 2.0f) + (centerDistanceAtMaxSpeed / maxSpeed);
        }

        return timeTaken;
    }

    public List<Edge> solve(){
        return solver.solve();
    }

    public List<Car> getCars() {
        return cars;
    }
    public float getTime(){
        return time;
    }
    public float getDistanceTravelled(){ return solver.getDistance();}
    public List<Edge> getAllEdges() {
        return allEdges;
    }
}

