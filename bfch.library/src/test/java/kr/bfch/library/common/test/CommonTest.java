package kr.bfch.library.common.test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import kr.bfch.library.domain.Book;

public class CommonTest {

	@Test
	public void testNewBook() throws Exception {
		Book book = new Book(10L, "스칼라 프로그래밍", "케이 호스트만", "프로그래밍 언어", new Date());
		System.out.println(book.toString());
	}
}
