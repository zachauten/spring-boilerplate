package app.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class EitherTest {

  @Test
  public void left() {
    var maybe = stringOrInt(true);
    var res = maybe.match(
      str -> str,
      i -> i.toString()
    );
    assertEquals("42", res);
  }

  @Test
  public void right() {
    var maybe = stringOrInt(false);
    var res = maybe.match(
      Integer::parseInt,
      i -> i
    );
    assertEquals(Integer.valueOf(99), res);
  }

  private Either<String, Integer> stringOrInt(boolean left) {
    return left ? Either.left("42") : Either.right(99);
  }
}
