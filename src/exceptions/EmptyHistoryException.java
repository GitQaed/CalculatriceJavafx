
package exceptions;

@SuppressWarnings("serial")
public class EmptyHistoryException extends Exception {
	public EmptyHistoryException() {
		super("\n*** Historique vide ***");
	}
}
