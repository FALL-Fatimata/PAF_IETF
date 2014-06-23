import java.util.List;

import com.jaunt.Element;
import com.jaunt.Elements;
import com.jaunt.Filter;
import com.jaunt.JauntException;
import com.jaunt.NodeNotFound;
import com.jaunt.ResponseException;
import com.jaunt.UserAgent;


public class Main {

	public static void main(String[] args) {
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
				System.out.println(element);
				List<Element> rows = element.getChildElements();
				if (i==list.size()-1){
					rows.remove(0);
				}
				for(int j = 0 ; j < rows.size() ; j++) {
					Element row = rows.get(j);
					//System.out.println(row.findFirst("<td nowrap>").getElement(0).innerText().trim());
					System.out.println(row.findFirst("<td nowrap>").getElement(0).innerHTML().trim());
				}
			}
			
			
		}
		catch(JauntException e){
			System.err.println(e);
		}
		
	}

}
