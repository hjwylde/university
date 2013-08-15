package com.hjwylde.uni.swen221.lab10.swen221.concurrent;

import java.util.concurrent.LinkedBlockingQueue;

/*
 * Code for Laboratory 10, SWEN 221
 * Name: Henry J. Wylde
 * Usercode: wyldehenr
 * ID: 300224283
 */

/**
 * A thread pool contains a number of threads which are used to execute jobs. Clients submit jobs to
 * the pool, and those jobs are then schedule to be executed on the next available worker thread.
 * This is very similar to <code>java.lang.concurrent.ExecutorService</code>.
 * 
 * @author David J. Pearce
 */
public class ThreadPool {
    
    /**
     * The queue of jobs waiting to be scheduled.
     */
    private final LinkedBlockingQueue<Job> queue;
    
    /**
     * The actual pool of worker threads.
     */
    private final Worker[] pool;
    
    /**
     * The shutdown flag is used to signal worker threads that they should terminate as soon as
     * possible.
     */
    private volatile boolean shutdown;
    
    /**
     * Create a thread pool with <code>n</code> worker threads. Ideally, <code>n</code> should match
     * the number of cores on the machine.
     * 
     * @param nworkers the number of worker threads to create.
     */
    public ThreadPool(int nworkers) {
        queue = new LinkedBlockingQueue<>();
        pool = new Worker[nworkers];
        shutdown = false;
        
        for (int i = 0; i != nworkers; ++i)
            pool[i] = new Worker();
    }
    
    public void submit(Job job) {
        queue.add(job);
    }
    
    private class Worker extends Thread {
        
        private Worker() {
            setDaemon(true); // prevents workers from stopping jvm shutdown.
            start();
        }
        
        @Override
        public void run() {
            while (!shutdown)
                try {
                    Job r = queue.take();
                    r.start();
                } catch (InterruptedException e) {}
        }
    }
}