import java.util.*;

public class QuickSelectDiff {

	public static void main(String[] args) {
		QuickSelectDiff quickSelecter = new QuickSelectDiff();
		
		Scanner scnr = new Scanner(System.in);
		int n; // Variable for size of array
		
		System.out.println("Enter the desired array length: ");
		
		n = scnr.nextInt(); 
		
		int[] a = new int[n]; // Creating array with the desired size
		
		for (int i = 0; i < n; i++) { // Adding random numbers from the range -100 to 100 into the array
			int randNum = -100 + (int)(Math.random() * (100 - -100) + 1);
			a[i] = randNum;
		}
		
		System.out.println("The generated random array with the desired length of " + n + " is: " + Arrays.toString(a)); // Printing out the generated array
		
		System.out.println("Enter a number from 1 to " + n + ":");
		
		int k; // Variable for how many numbers to find that are close to the median
		
		k = scnr.nextInt();
		
		int firstElem = 0;
		int lastElem = a.length - 1;
		int tempMedian = a.length / 2; // Index of the median in the array
		
		int median = quickSelecter.quickSelect(a, firstElem, lastElem, tempMedian); // Call Quick Select method to find median of the array

		System.out.println("The median of the generated array is: " + median);
		
		int[] diff = a; // Copy the values from a[] into diff[]
		
		for(int j = 0; j < a.length; j++) { // Subracting each of the values from a by median to find the diff
			int temp = diff[j] - median;
			diff[j] = temp;
		}
		
		// Call partition method checking abs(pivot) and abs(diff)
		int[] finalArr = new int[k];
		int closestValue = quickSelecter.quickSelectDiff(diff, firstElem, lastElem, tempMedian);
		for (int i = 1; i <= k; i++) {
			finalArr[i - 1] = diff[i] + median;
		}
		
		System.out.println("The " + k + " closest numbers to the median " + median + " is: "); // Printing out the final answer
		for (int j = 0; j < finalArr.length; j++) {
			System.out.print(finalArr[j] + " ");
		}
	}
	
	// Time Complexity: O(1) + O(n) = O(n) 
	// QuickSelect Method
	public int quickSelect (int a[], int firstElem, int lastElem, int tempMedian) {
		int pivotIdx = partition(a, firstElem, lastElem); // Call partition to find new pivot;
		int result = 0;
		
		if (tempMedian == pivotIdx) { // Base Case: Checks if tempMedian is equal to the current index of the pivot
			result = a[pivotIdx];
		} else if (tempMedian > pivotIdx) { // Checks if tempMedian is greater than the current index of pivot
				result = quickSelect(a, pivotIdx + 1, lastElem, tempMedian); // Keep partitioning array
		} else { // tempMedian is less than the current index of pivot
			result = quickSelect(a, firstElem, pivotIdx - 1, tempMedian); // Keep partitioning array
		}
		
		return result; // Return median value

	}
	
	// Time Complexity: O(n) 
	// Partition Method
	public int partition (int a[], int firstElem, int lastElem) { // Using first element in the array as the pivot and swapping with the middle
		int pivot = a[firstElem];
		int i = firstElem - 1;
		int j = lastElem + 1;
		
		while (i < j) {
			i++;
			while (a[i] < pivot) { // Left pointer i starts at left of array and increases towards to pivot
				// start at beginning of array and move toward pivot
				i++;
			}
			
			j--;
			while (a[j] > pivot){ // Right pointer j starts at right of array and decrements towards pivot
				// start at end of array and move toward pivot
				j--;
			}
			
			// Call the swap method to swap the two values 	
			if (i < j) {
				swap(a, i, j);
				
			}		
		}
		
		return j; // Return the index of the median
		
	}
	
	// Time Complexity: O(1)
	// Helper function to swap two elements that need to be swapped 
	public void swap (int[] a, int x, int y) {
		int temp = a[x];
		a[x] = a[y];
		a[y] = temp;
		
	}
	
	// Time Complexity: O(1) + O(n) = O(n) 
	// QuickSelect Method for the Difference
	public int quickSelectDiff (int a[], int firstElem, int lastElem, int tempMedian) { 
		int pivotIdx = partitionDiff(a, firstElem, lastElem); // Call partition to find new pivot;
		int result = 0;
		
		if (tempMedian == pivotIdx) { // Base Case: Checks if tempMedian is equal to the current index of the pivot
			result = pivotIdx;
		} else if (tempMedian > pivotIdx) { // Checks if tempMedian is greater than the current index of pivot
				result = quickSelectDiff(a, pivotIdx + 1, lastElem, tempMedian); // Keep partitioning array
		} else { // tempMedian is less than the current index of pivot
			result = quickSelectDiff(a, firstElem, pivotIdx - 1, tempMedian); // Keep partitioning array
		}

		return result; // Return median value

	}
	
	// Time Complexity: O(n)
	// Partition Method for the Difference
	public int partitionDiff (int a[], int firstElem, int lastElem) { // Using first element in the array as the pivot and swapping with the middle
		int pivot = a[firstElem];
		int i = firstElem - 1;
		int j = lastElem + 1;
		
		while (i < j) {
			i++;
			while (Math.abs(a[i]) < Math.abs(pivot)) { // Left pointer i starts at left of array and increases towards to pivot
				// start at beginning of array and move toward pivot
				i++;
			}
			
			j--;
			while (Math.abs(a[j]) > Math.abs(pivot)) { // Right pointer j starts at right of array and decrements towards pivot
				// start at end of array and move toward pivot
				j--;
			}
			
			// Call the swap method to swap the two values
			if (i < j) {
				swap(a, i, j);
				
			}		
		}
				
		return j;
		
	}
	
}

// #5 The Time complexity to find the median is O(n)
// --> To find the median of the array I can just use my QuickSelect implementation to return the median
// #6 The time complexity to save the difference from the median (a[i] - median) into a new array called diff is O(1)
// --> The time complexity will be O(1) because within the for loop that's iterating through the array,
// --> there is only a simple subtraction calculating the difference of the median and that element. 
// --> There is no nested for loop or recursive method call, meaning that at this point the time complexity is O(1)
// #8 The time complexity to shift the K found numbers back into their original value is O(1).
// --> Similar to calculating the difference, there is only a for loop that is iterating through the diff array
// --> adding the median and diff[i]. Therefore, the time complexity at this stage is O(1).
// The total time complexity of my algorithm will be O(n). The total time complexity of my algorithm will be O(n)
// --> because the time it takes to find the median of the random generated array will be O(n), since it is using 
// --> the QuickSelect algorithm. Also, the time it takes to K closest values to the median will again be O(n) 
// --> because it is using the QuickSelect Algorithm as well. Knowing the total time complexity of each method and 
// --> of the points at #6 and #8, we get O(1) + O(1) + O(n) + O(n) = O(n).
// --> Total time complexity of my algorithm will be O(n) because we need to pick the larger time and I know that 
// --> O(n) > O(1). Therefore T(n) = O(n) for my algorithm.
