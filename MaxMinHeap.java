
/**
 * Maman 13
 * User friendly Max-Min heap creator
 *
 * @author Gal Levi
 * @version 09/05/2023
 */
import java.util.Scanner;
public class MaxMinHeap{
    
    private double[] _arr; // internal array that stores the elements of the heap
    private int _size; // current size of the heap
    final private int ARRAY_LEN = 512; // default array length
    
/*-----------------------------------------------Instance methods---------------------------------------------------*/
    
    /**
     * Constructor that initializes the heap with an empty array of size 512 and sets the size to zero
     */
    public MaxMinHeap(){
        
        _arr = new double[ARRAY_LEN]; // ARRAY_LEN = 512
        _size = 0;
        
    } // end of constructor MaxMinHeap
    
    /**
     * Adds a new element to the heap
     * @param num the element to add
     */
    public void add(double num){
        
        _arr[_size] = num;
        _size++;
        
    } // end of method add
    
    /**
     * Returns the size of the heap
     * @return the size of the heap
     */
    public int getSize(){
        
        return _size;
        
    } // end of method getSize
    
    
    /**
     * Returns the array length of the heap(capacity)
     * @return the array length of the heap
     */
    public int getCapacity(){
        
        return ARRAY_LEN;
        
    } // end of method getCapacity
    
    /**
     * Resets the size of the heap
     */
    public void resetSize(){
        
        _size = 0;
        
    } // end of method setSize
        
/*---------------------------------Main methods of the program---------------------------------------------------*/

    /**
     * The main method of the program.
     * It creates a MaxMinHeap object for the user. 
     * It first checks if the user wants to build the max-min heap.
     * If not, the program exits.
     * Otherwise, it calls the buildUserHeap method to allow the user to build a heap.
     * It then enters a loop, checking if the heap is empty, in which case it calls the limitedMenu method,
     * or if it is not empty, in which case it calls the fullMenu method.
     * The loop continues indefinitely until the user exits the program.
     */
    public static void main(String[] args){
        
        // creates a MaxMinHeap object for the user
        MaxMinHeap userHeap = new MaxMinHeap();
        
        // checks if the user wants to build the heap - if not, exits the program
        if (!play()){
            System.out.println("You chose to exit the program");
            System.exit(0);
        } // end of if statement
        
        // calls the buildUserHeap method after user decision
        buildUserHeap(userHeap);
        
        // infinite loop until user exits the program
        while (true){
            if (userHeap.getSize() == 0) // if the heap is empty
                limitedMenu(userHeap);
            else // if the heap is not empty
                fullMenu(userHeap);
        
        } // end of while loop
    } // end of method main
    
