package test.java.concurrent.thread;

import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.*;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;


public class ListenableFutureTest {


	public static void main(String[] args) throws InterruptedException, ExecutionException {
//		singleListenFuture();
		listFuture();
	}

	private static void singleListenFuture() throws InterruptedException {
		ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
		ListenableFuture<Integer> listenableFuture = service.submit(getCallback(1000, 1));

		Futures.addCallback(listenableFuture, new FutureCallback<Integer>() {
			@Override
			public void onSuccess(@Nullable Integer result) {
				System.out.println(result);
			}

			@Override
			public void onFailure(Throwable t) {
				t.printStackTrace();
			}
		}, service);
		service.awaitTermination(1000,TimeUnit.MILLISECONDS);
		service.shutdown();
	}

	public static void listFuture() {
		ListeningScheduledExecutorService scheduledExecutorService = MoreExecutors
				.listeningDecorator(Executors.newScheduledThreadPool(20));

		List<ListenableFuture<Integer>> list = new LinkedList<>();
		long l = System.currentTimeMillis();
//		for (int j = 1; j <= 3; j++) {
			for (int i = 1; i <= 20; i++) {
//			list.add(Futures.catching(
//					Futures.withTimeout(scheduledExecutorService.submit(getCallback(i * 1000, i)), 3, TimeUnit.SECONDS,
//							scheduledExecutorService),
//					TimeoutException.class, exception -> 0, scheduledExecutorService));

				list.add(Futures.withTimeout(scheduledExecutorService.submit(getCallback(1000, 1)), 1,
						TimeUnit.SECONDS,
						scheduledExecutorService));

			}
//		}
		ListenableFuture<List<Integer>> result = Futures.successfulAsList(list);
		List<Integer> integers = null;
		try {
			integers = result.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		long l1 = System.currentTimeMillis();
		System.out.println(l1 - l);
		System.out.println(integers);
		scheduledExecutorService.shutdownNow();
	}

	private static Callable<Integer> getCallback(int timeout, int value) {
		return () -> {
			Thread.sleep(timeout);
//			throw new RuntimeException();
			return null;
		};
	}


	/**
	 * Futures.addCallback源码，其实就是包装了一层addListener，可以不加executor参数，使用上文说的DirectExecutor
	 * 需要说明的是不加Executor的情况，只适用于轻型的回调方法，如果回调方法很耗时占资源，会造成线程阻塞
	 * 因为DirectExecutor有可能在主线程中执行回调
	 */
	public static <V> void addCallback(final ListenableFuture<V> future,
									   final FutureCallback<? super V> callback, Executor executor) {
		Preconditions.checkNotNull(callback);
		Runnable callbackListener =
				new Runnable() {
					@Override
					public void run() {
						final V value;
						try {
							value = getDone(future);
						} catch (ExecutionException e) {
							callback.onFailure(e.getCause());
							return;
						} catch (RuntimeException e) {
							callback.onFailure(e);
							return;
						} catch (Error e) {
							callback.onFailure(e);
							return;
						}
						callback.onSuccess(value);
					}
				};
		future.addListener(callbackListener, executor);


	}

	private static <V> V getDone(ListenableFuture<V> future) throws ExecutionException {
		return null;
	}

}
