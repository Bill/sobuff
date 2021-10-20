Java's `ByteBuffer` is a useful class but keeping track of the mode after `flip()` is called can be difficult.

This repo is a little experiment to see if it's feasible to use a separate interface for each mode. By using interfaces, the compiler can keep track of the mode so mistakes are caught earlier.