    /**
     * This method performs either a max heapify or a min heapify operation on a max-min heap 
     * at a given index i based on the level of the index in the heap. It uses the floored base-2 logarithm 
     * of the index to determine if the level is even or odd, and calls the corresponding heapify operation.
     * 
     * param heap - a max-min heap to perform the heapify operation on
     * param i - the index to start the heapify operation at
     */
    public static void heapify(MaxMinHeap heap, int i){
        
        // determines the level of the index i using the floored base-2 logarithm
        if (flooredLog2(i+1) % 2 == 0) // i+1 equals to i in array which starts in index 1
            maxHeapify(heap, i);
        else // if the level is odd, perform a min heapify operation
            minHeapify(heap, i);
        
    } // end of method heapify
    
    
    /**
     * This method builds a MaxMinHeap from an array by performing a bottom-up heap construction.
     * It starts by heapifying the subtrees rooted at the parent nodes of the leaves, and works its way 
     * up to the root of the tree.
     * The resulting heap satisfies the max-min heap property.
     * 
     * @param heap a MaxMinHeap object to build a heap from
     */
    public static void buildHeap(MaxMinHeap heap){
        
        // iterates through the indices of the parent nodes of the leaves, in reverse order
        for (int i = heap._size/2 - 1; i >= 0; i--)
            heapify(heap, i);
                
        
        
    } // end of method buildHeap
    
    
    /**
     * This method extracts and returns the maximum element from the max-min heap,
     * while maintaining the max-min heap property.
     * If the heap is empty, it returns Integer.MAX_VALUE.
     * If the heap contains only one element, that element is returned and the heap is emptied.
     * 
     * @param heap a max-min heap to extract the maximum element from
     * @return the maximum element of the heap, or Integer.MAX_VALUE if the heap is empty
     */
    public static double heapExtractMax(MaxMinHeap heap){
        
        // if the heap has only one element, returns that element and empties the heap
        if (heap._size == 1)
            return heap._arr[--heap._size];
        
        // if the heap is empty, returns "infinity"
        else if (heap._size == 0)
            return Integer.MAX_VALUE;
        
        // swaps the maximum element with the last leaf in the heap
        swap(heap, 0, heap._size - 1);
        heap._size--;
        
        // performs a heapify operation on the root of the heap to restore the max-min heap property
        heapify(heap, 0);
        
        // returns the maximum element that was extracted
        return heap._arr[heap._size]; // the heap range is 0 to heap._size-1
    } // end of method heapExtractMax
    
    
    /**
     * This method extracts and returns the minimum element from a max-min heap, 
     * while maintaining the max-min heap property. 
     * If the heap is empty, it returns Integer.MIN_VALUE. 
     * If the heap contains only one or two elements, the last element is returned and the heap is emptied.
     * 
     * @param heap a max-min heap to extract the minimum element from
     * @return the minimum element of the heap, or Integer.MIN_VALUE if the heap is empty
     */
    public static double heapExtractMin(MaxMinHeap heap){
        
        switch (heap._size){
            
            case 0: return Integer.MIN_VALUE; // if the heap is empty, returns "negative infinity"
            // if the heap has only one element, returns it and empties the heap
            case 1: return heap._arr[--heap._size];
            // if the heap has only two elements, returns the second element(index 1) and deletes it from heap
            case 2: return heap._arr[--heap._size];
        } // end of switch
        
        /* the minimum value is always at level 1 of the heap.
           thus we need to find the minimum between the two children of root */
        int smallest = 1;
        if (heap._arr[2] < heap._arr[1])
            smallest = 2;
        
        swap(heap, smallest, heap._size - 1);
        heap._size--;
        
        // performs a heapify operation on the swapped element to restore the max-min heap property
        heapify(heap, smallest);
        // returns the minimum element that was extracted
        return heap._arr[heap._size];
    } // end of method heapExtractMin
    
    /**
     * Inserts a new element into the heap, preserving the heap property.
     * The element is first added to the end of the array and then bubbled
     * up until it reaches its correct position according to the heap property.
     *
     * @param heap the max-min heap to insert the key into
     * @param key the value to be inserted into the heap
     */
    public static void heapInsert(MaxMinHeap heap, double key){
        
        heap.add(key);
        bubbleUp(heap, heap._size-1);
        
    } // end of method heapInsert
    
    
    /**
     * Deletes the element at index i from the heap, preserving the heap property.
     * 
     * @param heap the max-min heap
     * @param i the index of the element to be deleted
     * @return 1 if the element is successfully deleted, 0 otherwise
     */
    public static int heapDelete(MaxMinHeap heap, int i){
        
        // if i is out of range, returns 0
        if (i < 0 || i >= heap._size)
            return 0;
        
        // swaps the element to be deleted with the last element
        swap(heap, i, heap._size - 1);
        heap._size--;
        
        // only one of the methods below actually works
        bubbleUp(heap, i); // bubble up the swapped element to maintain max-min heap property
        heapify(heap, i); // heapify the tree rooted at the swapped element to maintain max-min heap property
        return 1; // indicates successful deletion
    } // end of method heapDelete
    
    /**
     * Sorts the elements of a max-min heap in non-decreasing order.
     * The sorted elements are left in the heap array and deleted from the heap itself.
     * 
     * @param heap the max-min heap to sort
     * @return the number of elements sorted
     */
    public static int heapSort(MaxMinHeap heap){
        
        int sortedArrLen = heap._size; // stores the number of elements
        // if there is only one element, there is nothing to sort
        if (heap._size == 1)
                heap._size--;
                
        while (heap._size > 0){ // while there are still elements in the heap
            // swaps the max element with the last element in the "array" and deletes it from heap
            swap(heap, 0, heap._size - 1);
            heap._size--;
            
            // re-heapify the heap to maintain the max-min heap property
            heapify(heap, 0);
        } // end of while
        return sortedArrLen; // returns the number of the sorted elements
        
    } // end of method heapSort
    
/*---------------------------------Helper methods-------------------------------------------------------------------*/  
    /*
     * Calculates the index of the parent of the element at index i
     * param i - the index of the element
     * returns the index of the parent of the element
     */
    private static int parent(int i){
        
        return (i+1)/2-1; // equals to i/2 in array which starts in index 1
        
    } // end of method parent
    
