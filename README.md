# Buddy Memory Allocation System

## Introduction
The Buddy Memory Allocation System is a memory management technique used in operating systems to allocate and deallocate memory efficiently. This project implements the Buddy System in Java, simulating how memory is managed dynamically by splitting and merging blocks of memory.

## Features
- Allocation of memory blocks based on power-of-two sizes.
- Deallocation of memory blocks with automatic coalescing of adjacent free blocks.
- Efficient searching for available blocks using an array of free lists.
- Utilization of a hashmap to track allocated memory blocks.
- Simulated memory management using console output.

## Implementation Details
### Class: `Buddy`
- **Inner Class: `Pair`**
  - Represents a memory block with lower and upper bounds.
- **Fields:**
  - `size`: The total size of memory managed by the system.
  - `arr`: An array of lists that store free memory blocks of various sizes.
  - `hm`: A hashmap tracking allocated memory blocks and their sizes.
- **Constructor:**
  - Initializes memory blocks and organizes them in free lists.
- **Methods:**
  - `allocate(int s)`: Allocates a memory block of size `s` if available.
  - `deallocate(int s)`: Frees a memory block starting at address `s` and merges it with its buddy if possible.

## How It Works
1. **Initialization:** The system starts with a single large memory block.
2. **Allocation:**
   - Finds the smallest free block that fits the requested size.
   - If an exact match is unavailable, larger blocks are recursively split into smaller blocks.
   - The allocated block is tracked using the hashmap.
3. **Deallocation:**
   - Marks the block as free and attempts to merge with its buddy.
   - If the buddy block is also free, they are merged into a larger block.

## Example Usage
The `main` method demonstrates the functionality:
```java
public static void main(String args[]) throws IOException {
    int initialMemory = 1028;
    Buddy obj = new Buddy(initialMemory);
    obj.allocate(100);
    obj.allocate(1028);
    obj.allocate(64);
    obj.allocate(256);
    obj.deallocate(240);
    obj.deallocate(0);
    obj.allocate(75);
    obj.deallocate(64);
    obj.deallocate(32);
}
```

## Installation & Execution
1. Clone this repository.
2. Compile the Java file using:
   ```sh
   javac Buddy.java
   ```
3. Run the program using:
   ```sh
   java Buddy
   ```

## Future Improvements
- Implementing a graphical visualization of memory allocation.
- Enhancing memory allocation efficiency with optimized search algorithms.
- Extending support for non-power-of-two memory sizes.

## License
This project is open-source and available under the MIT License.
