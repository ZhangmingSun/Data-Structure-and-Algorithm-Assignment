

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode() {}	//constructor
	TreeNode(int x) { val = x; } //constructor
}

public class FirstQuestion
{
	public static int count=0;
	
	public static void InorderTraversal(TreeNode root, int[] Array)
	{
		if(root==null) return;
		InorderTraversal(root.left, Array);
		System.out.print(root.val+" ");
		Array[count++] = root.val;
		InorderTraversal(root.right, Array);
	}
	
	// Use Recursion to create a Tree form Array
	// 注意：Array数组的第一个元素为空，cnt从index=1的地方开始计数，这样才能cnt*2和cnt*2+1
	public static TreeNode createTree(int[] Array, int cnt)
	{
		if(cnt >= Array.length) return null;
		
		TreeNode root = new TreeNode(Array[cnt]);
		root.left = createTree(Array, cnt*2);   //it is very important to use "cnt*2"
		root.right = createTree(Array, cnt*2+1);//it is very important to use "cnt*2+1"
		return root;
	}

	public static void Judge(int[] Array)
	{
		System.out.println("First Step: input a Array");
		for(int i = 1;i < Array.length; i++)
			System.out.print(Array[i]+" ");
		System.out.println();
		System.out.println();
		
		System.out.println("Second Step: to build a tree from the input array");
		TreeNode root = createTree(Array, 1); // to build a BST
		System.out.println();
		
		count = 1; //we need "count" to count during Traversal process
		System.out.println("Third Step：output Inorder Traversal result of the created Tree:");
		InorderTraversal(root, Array); //InorderTraversal BST and output to Array
		System.out.println();
		System.out.println();
		
		for(int i = 1;i < Array.length-1; i++)
		{
			if(Array[i] > Array[i+1]) 
			{
				System.out.println("FALSE : the array is NOT a Binary Search Tree.");
				return;
			}
		}
		System.out.println("TRUE : the array is a Binary Search Tree.");
	}
	
	public static void main(String[] args)
	{
		//the first element of Array represents NULL with -1 
		int[] Array1 = {-1,10,5,15,3,8,13,18,2,4,7,9,11,14,16,19};	// it is ture
		int[] Array2 = {-1,10,15,5,3,8,13,18,2,9,7,4,11,14,16,19}; 	// it is false
		
		System.out.println("========================First Input=========================");
		Judge(Array1);
		
		System.out.println();
		
		System.out.println("========================Second Input=========================");
		Judge(Array2);
	}
	
}
