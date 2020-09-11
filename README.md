# AVL-Tree-English-Dictionary-Application
Java AVL Tree data structure implementation supporting the following operations:
1. Search: Search for a specific element in an AVL Tree.
2. Insertion: Insert a new node in an AVL tree. Tree balance maintained via the rotation operations.
3. Deletions: Delete a node from an AVL tree. Tree balance maintained via the rotation operations.
4. Print Tree Height: Print the height of the AVL tree. This is the longest path from the root to a leaf-node.


# Application: English Dictionary
an application based on your AVL Tree implementation, that implement a simple English dictionary, with a simple text-based user interface,
supporting the following functionalities:
1. Load Dictionary:
 	 * There is a text file, “dictionary.txt", containing a list of words. Each word will be in a separate line.
	 * load the dictionary into an AVL Tree data structure to support efficient insertions, deletions and search operations.

2. Print Dictionary Size: Prints the current size of your dictionary.

3. Insert Word: Takes a word from the user and inserts it, only if it is not already in the dictionary. Otherwise, print the appropriate error message.

4. Look-up a Word: Takes a word from the user and prints “YES" or “NO" according to whether it is found or not.

5. Remove Word: Takes a word from the user and removes it from the dictionary. If the word is not in the dictionary, then print the appropriate error message.

6. Batch Look-ups:
  	* There is a text file, “queries.txt", containing a list of words to lookup in your dictionary.
  	* print the total number of found words. Also, for each word, print the word and “YES" if it exists in your dictionary. Otherwise, print “NO".

7. Batch Deletions: There is a text file “deletions.txt" containing a list of words to remove from your dictionary.
