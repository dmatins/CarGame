/**
 * Created by David Matins on 11/22/15.
 *
 * Acts as a link between two cars and holds a weight
 */
public class Edge  {
    private final String id;
    private final Car source;
    private final Car destination;
    private final float weight;

    public Edge(String id, Car source, Car destination, float weight) {
        this.id = id;
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public String getId() {
        return id;
    }
    public Car getDestination() {
        return destination;
    }

    public Car getSource() {
        return source;
    }
    public float getWeight() {
        return weight;
    }

    public String toString() {
        return source + "-->" + destination;
    }


}