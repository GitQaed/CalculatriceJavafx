
package exceptions;

public class ExceptionHandler {
	public static String handleError(String type) {
		String Error = "";

		switch (type) {
		case "ZeroDivision-exception" -> Error = new ZeroDivisionException().getMessage();
		case "EmptyHistory-exception" -> Error = new EmptyHistoryException().getMessage();
		default -> System.out.println("Erreur inconnue");
		}
		return Error;
	}
}
