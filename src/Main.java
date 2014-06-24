import java.io.IOException;
import java.util.List;

import com.jaunt.Document;
import com.jaunt.Element;
import com.jaunt.Elements;
import com.jaunt.Filter;
import com.jaunt.JauntException;
import com.jaunt.NodeNotFound;
import com.jaunt.ResponseException;
import com.jaunt.UserAgent;


public class Main {

	public static void main(String[] args) {
		
		UserAgent ua = new UserAgent(); //will contain the result doc
		int id = 0; //count the ids
		
		try {
			ua.openContent("<liste></liste>");
		} catch (ResponseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		try{
			UserAgent userAgent = new UserAgent();    
			userAgent.setFilter(new Filter(){                       //subclass Filter to override default settings
				
				public boolean tagNameAllowed(String tagName){        //overriding callback method
					return !tagName.matches("b|a|i|img");               //only allow tags named 'input' or 'form'
				}
				 
			});
			userAgent.visit("http://www.infres.enst.fr/wp/general-information/annuaire/"); 
			                
			String HTMLCode = userAgent.doc.innerHTML();      
			Elements elements = userAgent.doc.findEvery("<table>");
			
			List<Element> list = elements.toList();
			list.remove(0);
			list.remove(0);
			for(int i = 0 ; i < list.size() ; i++) {
				Element element = list.get(i);
				//System.out.println(element);
				List<Element> rows = element.getChildElements();
				if (i==list.size()-1){
					rows.remove(0);
				}
				for(int j = 0 ; j < rows.size() ; j++) {
					Element row = rows.get(j);
					//System.out.println(row.findFirst("<td nowrap>").getElement(0).innerText().trim());
					String name = row.findFirst("<td nowrap>").getElement(0).innerHTML().trim().replaceAll("<br>", " ~~~").trim();
					String firstName = "";
					String lastName = "";
					
					if (i != list.size()-1){
						name = name.split("~~~")[0].trim();
					}
					else{
						name = name.split("~~~")[1].trim() + " " + name.split("~~~")[0].trim();
					}
					
					String[] tab = name.split(" ");
					for(int k = 0 ; k < tab.length ; k++){
						if(isUpperCase(tab[k])==false){
							firstName = firstName + tab[k]+" ";
						}
						else{
							lastName = tab[k];
						}
					}
					
					firstName.trim();
					lastName.trim();
					
					Element pers = new Element("personne", null);
					pers.setAttribute("id", Integer.toString(id));
					pers.innerXML("<nom>" + lastName.toLowerCase() + "</nom><prenom>" + firstName.toLowerCase() + "</prenom>");
					id++;
					ua.doc.getElement(0).addChild(pers);
				}
			}
			
			
		}
		catch(JauntException e){
			System.err.println(e);
		}
		
		try {
			ua.doc.saveAsXML("listeINFRES.xml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static boolean isUpperCase(String str){
		int j = 0;
		for(int i = 0 ; i < str.length();i++){
			if(Character.isUpperCase(str.charAt(i))){
				j++;
			}
		}
		
		if(j == str.length()) return true;
		return false;
	}

}
