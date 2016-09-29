package test.java.concurrent.thread;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.commons.io.IOUtils;
import org.springframework.scheduling.annotation.Async;

public class ThreadPol {
	private final ExecutorService pool = Executors.newFixedThreadPool(10);

	public Future<String> startDownloading(final URL url) throws IOException {
		return pool.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				InputStream input = url.openStream();
				try  {
					return IOUtils.toString(input, StandardCharsets.UTF_8);
				} finally {
					input.close();
				}
			}
		});
	}
	
	//   spring  中注解方式异步调用
//	@Async
//	public Future<String> startDownloading(final URL url) throws IOException {
//	    try (InputStream input = url.openStream()) {
//	        return new AsyncResult<>(
//	                IOUtils.toString(input, StandardCharsets.UTF_8)
//	        );
//	    }
//	}
}
