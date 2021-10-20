Java's `ByteBuffer` is a useful class but keeping track of the mode after `flip()` is called can be difficult.

This repo is a little experiment to see if it's feasible to use a separate interface for each mode. By using interfaces, the compiler can keep track of the mode so mistakes are caught earlier.

Currently, the experiment instantiates implementation classes as the mode flips. This proves out the user interface but does unnecessary allocation.

## TODO
1. experiment with a single implementation class that wraps the `ByteBuffer` and implements both `Readable` and `Writable`
2. further, see what it looks like to implement that class using Java's Dynamic Proxy framework