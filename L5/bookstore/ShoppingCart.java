package bookstore;
import java.util.Currency;

public class ShoppingCart extends BookCollection implements ShoppingCartInterface {
    private Catalog catalog;
    
    public ShoppingCart( Catalog catinit ){
        super();
        catalog = catinit;
        /**
         * Strange use of protected attribute. We thought that it was possible
         * to access protected attributes from subclasses, but WITHIN AN INSTANCE
         * 
         * Instead, here we are accessing an attribute of the super class of
         * another instance.
         */
        // this.collection = catalog.collection;
        // for(StockInterface stock: this.collection) {
        //     int copies = stock.numberOfCopies();
        //     stock.removeCopies(copies);
        // }
        /**
         * It is important to notice here that we are building the ShoppingCart from the
         * catalog instead of reading the "books.xml" file directly. In our case it does
         * not matter, since we only have 1 ShoppingCart throughout the execution of the
         * program, but if there were more this would be the apptopiate approach.
         */
        for (StockInterface s: catalog.collection) {
            if (s instanceof Stock) {
                Stock stock = (Stock) s;
                Book book = stock.getBook();
                double price = stock.getPrice();
                Currency currency = stock.getCurrency();
                int copies = 0;
                
                Stock stockCopy = new Stock(book, copies, price, currency);
                this.collection.add(stockCopy);
            }
        }
    }

    @Override
    public void addCopies( int numberOfCopies, String booktitle){
        catalog.removeCopies( numberOfCopies, booktitle);
        super.addCopies( numberOfCopies, booktitle);
    }

    @Override
    public void removeCopies(int numberOfCopies, String booktitle){
        catalog.addCopies( numberOfCopies, booktitle );
        super.removeCopies( numberOfCopies,  booktitle);
    }

    public double totalPrice() {
        double totalPrice = 0.0;
        for (StockInterface stock: this.collection)
            totalPrice += stock.totalPrice();
        return totalPrice;
    }
    
    public String checkout() {
        if ( Double.compare(this.totalPrice(), 0.001) > 0 ){
            long VISANumber = 1234567890;
            String cardHolder = "John Smith";
            double totalPrice = this.totalPrice();
            Currency currency = Currency.getInstance("EUR");
            Payment payInstance = Payment.getTheInstance();

            String payResult = payInstance.doPayment( VISANumber, cardHolder, totalPrice, currency );
            clearCart(); // Clear the ShoppingCart after checkout
            return payResult;
        }
        else return "ERROR! The cart is empty.";
    }

    private void clearCart() {
        for (StockInterface s: this.collection) {
            int copies = s.numberOfCopies();
            s.removeCopies(copies);
        }
    }
}