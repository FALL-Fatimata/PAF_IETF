package IETF;

import java.util.ArrayList;


public class Liste {
	
	private ArrayList<String> liste;
	
	Liste(ArrayList<String> article)
	{
		liste=new ArrayList();
		for (int i=0;i<article.size();i++){
			add(article.get(i));
		}
	}
	
	private void add(String url)
	{
		String article="https://datatracker.ietf.org"+url;
		System.out.println("Ajout de "+article+"a la liste");//-----------------------------A EFFACER
		if (!liste.contains(article))//verifie si l'url est pas déjà dans la liste (si plusieurs membres ont publié le même article)
		{
			
			liste.add(article);//ajoute l'url à la liste si il n'y est pas déjà
		}
	}
	public ArrayList<String> getListe()
	{
		
		return liste;
	}
}
