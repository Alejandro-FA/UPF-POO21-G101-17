
package bookstore;

import java.util.Currency;

public class Payment {
	protected static Payment theInstance;

	protected Payment() {
	}

	public static Payment getTheInstance() {
		if ( theInstance == null )
			theInstance = new Payment();
		return theInstance;
	}

	public String doPayment( long VISANumber, String cardHolder,
	                         double totalPrice, Currency currency ) {
		String price = String.format("%.2f", totalPrice);
		String result = "Paying " + price + currency.getSymbol() + "; ";
		result += "Visa card " + VISANumber + ", holder " + cardHolder + "\n";
		return result;
	}
}
