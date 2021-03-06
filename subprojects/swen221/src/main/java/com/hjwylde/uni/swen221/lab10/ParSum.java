package com.hjwylde.uni.swen221.lab10;

import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.hjwylde.uni.swen221.lab10.swen221.concurrent.Job;
import com.hjwylde.uni.swen221.lab10.swen221.concurrent.ThreadPool;

/*
 * Code for Laboratory 10, SWEN 221 Name: Henry J. Wylde Usercode: wyldehenr ID: 300224283
 */

/**
 * A simple illustration of the swen221.concurrent package.
 * 
 * @author David J. Pearce
 */
public class ParSum {

    // =======================================================================
    // SWEN221: Look at this
    // =======================================================================

    public static void main(String[] args) {
        boolean parallelMode = false;
        String filename = null;

        // First, some rudimentary command-line argument processing.
        if (args.length == 0) {
            System.out.println("Usage: java Main [-gui] input.dat");
            System.exit(1);
        }
        int index = 0;

        while (args[index].startsWith("--")) {
            String arg = args[index++];
            if (arg.equals("--parallel"))
                parallelMode = true;
            else {
                System.out.println("Unrecognised argument encountered: " + arg);
                System.exit(1);
            }
        }
        filename = args[index];

        try {
            int numProcessors = Runtime.getRuntime().availableProcessors();
            System.out.println("Executing on machine with " + numProcessors + " processor(s).");

            List<Integer> data;
            try (FileReader fr = new FileReader(new File(filename))) {
                data = ParSum.readInput(fr);
            }

            System.out.println(new File(filename).exists());
            System.out.println("Read " + data.size() + " data items.");

            BigInteger sum;
            long start = System.currentTimeMillis();

            if (parallelMode) {
                // do a parallel quick sort
                System.out.println("Performing a PARALLEL sum...");
                sum = ParSum.parallelSum(data, numProcessors);
            } else {
                // perform a sequential quick sort
                System.out.println("Performing a SEQUENTIAL sum...");
                sum = ParSum.sequentialSum(data, 0, data.size());
            }

            long time = System.currentTimeMillis() - start;

            System.out.println("Summed data in " + time + "ms");
            System.out.println("Sum was " + sum);

            if (sum.equals(ParSum.sequentialSum(data, 0, data.size())))
                System.out.println("Data was summed correctly!");
            else
                System.out.println("!!! ERROR: data not summed correctly");
        } catch (IOException e) {
            System.out.println("I/O error: " + e.getMessage());
        }
    }

    public static BigInteger parallelSum(List<Integer> data, int numProcessors) {
        // Create thread pool and Job List
        ThreadPool pool = new ThreadPool(numProcessors);
        SumJob[] jobs = new SumJob[numProcessors];

        // Each job will process jobSize elements
        int jobSize = data.size() / numProcessors;

        // Create the jobs and start them
        int index = 0;
        for (int i = 0; i != numProcessors; ++i) {
            if ((i + 1) != numProcessors)
                jobs[i] = new SumJob(data, index, index + jobSize);
            else
                // for the very last job, we allocate all remaining elements
                jobs[i] = new SumJob(data, index, data.size());
            pool.submit(jobs[i]);
            index = index + jobSize;
        }

        // Finally, gather up results and return
        BigInteger result = BigInteger.ZERO;
        for (int i = 0; i != numProcessors; ++i) {
            SumJob job = jobs[i];
            job.waitUntilFinished();
            result = result.add(job.result);
        }
        return result;
    }

    public static ArrayList<Integer> readInput(Reader input) throws IOException {
        BufferedReader reader = new BufferedReader(input);

        String line;
        ArrayList<Integer> data = new ArrayList<>();
        while ((line = reader.readLine()) != null) {
            if (line.equals(""))
                continue;
            data.add(Integer.parseInt(line));
        }
        return data;
    }

    // =======================================================================
    // SWEN221: No need to look at code below this!
    // =======================================================================

    public static BigInteger sequentialSum(List<Integer> data, int start, int end) {
        BigInteger r = BigInteger.ZERO;
        for (int i = start; i != end; ++i)
            r = r.add(BigInteger.valueOf(data.get(i)));
        return r;
    }

    public static class SumJob extends Job {

        private final List<Integer> data;
        private final int start;
        private final int end;
        private volatile BigInteger result;

        public SumJob(List<Integer> data, int start, int end) {
            this.data = data;
            this.start = start;
            this.end = end;
        }

        @Override
        public void run() {
            result = ParSum.sequentialSum(data, start, end);
        }
    }
}