    /*
     * Calculates the index of the left child of the element at index i
     * param i - the index of the element
     * returns the index of the left child of the element
     */
    private static int left(int i){
        
        return (i+1)*2-1; // equals to i*2 in array which starts in index 1
        
    } // end of method left
    
    /*
     * Calculates the index of the right child of the element at index i
     * param i - the index of the element
     * returns the index of the right child of the element
     */
    private static int right(int i){
        
        return (i+1)*2; // equals to i*2+1 in array which starts in index 1
        
    } // end of method right
    
    /*
     * Swaps the elements at indices i and j in the heap
     * param heap - the heap in which to perform the swap
     * param i - the index of the first element
     * param j - the index of the second element
     */
    private static void swap(MaxMinHeap heap, int i, int j){
        
        double temp;
        temp = heap._arr[i];
        heap._arr[i] = heap._arr[j];
        heap._arr[j] = temp;
        
    } // end of method swap
    
    /*
     * Helper method for heapify
     * Fixes the even levels of the heap (the max areas)
     * param heap - the heap to heapify
     * param i - the index of the root of the subtree
     */
    private static void maxHeapify(MaxMinHeap heap, int i){
        
        // finds the left and right child indices of the given index
        int l = left(i);
        int r = right(i);
        
        // initializes the largest value index as the given index
        int largest;
        if (l < heap._size && heap._arr[l] > heap._arr[i])
            largest = l;
        else
            largest = i;
        
        /* checks if the left child is larger than the given index
           if it is, updates the largest value index to the left child index */
        if (r < heap._size && heap._arr[r] > heap._arr[largest])
            largest = r;
        
        // if the largest value index is not the given index, swap the values of the given index and the largest index
        if (largest != i){
            swap(heap, i, largest);
            largest = i; // updates the largest index after swap - necessary for the next loop
        } // end of if statement
        
        /* checks the largest value index for its grandchildren,
           the loop iterates 4 times for the 4 grandchildren indices */
        for (int j = left(left(i)); j < heap._size && j <= right(right(i)); j++){
            // if a grandchild is larger than the largest value index, updates the largest value index
            if (heap._arr[j] > heap._arr[largest])
                largest = j;
        } // end of for
        
        /* if the largest value index is not the given index after checking grandchildren,
           swap the values of the given index and the largest index and perform max heapify on the largest index recursively */
        if (largest != i){
            swap(heap, i, largest);
            maxHeapify(heap, largest);
        } // end of if statement
        
    } // end of method maxHeapify
    
    /*
     * Helper method for heapify
     * Fixes the odd levels of the heap (the min areas)
     * param heap - the heap to heapify
     * param i - the index of the root of the subtree
     */
    private static void minHeapify(MaxMinHeap heap, int i){
    
        int l = left(i);
        int r = right(i);
        int smallest;
        if (l < heap._size && heap._arr[l] < heap._arr[i])
            smallest = l;
        else
            smallest = i;
        
        if (r < heap._size && heap._arr[r] < heap._arr[smallest])
            smallest = r;
        
        if (smallest != i){
            swap(heap, i, smallest);
            smallest = i;
        } // end of if statement 
        
        // checks the smallest value index for its grandchildren
        for (int j = left(left(i)); j < heap._size && j <= right(right(i)); j++){
            if (heap._arr[j] < heap._arr[smallest])
                smallest = j;
        } // end of for
        
        /* if the smallest value index is not the given index after checking grandchildren,
           swap the values of the given index and the smallest index and perform min heapify on the smallest index recursively */
        if (smallest != i){
            swap(heap, i, smallest);
            minHeapify(heap, smallest);
        } // end of if statement
                
    } // end of method minHeapify
    

