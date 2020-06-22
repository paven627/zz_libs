package test.java.io;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * @author dengbin
 */
public class GlobTest {
    public static void main(String args[]) throws IOException {
        String glob = "glob:*.log*.gz";
        String path = "/Users/apus/work/dsp_launch/logs";
        match(glob, path);
    }

    public static void match(String glob, String location) throws IOException {

        final PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher(
                glob);


        Files.walkFileTree(Paths.get(location), new SimpleFileVisitor<Path>() {

            @Override
            public FileVisitResult visitFile(Path path,
                                             BasicFileAttributes attrs) throws IOException {
//                System.out.println(path);

                if (pathMatcher.matches(path)) {
                    System.out.println(path);
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc)
                    throws IOException {
                return FileVisitResult.CONTINUE;
            }
        });
    }

}
