

public class SecondQuestion
{
	
	public static void main(String[] args)
	{
		int[] Array = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
		int[] Test_Input = {12,-3,2,35,45,-15,65,75,85,37,10,11};
		
		System.out.println("=================================================================");
		System.out.println("NOTE: 1~20 are TRUE number in Array, and Others are not in Array.");
		System.out.println("=================================================================");
		
		System.out.println("Test first number in Array:");
		Test(Array, 0, Array.length-1, Array[0]); //test first number
		System.out.println();
		System.out.println("Test last number in Array:");
		Test(Array, 0, Array.length-1, Array[Array.length-1]); //test last number
		System.out.println();
		System.out.println("Test Random number 13 in Array:");
		Test(Array, 0, Array.length-1, 13); //test Random number 13
		System.out.println();
		System.out.println("Test number not in the Array:");
		Test(Array, 0, Array.length-1, 33); //test number not in the Array
		System.out.println();
		
		System.out.println();
		System.out.println("===============My Own Random Test===============");
		for(int i = 0;i < Test_Input.length; i++)
			Test(Array, 0, Array.length-1, Test_Input[i]);
	}

	public static void Test(int[] Array, int first, int last, int key)
	{
		int returnValue;
		returnValue = Tertiary_search(Array, 0, Array.length-1, key);
		if(returnValue<0)
			System.out.println("FALSE : the NUMBER "+key+" is NOT in the Array.");
		else
			System.out.println("TRUE : the NUMBER "+key+" is in the Array.");		
	}
	
	// please NOTE: "last" could not over "Array.length-1" !!!!!!
	public static int Tertiary_search(int[] sortedArray, int first, int last, int key)
	{
		if (first > last) return -(first + 1); // failed to find key
		
        int mid1 = first + (last-first) / 3;
        int mid2 = first + (last-first)*2 / 3;
        
        if (key == sortedArray[mid1])
            return mid1; 
        if (key == sortedArray[mid2])
            return mid2; 
       
        if (key < sortedArray[mid1]) 
            return Tertiary_search(sortedArray, first, mid1-1, key);      
        else if (key > sortedArray[mid1] && key < sortedArray[mid2])
        	return Tertiary_search(sortedArray, mid1+1, mid2-1, key);        
        else 
            return Tertiary_search(sortedArray, mid2+1, last, key);
	}
}

