package com.alquds.datastructure;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    public static void main(String args[]){
        System.out.println("yes");
        BitcoinSystem bitcoinSystem=new BitcoinSystem();

        /// The system starts from here
        //TODO task-1 read the transactions file
        Queue<String> fileLines = Utility.readTransactionsFile();

        //TODO task-2 Loop in the queue to parse line by line
        for(String str:fileLines){
            LinkedList<Block> chain= bitcoinSystem.parseTransactionsLine(str);
            bitcoinSystem.getAllChains().add(chain);
            bitcoinSystem.draw(false);
        }
        Utility.generateChainsResultsFile(bitcoinSystem.getAllChains());
        bitcoinSystem.draw(true);
    }







}
