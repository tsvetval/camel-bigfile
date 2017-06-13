package com.github.sonenko.hellojavacamelwebsockets;

/**
 * Created by tsval on 04.05.2017.
 */
public class MyReadWriteLock {
    int currentReaders = 0;
    int currentWriters = 0;
    int writeRequest = 0;

    public synchronized void lockRead() throws InterruptedException {
        while (currentWriters > 0 || writeRequest > 0 ){
            wait();
        }
        currentReaders++;
    }

    public synchronized void unlockRead(){
        currentReaders--;
        notifyAll();
    }


    public synchronized void lockWrite() throws InterruptedException {
        writeRequest++;
        while (currentWriters > 0 || currentReaders > 0){
            wait();
        }
        currentWriters++;
        writeRequest--;

    }

    public synchronized void unlockWrite(){
        currentWriters--;
        notifyAll();
    }
}
