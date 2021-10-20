package com.thoughtpropulsion.sobuff;

/**
 * This class narrows the java.nio.ByteBuffer interface to only the methods available
 * when in "reading" mode. Flipping an instance renders the receiver inoperable and
 * returns a Writable.
 *
 * This is an experiment to see if the type checker can make our buffer-manipulating
 * code easier to understand (without imposing an undue performance penalty).
 */
public interface Readable {

  /**
   * Returns the Writable and leaves the receiver inoperable.
   * If you need to read more, flip the Writable.
   */
  Writable flip();

  byte get();
//  byte get(int index);
//  Readable get(byte[] dst, int offset, int length);
//  Readable get(byte[] dst);


//  Readable position(int newPosition);
//  Readable limit(int newLimit);
//  Readable mark();
//  Readable reset();
//  Readable clear();
//  Readable rewind();

//  int capacity();
//  int position();
//  int limit();
//  int remaining();
//  boolean hasRemaining();
//  boolean isReadOnly();
//  boolean hasArray();
//  Object array();
//  int arrayOffset();
//  boolean isDirect();
//  int nextGetIndex();
//  int nextGetIndex(int nb);
//  int nextPutIndex();
//  int nextPutIndex(int nb);
//  int checkIndex(int i);
//  int checkIndex(int i, int nb);
//  int markValue();
//  void truncate();
//  void discardMark();

}
