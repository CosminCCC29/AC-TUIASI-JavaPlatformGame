package PaooGame.Graph;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/*! \class Graph
    \brief Implementeaza notiunea de graf.
 */
public class Graph
{
    private HashMap<Node, ArrayList<Node>> nodesMap;

    /*! \fn public Graph()
        \brief Constructor de initializare al clasei Graph.
     */
    public Graph()
    {
        nodesMap = new HashMap<>();
    }

    public void addNode(int x, int y, int cost)
    {
        Node tmp = new Node(x,y,cost);

        if(!nodesMap.containsKey(tmp))
        {
            nodesMap.put(tmp, new ArrayList<>());
        }
    }

    /*! \fn public void removeNode(int x, int y)
        \brief Metoda pentru stergerea unui nod din graf
     */
    public void removeNode(int x, int y)
    {
        Node tmp = new Node(x,y);

        nodesMap.values().forEach(it -> it.remove(tmp));
        nodesMap.remove(tmp);
    }

    /*! \fn public void addNeighbours(Node node)
        \brief Metoda pentru adaugarea vecinilor unui nod in graf
     */
    public void addNeighbours(Node node)
    {
        ArrayList<Node> tmpList;
        Point[] dirs = {new Point(1,0), new Point(0,1), new Point(-1,0), new Point(0,-1)};

        tmpList = nodesMap.remove(node);

        for (Point dir : dirs) {
            Node tmp = new Node(node.x + dir.x, node.y + dir.y);

            for(Node key : nodesMap.keySet())
            {
                if(key.equals(tmp))
                {
                    tmp.setCost(key.cost);
                }
            }

            if (tmpList != null && nodesMap.containsKey(tmp)) {
                tmpList.add(tmp);
            }

        }

        nodesMap.put(node, tmpList);

    }

    public ArrayList<Node> getNeighbours(Node node)
    {
        return nodesMap.get(node);
    }

    public boolean isKey(int x, int y)
    {
        return this.nodesMap.containsKey(new Node(x, y));
    }

    public int getCostTo(Node to)
    {
        return to.cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Graph graph = (Graph) o;
        return nodesMap.equals(graph.nodesMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodesMap);
    }

    public HashMap<Node, ArrayList<Node>> getNodesMap() {
        return nodesMap;
    }
}
