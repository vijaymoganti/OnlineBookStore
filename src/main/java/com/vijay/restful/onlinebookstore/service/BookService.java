package com.vijay.restful.onlinebookstore.service;

import java.util.List;

import org.vijay.restful.onlinebookstore.model.Book;

import com.vijay.restful.onlinebookstore.dao.BookStoreDao;

public class BookService {

	BookStoreDao bookStoreDao = new BookStoreDao();

	public void saveBook(Book book) throws Exception {
		bookStoreDao.saveBook(book);
	}

	public void update(Book book) throws Exception {
		bookStoreDao.update(book);
	}

	public Book getBook(int isbn) throws Exception {
		return bookStoreDao.getBook(isbn);
	}

	public void deleteBook(int isbn) throws Exception {
		bookStoreDao.deleteBook(isbn);
	}

	public List<Book> getBooks() throws Exception {
		return bookStoreDao.getBooks();
	}
}