    /*
     * This method returns the floored base-2 logarithm of the given integer.
     * 
     * param i - the integer to compute the floored base-2 logarithm of
     * returns the floored base-2 logarithm of the given integer
     */
    private static double flooredLog2(int i){
        
        return Math.floor((Math.log(i)/Math.log(2)));
        
    } // end of method flooredLog2 
    
    /*
     * Helper method for heapInsert and heapDelete methods
     * 
     * This method moves the node at index i up in the heap until the heap property is satisfied.
     * It checks whether the parent and the grandparent of the node (if it exists) violate the heap property.
     * If the node has an even depth (i.e., it is a min-level node), then it must be greater than its parent 
     * and less than or equal to its grandparent. If it has an odd depth (i.e., it is a max-level node),
     * then it must be less than its parent and greater than or equal to its grandparent.
     * If either the parent or the grandparent violate the heap property, then the node is swapped with the 
     * parent or the grandparent, depending on the depth of the node, and the loop continues with the new index.
     * If the heap property is already satisfied, the loop ends.
     * 
     * param heap - the MaxMinHeap instance to which the node belongs
     * param i - the index of the node to be moved up in the heap
     */
    private static void bubbleUp(MaxMinHeap heap, int i){
        
        // flag to determine if the node is at an even or odd level
        boolean isEvenDepth;
        int grandParent, node = i;
        while (node > 0){ // until the root
            isEvenDepth = flooredLog2(node+1) % 2 == 0;
            grandParent = parent(parent(node)); // equals to i*4 in array which starts in index 1
            
            // checks if the node violates the max-min heap property with its parent
            if ( parent(node) >= 0 && 
            ((isEvenDepth && heap._arr[parent(node)] > heap._arr[node]) || 
            (!isEvenDepth && heap._arr[parent(node)] < heap._arr[node])) ){
                    swap(heap, parent(node), node);
                    node = parent(node);
            } // end of if statement
            
            // checks if the node violates the max-min heap property with its grandparent
            else if ( grandParent >= 0 &&
            ( (isEvenDepth && heap._arr[grandParent] < heap._arr[node]) ||
            (!isEvenDepth && heap._arr[grandParent] > heap._arr[node]) ) ){
                    swap(heap, grandParent, node);
                    node = grandParent;
            } // end of else if statement
            else // heap property is satisfied
                break;
            
        } // end of while
        
        
    } // end of method bubbleUp

/*---------------------------------Interaction methods and their helpers--------------------------------------------*/ 
   
     /*
     * Helper method for printInput, printHeap, printSortedArr
     * 
     * Prints the elements' values of the param arr in the range of param length.
     * If the element's value is an Integer one - prints it as Integer, otherwise as a double.
     * param arr - the array to print
     * param length - the range for printing
     */
    private static void printIntDou(double[] arr, int length){
        
        for (int i = 0; i < length; i++){
            if (isInt(arr[i])) // if the element is an integer, prints it as an integer
                System.out.print((int)arr[i]);
            else // otherwise, prints it as a double
                System.out.print(arr[i]);
            if (i != length - 1) // if we are not at the end of the heap, prints a comma and space
                System.out.print(", ");
        } // end of for
    } // end of method printIntDou


     /*
     * Prints the input numbers of user for the max-min heap.
     * 
     * param heap - the pre-build max-min heap
     */
    private static void printInput(MaxMinHeap heap){
        
        printIntDou(heap._arr, heap._size);
        System.out.println(".");
        
    } // end of method printInput
    
    /*
     * Prints the elements of the heap in array form.
     * If the heap is empty, prints a message indicating so.
     * 
     * param heap - The max-min heap to print.
     */
    private static void printHeap(MaxMinHeap heap){
        
        if (heap._size == 0){
            System.out.println("The heap is empty...\n");
            return;
        } // end of if statement
        
        System.out.print("[");
        printIntDou(heap._arr, heap._size);
        System.out.println("]");
    } // end of method printHeap
    
    /*
     * Prints the sorted numbers in the heap after heapSort() has been called.
     *
     * param heap - The max-min heap containing the array of sorted numbers.
     * param sortedArrLen - The length of the sorted array.
     */
    private static void printSortedArr(MaxMinHeap heap ,int sortedArrLen){
        
        System.out.println("The sorted numbers:");
        System.out.print("[");
        printIntDou(heap._arr, sortedArrLen);
        System.out.println("]");
        
    } // end of method printSortedArr
    
