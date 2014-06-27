package IETF;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Nom {
	private String noms;
	public String getNoms(){
		return noms;
	}
	Nom(String url){
		noms="";
		extractNom(url);
	}
	private void extractNom(String url){
		try {
		System.out.println("Ouverture de "+url);
	String code=HttpClass.getCode(url);
	System.out.println("site ouvert");
		BufferedReader txt = new BufferedReader(new StringReader(code));
		String rgex="[A-Z]\\. [A-Z][a-z]+";
	    Pattern pattern=Pattern.compile(rgex);
	    String courant;
    	ArrayList<String> mot=new ArrayList();
    	int j=0;
	    while ((courant=txt.readLine()) != null && j<20)
	    {
	    	Matcher m = pattern.matcher(courant); // get a matcher object 
	    	System.out.println(courant);
	    	while (m.find()) { 
	    		mot.add(m.group(0));
	    		System.out.println(m.group(0));
	  		 }
	    j++;
	    }
	    int i=0;
	    while (i<mot.size()){
	    	noms=mot.get(i)+", "+noms;
	    	i++;
	    }
	} catch (FileNotFoundException e) {e.printStackTrace();}catch (IOException e){e.printStackTrace();}
	}
}
