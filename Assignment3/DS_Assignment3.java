import java.util.Scanner;

class Node
{
	private int iData;

	public Node(int key)	// constructor
	{ iData = key; }

	public int getKey()
	{ return iData; }
}/**/

class Heap
{
	private Node[] heapArray;
	private int maxSize;           // size of array
	private int currentSize;       // number of items in array
	
	public Heap(int mx)            // constructor
	{
		maxSize = mx;
		currentSize = 0;
		heapArray = new Node[maxSize]; // it is important to initial the heapArray
	}
	
	public Node remove()           // delete item with max key (assumes non-empty list)
	{
		Node root = heapArray[0];
		heapArray[0] = heapArray[--currentSize];
		trickleDown(0);
		return root;
	}
	
	public void trickleDown(int index)
	{
		int largerChild;
		Node top = heapArray[index];        // save root
		while(index < currentSize/2)        // it's important, NOTE: not on bottom row
		{
			int leftChild = 2*index+1;
			int rightChild = leftChild+1;
			
			// find larger child
			if(rightChild < currentSize &&   // 
			heapArray[leftChild].getKey() < heapArray[rightChild].getKey())
				largerChild = rightChild;	// if rightChild exists and is larger than leftChild
			else
				largerChild = leftChild;
			
			// Compare largerChild with top
			if(top.getKey() >= heapArray[largerChild].getKey())
				break;
			// swap the larger child with parent node
			heapArray[index] = heapArray[largerChild];
			index = largerChild;
		}
		heapArray[index] = top;	// root to index
	}
	
	public void displayArray()
	{
		for(int j=0; j<maxSize; j++)
		System.out.print(heapArray[j].getKey() + " ");
		System.out.println("");
	}
	
	public void insertAt(int index, Node newNode)
	{ heapArray[index] = newNode; }
	
	public void incrementSize()
	{ currentSize++; }
}

public class DS_Assignment3
{	
	public static void Heapsort(int[] arr)
	{	
		int size = arr.length;
		Heap theHeap = new Heap(size); //
		for(int j=0; j<size; j++)
		{                        	
			//int random = (int)(java.lang.Math.random()*100); // random nodes
			//Node newNode = new Node(random);
			Node newNode = new Node(arr[j]);
			theHeap.insertAt(j, newNode);
			theHeap.incrementSize();
		}
		
		System.out.println("Now it is making random array into a heap......");
		for(int j=size/2-1; j>=0; j--)  // it can save time, if we trickleDown from the index size/2-1
			theHeap.trickleDown(j);
		System.out.println("The heap have been built.");
		System.out.println("");
		
		
		System.out.println("Now it is removing data from heap and storing that data at array end...");
		for(int j=size-1; j>=0; j--)    // remove from heap and store at array end, so it can save space.
		{ 
			Node biggestNode = theHeap.remove();
			theHeap.insertAt(j, biggestNode);
		}
		System.out.println("All data have been removed from the heap and the sorting process have been done.");
		System.out.println("");
		
		System.out.println("Sorted array is as following: ");
		theHeap.displayArray();     // display sorted array
	}
	
	public static void main(String[] args)
	{		
		//test data: 12 11 45 50 99 33 54 62 15 1 4 67 6 8 34 77 85 63 90 91
		
		System.out.println("Please input a list of data from your keyboard:");
        Scanner sc=new Scanner(System.in);
        String inputString=sc.nextLine(); //input a line of string
        String strArr[]=inputString.split("\\s+"); // "\\s+" denotes it can split many blanks
        //System.out.println(strArr.length);
        
        int arr[]=new int[strArr.length];
        for(int i=0;i<strArr.length;i++)
        	arr[i]=Integer.parseInt(strArr[i]); // transfer char to int
        sc.close();
        System.out.println("");
        
        System.out.println("Your input is as following:");
        for(int i = 0; i < arr.length; i++)
        	System.out.print(arr[i] + " ");
		System.out.println("");
		System.out.println("");
		/**/
        
        Heapsort(arr); // The core of the algorithm
	}
}