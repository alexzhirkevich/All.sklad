package lab7;

import java.io.Serializable;
import java.util.Scanner;


public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    String strISBN;
    String author;
    String name;
    String year;
    String publisher;
    String annotation;
    String price;
	
    public static Book read( Scanner fin ) {
        Book book = new Book();a
        book.strISBN = fin.nextLine();
        if ( ! fin.hasNextLine()) return null;
        System.out.println("author:");
        book.author = fin.nextLine();
        if ( ! fin.hasNextLine()) return null;
        System.out.println("name::");
        book.name = fin.nextLine();
        if ( ! fin.hasNextLine()) return null;
        System.out.println("year:");
        book.year = fin.nextLine();
        if ( ! fin.hasNextLine()) return null;
        System.out.println("publisher:");
        book.publisher = fin.nextLine();
        if ( ! fin.hasNextLine()) return null;
        System.out.println("annotation:");
        book.annotation = fin.nextLine();
        if ( ! fin.hasNextLine()) return null;
        System.out.println("price:");
        book.price = fin.nextLine(); 
        return book;
    }
	
    public Book() {
    }

    public String toString() {
        return new String (
            strISBN + "|" +
            author + "|" +
            name + "|" +
            year + "|" +
            publisher + "|" +
            annotation + "|" +
            price			
        );
    }
}
