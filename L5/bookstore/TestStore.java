package bookstore;
import java.util.*;

import javax.security.sasl.AuthorizeCallback;

public class TestStore {
    public static void main(String[] args) {
        // We create the Catalog from the XML file and the Shipping 
        Catalog storeCatalog = new Catalog();
        ShoppingCart shoppingCart = new ShoppingCart(storeCatalog);
        // Create BookStore instance
        BookStore bookstore = new BookStore( storeCatalog, shoppingCart);
    }
}
