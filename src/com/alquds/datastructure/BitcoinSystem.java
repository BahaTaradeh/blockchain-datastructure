package com.alquds.datastructure;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.MessageDigest;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class BitcoinSystem {

    ArrayList<LinkedList> allChains;
    JFrame window;
    Container container;

    public BitcoinSystem(){
        allChains= new ArrayList<LinkedList>();
        window = new JFrame();
        container=window.getContentPane();
    }


    public LinkedList<Block> parseTransactionsLine(String line){
        //TODO task-2 initialize the linkedlist of chain and start parsing the data through using tokenizer
        LinkedList<Block> chain = null;


        //TODO here we need to parse the line as it contains list of blocks, each block is separated by |
        //TODO note that when parsing the the block we need to know the previous block has header to insert it in the current block
        //TODO insert the block in the chain after it filled with all needed data, then continue in the next token which contains the next block
        /** parse line per blocks, as each block is surrounded with delim= |  **/

        boolean starterBlock=true;
        Block block=null;

        //TODO implement the tokenizer
        //TODO loop in each token via tokenizer as it contains block
        //TODO each block has data and previous header hash value
        return chain;
    }


    //This function will draw the blocks in a JFrame
    public void draw(boolean isDoneProcessing){
        Container container = window.getContentPane();
        container.removeAll();
        container.setLayout(new BorderLayout());

        JPanel centerPanel = new JPanel();
             for(int i=0;i<allChains.size();i++){
              LinkedList<Block> chainObj = allChains.get(i);

            JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEADING));
            for(Block block: chainObj)
            {
                JPanel panelBlock = new JPanel(new FlowLayout(FlowLayout.CENTER));
                JButton button;
                if(block.getPrev_hash()==null){
                    button=new JButton("first block");
                    button.setBackground(Color.LIGHT_GRAY);
                }else{
                    button=new JButton(block.getPrev_hash().substring(0,10)+", "+getBlockHeaderHash(block).substring(0,10));
                }
                button.setFont(new Font("Arial", Font.PLAIN, 10));
                panelBlock.add(button);
                panel.add(panelBlock);

                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                }catch (Exception e){}
            }
            centerPanel.add(panel);
            }

        container.add(centerPanel, BorderLayout.CENTER);
        JPanel topPanel = new JPanel();
        JLabel label =new JLabel("Blockchain Project - Data Structure");
        label.setFont(new Font("Arial", Font.PLAIN, 20));
        topPanel.add(label);
        container.add(topPanel, BorderLayout.NORTH);
        if(isDoneProcessing){
            JPanel panelEnd = new JPanel(new FlowLayout(FlowLayout.CENTER));
             label =new JLabel("Done processing");
            label.setFont(new Font("Arial", Font.PLAIN, 20));
            panelEnd.add(label);
            container.add(panelEnd, BorderLayout.SOUTH);
        }
        if(!window.isActive()){
            window.setSize(1200, 800);
            window.setLocationRelativeTo(null);
        }
        window.setVisible(true);
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        }catch (Exception e){}

        window.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(window,
                        "Are you sure you want to close this window?", "Close Window?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
                    System.exit(0);
                }
            }
        });
    }

    public String getBlockHeaderHash(Block block){
        //TODO task-3 return the header of the block as string concatenated for all header fields prev_hash, hash_transactions, timeStamp, nonce
        String blockHeader="";
        return sha256(blockHeader);
    }
    public Block parseBlockTransactions(String prev_hash,String blockLine){
        Block block=new Block();
        block.setTimeStamp(new Date());
        block.setNonce(0);
        block.setPrev_hash(prev_hash);

        //TODO task-4 in this task we need to parse the line that we read from the "blockstransactions.txt" file


        //Second step hashing
        ///Now we need to hash the transactions and set the value of them in the block
        //TODO task4-2 we need here to use the sha256() function, and input to it all transactions string to return the transactions hash number
        String hash_transactions= "";
        block.setHash_transactions(hash_transactions);
        return block;
    }



    //This function used to hash a string and return a hash number via using has256 mechanism
    public static String sha256(String base) {
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

    public ArrayList<LinkedList> getAllChains() {
        return allChains;
    }

    public void setAllChains(ArrayList<LinkedList> allChains) {
        this.allChains = allChains;
    }
}
