package test.java.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class FileLock {
    public static void main(String[] args) throws IOException, InterruptedException {
//        File file = new File("C:\\Users\\bin.deng\\Desktop\\重复点击 .txt");

        FileChannel channel = null;
        java.nio.channels.FileLock lock = null;
        RandomAccessFile raf = new RandomAccessFile("C:\\Users\\bin.deng\\Desktop\\重复点击 .txt","rw");

        //在文件末尾追加内容的处理
        raf.seek(raf.length());
        channel = raf.getChannel();

        //获得锁方法一：lock()，阻塞的方法，当文件锁不可用时，当前进程会被挂起
        lock = channel.lock();//无参lock()为独占锁
        System.out.println(lock);
        Thread.sleep(10000);

    }
}
