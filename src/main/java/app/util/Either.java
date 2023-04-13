package app.util;

import java.util.function.Function;

interface Either<A, B> {
  <R> R match(Function<A, R> left, Function<B, R> right);

  static <A, B> Either<A, B> left(A val) {
    return new Left<A, B>(val);
  }

  static <A, B> Either<A, B> right(B val) {
    return new Right<A, B>(val);
  }

  record Left<A, B>(A value) implements Either<A, B> {
    public <R> R match(Function<A, R> left, Function<B, R> right) {
      return left.apply(value);
    }
  }

  record Right<A, B>(B value) implements Either<A, B> {
    public <R> R match(Function<A, R> left, Function<B, R> right) {
        return right.apply(value);
    }
  }
}