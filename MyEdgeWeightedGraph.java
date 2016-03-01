import edu.princeton.cs.algs4.*;
import java.lang.Exception;

class MyEdgeWeightedGraph {
    private final int V;
    private final int E;
    private Bag<MyEdge> edges = new Bag<MyEdge>();

    public int V() { return V; }
    public int E() { return E; }

    public Bag<MyEdge> edges() { return edges; }

    public Bag<MyEdge> without_edge(MyEdge r) {
      Bag<MyEdge> ret = new Bag<MyEdge>();
      for (MyEdge e : edges) {
        if (!e.equals(r)) {
          ret.add(e);
        }
      }
      return ret;
    }

	
	public MyEdgeWeightedGraph( int V ) {
		assert V >= 0;
		this.V = V;
		this.E = 0;
    
	}
	public MyEdgeWeightedGraph( int V, int E ) {
		assert V >= 0;
		this.V = V;
		this.E = E;
    
	}
	
    public MyEdgeWeightedGraph( In in ) {
        this.V = in.readInt();
		    this.E = in.readInt();
		    assert E > 0 && V > 0;
		    for(int i = 0; i < E; i++) {
		    	int v = in.readInt();
		    	int w = in.readInt();
		    	long weight = in.readLong();
		    	MyEdge e = new MyEdge(v, w, weight);
          assert e != null : "Adding a null edge.";
          edges.add(e);
        }

        if (edges.size() != this.E) { throw new RuntimeException("Graph has been malformed: number edges is WRONG"); }
    }
}
