package IETF;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class Url {

	ArrayList<Objet> articles = new ArrayList();

	Url(ArrayList<String> auteur){

		for (int i = 0;i<auteur.size();i++){
			try {
				extractUrl(auteur.get(i));
			} catch (IOException e) {e.printStackTrace();}
		}

	}

	private void extractUrl(String auteur) throws IOException {
		//On se connecte au site et on charge le document html
		System.out.println("Connection aux données de "+auteur);//-----------------------------A EFFACER
		//if (auteur.length()>=3){
		try{
			Document doc = Jsoup.connect("https://datatracker.ietf.org/doc/search/?name=&rfcs=on&activedrafts=on&olddrafts=on&sort=&by=author&author="+auteur).timeout(6000).get();


			Elements element= doc.select("td.doc");//Extraction des urls
			Elements element2=null;
			Elements element3=null;
			if (element.size()==0){System.out.println("Pas d'article");}
			else {
				System.out.println("Taille element:"+element.size());
				element2=doc.select("td.title");//extraction du titre
				element3=doc.select("td.date");

				int h=1;
				for (int i=0;i<element.size();i++){
					System.out.println("Extraction de "+h+" sur "+element.size());//-----------------------------A EFFACER
					h++;
					String txt1=element.get(i).getElementsByTag("a").attr("href");
					String txt="http://tools.ietf.org//html/"+txt1.substring(5, txt1.length());
					String gTitre=element2.get(i).text();
					String pTitre=element.get(i).getElementsByTag("a").text();
					String date=element3.get(i).text();
					String nom= new Nom("http://tools.ietf.org/id/"+txt1.substring(5, txt1.length()-1)+".txt").getNoms();
					articles.add(new Objet(txt,gTitre,pTitre,date,nom));
				}
			}
		}catch(SocketTimeoutException e){System.out.println("Erreur: Socket Time Out");extractUrl(auteur);}
	}

	public ArrayList<Objet> getArticles(){
		return articles;
	}

}

