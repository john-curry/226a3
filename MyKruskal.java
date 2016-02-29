import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.*;
import java.lang.Exception;
import java.util.*;
class MyKruskal {

   private long weight;                              // weight of MST
   private Queue<MyEdge> mst = new Queue<MyEdge>();  // edges in MST
   //private MyEdge[] edges;

   public long weight() { return weight; }

   public Iterable<MyEdge> edges() { return mst; }
   

   public MyKruskal( MyEdgeWeightedGraph G ) {
		MyEdge[] edges = edge_array(G);
		
		MinPQ<MyEdge> pq = new MinPQ<MyEdge>(edges);
		
		UF uf = new UF(G.V());
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

   public static MyEdge[] edge_array( MyEdgeWeightedGraph G ) {
		Iterator<MyEdge> it = G.edges().iterator();
		
		MyEdge[] edges = new MyEdge[G.E()];
		int i = 0;
		while(it.hasNext()) {
			edges[i] = it.next();
			i++;
		}
    return edges;
   }


   public static long include( MyEdgeWeightedGraph G ) {
     // for all edges e in MyEdgeWeightedGraph
     // add e to the mst
     // compute the mst with all the other edges
     long inc_weight = 0;
      
     for (
      throw new RuntimeException("Not yet implemented: include");
   }
   
   public static long exclude( MyEdgeWeightedGraph G ) {
      throw new RuntimeException("Not yet implemented: exclude");
   }
   
    public static void main( String[] args ) {

        In in = new In( args[0] );
        MyEdgeWeightedGraph G = new MyEdgeWeightedGraph( in );

        MyKruskal mst = new MyKruskal( G );
        System.out.println( mst.weight() );
        //System.out.println( MyKruskal.include( G ) );
        //System.out.println( MyKruskal.exclude( G ) );
    }
}
