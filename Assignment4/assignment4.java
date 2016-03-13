/*
 * I use the Adjacency matrix representation. In this representation, each graph of n nodes is represented by an n x n matrix A.
 * Because it is simple to implement, and it is easy and fast to tell if a pair (i,j) is an edge: simply check if A[i][j] is 1 or 0.
 */

import java.util.Stack;

////////////////////////////////////////////////////////////////
class Vertex
{
	public char label;        // label (e.g. 'A')
	public boolean wasVisited;
	
	public Vertex(char lab)   // constructor
	{
		label = lab;
		wasVisited = false;
	}
}
////////////////////////////////////////////////////////////////
class Graph
{
	private final int MAX_VERTS = 10;
	private Vertex vertexList[]; // list of vertices
	private int adjMat[][];      // adjacency matrix
	private int nVerts;          // current number of vertices
	Stack<Integer> theStack;
	//-----------------------------------------------------------
	public Graph()               // constructor
	{
		vertexList = new Vertex[MAX_VERTS];		//Allocate space for vertexList
		adjMat = new int[MAX_VERTS][MAX_VERTS];	//Allocate space for adjacency matrix
		nVerts = 0;
		for(int j=0; j<MAX_VERTS; j++)      	// set adjacency
			for(int k=0; k<MAX_VERTS; k++)   	// matrix to 0
				adjMat[j][k] = 0;
		theStack = new Stack<Integer>();
	}
	//-----------------------------------------------------------
	public void addVertex(char lab)
	{
		vertexList[nVerts++] = new Vertex(lab);
	}
	// -----------------------------------------------------------
	public void addEdge(char char_start, char char_end) 
	{
		int start=0, end=0;		
		// transfer the char to the digital coordinate in the vertexList
		for(int j=0; j<MAX_VERTS; j++)
			if(vertexList[j].label == char_start)
			{start = j; break;}
		for(int j=0; j<MAX_VERTS; j++)
			if(vertexList[j].label == char_end)
			{end = j; break;}
		adjMat[start][end] = 1;
		adjMat[end][start] = 1;
		
		System.out.print(""+ char_start + char_end + " "); // "" indicates the class type is string
	}	
	
	/*public void addEdge(int start, int end)
	{ adjMat[start][end] = 1; adjMat[end][start] = 1; }*/
	
	// ------------------------------------------------------------
	public void displayVertex(int v)
	{
		System.out.print(vertexList[v].label + " ");
	}
	// ------------------------------------------------------------
	public void dfs()  // depth-first search
	{                                 // begin at vertex 0
		vertexList[0].wasVisited = true;  // mark it
		displayVertex(0);                 // display it
		theStack.push(0);                 // push it
		while( !theStack.isEmpty() )      // until stack empty,
		{
			// get an unvisited vertex adjacent to stack top
			int v = getAdjUnvisitedVertex( theStack.peek() );
			if(v == -1)                    // if no such vertex,
				theStack.pop();
			else                           // if it exists,
			{
				vertexList[v].wasVisited = true;  // mark it
				displayVertex(v);                 // display it
				theStack.push(v);                 // push it
			}
		}
		// stack is empty, so we're done
		for(int j=0; j<nVerts; j++)          // reset flags
			vertexList[j].wasVisited = false;
	}
	// ------------------------------------------------------------
	// returns an unvisited vertex adj to v
	public int getAdjUnvisitedVertex(int v)
	{
		for(int j=0; j<nVerts; j++)
		if(adjMat[v][j]==1 && vertexList[j].wasVisited==false)
		return j;
		return -1;
	}
	
	public void TraverseVertex()
	{
		System.out.println("All vertexes in the graph are like follows:");
		for(int j=0; j<vertexList.length; j++)
			System.out.print(vertexList[j].label + " ");
		System.out.println("");
	}
// ------------------------------------------------------------
}  // end class Graph
////////////////////////////////////////////////////////////////

public class assignment4
{
	public static void main(String[] args)
	{
		Graph theGraph = new Graph();
		
		theGraph.addVertex('A');    // 0  (start for dfs)
		theGraph.addVertex('B');    // 1
		theGraph.addVertex('C');    // 2
		theGraph.addVertex('D');    // 3
		theGraph.addVertex('E');    // 4
		theGraph.addVertex('F');    // 5
		theGraph.addVertex('G');    // 6
		theGraph.addVertex('H');    // 7
		theGraph.addVertex('I');    // 8
		theGraph.addVertex('J');    // 9
		theGraph.TraverseVertex();	// Display all vertexes
		
		System.out.println("\nAll edges in the graph are like follows:");   
		
		theGraph.addEdge('A','E'); 	// create edge AE 
		theGraph.addEdge('E','G');     
		theGraph.addEdge('G','I');    
		
		theGraph.addEdge('A','F'); 
		theGraph.addEdge('H','F'); 
		theGraph.addEdge('B','H'); 	
		
		theGraph.addEdge('A','J');
		theGraph.addEdge('J','C');     
		theGraph.addEdge('C','D');  
		
		theGraph.addEdge('E','F'); 	
		theGraph.addEdge('I','J'); 
		theGraph.addEdge('C','B'); 
		theGraph.addEdge('H','I'); 
		
		theGraph.addEdge('E','C'); 
		theGraph.addEdge('F','D'); 
		theGraph.addEdge('B','J'); 
		
		theGraph.addEdge('F','I'); 
		theGraph.addEdge('J','B'); 
		theGraph.addEdge('H','G'); 
		theGraph.addEdge('C','H'); 
		
		System.out.println("\n\nThe order of visit(A is the start vertex for DFS): ");
		theGraph.dfs();             // depth-first search
		System.out.println();
	}
}
