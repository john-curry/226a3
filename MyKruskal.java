import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.*;
import java.lang.Exception;
import java.util.*;
class MyKruskal {
  private final boolean INCLUDE = true;
  private final boolean REMOVE  = true;
  
  private long weight;                              // weight of MST
  private Queue<MyEdge> mst = new Queue<MyEdge>();  // edges in MST

  public long weight() { return weight; }

  public Iterable<MyEdge> edges() { return mst; }

  public MyKruskal( MyEdgeWeightedGraph G ) {
    this( G , null , false );
  }

  // three use cases
  // 1) all edges included
  // 2) one edge forced include
  // 3) one edge forced remove

  public MyKruskal(MyEdgeWeightedGraph G , MyEdge r, boolean include) {
    MyEdge[] my_edges = without_edge_array( G , r );

    assert my_edges != null : "Edges is null";

    for (MyEdge e : my_edges) {
      if (e == null) System.out.println("Found a null edge");
    }

 	 MinPQ<MyEdge> pq = new MinPQ<MyEdge>(my_edges);
 	 
 	 UF uf = new UF(G.V());

    if (r != null && include == true) {
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
  }

  public static MyEdge[] without_edge_array( MyEdgeWeightedGraph G , MyEdge r ) {
    Bag<MyEdge> bag_edges = G.without_edge(r);
    MyEdge[] edges = new MyEdge[bag_edges.size()];
    int i = 0;
    for (MyEdge e : bag_edges) {
      edges[i] = e;
      i++;
    }
    return edges;
  }


  public static MyEdge[] edge_array( MyEdgeWeightedGraph G ) {
    MyEdge[] edges = new MyEdge[G.edges().size()];
    int i = 0;
    for (MyEdge e : G.edges()) {
      edges[i] = e;
      i++;
    }
    return edges;

  }


  public static long include( MyEdgeWeightedGraph G ) {
    long inc_weight = 0;
    for (MyEdge e : edge_array( G )) {
      inc_weight += (new MyKruskal( G , e , true)).weight();
    }
    return inc_weight;
  }
  
  public static long exclude( MyEdgeWeightedGraph G ) {
    long exc_weight = 0;
    for (MyEdge e : edge_array( G )) {
      exc_weight += (new MyKruskal( G , e , false)).weight();
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

