package com.github.sonenko.hellojavacamelwebsockets;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

/**
 *
 */
public class InputStreamWriterThread implements Runnable {
    private OutputStream out;
    private Long length;

    public OutputStream getOut() {
        return out;
    }

    public void setOut(OutputStream out) {
        this.out = out;
    }

    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }

    public void run() {
        int dev = 10000;
        if (length % dev > 0){
            throw new RuntimeException("Length can't be = " + length );
        }
        byte[] array = new byte[dev];
        Arrays.fill(array, (byte)0x1);

        for (long i = 0; i < length/dev; i++){
            try {
                out.write(array);
                System.out.println("write byte " + i * dev);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("SOME ERROR in InputStreamWriterThread");
            }
        }
        System.out.println("FINISH InputStreamWriterThread");

    }
}
