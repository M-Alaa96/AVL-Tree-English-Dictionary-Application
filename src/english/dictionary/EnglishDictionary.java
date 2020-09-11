/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package english.dictionary;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author RS
 */
public class EnglishDictionary {

    /**
     * @param args the command line arguments
     */
    /* Creating object of AVLTree */
    static AVLTree dictionary = new AVLTree();
    static ArrayList<String> list = new ArrayList<>();

    public static void main(String[] args) {
        // TODO code application logic here
        Scanner scan = new Scanner(System.in);

        System.out.println("AVLTree Tree Test\n");
        int choice;
        /*  Perform Dictionary operations  */
        do {
            System.out.println("\n Dictionary Operations\n");
            System.out.println("1. Load Dictionary ");
            System.out.println("2. insert");
            System.out.println("3. search");
            System.out.println("4. delete");
            System.out.println("5. print tree height");
            System.out.println("6. Dictionary Size");
            System.out.println("7. Batch Look-ups");
            System.out.println("8. Batch Deletions");
            System.out.println("9. exit");
            choice = scan.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Load Dictionary");
                    try {
                        loadDictionary();
                        System.out.println("Tree Height = " + dictionary.height(dictionary.getRoot()));
                        System.out.println("Dictionary Size = " + dictionary.countNodes());
                        dictionary.PrintTree();
                    } catch (Exception e) {
                        System.err.println("Error " + e.getMessage());
                    }
                    break;
                case 2:
                    System.out.println("Enter element to insert");
                    String word = scan.next();
                    if (!dictionary.found(word)) {
                        dictionary.insert(word);
                        System.out.println("Tree Height = " + dictionary.height(dictionary.getRoot()));
                        System.out.println("Dictionary Size = " + dictionary.countNodes());
                        dictionary.PrintTree();
                    } else {
                        System.out.println("ERROR: Word already in the dictionary! ");
                    }
                    break;
                case 3:
                    System.out.println("Enter  element to search");
                    String key = scan.next();
                    if (dictionary.found(key)) {
                        System.out.println(" Found");
                    } else {
                        System.out.println("Not Found");
                    }
                    break;
                case 4:
                    System.out.println("Enter element to delete");
                    word = scan.next();
                    if (dictionary.found(word)) {
                        dictionary.delete(word);
                        System.out.println("Tree Height = " + dictionary.height(dictionary.getRoot()));
                        System.out.println("Dictionary Size = " + dictionary.countNodes());
                        dictionary.PrintTree();
                    } else {
                        System.out.println("ERROR: Not Found! ");
                    }
                    break;
                case 5:
                    System.out.println("Tree Height = " + dictionary.height(dictionary.getRoot()));
                    break;
                case 6:
                    System.out.println("Dictionary Size = " + dictionary.countNodes());
                    break;
                case 7:
                    try {
                        BatchLookUps();

                    } catch (Exception e) {
                        System.err.println("Error " + e.getMessage());
                    }
                    break;
                case 8:
                    try {
                        BatchDeletions();
                        System.out.println("Tree Height = " + dictionary.height(dictionary.getRoot()));
                        System.out.println("Dictionary Size = " + dictionary.countNodes());
                    } catch (Exception e) {
                        System.err.println("Error " + e.getMessage());
                    }
                    break;
                case 9:
                    break;
                default:
                    System.out.println("Wrong Entry \n ");
                    break;
            }

        } while (choice != 9);
    }

    public static void loadDictionary() throws FileNotFoundException {
        try {
            FileInputStream stream = new FileInputStream("dictionary.txt");
            DataInputStream in = new DataInputStream(stream);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String word;
            while ((word = reader.readLine()) != null) {
                dictionary.insert(word);
            }
        } catch (Exception e) {
            System.err.println("Error " + e.getMessage());
        }
    }

    public static void BatchLookUps() throws FileNotFoundException {
        try {
            FileInputStream stream = new FileInputStream("queries.txt");
            DataInputStream in = new DataInputStream(stream);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String word;
            int found = 0, notFound = 0;
            while ((word = reader.readLine()) != null) {
                if (dictionary.found(word)) {
                    System.out.println(" Found");
                    found++;
                } else {
                    System.out.println("Not Found");
                    notFound++;
                }
            }
            System.out.println("total number of deleted words = " + found);
            System.out.println("total number of NOT found words = " + notFound);
        } catch (Exception e) {
            System.err.println("Error " + e.getMessage());
        }
    }

    public static void BatchDeletions() throws FileNotFoundException {
        try {
            FileInputStream stream = new FileInputStream("deletions.txt");
            DataInputStream in = new DataInputStream(stream);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String word;
            int found = 0, notFound = 0;
            while ((word = reader.readLine()) != null) {
                if (dictionary.found(word)) {
                    System.out.println(" Found");
                    dictionary.delete(word);
                    found++;
                } else {
                    System.out.println(word + "   Not Found");
                    notFound++;
                }
                System.out.println("total number of deleted words = " + found);
                System.out.println("total number of NOT found words = " + notFound);
            }
        } catch (Exception e) {
            System.err.println("Error " + e.getMessage());
        }
    }
}
