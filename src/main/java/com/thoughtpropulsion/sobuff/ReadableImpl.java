package com.thoughtpropulsion.sobuff;

import java.nio.ByteBuffer;

/**
 * This class is not public since it should only be constructed by WritableImpl.
 */
class ReadableImpl implements Readable {

  private final ByteBuffer buffer;
  private boolean flipped = false;

  ReadableImpl(final ByteBuffer buffer) {
    this.buffer = buffer;
  }

  @Override
  public Writable flip() {
    checkUsable("flip");
    buffer.flip();
    flipped = true;
    return new WritableImpl(buffer);
  }

  @Override
  public byte get() {
    checkUsable("read from");
    return buffer.get();
  }

  private void checkUsable(final String action) {
    if (flipped) {
      throw new IllegalStateException("Attempt to " + action + " flipped Readable");
    }
  }
}
