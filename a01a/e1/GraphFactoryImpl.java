package a01a.e1;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GraphFactoryImpl implements GraphFactory {

    @Override
    public <X> Graph<X> createDirectedChain(List<X> nodes) {
        return new Graph<X>() {

            @Override
            public Set<X> getNodes() {
                return nodes.stream().collect(Collectors.toSet());
            }

            @Override
            public boolean edgePresent(Object start, Object end) {
                return false;
            }

            @Override
            public int getEdgesCount() {
                // TODO Auto-generated method stub
                return 0;
            }

            @Override
            public Stream<Pair<X,X>> getEdgesStream() {
                // TODO Auto-generated method stub
                return null;
            }
            
        };
    }

    @Override
    public <X> Graph<X> createBidirectionalChain(List<X> nodes) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <X> Graph<X> createDirectedCircle(List<X> nodes) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <X> Graph<X> createBidirectionalCircle(List<X> nodes) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <X> Graph<X> createDirectedStar(X center, Set<X> nodes) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <X> Graph<X> createBidirectionalStar(X center, Set<X> nodes) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <X> Graph<X> createFull(Set<X> nodes) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <X> Graph<X> combine(Graph<X> g1, Graph<X> g2) {
        // TODO Auto-generated method stub
        return null;
    }

}
