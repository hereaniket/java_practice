package my.leetcode;

/*
Bubble sort
Selection sort
Insertion sort

Merge sort
Heap sort
 */

public class SortArray {

    public static void main(String[] args) {
        int[] array1 = new int[]{2,3,4,6,8,11,34,66,99};
        int[] array2 = new int[]{3,6,9,11,56,56,77,88,101};

        int[] mergArr = new int[array1.length+ array2.length];

        int i=0, j=0;

        while(i< array1.length + array2.length){
            mergArr[i] = array1[j];
            mergArr[i+1] = array2[j];
            i += 2;
            j++;
        }

        //printArray(mergArr);
        //System.out.println("----------");
        int[] sortedArray = sort(mergArr, true);
        printArray(sortedArray);
    }

    public static int[] sort(int[] ipArray, boolean ascending){
        long st = System.nanoTime();
        if (ascending){
            int needToStop = 0;

            while(needToStop != ipArray.length){

                for (int i = 0; i < ipArray.length; i++) {
                    if(i == 0)
                        needToStop = 0;

                    if (i != ipArray.length - 1 && ipArray[i] > ipArray[i+1]){
                        int current = ipArray[i];
                        int next = ipArray[i+1];
                        ipArray[i+1] = current;
                        ipArray[i] = next;
                    } else {
                        needToStop++;
                    }
                }
            }
        } else {
            System.out.println("Still building");
        }
        double end = (System.nanoTime()-st)/1000000.00;
        System.out.println("Time taken "+end+" milli");
        return ipArray;
    }

    public static void printArray(int[] ipArray){
        System.out.println("The content of the array");
        for (int i = 0; i < ipArray.length; i++) {
            System.out.print(ipArray[i]);
            if (i < ipArray.length)
                System.out.print(",");
        }
        System.out.println();
    }
}