    /*
     * Returns true if the given number is an integer, false otherwise.
     * 
     * param num - the number to check
     * returns true if num is an integer, false otherwise
     */
    private static boolean isInt(double num){
        
        return num == (int)num;
        
    } // end of methos isInt
    
    /*
     * Displays a menu and waits for user input to either build a heap or exit the program.
     * 
     * returns a boolean value indicating whether the user wants to build a heap or exit the program.
    */
    private static boolean play(){
        
        // creates a new scanner to read user input
        Scanner scan = new Scanner(System.in);
        
        String menu = "***Menu***\n" +
                        "Enter 1 to build a heap\n" +
                        "Enter 0 to exit the program.";
                        
        System.out.println(menu); // display the menu to the user
        while (true){ // continuously prompts the user until a valid input is entered
            String input = scan.nextLine();
            switch (input){
                case "1": return true;
                case "0": return false;
                default: System.out.println("Invalid input, try again..\n" + menu);
            } // end of switch
        } // end of while
                                
    } // end of method play
    
    /*
     * Prompts the user to enter keys for a max-min heap and builds the heap using the inputs.
     * 
     * param userHeap - the max-min heap to build
    */
    private static void buildUserHeap(MaxMinHeap userHeap){
        
        double inputNum; // variable to hold the user's input
        Scanner scan = new Scanner(System.in);
        userHeap.resetSize(); // resets the heap size to zero(mainly for re-build)
        System.out.println("Enter keys for Max-Min heap: enter a non-number character to stop");
        try{
            while (true){// continue looping until an exception is caught
                
                inputNum = scan.nextDouble();
                userHeap.add(inputNum); // add the input number to the heap
                
            } // end of while
        } // end of try
        catch(Exception e){ // if an exception is caught, the user has stopped entering numbers
            
            if (userHeap.getSize() != 0){ // if any numbers have been entered, prints them
                if (userHeap.getSize() == userHeap.getCapacity()) // if heap is full
                    System.out.println("You have entered too much numbers..."); 
                System.out.println("The received numbers:");
                printInput(userHeap);
            } // end of if statement
            else{ // no number has been received, informs the user that they have not entered any numbers
                System.out.println("You have not entered numbers...");
                return;
                
            } // end of else
        
        } // end of catch
        
        buildHeap(userHeap); // builds the heap using the inputs
        System.out.println("Your Max-Min heap is: ");
        printHeap(userHeap); // prints the final heap
        
    } // end of method buildUserHeap
    
    
    /*
     * Displays a limited menu of options for the user to interact with the max-min Heap.
     * Continuously prompts the user for input until a non-number character is entered.
     * param userHeap - the max-min Heap to be used for operations
     */
    private static void limitedMenu(MaxMinHeap userHeap){
        
        Scanner scan = new Scanner(System.in);
        Scanner newKey = new Scanner(System.in);
        int menuNum;
        
        // prints the limited menu
        System.out.println("***Menu***\n" +
                        "Enter 1 to build a heap\n" +
                        "Enter 2 to insert a value\n" +
                        "Enter a non-number character to exit the program");
                        
        try{
                    
                menuNum = scan.nextInt();
                switch(menuNum){
                    
                    // case 1: allows the user to build a heap
                    case 1: buildUserHeap(userHeap);
                    break;
                    // case 2: allows the user to insert a value into the heap
                    case 2: boolean success = false; // flag to track of successful insertion
                            System.out.println("Enter a new value:");
                            while (!success){
                                try {
                                    double key = newKey.nextDouble();
                                    heapInsert(userHeap, key);
                                    System.out.println("The heap after the insertion:");
                                    printHeap(userHeap);
                                    success = true;
                                } // end of try
                                // handles invalid input for the new value
                                catch(Exception e) {
                                    System.out.println("ERROR - Invalid input\n") ;
                                }
                            } // end of while
                    break;
                    // default: prompts the user for a valid menu number
                    default: System.out.println("Please enter a valid menu number\n");
                    
                } // end of switch
                
        } // end of try
        // handles program exit if a non-number character is entered in menu
        catch(Exception e){
            
            System.out.println("You chose to exit the program");
            System.exit(0);
        
        } // end of catch
        
        
    } // end of method limitedMenu
    
