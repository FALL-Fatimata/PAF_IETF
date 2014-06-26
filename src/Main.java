package IETF;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.jaunt.Document;
import com.jaunt.Element;
import com.jaunt.Elements;
import com.jaunt.Filter;
import com.jaunt.JauntException;
import com.jaunt.NodeNotFound;
import com.jaunt.ResponseException;
import com.jaunt.UserAgent;

public class main {

	public static void main(String[] args)
	{
		UserAgent ua = new UserAgent(); //will contain the result doc

		try {
			ua.openContent("<liste></liste>");
		} catch (ResponseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("Ouverture du fichier");//-----------------------------A EFFACER


		try{
			UserAgent userAgent = new UserAgent();    
			userAgent.setFilter(new Filter(){                       //subclass Filter to override default settings

				public boolean tagNameAllowed(String tagName){        //overriding callback method
					return !tagName.matches("b|a|i|img");               //only allow tags named 'input' or 'form'
				}

			});
			System.out.println("Filtrage des données");//-----------------------------A EFFACER
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
					pers.innerXML("<nom>" + firstName.toLowerCase() + lastName.toLowerCase() + "</nom>");
					ua.doc.getElement(0).addChild(pers);
				}
			}

		}
		catch(JauntException e){
			System.err.println(e);
		}

			try {
				ua.doc.getElement(0).saveAsXML("listeINFRES.xml");
			} catch (NodeNotFound e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("XML crée");//-----------------------------A EFFACER
		
		Xml xml=new Xml("listeINFRES.xml");//traite le xml pour en extraire la liste de nom
		System.out.println("Extraction des noms");//-----------------------------A EFFACER
		Url url = new Url(xml.getNom());//traite la liste de nom pour chercher les articles
		System.out.println("Recherche des urls");//-----------------------------A EFFACER
		Liste liste = new Liste(url.getArticles()); //Suprime les doublons
		System.out.println("Suppression des doublons");//-----------------------------A EFFACER
		PrintWriter receveur=null;
		try {
			receveur = new PrintWriter("article.txt");

		
		for (int i=0;i<liste.getListe().size();i++){
			receveur.print(liste.getListe().get(i).getNom()+"; "+liste.getListe().get(i).getGTitre()+"; "+liste.getListe().get(i).getPTitre()+"; "+liste.getListe().get(i).getDate()+"; "+liste.getListe().get(i).getArticle()+"\n");
		}
		
		} catch (FileNotFoundException e) {e.printStackTrace();}finally{receveur.close();}
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
	
