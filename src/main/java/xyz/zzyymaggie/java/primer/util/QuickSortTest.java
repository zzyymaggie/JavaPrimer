package xyz.zzyymaggie.java.primer.util;

/**
 * 快速排序 二分法+递归
 * @author zzyymaggie
 *
 */
public class QuickSortTest {

	public static void main( String[] args) {
		int[] values = new int[] {6,1,4,7,3,2};
		quickSort(values, 0, values.length-1);
		for(int i=0;i<values.length;i++) {
			if(i<(values.length -1)) {
				System.out.print(values[i] + ",");
			} else {
				System.out.print(values[i]);
			}
		}
	}
	
    public static void quickSort(int[] array,int lo ,int hi){
        if(lo>=hi){
            return ;
        }
        int index=partition(array,lo,hi);
        quickSort(array,lo,index-1);
        quickSort(array,index+1,hi); 
    }
    public static int partition(int []array,int left,int right){
		int pivot = array[left];
		int i = left, j = right;
		while(i < j) {
			if(array[j] >= pivot && i < j) {
				j--;
			}
			if(array[i] <= pivot && i < j) {
				i++;
			}
			int temp = array[i];
			array[i] = array[j];
			array[j] = temp;
		}
		array[left] = array[j];
		array[j] = pivot;
		return j;
    }
}
