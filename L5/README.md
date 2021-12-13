# Report Lab 5
Alejandro Fernández and Marc Aguilar
<br>
<br>

First of all we want to make clear that we have used the given design. Therefore, each class contains the attributes and methods of the given UML design. 

## Book

This class contains the attributes `title`, `author`, `publicationDate`, `publicationPlace` and `ISBN`. Apart from the constructor, the other methods are getters of the attributes. 

## Stock

This class has the attributes `book`, `copies`, `price` and `currency`. The purpose of it is storing the economical information and number of copies of each book. Therefore, each book will have its associated `Stock` class. 

The constructor only sets each attribute to a given initial values. We have also added another constructor which receives a `Stock` instance and copies it by creating a new instance with the same attributes. This will be used in the `ShoppingCart`, so it will be explained with more details in the corresponding section.

Then, there are three getters for the book, the title and the number of copies. Apart from those, we have the method `addCopies` which adds a given number of copies to the number of copies, `removeCopies` which removes a given number of copies. We check that the number of copies to add or remove is positive in order to avoid unexpected program states. Finally, `totalPrice` which computes the total price of **that specific** stock.

## Catalog

This class does not have any specific attribute, it inherits the `collection` from the abstract class `BookCollection`. Its only method is the constructor, from which we read and parse each book from the file `books.xml` and we create the appropriate instances. 

First of all, we create a `LinkedList` of `String`, which contains each book in the format `[Title],[Author],[PublicationDate], [PublicationPlace], [ISBN], [Price], [Currency], [Copies]`. We obtain each book in this format with the given method `readCatalog` of the class `BookCollection`. 

Then, we have to parse each of the fields. The fields which are already `String` such as the title, the author and the publication place do not need any further parsing. On the other hand, the publication date is parsed by using the class `SimpleDateFormat`. We specified the format of the date which is `yyyy/MM/dd` and then we use the method `parse`. The ISBN, the price and the number of copies are parsed with each corresponding `parseTypeOfNumber` method, and the currency with the `getInstance` method. 

Once we have all this fields correctly parsed into variables, we create a new instance of book with the corresponding information, and **for each** book we create a new `Stock`. 

The last step is adding each `Stock` instance to the `collection` of the Store, which is the attribute inherited from `BookCollection`. 

##  ShoppingCart

This class only has one attribute which is a `catalog`, used to store the stocks and consequently the books that we have in the Store at any moment.

#### Constructor

This class is probably the one with the **largest amount of possible approaches** of the program. Given that its only attribute is the catalog of the store, we have decided to **build the `ShoppingCart` stock `collection` from the catalog** instead of using the `book.xml` file. Given that in our program only one shopping cart is created, the latter approach would also work (but it would not for multiple shopping carts).

Our approach requires some tweaking of the design though. We require to do a **deep copy** of `Stock` instances. We could have added a `getPrice()` and `getCurrency()` method to the `Stock` class. That way we would be able to access all of its attributes from outside. But we thought that it would be cleaner to use a **`copy constructor`**, as mentioned before, which given a Stock it returns another one with the same attributes.

In the constructor we basically set the the attribute `catalog` to a given initial catalog, which will contain all the books of the store. Then we add a copy of each `Stock` (with the second constructor of the class) in the catalog to the `collection` inherited from `BookCollection`. Finally we call the method `clearCart` which iterates over all the `Stock` of the `collection` and sets its number of copies value to 0, as initially the cart is empty. 

As a last comment, it should be noted that we could have set the number of copies of each `Stock`instance to 0 directly inside the copy constructor. But finally we decided against it since it does not seem intuitive, and it would be harder to reuse such constructor in case of necessity.

#### Other methods

Then we have the methods `addCopies` and `removeCopies`. The first one first removes the given book and number of copies from the store, so from `catalog`. Then we add the given book and number of copies to the cart, so to the `collection` attribute. The second method works in the inverse way, we add the copies to the `catalog` of the store and we remove them from the cart. 

The methods in charge of dealing with payments are `totalPrice` and `checkout`. The first one is very simple, it justs iterate over all the `Stocks/books` in the cart, adding their price with the method `totalPrice` of `Stock` and returning the total price. The other method is `checkout`, which first checks if the total price is greater than `0.001` (it takes into account  possible float arithmetic errors). Then, we have made up some buyer information. We create or get (depending if it is the first payment or not) the only existing `Payment` instance, and finally we call the method `doPayment` with the made up information. After that we clear the cart with the aforementioned method `clearCart`. Finally return the result of the returned string in `doPayment`. 

## TestStore

This class contains the `main` method and is the class from where we call and create all the necessary instances.

First we create the catalog of the store, by creating a new instance of `Catalog` called `storeCatalog` (which will contain the books of the XML file). Then, with this catalog we create the `ShoppingCart` named `shoppingCart`. 

Finally, in order to create the store and display the GUI, we create an instance of `BookStore` with the `storeCatalog` and the `shoppingCart` as parameters. 

## Other comments

Some other classes have been modified in order to ensure that the price is always displayed with just two decimal places.



