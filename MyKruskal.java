import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.*;
import java.lang.Exception;
import java.util.*;
class MyKruskal {
  public static final boolean INCLUDE = true;
  public static final boolean REMOVE  = false;
  
  private int V;
  private int E;
  private long weight;                              // weight of MST
  private Queue<MyEdge> mst = new Queue<MyEdge>();  // edges in MST
  private boolean connected = false;

  public long weight() { return connected ? weight : -99; }
  public Iterable<MyEdge> edges() { return mst; }
  public boolean IsConnected() { return connected; }

  public MyKruskal( MyEdgeWeightedGraph G ) {
    this( G , null , false );
  }

  public MyKruskal(MyEdgeWeightedGraph G , MyEdge r, boolean method) {
    MyEdgeWeightedGraph graph = G;
    
    if (method == REMOVE && r != null) {
      graph = G.RemoveEdge(r);
    }

    this.V = graph.V();
    this.E = graph.V() - 1;


 	  MinPQ<MyEdge> pq = new MinPQ<MyEdge>(graph.EdgeArray());
 	 
 	  UF uf = new UF(G.V());

    if (r != null && method == INCLUDE) {
      mst.enqueue(r);
      int v = r.either();
      int w = r.other(v);
      uf.union(v, w);
      weight += r.weight();
    }

    while (!pq.isEmpty() && mst.size() < G.V() - 1) {
      MyEdge e = pq.delMin();
      int v = e.either();
      int w = e.other(v);
      if (!uf.connected(v, w)) {
        uf.union(v, w);
        mst.enqueue(e);
        weight += e.weight();
      }
    }
    
    this.connected = (new MyEdgeWeightedGraph( graph.V() , graph.EdgeArray() )).IsConnected();

    if (this.connected) assert mst.size() == graph.V() - 1 : "The MST does not contain the right amount of edges: " + mst.size();
  }

  public static long include( MyEdgeWeightedGraph G ) {
    long inc_weight = 0;
    for (MyEdge e : G.EdgeArray()) {
      MyKruskal mk = new MyKruskal( G , e , INCLUDE );
      if (!mk.IsConnected()) return -99;
      inc_weight += mk.weight();
    }
    return inc_weight;
  }
  
  public static long exclude( MyEdgeWeightedGraph G ) {
    long exc_weight = 0;
    for (MyEdge e : G.EdgeArray()) {
      MyKruskal mk = new MyKruskal( G , e , REMOVE );
      if (!mk.IsConnected()) return -99;
      exc_weight += mk.weight();
    }
    return exc_weight;
  }
  
   public static void main( String[] args ) {

       In in = new In( args[0] );
       MyEdgeWeightedGraph G = new MyEdgeWeightedGraph( in );

       MyKruskal mst = new MyKruskal( G );
       System.out.println( mst.weight() );
       System.out.println( MyKruskal.include( G ) );
       System.out.println( MyKruskal.exclude( G ) );
   }
}

