import java.util.Scanner;
/*
 * Date:29/3/2018
 * author:joe
 */
public class MaxSum {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
         Scanner in=new Scanner(System.in);
         int n=in.nextInt();
         int k,MaxSum;
         int[] arr=new int[n];
         for(int i=0;i<arr.length;i++)
         {
        	 k=in.nextInt();
        	 arr[i]=k;
         }
         MaxSum=Sum(n,arr);
         System.out.println(MaxSum);
         in.close();
	 } 
	public static int Sum(int n,int[] arr) {
		int MaxSum=0;
         for(int i=0;i<arr.length;i++)
         {
        	 int Sum=0;//当前数列
        	 for(int j=i;j<arr.length;j++)
        	 {
        		 Sum+=arr[j];
        		 if(Sum>MaxSum)
        		 {
        			 MaxSum=Sum;
        		 }
        	 }
         }
         return MaxSum;
	}

}
