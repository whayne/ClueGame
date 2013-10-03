// Will Hayne and Anthony Nguyen

package Game;

public class BadConfigFormatException extends Exception{
	public BadConfigFormatException() {}
	
	public BadConfigFormatException(String message)
	{
		super(message);
	}
	
	public String toString() {
		return "Bad format in configuration files (map and legend)";
	}

}
