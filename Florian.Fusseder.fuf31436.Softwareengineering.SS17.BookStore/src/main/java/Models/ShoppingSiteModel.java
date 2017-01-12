/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Annotations.CustomerAnnotation;
import Entitys.AbstractBook;
import Entitys.Author;
import Entitys.CartItem;
import Entitys.Customer;
import Entitys.ShoppingCart;
import Services.Interfaces.IBookService;
import Services.Interfaces.IPersonService;
import Services.Interfaces.IShoppingService;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.convert.PersonConverter;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Florian
 */
@Named
@SessionScoped
@NoArgsConstructor
public class ShoppingSiteModel implements Serializable {

	@Inject
	private IBookService bookService;

	@Inject
	private IShoppingService shoppingService;

	@Inject
	@CustomerAnnotation
	private IPersonService<Customer> customerService;

	@Getter
	@Setter
	private Customer customer;

	@Setter
	private List<AbstractBook> bookList;

	@Inject
	@Getter
	@Setter
	private PersonConverter converter;

	@Getter
	@Setter
	private String searchTerm;

	@PostConstruct
	public void init() {
		searchTerm = "";
		this.bookList = this.bookService.findAll();
	}

	public List<AbstractBook> getBookList() {
		if (searchTerm.isEmpty()) {
			this.bookList = this.bookService.findAll();
		}
		return bookList;
	}

	public void searchFor() {
		this.bookList = this.bookService.searchBooks(this.searchTerm);
	}

	public List<ShoppingCart> getAllBoughtCarts() {
		return this.customer.getPayedShoppingCarts().stream()
				.sorted((ShoppingCart t, ShoppingCart t1) -> {
					if (t.getCheckoutDate().after(t1.getCheckoutDate())) {
						return -1;
					} else {
						return 1;
					}
				})
				.collect(Collectors.toList());

	}

	public ShoppingCart getShoppingCart() {
		return this.customer.getShoppingCart();
	}

	public String acceptShoppingCart() {
		if (!this.customer.getShoppingCart().getShoppingList().isEmpty()) {
			this.searchTerm = "";
			this.init();
			return "chooseDelivery";
		}
		return "";
	}

	public String buyShoppingCart() {
		if (!this.customer.getShoppingCart().getShoppingList().isEmpty()) {
			this.customer = this.shoppingService.buyCurrentCart(this.customer);
			this.searchTerm = "";
			this.init();
			return "shop";
		}
		return "";
	}

	public List<Customer> getCustomerList() {
		List<Customer> customerList = this.customerService.findAll();
		return customerList;
	}

	public void addBookToCart(AbstractBook book) {
		this.customer = this.shoppingService.alterShoppingCart(this.customer, book, 1);
	}

	public void removeBook(CartItem cartItem) {
		this.customer = shoppingService.alterShoppingCart(this.customer, cartItem.getAbstractBook(), -1);
	}

	public int booksInCart() {
		return this.customer.getShoppingCart().getShoppingList().stream()
				.mapToInt(ci -> ci.getCount())
				.sum();
	}

	public String authorsAsString(List<Author> authorList) {
		return authorList.stream().map(a -> (a.getFirstName() + " " + a.getLastName())).collect(Collectors.joining(", "));
	}

	public String logout() {
		this.searchTerm = "";
		this.customer = null;
		return "login";
	}
}
