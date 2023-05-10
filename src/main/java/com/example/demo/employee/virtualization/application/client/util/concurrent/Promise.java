package com.example.demo.employee.virtualization.application.client.util.concurrent;

import lombok.NonNull;

import java.util.concurrent.Future;
import java.util.function.Consumer;
import java.util.function.Function;

public interface Promise<A> extends Future<A> {

  A claim();

  Promise<A> done(Consumer<? super A> var1);

  Promise<A> fail(Consumer<Throwable> var1);

  public interface TryConsumer<A> extends Consumer<A> {

    void fail(@NonNull Throwable var1);

  }


}
