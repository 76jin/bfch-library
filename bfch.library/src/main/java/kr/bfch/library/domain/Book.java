package kr.bfch.library.domain;

import java.util.Date;

import org.pojomatic.Pojomatic;
import org.pojomatic.annotations.AutoProperty;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AutoProperty
public class Book {

	private Long id;
	private String title;
	private String creator;
	private String type;
	private Date date;
	
	public Book() {}

	public Book(Long id, String title, String creator, String type, Date date) {
		this.id = id;
		this.title = title;
		this.creator = creator;
		this.type = type;
		this.date = date;
	}
	
	@Override
	public boolean equals(Object obj) {
		return Pojomatic.equals(this, obj);
	}
	
	@Override
	public int hashCode() {
		return Pojomatic.hashCode(this);
	}
	
	@Override
	public String toString() {
		return Pojomatic.toString(this);
	}
	
}
