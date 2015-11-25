import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *  created by David Matins on 11/22/15.
 */
public class DijkstraAlgorithmSolver {
    private final List<Car> cars;
    private final List<Edge> allEdges;
    private final float time;
    private float distance = 0.0f;

    public DijkstraAlgorithmSolver(List<Car> cars, List<Edge> edges, float time) {
        this.cars = cars;
        this.allEdges = edges;
        this.time = time;
    }

    public List<Edge> solve(){
        //should actually implement the algorithm here.......

        //TODO: actually implement the algorithm.... (return a path made of edges)
        //randomly add cars to the list of ones the player will take.
        //this is for testing purposes only, but the actual list of cars should be added in a similar way
        Random rand = new Random();
        ArrayList<Edge> path = new ArrayList();
        Car carInPath = null;
        for (int i=0; i<cars.size(); i++) {
            Car raceCar = cars.get(i);
            if (rand.nextFloat() > 0.65f && i != 0) {
                raceCar.choose(true);
                path.add(new Edge(carInPath.toString(), carInPath, raceCar, Graph.calculateTimeToNextCar(carInPath, raceCar)));
                carInPath = raceCar;
            } else if (i == 0) {
                raceCar.choose(true);
                carInPath = raceCar;
            }
        }

        //TODO: account for the distance driven after getting in last car
        //updates distance travelled using the path data
        if(path.size() > 0) {
            distance += path.get(path.size()-1).getDestination().getPosition();
        }

        return path;
    }

    public float getDistance(){
        return distance;
    }

}
