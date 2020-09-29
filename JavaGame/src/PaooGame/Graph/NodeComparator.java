package PaooGame.Graph;

import java.util.Comparator;

/*! \class NodeComparator
    \brief Implementeaza notiunea comparator intre noduri.
 */
public class NodeComparator implements Comparator<Node> {

    /*! \fn public int compare(Node node, Node t1)
        \brief Metoda de comparare intre doua noduri
     */
    @Override
    public int compare(Node node, Node t1) {

        if(node.cost < t1.cost)
            return -1;

        if(node.cost > t1.cost)
            return 1;

        return 0;
    }
}
