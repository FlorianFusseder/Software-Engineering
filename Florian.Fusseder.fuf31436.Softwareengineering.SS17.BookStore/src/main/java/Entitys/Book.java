/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entitys;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

/**
 *
 * @author Florian
 */
@Entity
abstract class Book extends SingleIdEntity<Long>
{
    private String name;
    
    private String isbn;
    
    private Date release;
    
    @ManyToMany(mappedBy = "books")
    @ElementCollection
    private List<Author> author;
    
    private BigDecimal price;

    protected Book()
    {
        this.author = new ArrayList<>();
    }

    protected Book(String name, String isbn, Date release, BigDecimal price)
    {   this();
        this.name = name;
        this.isbn = isbn;
        this.release = release;
        this.price = price;
    }

    protected Book(String name, String isbn, Date release, List<Author> author, BigDecimal price)
    {
        this.name = name;
        this.isbn = isbn;
        this.release = release;
        this.author = author;
        this.price = price;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getIsbn()
    {
        return isbn;
    }

    public void setIsbn(String isbn)
    {
        this.isbn = isbn;
    }

    public Date getRelease()
    {
        return release;
    }

    public void setRelease(Date release)
    {
        this.release = release;
    }

    public List<Author> getAuthor()
    {
        return author;
    }

    public void setAuthor(List<Author> author)
    {
        this.author = author;
    }

    public BigDecimal getPrice()
    {
        return price;
    }

    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }

    @Override
    public String toString()
    {
        return super.toString() + " Book{" + "name=" + name + ", isbn=" + isbn + ", release=" + release + ", author=" + author + ", price=" + price + '}';
    }
}
