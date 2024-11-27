# A7 Linked Lists

Your readme should include the following information. **Each student** needs to submit all of this information, even when pair programming. 

## Submission Details

Programming Partner Name (if you are submitting identical code):


Other Collaborators (submitting related but non-identical code):


Kudos/shout-out to particularly helpful members of the class or teaching staff:
Chioma and Beamlak

Any references used besides JavaDoc and course materials:
None


If you used AI, please describe how and the interaction between AI and your understanding of this assignment and specifically the related data structures and algorithms:

## Reflection

What do you see as the benefits and downsides of working with linked lists instead of arrays? When might they be most useful versus limiting?
Benefits:
--- Storage: With linked lists, the storage space doesn't have to be set up at initialization. Storage space is rarely a problem in linked lists as the heap memory is used. It is a problem with arrays because storage has to be initialized from start

--- splice, add, remove: To implement this methods in an array, espescially at points that are not the end of the array, a new array must be created and everything re-copied. Thus, there's always the complexity of O(n). For linkedlists, however, if the position to be added to, removed from, or spliced into is known, the complexity will be O(1).

Downsides:
--- Search: Search operations are more expensive in linkedlists than arrays. To search for an element given its position (index) in array, the computer simply does a series of calculations to find where exactly in memory the object at the given position is. This is a constant-time operation. To search for an element in a linked list, however, the computer would have to iterate through the list until the element is found. This is a linear operation.

--- Size: The size of arrays are underlyingly kept track of in memory. This is not true for linkedlists. As such, to know a linkedlist's length, you'd have to keep; track yourself or iterate through the list to count the elements whenever you need to know its length.

Neutralside:
--- methods that compare elements like max and min. For both arrays and linkedlists, finding the max/min values take O(n) time as the computer has to iterate through the entire list to ascertain for sure that the smallest or largest has been found

--- isEmpty(): In both cases, this is an O(1) operation


What was the major challenge you faced in completing this assignment?
Starting as early as I'd have loved to was definitely a major challenge for me. I'm unable to complete the kudos part (Phase 5) before submission but would get it done this week, hopefully!


What do you feel you learned from this assignment?
How linkedlists work! It was a good learning experience implementing a linkedlist for scratch. I appreciate that I was able to think through the implementation for special cases. The tests were very helpful too!

Design decision I made that should be taken note of:
Whenever afterHere in the splice methods was null, I updated afterHere to point to a null node instead. This way, I could handle null statements vs null nodes the same way. 

Personal notes:
//write test for findBefore to ensure it handles singleton 
//assumption for findBefore-- won't have to handle edge case of here not existing?
//Missing element exception: removeFirst, removeLast, removeAfter,findBefore

