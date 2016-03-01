import edu.princeton.cs.algs4.*;
import java.lang.Exception;

class MyEdgeWeightedGraph {
  private final int V;
  private final int E;
  private Bag<MyEdge> edges = new Bag<MyEdge>();

  public MyEdgeWeightedGraph( int V , Bag<MyEdge> edges_bag ) {
    this.V = V;
    for (MyEdge e : edges_bag) {
      if (e == null) throw new RuntimeException("Adding a null edge.");
      edges.add(e);
    }
    this.E = edges.size();
  }

  public MyEdgeWeightedGraph( int V , MyEdge[] edges_array ) {
    this.V = V;
    for (MyEdge e : edges_array) {
      if (e == null) throw new RuntimeException("Adding a null edge.");
      edges.add(e);
    }
    this.E = edges.size();
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

  public MyEdge[] EdgeArray() {
    MyEdge[] ret = new MyEdge[this.E];
    int i = 0;
    for (MyEdge e : edges) {
        ret[i] = e;;
        i++;
    }
    return ret;
  }
  
  public MyEdgeWeightedGraph RemoveEdge( MyEdge r ) {
    if (r == null) return this;
    return new MyEdgeWeightedGraph( this.V , this.without_edge(r) );
  }
	
	public MyEdgeWeightedGraph( int V ) {
		assert V >= 0;
		this.V = V;
		this.E = 0;
    
	}

  public boolean IsConnected() {
    UF uf  = new UF(V);
    for (MyEdge e : edges) {
      int v = e.either();
      int w = e.other(v);
      if (!uf.connected(v, w)) {
        uf.union(v, w);
      }
    }
    for (int i = 0; i < V; i++) {
      if (!uf.connected(0, i)) return false;
    }
    return true;
  }

}
