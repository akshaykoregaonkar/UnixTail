### Problem statement

Write a function that is equivalent to calling the command tail -n N for an arbitrary integer N, 
only using the standard library. 

**Definition**

Tail is a command which prints the last few number of lines (10 lines by default) of a certain file, then terminates. 
We can even open multiple files using tail command without the need to execute multiple tail commands to view multiple files

**Key problems**

- Need a fast way to read large files
- Need a data structure that reads through a file and only stores the last N lines (can just use string array) 
- Should handle multiple files 

##### Test results:

![Alt text](/src/com/akshay/resources/image-20200608102852637.png?raw=true "Test results")
