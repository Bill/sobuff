import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.nio.ByteBuffer;

import com.thoughtpropulsion.sobuff.Readable;
import com.thoughtpropulsion.sobuff.Writable;
import org.junit.jupiter.api.Test;

public class SoBuffTest {

  /*
   Let's first be clear on what the JDK ByteBuffer does.
   */
  @Test
  void baseline() {
    final ByteBuffer buf = ByteBuffer.allocate(10);

    buf.put((byte)5);

    buf.flip();

    final byte got = buf.get();

    assertThat(got).isEqualTo((byte)5);
  }

  @Test
  void writableToReadable() {
    final ByteBuffer buf = ByteBuffer.allocate(10);

    // There's only one way to create a Writable from a ByteBuffer
    final Writable writable = Writable.create(buf);

    writable.put((byte)5);

    // Flipping gives you a Readable
    final Readable readable = writable.flip();

    final byte got = readable.get();

    assertThat(got).isEqualTo((byte)5);
  }

  /*
   Since I don't see any good way to "dispose" of a Writable after it's flipped, I opted
   to keep a state variable in it and to have it invalidate itself after it's flipped.
   */
  @Test
  void writeAfterFlipThrows() {
    final ByteBuffer buf = ByteBuffer.allocate(10);

    final Writable writable = Writable.create(buf);

    writable.put((byte)5);

    writable.flip();

    assertThatThrownBy( () -> writable.put((byte)6) ).isInstanceOfAny(IllegalStateException.class);
  }

  /*
   Ditto Readable
   */
  @Test
  void readAfterFlipThrows() {
    final ByteBuffer buf = ByteBuffer.allocate(10);

    final Writable writable = Writable.create(buf);

    writable.put((byte)5);

    final Readable readable = writable.flip();

    readable.flip();

    assertThatThrownBy( () -> readable.get() ).isInstanceOfAny(IllegalStateException.class);
  }

}
