import edu.princeton.cs.algs4.*;
class MyEdge implements Comparable<MyEdge>{
   private final int v; // NOTE: ensure v < w.
   private final int w;
   private long weight;

   public int minv() { return v; }
   public int maxv() { return w; }
   public int either() { return v; }
   public int other(int o) { return o == v ? w : v; }
   public long weight() { return weight; }
   public void changeWeight( long weight ) { this.weight = weight; }

   MyEdge ( int v, int w, long weight ) {
      this.v = v < w ? v : w;
      this.w = v < w ? w : v;
      this.weight = weight;
   }

   public String toString() {
      return String.format("%d-%d %d", v, w, weight);
   }

   public int compareTo(MyEdge that) {
	   if (that.weight() < this.weight) return 1;
	   else if (that.weight() > this.weight) return -1;
	   return 0;
   }

}
