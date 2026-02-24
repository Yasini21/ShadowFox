package main;

import dao.BookDAO;
import model.Book;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        BookDAO bookDAO = new BookDAO();

        while (true) {

            System.out.println("\n--- Library Book Tracking System ---");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Exit");
            System.out.println("6. Calculate Fine");
            System.out.println("7. Export Issued Books to CSV");


            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {

                case 1:
                    System.out.print("Enter book title: ");
                    String title = sc.nextLine();

                    System.out.print("Enter author name: ");
                    String author = sc.nextLine();

                    bookDAO.addBook(title, author);
                    break;

                case 2:
                    List<Book> books = bookDAO.getAllBooks();

                    if (books.isEmpty()) {
                        System.out.println("No books available.");
                    } else {
                        for (Book b : books) {
                            System.out.println(
                                b.getBookId() + " | " +
                                b.getTitle() + " | " +
                                b.getAuthor() + " | Issued: " +
                                b.isIssued()
                            );
                        }
                    }
                    break;

                case 3:
               System.out.print("Enter Book ID to issue: ");
               int bookId = sc.nextInt();
               sc.nextLine();

            System.out.print("Enter User ID: ");
            int userId = sc.nextInt();

            bookDAO.issueBookToUser(bookId, userId);
            break;


                case 4:
    System.out.print("Enter Book ID to return: ");
    int returnBookId = sc.nextInt();

    bookDAO.returnBookFromUser(returnBookId);
    break;


                case 5:
                    System.out.println("Exiting system safely...");
                    sc.close();
                    System.exit(0);
                    break;

                    case 6:
    System.out.print("Enter Book ID: ");
    int fineBookId = sc.nextInt();
    bookDAO.calculateFine(fineBookId);
    break;
                  case 7:
    bookDAO.exportIssuedBooksToCSV();
    break;


                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