    /*
     * Displays a full menu of options for the user to interact with the max-min heap.
     * The method prompts the user to enter a number for their desired operation and 
     * calls the appropriate method based on their input.
     * Prints the heap after each operation has called.
     *
     * param userHeap - The max-min heap to operate on.
     */
    private static void fullMenu(MaxMinHeap userHeap){
        
        Scanner scan = new Scanner(System.in);
        Scanner newKey = new Scanner(System.in);
        Scanner delScan = new Scanner(System.in);
        int menuNum;
        
        // prints the full menu
        System.out.println("***Menu***\n" +
                        "Enter 1 to build a new heap\n" +
                        "Enter 2 to print the heap\n" +
                        "Enter 3 to extract the maximum value\n" +
                        "Enter 4 to extract the minimum value\n" +
                        "Enter 5 to insert a new value to heap\n" +
                        "Enter 6 to delete an existing value from heap\n" +
                        "Enter 7 to sort the heap(deletes the existing heap)\n" +
                        "Enter a non-number character to exit the program");
                        
        try{
            menuNum = scan.nextInt();
            switch(menuNum){
                    
                // case 1: allows the user to build a new heap by trampling the old one
                case 1: buildUserHeap(userHeap);
                break;
                // case 2: allows the user to print the current state of the max-min heap.
                case 2: System.out.println("Your Max-Min heap is: ");
                            printHeap(userHeap);
                break;
                // case 3: allows the user to extract the current maximum value of the heap.
                case 3: double max = heapExtractMax(userHeap);
                        if (max != Integer.MAX_VALUE){
                            System.out.print("The max value was:"); // if the heap is not empty
                                
                            if (isInt(max))
                                System.out.println((int)max);
                            else
                                System.out.println(max);
                                    
                            System.out.println("The heap after the extraction:");
                            printHeap(userHeap);
                        } // end of external if statement
                break;
                // case 4: allows the user to extract the current minimum value of the heap.
                case 4: double min = heapExtractMin(userHeap);
                        if (min != Integer.MIN_VALUE){ // if the heap is not empty
                            System.out.print("The min value was:");
                                
                            if (isInt(min))
                                System.out.println((int)min);
                            else
                                System.out.println(min);
                                    
                            System.out.println("The heap after the extraction:");
                            printHeap(userHeap);
                        } // end of external if statement
                break;
                // case 5: allows the user to insert a new value to the heap.
                case 5: if (userHeap.getSize() == userHeap.getCapacity()){ // if heap is full
                            System.out.println("The heap is full, please choose another operation...");
                            break;
                        }
                        System.out.println("Enter the new value:") ;
                        try {
                            double key = newKey.nextDouble();
                            heapInsert(userHeap, key);
                            System.out.println("The heap after the insertion:");
                            printHeap(userHeap);
                        } // end of try
                                
                        catch(Exception e) {
                            System.out.println("ERROR - Invalid input\n") ;
                        }
                break;
                // case 6: allows the user to delete an existing value in the heap.
                case 6: System.out.println("Enter the index of the value you want to delete:") ;
                        try {
                            int delIdx = delScan.nextInt();
                            int checkDel = heapDelete(userHeap, delIdx);
                            if (checkDel == 1){ // successful deletion
                                System.out.println("The heap after the deletion:");
                                printHeap(userHeap);
                            } // end of if statement
                            else // out of range index has entered
                                System.out.println("ERROR - index is out of range\n");
                                
                        } // end of try
                                
                        catch(Exception e) {
                            System.out.println("ERROR - Invalid input\n");
                        }
                break;
                // case 7: allows the user to sort the numbers he has entered.
                case 7: int sortedArrLen = heapSort(userHeap);
                        printSortedArr(userHeap, sortedArrLen);
                break;
                default: System.out.println("Please enter a valid menu number\n");
                    
            } // end of switch
                
        } // end of try
        catch(Exception e){ // the user entered a non-number character
            
            System.out.println("You chose to exit the program");
            System.exit(0);
        
        } // end of catch
        
    } // end of method fullMenu
    
    
} // end of class MaxMinHeap



