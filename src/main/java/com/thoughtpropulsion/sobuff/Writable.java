package com.thoughtpropulsion.sobuff;

import java.nio.ByteBuffer;

/**
 * This class narrows the java.nio.ByteBuffer interface to only the methods available
 * when in "writing" mode. Flipping an instance renders the receiver inoperable and
 * returns a Readable.
 */
public interface Writable {

  /**
   * The one and only way to create a Writable from a ByteBuffer.
   * Once you have a Writable, the only way to create a Readable
   * is to call flip() on it. Calling flip() on that result gives
   * you another Writable() etc.
   */
  static Writable create(final ByteBuffer buffer) {
    return new WritableImpl(buffer);
  }

  /**
   * Returns the Readable and leaves the receiver inoperable.
   * If you need to write more, flip the Readable.
   */
  Readable flip();

  Writable put(byte b);
//  Writable put(int index, byte b);
//  Writable put(Readable src);
//  Writable put(byte[] src, int offset, int length);
//  Writable put(byte[] src);

//  Writable position(int newPosition);
//  Writable limit(int newLimit);
//  Writable mark();
//  Writable reset();
//  Writable clear();
//  Writable rewind();

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
