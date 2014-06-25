package IETF;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class Url {
	
	ArrayList<String> articles = new ArrayList();
	
	Url(ArrayList<String> auteur){
		
		for (int i = 0;i<auteur.size();i++){
			try {
				getFinal(auteur.get(i));
			} catch (IOException e) {e.printStackTrace();}
		}
		
	}
	
	private void getFinal(String auteur) throws IOException {
	//On se connecte au site et on charge le document html
		System.out.println("Connection aux données de "+auteur);//-----------------------------A EFFACER
		//if (auteur.length()>=3){
		try{
	Document doc = Jsoup.connect("https://datatracker.ietf.org/doc/search/?name=&rfcs=on&activedrafts=on&sort=&by=author&author="+auteur).get();
		
	
	Elements element= doc.select("td.doc");//on regarde toutes les balise td qui ont pour classe doc et on les met dans element
	if (element.size()==0){System.out.println("Pas d'article");}
	else {System.out.println("Taille element:"+element.size());}//-----------------------------A EFFACER

	int h=1;
	for (int i=0;i<element.size();i++){
		System.out.println("Extraction de "+h+" sur "+element.size());//-----------------------------A EFFACER
		h++;
	articles.add(element.get(i).getElementsByTag("a").attr("href"));//pour chaque td, on extrait le contenu du href des <a> et on les met dans l'arraylist
	}
		}catch(SocketTimeoutException e){System.out.println("Erreur: Socket Time Out");getFinal(auteur);}
		
	//}else {System.out.println(auteur+"possède un nom trop court:\n Le moteur de recherche de ietf n'est pas assez performant");}
	}
	
	public ArrayList<String> getArticles(){
		return articles;
	}
}

