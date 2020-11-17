package com.company;
import java.io.File;
import java.io.*;
import java.lang.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadFile {

    public static void main(String[] args) throws Exception {

        try{
            System.out.println("Enter file name:");
            Scanner input = new Scanner(System.in);
            String filename = input.next();
            File f = new File(filename);
            if (!f.isFile() || !f.canRead() || !f.exists())
                throw new FileNotFoundException();


            int option;
            do {

                System.out.println("Choose one option:\n");
                System.out.print("1.Switch words\n");
                System.out.print("2.Switch lines\n");
                System.out.print("3.Exit\n");
               option = input.nextInt();
                switch (option){
                    case 1:
                        System.out.println("Enter lines number and words index:");
                        System.out.println("Enter first line and word index");
                        int firstLine = input.nextInt();
                        int firstLineWord = input.nextInt();
                        System.out.println("Enter second line and word index");
                        int secondLine = input.nextInt();
                        int secondLineWord = input.nextInt();
                        switchWords(f,firstLine, firstLineWord,secondLine,secondLineWord);
                        break;
                    case 2:
                        System.out.println("Enter number of lines to switch:");
                        System.out.println("Enter first line");
                        firstLine = input.nextInt();
                        System.out.println("Enter second line");
                        secondLine = input.nextInt();
                        switchLines(f,firstLine,secondLine);
                        break;
                    case 3:
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + option);
                }


            } while (option < 3);


        } catch (FileNotFoundException e) {
            System.err.println("Wrong filename!");
            e.printStackTrace();
        }
        catch (IndexOutOfBoundsException e) {
            System.err.println(e);
            e.printStackTrace();
        }


    }
    public static void switchLines(File f,int firstLine,int secondLine) throws Exception {


        FileInputStream inp = new FileInputStream(f.getAbsolutePath());
        BufferedReader reader = new BufferedReader(new InputStreamReader(inp));

        ArrayList<String[]> newFileContent = new ArrayList<String[]>();
        String p=reader.readLine();
        while (p != null) {
            String [] b;
            b = p.split("\\s+");
            newFileContent.add(b);

            p=reader.readLine();
        }
        if (firstLine > newFileContent.size() || secondLine > newFileContent.size()) {
            throw new IndexOutOfBoundsException("One or both rows are out of bounds.");
        }
        String[] temp1 = newFileContent.get(firstLine - 1);
        String[] temp2 = newFileContent.get(secondLine - 1);


        newFileContent.set(firstLine - 1, temp2);
        newFileContent.set(secondLine - 1, temp1);


        PrintWriter writer = new PrintWriter(f.getAbsolutePath());
        for (String[] line : newFileContent) {
            for (String word : line) {
                writer.print(word + " ");
            }
            writer.println();
        }
        writer.close();
    }


    public static void switchWords(File f,int firstLine, int firstLineWord ,int secondLine, int secondLineWord) throws Exception {


        FileInputStream input = new FileInputStream(f.getAbsolutePath());
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));

        ArrayList<String[]> newFileContent = new ArrayList<String[]>();
        String p=reader.readLine();
        while (p != null) {
            String [] b = p.split("\\s+");
            newFileContent.add(b);

            p=reader.readLine();
        }
        if (firstLine > newFileContent.size() || secondLine > newFileContent.size()) {
            throw new IndexOutOfBoundsException("One or both rows are out of bounds.");
        }
        if (firstLineWord > newFileContent.get(firstLine - 1).length || secondLineWord > newFileContent.get(secondLine - 1).length) {
            throw new IndexOutOfBoundsException("One or both words are out of bounds.");
        }

        String temp = newFileContent.get(firstLine - 1)[firstLineWord -1];
        newFileContent.get(firstLine - 1)[firstLineWord - 1 ] = newFileContent.get(secondLine - 1)[secondLineWord - 1];
        newFileContent.get(secondLine - 1)[secondLineWord - 1] = temp;


        PrintWriter writer = new PrintWriter(f.getAbsolutePath());
        for (String[] line : newFileContent) {
            for (String word : line) {
                writer.print(word + " ");
            }
            writer.println();
        }
        writer.close();
    }
}