package com.thoughtpropulsion.sobuff;

import java.nio.ByteBuffer;

/**
 * This class is not public since it should only be constructed by WritableImpl.
 */
class ReadableImpl implements Readable {

  private final ByteBuffer buffer;
  private volatile boolean currentlyReadable = true;
  private final WritableImpl writable;

  ReadableImpl(final ByteBuffer buffer, final WritableImpl writable) {
    this.buffer = buffer;
    this.writable = writable;
  }

  @Override
  public Writable flip() {
    checkUsable("flip");
    buffer.flip();
    currentlyReadable = false;
    writable.setCurrentlyWritable(true);
    return writable;
  }

  @Override
  public byte get() {
    checkUsable("read from");
    return buffer.get();
  }

  public void setCurrentlyReadable(final boolean currentlyReadable) {
    this.currentlyReadable = currentlyReadable;
  }

  private void checkUsable(final String action) {
    if (!currentlyReadable) {
      throw new IllegalStateException("Attempt to " + action + " flipped Readable");
    }
  }
}
