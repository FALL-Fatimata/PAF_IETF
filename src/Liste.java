package IETF;

import java.util.ArrayList;


public class Liste {
	
	private ArrayList<Objet> liste;
	
	Liste(ArrayList<Objet> article)
	{
		liste=new ArrayList();
		for (int i=0;i<article.size();i++){
			add(article.get(i));
		}
	}
	
	private void add(Objet url)
	{
		String article=url.getArticle();
		System.out.println("Ajout de "+article+"a la liste");
		if (!liste.contains(url))//verifie si l'url est pas déjà dans la liste (si plusieurs membres ont publié le même article)
		{
			
			liste.add(url);//ajoute l'url à la liste si il n'y est pas déjà
		}
	}
	public ArrayList<Objet> getListe()
	{
		
		return liste;
	}
}
