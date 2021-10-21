A Java `ByteBuffer` starts out in a writable mode. In that mode, certain methods, such as `put(byte b)` make sense, but certain others, like `byte get()` don't. After `flip()` is called, the `ByteBuffer` switches to a readable mode where the same situation holds but in reverse—now `byte get()` makes sense but `put(byte b)` doesn't.

So the `ByteBuffer` class has methods that are mode-specific. This is a familiar design flaw with a familiar solution: divide the class into two interfaces: one per mode.

This repo is a little experiment to see if it's feasible to offer a separate interface for each mode. By using separate interfaces, the compiler can keep track of the mode so mistakes are caught earlier.

## Example

```java
    final ByteBuffer buf = ByteBuffer.allocate(10);

    /*
     There's only one way to create a Writable from a ByteBuffer
     and you can't create a Readable (directly) at all (see flip())
     */
    final Writable writable = Writable.create(buf);

    writable.put((byte)5);
    
    // there is no writable.get() method!

    // Flipping gives you a Readable
    final Readable readable = writable.flip();
    
    // there is no readable.put(byte) method!
    
    // if you call methods on the writable here you'll get an exception!

    final byte got = readable.get();
```

## TODO

* For the two interfaces, `Writable`, `Readable`, decide exactly which `ByteBuffer` methods should be available on each. Currently we've just got `put(byte)`, `byte get()` and `flip()` as an experiment.
* Profit!