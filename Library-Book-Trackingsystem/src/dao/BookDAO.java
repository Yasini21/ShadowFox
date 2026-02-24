package dao;

import db.DBConnection;
import model.Book;

import java.io.FileWriter;
import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    // ---------------- BASIC BOOK METHODS ----------------

    public void addBook(String title, String author) {
        String sql = "INSERT INTO books (title, author, is_issued) VALUES (?, ?, false)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, title);
            ps.setString(2, author);
            ps.executeUpdate();
            System.out.println("Book added successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                books.add(new Book(
                        rs.getInt("book_id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getBoolean("is_issued")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    // ---------------- ISSUE BOOK (CORRECT FLOW) ----------------

    public void issueBookToUser(int bookId, int userId) {

        // VALIDATION FIRST
        try {
            if (!bookExists(bookId)) {
                System.out.println("Invalid Book ID.");
                return;
            }
            if (!userExists(userId)) {
                System.out.println("Invalid User ID.");
                return;
            }
            if (isBookIssued(bookId)) {
                System.out.println("Book is already issued.");
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        // ACTION PHASE
        String insertIssue =
                "INSERT INTO issued_books (book_id, user_id, issue_date) VALUES (?, ?, ?)";
        String updateBook =
                "UPDATE books SET is_issued = true WHERE book_id = ?";

        try (Connection con = DBConnection.getConnection()) {
            con.setAutoCommit(false);

            try (PreparedStatement ps1 = con.prepareStatement(insertIssue);
                 PreparedStatement ps2 = con.prepareStatement(updateBook)) {

                ps1.setInt(1, bookId);
                ps1.setInt(2, userId);
                ps1.setDate(3, Date.valueOf(LocalDate.now()));
                ps1.executeUpdate();

                ps2.setInt(1, bookId);
                ps2.executeUpdate();

                con.commit();
                System.out.println("Book issued successfully.");

            } catch (Exception e) {
                con.rollback();
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ---------------- RETURN BOOK ----------------

    public void returnBookFromUser(int bookId) {

        // VALIDATION FIRST
        try {
            if (!bookExists(bookId)) {
                System.out.println("Invalid Book ID.");
                return;
            }
            if (!isBookIssued(bookId)) {
                System.out.println("Book is not currently issued.");
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        String updateIssue =
                "UPDATE issued_books SET return_date = ? WHERE book_id = ? AND return_date IS NULL";
        String updateBook =
                "UPDATE books SET is_issued = false WHERE book_id = ?";

        try (Connection con = DBConnection.getConnection()) {
            con.setAutoCommit(false);

            try (PreparedStatement ps1 = con.prepareStatement(updateIssue);
                 PreparedStatement ps2 = con.prepareStatement(updateBook)) {

                ps1.setDate(1, Date.valueOf(LocalDate.now()));
                ps1.setInt(2, bookId);
                ps1.executeUpdate();

                ps2.setInt(1, bookId);
                ps2.executeUpdate();

                con.commit();
                System.out.println("Book returned successfully.");

            } catch (Exception e) {
                con.rollback();
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ---------------- FINE CALCULATION ----------------

    public void calculateFine(int bookId) {
        String sql =
                "SELECT issue_date, return_date FROM issued_books " +
                "WHERE book_id = ? ORDER BY issue_id DESC LIMIT 1";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, bookId);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                System.out.println("No issue record found.");
                return;
            }

            LocalDate issueDate = rs.getDate("issue_date").toLocalDate();
            LocalDate returnDate = rs.getDate("return_date") != null
                    ? rs.getDate("return_date").toLocalDate()
                    : LocalDate.now();

            long daysUsed = ChronoUnit.DAYS.between(issueDate, returnDate);
            long allowedDays = 7;

            if (daysUsed <= allowedDays) {
                System.out.println("No fine. Book returned on time.");
            } else {
                long fine = (daysUsed - allowedDays) * 2;
                System.out.println("Fine amount: Rs " + fine);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ---------------- CSV EXPORT ----------------

    public void exportIssuedBooksToCSV() {
        String sql =
                "SELECT b.title, u.name, i.issue_date, i.return_date " +
                "FROM issued_books i " +
                "JOIN books b ON i.book_id = b.book_id " +
                "JOIN users u ON i.user_id = u.user_id";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql);
             FileWriter fw = new FileWriter("issued_books_report.csv")) {

            fw.write("Book Title,User Name,Issue Date,Return Date\n");

            while (rs.next()) {
                fw.write(
                        rs.getString("title") + "," +
                        rs.getString("name") + "," +
                        rs.getDate("issue_date") + "," +
                        rs.getDate("return_date") + "\n"
                );
            }

            System.out.println("CSV exported successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ---------------- HELPER METHODS ----------------

    private boolean bookExists(int bookId) throws SQLException {
        String sql = "SELECT 1 FROM books WHERE book_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, bookId);
            return ps.executeQuery().next();
        }
    }

    private boolean isBookIssued(int bookId) throws SQLException {
        String sql = "SELECT is_issued FROM books WHERE book_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, bookId);
            ResultSet rs = ps.executeQuery();
            return rs.next() && rs.getBoolean("is_issued");
        }
    }

    private boolean userExists(int userId) throws SQLException {
        String sql = "SELECT 1 FROM users WHERE user_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, userId);
            return ps.executeQuery().next();
        }
    }
}
