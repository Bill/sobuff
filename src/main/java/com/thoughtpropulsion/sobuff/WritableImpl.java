package com.thoughtpropulsion.sobuff;

import java.nio.ByteBuffer;

/**
 * This class is not public since it should only be constructed by Writable factory method.
 */
class WritableImpl implements Writable {
  private final ByteBuffer buffer;
  private boolean currentlyWritable = true;
  private ReadableImpl readable;

  /**
   * buffer must be a newly-constructed ByteBuffer that has never had
   * flip() invoked on it
   * @param buffer
   */
  WritableImpl(final ByteBuffer buffer) {
    this.buffer = buffer;
  }

  @Override
  public Readable flip() {
    checkUsable("flip");
    buffer.flip();
    currentlyWritable = false;
    if (readable == null) {
      readable = new ReadableImpl(buffer, this);
    } else {
      readable.setCurrentlyReadable(true);
    }
    return readable;
  }

  @Override
  public Writable put(final byte b) {
    checkUsable("write to");
    buffer.put(b);
    return this;
  }

  public void setCurrentlyWritable(final boolean currentlyWritable) {
    this.currentlyWritable = currentlyWritable;
  }

  private void checkUsable(final String action) {
    if (!currentlyWritable) {
      throw new IllegalStateException("Attempt to " + action + ""
          + " flipped Writable");
    }
  }
}
