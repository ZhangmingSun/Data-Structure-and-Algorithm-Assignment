import java.util.LinkedList;
import java.util.Queue;

public class Assignment6 
{
	public static void radixSort(int[] inlist, int maxDigits)
	{
		// it's essential to define 10 Queues, and every Queue can save many int data
		Queue<Integer>[] bucket = new Queue[10];
		for(int j=0; j<10; j++)
		{
			Queue<Integer> tmp = new LinkedList<Integer>();
			bucket[j] = tmp;
		}
		/*Queue<LinkedList<Integer>> bucket = new LinkedList<LinkedList<Integer>>();
		for(int j=0; j<10; j++)
		{
			LinkedList<Integer> tmp = new LinkedList<Integer>();
			bucket.add(tmp);
		}*/	
		int digit=0;
		for(int i=0; i<maxDigits; i++)
		{
			for(int j=0; j<10; j++) {
				bucket[j].clear();
			}
			
			for(int index=0; index<inlist.length; index++) //normally first=0
			{
				digit = getDigit(inlist[index], i);
				bucket[digit].add(inlist[index]);	// add to the bucket
			}
			
			int num = 0;
			for(int j=0; j<10; j++) // place all buckets into the input array
			{
				while(!bucket[j].isEmpty())
				{
					inlist[num] = bucket[j].poll();
					num++;
				}
			}
		}	
	}

	// e.g. if number=7345, then digit_place=0 ==> digit"5"; digit_place=1 ==> digit"4"
	public static int getDigit(int number, int digit_place)
	{
		int tmp = 1;
		for(int i=0; i<digit_place; i++)
			tmp *= 10;
		if(digit_place == 0) 
			return number % 10;
		else
			return number/tmp % 10;
	}
	
	public static void main(String[] args)
	{	
		int[] inlist = new int[]
				{1256, 7345, 8866, 6104, 3473, 
				9087, 198, 7089, 2345, 3367, 
				8656, 6666, 6868, 9999, 868};
		
		System.out.println("The Original input number:");
		int newline_count=0;
		for(int j=0; j<inlist.length; j++) {
			System.out.print("" + inlist[j] + "  ");
        	newline_count++;
        	if(newline_count == 5)
        	{
        		System.out.println("");
        		newline_count=0;
        	}
        }
		//================RadixSort=====================
		//the input data array -- inlist
		//maxDigits -- 4
		//==============================================
		radixSort(inlist, 4); 
		
		System.out.println("\nThe sorted number by LSD RadixSort Algorithm:");
		for(int j=0; j<inlist.length; j++) {
			System.out.println("" + inlist[j]);
		}
	}
}
