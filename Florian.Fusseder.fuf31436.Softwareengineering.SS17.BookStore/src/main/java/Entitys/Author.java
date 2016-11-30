/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entitys;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Represents an Author
 *
 * @author Florian
 */
@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class Author extends Person {
	
	@ManyToMany(cascade = CascadeType.ALL)
	private List<AbstractBook> books = new ArrayList<>();

	public Author(String firstName, String lastName, Adress adress) {
		super(firstName, lastName, adress);
	}
	
	public Author(String firstName, String lastName, Adress adress, AbstractBook book) {
		super(firstName, lastName, adress);
		this.books.add(book);
	}

	public Author(String firstName, String lastName, Adress adress, List<AbstractBook> books) {
		super(firstName, lastName, adress);
		books.addAll(books);
	}

	public List<AbstractBook> getBooks() {
		return Collections.unmodifiableList(this.books);
	}
	
	public void addBook(AbstractBook book){
		this.books.add(book);
	}
}
