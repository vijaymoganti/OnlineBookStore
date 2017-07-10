package com.vijay.restful.onlinebookstore.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.vijay.restful.onlinebookstore.model.Book;

public class BookStoreDao {

	JdbcConnection jdbcConnection = new JdbcConnection();
	public void saveBook(Book book) throws IOException {
		String query = "insert into book (isbn, author, title, copies) values (?,?,?,?)";
		try (Connection connection = jdbcConnection.getConnection();PreparedStatement ps = connection.prepareStatement(query);){
			ps.setInt(1, book.getIsbn());
			ps.setString(2, book.getAuthor());
			ps.setString(3, book.getTitle());
			ps.setInt(4, book.getCopies());
			int out = ps.executeUpdate();
			if (out != 0) {
				System.out.println("Book saved with id=" + book.getIsbn());
			} else
				System.out.println("Book save failed with id=" + book.getIsbn());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Book getBook(int isbn) throws IOException {
		String query = "select author, title, copies from book where isbn = ?";
		Book book = null;
		ResultSet rs = null;
		try (Connection connection = jdbcConnection.getConnection();PreparedStatement ps = connection.prepareStatement(query);){
			ps.setInt(1, isbn);
			rs =  ps.executeQuery();
			if (rs.next()) {
				book = new Book();
				book.setIsbn(isbn);
				book.setAuthor(rs.getString("author"));
				book.setTitle(rs.getString("title"));
				book.setCopies(rs.getInt("copies"));
				System.out.println("Book Found::" + book);
				rs.close();
			} else {
				System.out.println("No Book found with id=" + isbn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return book;
	}

	public void update(Book book) throws IOException {
		String query = "update Book set author=?, title=?, copies=? where isbn=?";
		try(Connection connection = jdbcConnection.getConnection();PreparedStatement ps = connection.prepareStatement(query);){			
			ps.setString(1, book.getAuthor());
			ps.setString(2, book.getTitle());
			ps.setInt(3, book.getCopies());
			ps.setInt(4, book.getIsbn());
			int out = ps.executeUpdate();
			if (out != 0) {
				System.out.println("Book updated with isbn=" + book.getIsbn());
			} else
				System.out.println("No book found with isbn=" + book.getIsbn());
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}

	public void deleteBook(int isbn) throws IOException {
		String query = "delete from book where isbn=?";
		try (Connection connection = jdbcConnection.getConnection();PreparedStatement ps = connection.prepareStatement(query);){
			ps.setInt(1, isbn);
			int out = ps.executeUpdate();
			if (out != 0) {
				System.out.println("Book deleted with id=" + isbn);
			} else
				System.out.println("No Book found with id=" + isbn);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}

	public List<Book> getBooks() throws IOException {
		String query = "select isbn, author, title, copies from book";
		List<Book> bookList = new ArrayList<>();
		try (Connection connection = jdbcConnection.getConnection();PreparedStatement ps = connection.prepareStatement(query);ResultSet rs = ps.executeQuery();){
			while (rs.next()) {
				Book book = new Book();
				book.setIsbn(rs.getInt("isbn"));
				book.setAuthor(rs.getString("author"));
				book.setTitle(rs.getString("title"));
				book.setCopies(rs.getInt("copies"));
				bookList.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return bookList;
	}

}
