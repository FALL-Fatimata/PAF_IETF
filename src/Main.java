import com.jaunt.JauntException;
import com.jaunt.UserAgent;


public class Main {

	public static void main(String[] args) {
		try{
			UserAgent userAgent = new UserAgent();                       //create new userAgent (headless browser).
			userAgent.visit("http://www.infres.enst.fr/wp/general-information/annuaire/");                        //visit a url  
			System.out.println(userAgent.doc.innerHTML());               //print the document as HTML
		}
		catch(JauntException e){         //if an HTTP/connection error occurs, handle JauntException.
			System.err.println(e);
		}

	}

}
