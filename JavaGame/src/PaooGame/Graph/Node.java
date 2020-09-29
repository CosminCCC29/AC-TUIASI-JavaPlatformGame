package PaooGame.Graph;
import java.util.Objects;

/*! \class Node
    \brief Implementeaza notiunea de nod al grafului.
 */
public class Node
{
    int cost;   /*!< Costul unui nod.*/
    int x;      /*!< Pozitia x al unui nod.*/
    int y;      /*!< Pozitia y al unui nod.*/

    /*! \fn public Node(int x, int y)
        \brief Constructor de initializare al clasei Node.
     */
    public Node(int x, int y)
    {
        this.x = x;
        this.y = y;
        this.cost = 0;
    }

    /*! \fn public Node(int x, int y, int cost)
        \brief Constructor de initializare al clasei Node cu cost.
     */
    public Node(int x, int y, int cost)
    {
        this.cost = cost;
        this.x = x;
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setCost(int cost)
    {
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return x == node.x &&
                y == node.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
