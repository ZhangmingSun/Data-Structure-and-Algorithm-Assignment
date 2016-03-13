import java.util.PriorityQueue;

public class Assignment5
{
	public static void main(String[] args)
	{			
		Graph g = new Graph(10); // 10 stands for ten vertexes
	    g.AddEdge(0, 1, 4);
	    g.AddEdge(0, 7, 8);
	    g.AddEdge(0, 4, 7); 
	    g.AddEdge(1, 2, 8);
	    g.AddEdge(1, 7, 11); 
	    
	    g.AddEdge(1, 9, 5);
	    g.AddEdge(2, 3, 6);   
	    g.AddEdge(2, 5, 4);   
	    g.AddEdge(3, 4, 9);
	    g.AddEdge(3, 5, 14);
	    
	    g.AddEdge(4, 9, 7);
	    g.AddEdge(5, 4, 10);
	    g.AddEdge(6, 5, 2);
	    g.AddEdge(6, 4, 15);
	    g.AddEdge(7, 6, 1);
	    
	    g.AddEdge(7, 5, 17);  
	    g.AddEdge(7, 8, 7);
	    g.AddEdge(8, 2, 2);
	    g.AddEdge(8, 6, 6);
	    g.AddEdge(9, 3, 16);

	    System.out.println("All edges of the original graph:");
	    int newline_count=0;
        for(Edge e:g.edgeList)  {  
        	System.out.printf("Begin[%d],End[%d],Weight[%d]    ", e.Begin, e.End, e.Weight);
        	newline_count++;
        	if(newline_count == 3)
        	{
        		System.out.println("");
        		newline_count=0;
        	}
        }
	    /*for(int i=0; i<g.edgeList.size(); i++){
    	//System.out.printf("Begin[%d], End[%d], Weight[%d]\n", g.edgeList[i]., g.edgeList[i].End, g.edgeList[i].Weight);	    	
    	}*/
        
	    System.out.println("\n\nGraph Vertex Count : " + g.VertexCount);   
	    System.out.println("Graph Edge Count : " + g.EdgeCount);
	
	    Edge[] mst = g.Kruskal();	// The core of the program -- Kruskal's Algorithm
	    
	    System.out.println("\nThe edges of Minimum Spanning Tree are as following:");
	    for(int i=0; i<mst.length; i++)
	    {
	    	System.out.printf("Begin[%d], End[%d], Weight[%d]\n", mst[i].Begin, mst[i].End, mst[i].Weight);
	    }
	        
	}
}

class Edge implements Comparable<Edge>
{
    public int Begin;	// index of a vertex starting edge
    public int End;		// index of a vertex ending edge
    public int Weight;	// Weight of edge
    
	public Edge(int begin, int end, int weight) {
		this.Begin = begin;
		this.End = end;
		this.Weight = weight;
	}
	
	//This is must necessary, because PriorityQueue needs the way to compare Edge
    @Override  
    public int compareTo(Edge e) {  
        if(e.Weight == Weight)  
        return 0;  
        else if(Weight < e.Weight)  
            return -1;  
        else  
            return 1;       
    }
}

class Subset {
	public int Parent;	//"Parent" points to next. If subsets(Parent)=Parent, this indicates it's the root
	public int Rank;	//"Rank" indicates the height of tree(e.g. union)
}

class Graph
{
	PriorityQueue<Edge> edgeList; // Store all edges in PriorityQueue
	
	//public int[] Vertices;
	public int VertexCount;
	//public Edge[] Edges;
    public int EdgeCount;	// EdgeCount = Edges.length
    
	public Graph(int vertexCount) {
		this.VertexCount = vertexCount;
		//Vertices = new int[VertexCount];
		EdgeCount = 0;
		edgeList = new PriorityQueue<Edge>();
	}

    public void AddEdge(int begin, int end, int weight)
    {
    	Edge e = new Edge(begin, end, weight);
    	edgeList.add(e);
    	EdgeCount++;
    }
    
    // In the subsets, there's only one representative(e.g. root). So Find() is to find this root.
	private int Find(Subset[] subsets, int i)
	{ 
		// find root and make root as parent of i (path compression is very effective which makes O(n) to O(logn))
		if (subsets[i].Parent != i)
			subsets[i].Parent = Find(subsets, subsets[i].Parent);

		return subsets[i].Parent;
	}
 
	private void Union(Subset[] subsets, int x, int y)
	{
		int xroot = Find(subsets, x);
		int yroot = Find(subsets, y);

		// Attach smaller rank tree under root of high rank tree (Union by Rank)
		if (subsets[xroot].Rank < subsets[yroot].Rank)
			subsets[xroot].Parent = yroot;
		else if (subsets[xroot].Rank > subsets[yroot].Rank)
			subsets[yroot].Parent = xroot;
		else // If ranks are same, then make one as root and increment its rank by one
		{
			subsets[yroot].Parent = xroot;
			subsets[xroot].Rank++; //If ranks are same, it must "Rank++"
		}
	}
	
	public Edge[] Kruskal()
	{
		// If we are not allowed to change the given graph, we can create a copy of array of edges
		Edge[] mst = new Edge[VertexCount - 1];	// This will store the resultant MST
		//edgeList = generateEdgeList(graph); // Insert all edges into the priority queue
		
		// Allocate memory for creating V subsets with single elements
		Subset[] subsets = new Subset[VertexCount];
		//Subset[] subsets;
		for (int i = 0; i < subsets.length; i++)
		{
			subsets[i] = new Subset(); //subsets[i] is just the pointer, it needs to pointer the object before be used.
			subsets[i].Parent = i;
			subsets[i].Rank = 0;
		}

		// Number of edges to be taken is equal to V-1
		int e = 0;
		while (e < VertexCount - 1)
		{
			// Pick the top element in PriorityQueue which is the smallest edge.
			Edge nextEdge = edgeList.poll();
			
			int x = Find(subsets, nextEdge.Begin); //
			int y = Find(subsets, nextEdge.End);
			// If adding this edge does't cause cycle, then add it and increase the index of result
			if (x != y) {
				mst[e] = nextEdge;
				e++;
				Union(subsets, x, y); //it just needs to modify the pointer of one root.
			}
			else {
				// Else discard the nextEdge
			}
		}
		return mst;
	}	
}