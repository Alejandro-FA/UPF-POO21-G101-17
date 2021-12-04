package bookstore;
import java.util.*;
import java.text.SimpleDateFormat;

public class Catalog extends BookCollection {

    public Catalog() {
        super();
        // Read XML file
        String fileName = "books.xml";
        LinkedList< String[] > bookList = BookCollection.readCatalog( fileName );

        // Parse list of Book
        for (String[] fields: bookList) {
            String Title = fields[0];
            String Author =  fields[1];
            Date publicationDate = new Date();
            try { publicationDate = new SimpleDateFormat("yyyy/MM/dd").parse(fields[2]); }
            catch(Exception e){ System.out.println("Error when parsing the date."); }
            String publicationPlace = fields[3];
            long isbn = Long.parseLong( fields[4] );
            double price = Double.parseDouble( fields[5] );
            Currency currency = Currency.getInstance( fields[6] );
            int copies = Integer.parseInt( fields[7] );
            
            // Create stock instance
            Book book = new Book(Title, Author, publicationDate, publicationPlace, isbn);
            Stock stock = new Stock(book, copies, price, currency);

            // Add stock to collection
            this.collection.add(stock);
        }
    }
}