package it.unibo.oop.workers02;

import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

/**
 * This is a standard implementation of the calculation.
 * 
 */

public class MultiThreadedSumMatrix implements SumMatrix {

    private final int nthread;

    /**
     * Construct a multithreaded matrix sum.
     * 
     * @param nthread
     *                no. threads to be adopted to perform the operation
     */
    public MultiThreadedSumMatrix(final int nthread) {
        super();
        if (nthread < 1) {
            throw new IllegalArgumentException();
        }
        this.nthread = nthread;
    }

    private static final class Worker extends Thread {

        private final double[][] matrix;
        private final int start;
        private final int nelem;
        private double res;

        /**
         * Builds a new worker.
         * 
         * @param matrix
         * @param startpos
         * @param nelem
         */
        private Worker(final double[][] matrix, final int startpos, final int nelem) {
            super();
            this.matrix = matrix;
            this.start = startpos;
            this.nelem = nelem;
        }

        @Override
        public void run() {
            // this.res = IntStream
            //     .iterate(start, counter -> counter + 1)
            //     .limit(Math.min(this.matrix.length,this.start + this.nelem))
            //     .mapToDouble(i -> DoubleStream.of(this.matrix[i]).sum()).sum();
            for (int i = start; i < Math.min(this.matrix.length, this.start + this.nelem); i++) {
                this.res  += DoubleStream.of(matrix[i]).sum();
            }
        }

        /**
         * 
         * @return Returns the result that the thread got.
         */
        public double getResult() {
            return this.res;
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double sum(final double[][] matrix) {
        final int size = matrix.length / nthread + matrix.length % nthread;
        return IntStream
            .iterate(0, start -> start + size)
            .limit(this.nthread)
            .mapToObj(start -> new Worker(matrix, start, size))
            .peek(Thread::start)
            .peek(MultiThreadedSumMatrix::joinUninterruptibly)
            .mapToDouble(Worker::getResult)
            .sum();
    }

    @SuppressWarnings("PMD.AvoidPrintStackTrace")
    private static void joinUninterruptibly(final Thread target) {
        var joined = false;
        while (!joined) {
            try {
                target.join();
                joined = true;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
