package A4_2020390;

import java.lang.String;
import java.util.*;

public class question_1{

    public static void main(String[]args){
        ArrayList<book> library_books = new ArrayList<book>();
        ArrayList<Integer> unique_barcodes = new ArrayList<Integer>();

        System.out.println("Welcome to The Phoenix Library");
        System.out.println("--------------------------------------");
        Scanner books = new Scanner(System.in);

        System.out.print("Enter the number of the racks you have bought for the library: ");
        int rack = books.nextInt();
        Generic <Integer> K = new Generic<Integer>(rack);
        System.out.println("-----------------");

        int N;
        while(1!=13){
            System.out.print("Enter the number of the books you have bought for the library: ");
            int no_of_books = books.nextInt();
            books.nextLine();
            if(no_of_books%K.get_Generic()==0){
                N=no_of_books;
                System.out.println("--------------------------------------");
                break;
            }
            else{
                System.out.println("Value of number of books should be multiple of number of racks. Please re-enter the number of books.");
            }
        }

        System.out.println("Firstly, provide the billing details of all the books bought for The Phoenix Library:-");

        for(int m=0; m<N; m++){
            System.out.print("Enter the title of the book: ");
            String book_title = books.nextLine();
            System.out.println("-----------------");
            System.out.print("Enter the ISBN number of the book: ");
            int book_ISBN = books.nextInt();
            System.out.println("-----------------");

            book new_book = new book();
            new_book.set_book_title(book_title);
            new_book.set_book_ISBN(book_ISBN);

            while(1!=13){
                System.out.print("Enter the barcode number of the book: ");
                int book_barcode = books.nextInt();
                books.nextLine();

                if(unique_barcodes.contains(book_barcode)){
                    System.out.println("Invalid Barcode. Please re-enter the barcode.");
                }
                else{
                    unique_barcodes.add(book_barcode);
                    new_book.set_book_barcode(book_barcode);
                    System.out.println("-----------------");
                    break;
                }
            }
            library_books.add(new_book);
        }
        System.out.println("--------------------------------------");

        System.out.println("Secondly, sorting the books in The Phoenix Library.");
        book lib = new book();
        lib.sorting_the_books(library_books);
        lib.book_slots(N, K.get_Generic(), library_books);

        for(int m=0; m<N; m++){
            System.out.println("-----------------");
            System.out.println((m+1)+") BOOK NAME: "+library_books.get(m).get_book_title());
            System.out.println("        ISBN: "+library_books.get(m).get_book_ISBN());
            System.out.println("     BARCODE: "+library_books.get(m).get_book_barcode());
            System.out.println(library_books.get(m).get_rack_number());
            System.out.println(library_books.get(m).get_slot_number());
        }
        System.out.println("--------------------------------------");

        System.out.print("Enter the title of the book, whose location you want: ");
        String title = books.nextLine();
        System.out.print("Enter the ISBN number of the book: ");
        int ISBN = books.nextInt();
        System.out.print("Enter the barcode number of the book: ");
        int barcode = books.nextInt();
        System.out.println("-----------------");

        for(int m=0; m<N; m++){
            if(library_books.get(m).get_book_title().equals(title) && library_books.get(m).get_book_ISBN()==ISBN && library_books.get(m).get_book_barcode()==barcode){
                System.out.println("RACK NUMBER OF THE CHOSEN BOOK: "+library_books.get(m).get_rack_number());
                System.out.println("SLOT NUMBER OF THE CHOSEN BOOK: "+library_books.get(m).get_slot_number());
            }
        }
        System.out.println("--------------------------------------");
        System.out.println("Thank you for visiting The Phoenix Library. Come back soon.");
        System.out.println("--------------------------------------");
    }
}
class Generic<variable>{
    variable obj;
    Generic(variable obj){
        this.obj = obj;  
    } 
    public variable get_Generic(){
        return this.obj; 
    }
}

class book{
    private String book_title;
    private int book_ISBN;
    private int book_barcode;
    private int rack_number;
    private int slot_number;


    public String get_book_title(){
        return book_title;
    }
    public void set_book_title(String book_title){
        this.book_title = book_title;
    }
    public int get_book_ISBN(){
        return book_ISBN;
    }
    public void set_book_ISBN(int book_ISBN){
        this.book_ISBN = book_ISBN;
    }
    public int get_book_barcode(){
        return book_barcode;
    }
    public void set_book_barcode(int book_barcode){
        this.book_barcode = book_barcode;
    }
    public int get_rack_number(){
        return rack_number;
    }
    public void set_rack_number(int rack_number){
        this.rack_number = rack_number;
    }
    public int get_slot_number(){
        return slot_number;
    }
    public void set_slot_number(int slot_number){
        this.slot_number = slot_number;
    }

    void sorting_the_books(ArrayList<book> library_books){

        book temporary_book = null;
        
        for(int m=0; m<library_books.size(); m++){
            for(int n=1; n<library_books.size()-m; n++){
                int comparison = library_books.get(n-1).get_book_title().compareTo(library_books.get(n).get_book_title());

                if(comparison>0){
                    temporary_book = library_books.get(n-1);
                    library_books.set(n-1, library_books.get(n));
                    library_books.set(n, temporary_book);
                }
                else if(comparison==0){
                    if(library_books.get(n-1).get_book_ISBN()>library_books.get(n).get_book_ISBN()){
                        temporary_book = library_books.get(n-1);
                        library_books.set(n-1, library_books.get(n));
                        library_books.set(n, temporary_book);
                    }
                    else if(library_books.get(n-1).get_book_ISBN()==library_books.get(n).get_book_ISBN()){
                        if(library_books.get(n-1).get_book_barcode()>library_books.get(n).get_book_barcode()){
                            temporary_book = library_books.get(n-1);
                            library_books.set(n-1, library_books.get(n));
                            library_books.set(n, temporary_book);
                        }
                    }
                }
            }
        }
    }
    void book_slots(int N, int K, ArrayList<book> library_books){

        int m=0;
        int slot_number=1;
        int rack_number=1;
        while(1!=13){
            if(slot_number<=N/K && m<N && rack_number<=K){
                library_books.get(m).set_rack_number(rack_number);
                library_books.get(m).set_slot_number(slot_number);
                slot_number++;
                m++;
            }
            else if(slot_number>N/K && rack_number<=K){
                rack_number++;
                slot_number=1;
            }
            else if(rack_number>K){
                break;
            }
        }
    }
}