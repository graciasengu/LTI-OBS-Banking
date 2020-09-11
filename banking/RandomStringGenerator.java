package banking;
import java.security.SecureRandom;


public class RandomStringGenerator {

	public RandomStringGenerator() {
		// TODO Auto-generated constructor stub
	}
	static final String mValidCharacters = "1234567890QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm";
	SecureRandom mRandom = new SecureRandom();

	String RandomPassword( int maximum){
	   StringBuilder mComplier = new StringBuilder(maximum);
	   for( int i = 0; i < maximum; i++ ) 
		   mComplier.append( mValidCharacters.charAt( mRandom.nextInt(mValidCharacters.length()) ) );
	   return mComplier.toString();
	}
}
