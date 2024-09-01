# Max-Min Heap Java Implementation project

## Overview

This Java program implements a **Max-Min Heap**, a specialized binary heap structure where elements at even levels of the tree are greater than or equal to their descendants, and elements at odd levels are less than or equal to their descendants. The heap supports several operations, such as insertion, extraction of the maximum and minimum values, deletion of specific elements, and sorting.

## Features

1. **Heap Construction**:
   - Users can build a Max-Min Heap by entering a series of numbers.
   
2. **Heap Operations**:
   - Insert a value into the heap.
   - Extract the maximum or minimum value.
   - Delete an element at a specified index.
   - Sort the heap (using heap sort).

3. **User Interaction**:
   - The program includes a full and limited menu for interacting with the heap, allowing users to select and perform different operations.

## Interaction Methods and Helpers

- **`printInput(MaxMinHeap heap)`**:
  - Prints the user's input numbers for the Max-Min Heap.

- **`printHeap(MaxMinHeap heap)`**:
  - Prints the heap's elements in array form. If the heap is empty, an appropriate message is displayed.

- **`printSortedArr(MaxMinHeap heap, int sortedArrLen)`**:
  - Prints the sorted numbers in the heap after the `heapSort()` method is called.

- **`printIntDou(double[] arr, int length)`**:
  - Helper method for printing elements in integer form if they are integers, or in double form otherwise.

- **`isInt(double num)`**:
  - Helper method that returns `true` if the given number is an integer, `false` otherwise.

- **`play()`**:
  - Displays a menu and waits for user input to either build a heap or exit the program.

- **`buildUserHeap(MaxMinHeap userHeap)`**:
  - Prompts the user to enter keys for the Max-Min Heap and builds the heap using the inputs.

- **`limitedMenu(MaxMinHeap userHeap)`**:
  - Displays a limited menu for user interaction, allowing the user to build a heap or insert a value.

- **`fullMenu(MaxMinHeap userHeap)`**:
  - Displays a full menu for user interaction, allowing for various operations on the Max-Min Heap, including building, inserting, extracting, deleting, and sorting.

## Running the Program

To run the program, execute the `main` method in the `MaxMinHeap` class. The program will display a menu where you can choose to build a heap, insert values, extract the maximum or minimum, delete a value, sort the heap, or exit the program.

## Error Handling

The program includes robust error handling to manage invalid user inputs and edge cases, such as attempting to operate on an empty heap or inserting values when the heap is full.

## Conclusion

This implementation provides a comprehensive set of functionalities for working with a Max-Min Heap in Java, including user-friendly interaction and error management.



