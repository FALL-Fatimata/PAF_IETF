package IETF;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HttpClass {
	public static String getCode(String url) {//ouvre la page demandée et
		String code = "";

		if (urlExists(url)) {
			BufferedReader in = null;

			try {
				URL site = new URL(url);
				in = new BufferedReader(
						new InputStreamReader(site.openStream()));

				String inputLine;
				int i=0;
				while ((inputLine = in.readLine()) != null && i<100) {
					code = code + "\n" + (inputLine);
					i++;
				}

				in.close();

			} catch (IOException ex) {
				System.out.println("Erreur dans l'ouverture de l'URL : " + ex);
			} finally {
				try {
					in.close();
				} catch (IOException ex) {
					System.out.println("Erreur dans la fermeture du buffer : "
							+ ex);
				}
			}
		} else {
			System.out.println("Le site n'existe pas !");
		}
		if (code.contains("<TITLE>Found</TITLE>")){
			System.out.println("Il le contient");
			code=getCode(lien(code));
		}
		return code; //envoi un array list avec les infos contenus sur la page, le decodage se fait sur le main.
	}

	public static boolean urlExists(String url) {//verifie l'existence de l'URL
		try {
			URL site = new URL(url);
			try {
				site.openStream();
				return true;
			} catch (IOException ex) {
				return false;
			}
		} catch (MalformedURLException ex) {
			return false;
		}
	}
	public static String lien(String code){
		String rgex="http://tools.ietf.org/html/[a-z0-9\\-]*.txt";
	    Pattern pattern=Pattern.compile(rgex);
	    Matcher m = pattern.matcher(code); // get a matcher object 
		 while (m.find()) { 
		  code=m.group(0);
		  code=code.replace("html","id");
		 }
		 System.out.println(code);
		return code;
	}
}
