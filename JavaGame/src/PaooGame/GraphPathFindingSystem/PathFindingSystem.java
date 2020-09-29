package PaooGame.GraphPathFindingSystem;

import PaooGame.Graph.Graph;
import PaooGame.Graph.Node;
import PaooGame.Graph.NodeComparator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

/*! \class PathFindingSystem
    \brief Implementeaza algoritmul Dijkstra.
 */
public class PathFindingSystem {

    /*! \fn public static ArrayList<Node> PathFindingDijkstra(Node startNode, Node goalNode, Graph graph)
        \brief Algoritmul Dijkstra

        Metoda implementeaza algoritmul Dijkstra pentru a gasii drumul cel mai scurt de la startNode pana la goalNode

        \param startNode Nodul de pornire.
        \param goalNode Nodul de finish(nodul pe care se afla caracterul cautat).
        \param graph Graful de noduri.
     */
    public static ArrayList<Node> PathFindingDijkstra(Node startNode, Node goalNode, Graph graph) {

        HashMap<Node, Node> came_from = new HashMap<>();
        HashMap<Node, Integer> D = new HashMap();

        PriorityQueue<Node> frontier = new PriorityQueue<>(new NodeComparator());
        frontier.add(startNode);

        came_from.put(startNode, null);
        D.put(startNode, 0);

        while (!frontier.isEmpty()) {
            Node current = frontier.remove();

            if (current.equals(goalNode)) {
                ArrayList<Node> path = new ArrayList<>();

                while (!current.equals(startNode)) {
                    path.add(0, current);
                    current = came_from.get(current);
                }

                if (path.isEmpty()) {
                    return null;
                }

                return path;
            }

            for (Node nextNode : graph.getNeighbours(current)) {
                int newCost = D.get(current) + graph.getCostTo(nextNode);

                if(!D.containsKey(nextNode) || newCost < graph.getCostTo(nextNode))
                {
                    if(!D.containsKey(nextNode))
                    {
                        D.put(nextNode, newCost);
                    }
                    else
                    {
                        D.replace(nextNode, newCost);
                    }

                    came_from.put(nextNode, current);
                    frontier.add(new Node(nextNode.getX(), nextNode.getY(), newCost));
                }
            }
        }

        return null;
    }
}