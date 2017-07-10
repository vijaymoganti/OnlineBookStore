package org.vijay.restful.onlinebookstore.resources;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.vijay.restful.onlinebookstore.model.Book;

import com.vijay.restful.onlinebookstore.service.BookService;

@Path("/Books")
public class BookStore {

	BookService bookService = new BookService();

	@GET
	@Path("/allbooks")
	//@Produces(MediaType.APPLICATION_JSON)
	public Response getBooks() throws Exception {
		List<Book> books = new ArrayList<>();
		books = bookService.getBooks();
		GenericEntity<List<Book>> entity = new GenericEntity<List<Book>>(books){};
		return  Response.ok(entity,MediaType.APPLICATION_JSON).status(Status.OK).build();
	}

	@GET
	@Path("/getBook/{isbn}")
	//@Produces(MediaType.APPLICATION_JSON)
	public Response getBook(@PathParam("isbn") int isbn) throws Exception {
		return Response.ok(bookService.getBook(isbn)).status(Status.OK).build();

	}

	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	//@Produces(MediaType.APPLICATION_JSON)
	public Response updateBook(Book newBook) throws Exception {
		Book book = new Book();
		book.setAuthor(newBook.getAuthor());
		book.setIsbn(newBook.getIsbn());
		book.setCopies(newBook.getCopies());
		book.setTitle(newBook.getTitle());
		bookService.update(book);
		return Response.ok(book).status(Status.ACCEPTED).build();
	}

	@DELETE
	@Path("/delete/{isbn}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteBook(@PathParam("isbn") int isbn) throws Exception {
		Book book = new Book();
		book.setIsbn(isbn); 
		book.setAuthor(bookService.getBook(isbn).getAuthor());
		book.setTitle(bookService.getBook(isbn).getTitle());
		book.setCopies(bookService.getBook(isbn).getCopies());	
		bookService.deleteBook(isbn);
		return Response.ok(book).status(Status.GONE).header("Delete", 410).build();
	}

	@POST
	//@Consumes(MediaType.APPLICATION_JSON)
	//@Produces(MediaType.APPLICATION_JSON)
	public Response createBook(Book newBook) throws Exception {
		Book book = new Book();
		book.setAuthor(newBook.getAuthor());
		book.setIsbn(newBook.getIsbn());
		book.setCopies(newBook.getCopies());
		book.setTitle(newBook.getTitle());
		bookService.saveBook(book);
		return Response.ok(book).status(Status.CREATED).build();
	}

}
