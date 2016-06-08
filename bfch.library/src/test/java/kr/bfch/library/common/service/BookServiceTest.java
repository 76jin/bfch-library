package kr.bfch.library.common.service;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import kr.bfch.library.common.config.AppConfig;
import kr.bfch.library.domain.Book;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={AppConfig.class})
@TransactionConfiguration(
		transactionManager = "transactionManager",
		defaultRollback = true )
@Transactional
public class BookServiceTest {

	public static final Logger logger = LoggerFactory.getLogger(BookServiceTest.class);
	public static final Long INSERT_TEST_ID = 10L;
	public static final Long UPDATE_TEST_ID = 3L;
	public static final Long DELETE_TEST_ID = 3L;
	
	@Inject
	private BookService service;
	
	@Test
	public void testPrint() throws Exception {
		System.out.println("test print ok");
	}
	
	@Test
	public void testGetBooks() throws Exception {
		List<Book> books = service.getBooks();
		assertEquals(3, books.size());
		
		for (Book book : books)
			logger.info("book={}", book);
	}
	
	@Test
	public void testGetBook() throws Exception {
		Book selectedBook = service.getBook(1L);
		assertNotNull("아이디가 1번인 도서 정보를 가져올 수 없습니다.", selectedBook);
		
		assertEquals("명예의 조각들", selectedBook.getTitle());
		assertEquals("로이스 맥마스터 부졸드", selectedBook.getCreator());
	}
	
	@Test
	@Rollback(true)
	public void testCreateBook() throws Exception {
		Book selectedBook;
		
		Book book = new Book(INSERT_TEST_ID, "스칼라 프로그래밍", "케이 호스트만", "프로그래밍 언어", new Date());
		service.createBook(book);
		
		selectedBook = service.getBook(INSERT_TEST_ID);
		assertNotNull("아이디가 10번인 도서 정보를 가져올 수 없습니다.", selectedBook);
		
		assertEquals("스칼라 프로그래밍", selectedBook.getTitle());
		assertEquals("케이 호스트만", selectedBook.getCreator());
	}
	
	@Test
	public void testUpdateBook() throws Exception {
		Book selectedBook = service.getBook(UPDATE_TEST_ID);
		assertNotNull("아이디가 3번인 도서 정보를 가져올 수 없습니다.", selectedBook);
		
		assertEquals("피렌체의 여마법사", selectedBook.getTitle());
		assertEquals("살만 루슈디", selectedBook.getCreator());
		
		Book updateBook = new Book(UPDATE_TEST_ID, "어스시의 마법사", "어슐러 K. 르귄", "판타지소설", new Date());
		service.updateBook(updateBook);
		
		selectedBook = service.getBook(UPDATE_TEST_ID);
		assertEquals("어스시의 마법사", selectedBook.getTitle());
		assertEquals("어슐러 K. 르귄", selectedBook.getCreator());
	}
	
	@Test
	public void testDeleteBook() throws Exception {
		Book selectedBook = service.getBook(DELETE_TEST_ID);
		assertNotNull("아이디가 3번인 도서 정보를 가져올 수 없습니다.", selectedBook);
		
		service.deleteBook(DELETE_TEST_ID);
		
		selectedBook = service.getBook(DELETE_TEST_ID);
		assertNull("아이디가 3번인 도서 정보가 삭제되지 않았습니다.", selectedBook);
	}
	
}
