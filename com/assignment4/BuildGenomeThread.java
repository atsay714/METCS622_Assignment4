package com.assignment4;

import java.util.Random;
import java.util.concurrent.Callable;

/**
 * The BuildGenomeThread class represents a thread for generating a genome sequence.
 * It implements the Callable interface and provides functionality for generating a
 * genome sequence using a specified length and nucleotide composition. The generated
 * genome sequence is returned as a string value.
 */
public class BuildGenomeThread implements Callable<String> {

    /**
     * This method overrides the call method of the Callable interface.
     * It calls the generateGenomeSequence method to generate a genome sequence
     * and returns the generated sequence as a string value.
     * 
     * @return a string value representing the generated genome sequence
     */
    @Override
    public String call() {
        return this.generateGenomeSequence();
    }

    /**
     * This method generates a genome sequence using a specified length and
     * nucleotide composition. It creates a StringBuilder object and appends
     * nucleotides to it randomly selected from the given nucleotide array.
     * The generated genome sequence is returned as a string value.
     * 
     * @return a string value representing the generated genome sequence
     */
    private String generateGenomeSequence(){
        int length = 10; // change this to change the length of the genome sequence
        String[] nucleotides = {"A", "C", "G", "T"};
        Random random = new Random();

        StringBuilder genomeSequence = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(nucleotides.length);
            genomeSequence.append(nucleotides[index]);
        }
        String retString = genomeSequence.toString();

        // try{
        //     Thread.sleep(500);
        // } catch (InterruptedException e) {
        // }
        
        return retString;
    }
}