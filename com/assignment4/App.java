package com.assignment4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * The App class represents a main class for generating genome sequences.
 * It provides functionality for generating genome sequences using either
 * concurrent or sequential processing. The user is prompted to input the
 * number of genome sequences to be generated and the type of processing to use.
 * The program then generates the genome sequences using the chosen processing
 * method and displays the generated sequences along with the time taken to
 * generate them.
 */
public class App {
    /**
     * This constructor is private to prevent instantiation of the App class
     * as it only provides static methods and variables.
     */
    private App() {
    }
    /**
     * A synchronized list to hold the genome sequences generated using concurrent processing.
     */
    public static List<String> concurrentList = Collections.synchronizedList(new ArrayList<String>());

    /**
     * A list to hold the genome sequences generated using sequential processing.
     */
    public static List<String> sequentialList = new ArrayList<String>();

    /**
     * The main method prompts the user to input the number of genome sequences
     * to be generated and the type of processing to use. It then generates the
     * genome sequences using the chosen processing method, displays the generated
     * sequences and the time taken to generate them.
     * 
     * @param args an array of command-line arguments for the program
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("How many genome sequences would you like to generate?");
            int numSequences = sc.nextInt();
            
            System.out.println("1. Concurrent Processing 2. Sequential Processing");
            int processing = sc.nextInt();

            long startTime = System.nanoTime();

            switch(processing)
            {
                case 1:
                    concurrentProcessing(numSequences);
                    break;
                case 2:
                    sequentialProcessing(numSequences);
                    break;
                default:
                    System.out.println("Invalid Input!");

            }

            long endTime   = System.nanoTime();
            long totalTime = endTime - startTime;
            
            for(String seq : concurrentList) {
                System.out.println(seq);
            }

            for(String seq : sequentialList) {
                System.out.println(seq);
            }

            System.out.println("Total time taken to generate " + numSequences + " sequences: " + totalTime/1000000 + " ms");
        }
        catch(Exception e){

        }
        finally{
            sc.close();
        }
    }

    /**
     * This method generates the genome sequences using concurrent processing.
     * It creates a specified number of threads and starts each thread to generate
     * a genome sequence using the BuildGenomeThread class. The generated sequences
     * are added to the concurrentList.
     * 
     * @param numSequences the number of genome sequences to be generated
     */
    private static void concurrentProcessing(int numSequences) {
        Thread[] threads = new Thread[numSequences];
        for(int i = 0; i < numSequences; i++)
        {
            threads[i] = new Thread(() -> {
                BuildGenomeThread buildGenomeThread = new BuildGenomeThread();
                concurrentList.add(buildGenomeThread.call());
            });
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * This method generates the genome sequences using sequential processing.
     * It generates a genome sequence using the BuildGenomeThread class and adds
     * the sequence to the sequentialList. It repeats this process for the specified
     * number of genome sequences
     * 
     * @param numSequences the number of genome sequences to be generated
    */
    private static void sequentialProcessing(int numSequences) {
        for(int i = 0; i < numSequences; i++){
            BuildGenomeThread buildGenomeThread = new BuildGenomeThread();
            sequentialList.add(buildGenomeThread.call());
        }
    }
}
