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

/**
 *
 * @author Florian
 */
@Entity
@Table(name = "author")
public class Author extends Person
{

    private String sold;
    
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Book> books;

    public Author()
    {
        this.books = new ArrayList<>();
    }

    public Author(String sold, String name, Adress adress)
    {
        super(name, adress);
        this.sold = sold;
        this.books = new ArrayList<>();
    }
    
    public Author(String sold, List<Book> books, String name, Adress adress)
    {
        super(name, adress);
        this.sold = sold;
        this.books = books;
    }

    public String getSold()
    {
        return sold;
    }

    public void setSold(String sold)
    {
        this.sold = sold;
    }

    public List<Book> getBooks()
    {
        return Collections.unmodifiableList(books);
    }

    public void setBooks(List<Book> books)
    {
        this.books = books;
    }

    @Override
    public String toString()
    {
        return super.toString() + " Author{" + "sold=" + sold + ", books=" + books + '}';
    }
}
