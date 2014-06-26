package IETF;

public class Objet {

	String article;
	String pTitre;
	String gTitre;
	String date;
	String nom;
	
	Objet(String article, String gTitre, String pTitre, String date, String nom){
		this.article=article;
		this.pTitre=pTitre;
		this.gTitre=gTitre;
		this.date=date;
		this.nom=nom;
	}

	public String getArticle(){
		return article;
	}
	public String getPTitre(){
		return pTitre;
	}
	public String getGTitre(){
		return gTitre;
	}
	public String getDate(){
		return date;
	}
	public String getNom(){
		return nom;
	}
}
