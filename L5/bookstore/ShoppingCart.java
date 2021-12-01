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
        if (this.totalPrice()  > 0.0 ){
            long VISANumber = 1234567890;
            String cardHolder = "John Smith";
            double totalPrice = this.totalPrice();
            Currency currency = Currency.getInstance("EU");
            Payment payInstance = Payment.getTheInstance();
            return payInstance.doPayment( VISANumber, cardHolder, totalPrice, currency );
        }
        else return "ERROR! The cart is empty.";
    }
}