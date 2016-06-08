package kr.bfch.library.common.mapper;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.bfch.library.common.config.AppConfig;
import kr.bfch.library.domain.Book;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={AppConfig.class})
public class BookMapperTest {
	
	public static final Logger logger = LoggerFactory.getLogger(BookMapperTest.class);
	public static final Long INSERT_ID = 10L;
	
	@Inject
	private BookMapper bookMapper;
	
	private Book book;

	@Before
	public void setup() {
		this.book = new Book(INSERT_ID, "스칼라 프로그래밍", "케이 호스트만", "프로그래밍 언어", new Date());
		assertNotNull(book);
	}
	
	@Test
	public void testSelect() throws Exception {
		List<Book> books = bookMapper.select();
		assertEquals(3, books.size());
		
		for (Book book : books) 
			logger.info("book={}", book);
	}
	
	@Test
	public void testSelectByPrimaryKey() throws Exception {
		Book book = bookMapper.selectByPrimaryKey(1L);
		logger.info("book{}", book);
		assertNotNull(book);
	}
	
	@Test
	public void testInsert() throws Exception {
		bookMapper.insert(book);
		Book newBook = bookMapper.selectByPrimaryKey(INSERT_ID);
		logger.info("newBook={}", newBook);
		assertEquals(book, newBook);
	}
	
	@Test
	public void testUpdate() throws Exception {
		bookMapper.insert(book);
		book.setCreator("나잘난");
		
		int resultCount = bookMapper.updateByPrimaryKey(book);
		logger.info("resultCount:{}", resultCount);
		
		Book newBook = bookMapper.selectByPrimaryKey(INSERT_ID);
		logger.info("newBook={}", newBook);
		assertEquals("나잘난", newBook.getCreator());
	}
	
	@Test
	public void testDelete() throws Exception {
		bookMapper.insert(book);
		Book newBook = bookMapper.selectByPrimaryKey(INSERT_ID);
		assertEquals(book, newBook);
		
		int resultCount = bookMapper.deleteByPrimaryKey(INSERT_ID);
		logger.info("resultCount:{}", resultCount);
		
		Book deletedBook = bookMapper.selectByPrimaryKey(INSERT_ID);
		logger.info("deletedBook={}", deletedBook);
		assertNull(deletedBook);
	}
}
