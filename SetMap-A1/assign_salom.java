# SetMap-Asg1

##### Self Check

19. Nope.
20. Yep.
21. O(n^2).
22. It moves from left to right for each linked list, moving to the next bucket when it reaches the end.
23. If the iterator has a bucket remove to it returns true.
24. Since it doesn't matter where in the bucket/linked list an element is added

##### Chapter 16 Review Questions

20.    ​

21.    Moving an element from head to tail: O(1)
                2. Moving an element at the tail of the queue to the tail: O(n)

22.    ​

       |                | addFirst | removeFirst | addLast | removeLast |
       | -------------- | -------- | ----------- | ------- | ---------- |
       | Singly linked  | O(1)     | O(1)        | O(n)    | O(n)       |
       | Doubly linked  | O(1)     | O(1)        | O(1)    | O(1)       |
       | Circular Array | O(1)     | O(1)        | O(1)    | O(1)       |

23.    No, because the values do not indicate index.

24.    ​

                1. 1 2 3 4 5 _ _ _ _ _
                2. _ _ _ 4 5 _ _ _ _ _
                   3. 6 7 8 9 10 4 5 1 2 3 4 5
                   4. _ 7 8 9 10 _ _ _ _ _ _ _ 

25.    Each time you add an element add it to one stack. Each time you remove an element move all elements to the other stack, pop an element from that stack, then move then move them back. Adding: O(1). Removal: O(n)

26.    ​

27.    You will get hella long arrays.

#### Chapter 16 Excercise Questions

17.    ​
      private boolean nextExists = false;
      private int nextIdx = 0;
    
      public boolean hasNext() {
      	if (current != null && current.next != null) { nextExists = true; 
                                                        nextIdx = bucketIndex;
                                                        return true; } 
        	for (int b = bucketIndex + 1; b < buckets.length; b++) {
      		if (buckets[b] != null) { 
                nextExists = true;
                nextIdx = b;
                return true; 
              } 
          }
        	nextExists = false;
      	return false; 
      }
    
      public Object next() {
      	if (current != null && current.next != null) {
      		current = current.next; // Move to next element in bucket
      	}
      	else // Move to next bucket {
            	if(nextExists) {
                	nextExists = false;
      			current = buckets[nextIdx];
              }
        		else {
      			do {
                    bucketIndex++;
                    if (bucketIndex == buckets.length) { 
                        throw new NoSuchElementException(); 
                    }
                    current = buckets[bucketIndex]; 
                }
                while (current == null); 
              }
      	}
      	return current.data; 
      }
      ```


18.    ​
      public void fixLoadFactor() {
      	if((float)currentSize/(float)buckets.size > 1.0) {
      		Node[] newBuckets = new Node[currentSize * 2];
            	for(Node bucket : buckets) {
      			while(bucket.hasNext()) {
      				Object x = bucket.data;
                    	int h = x.hashCode();
                    	h = h % newBuckets.length;
                    	Node current = newBuckets[h];
                    	while(current != null) {
      					current = current.next;
                      }
                    	current.data = x;
                  }
              }
            	bucket = newBuckets;
          }
        	else if((float)currentSize/(float)buckets.size < 0.5) {
      		Node[] newBuckets = new Node[currentSize / 2];
            	for(Node bucket : buckets) {
      			while(bucket.hasNext()) {
      				Object x = bucket.data;
                    	int h = x.hashCode();
                    	h = h % newBuckets.length;
                    	Node current = newBuckets[h];
                    	while(current != null) {
      					current = current.next;
                      }
                    	current.data = x;
                  }
              }
            	bucket = newBuckets;
          }
      }
      ```

20.  ```java
    public int compressHash(int h) {
      int p = nextPrime(buckets.length);
      int a = 32;
      int b = p - 32;
      h = ((a * h + b) % p) % buckets.length;
      return h;
    }
    ```

21.  Collisions decrease when you use MAD because of the increased complexity. 