package IETF;
import java.io.*;

import org.jdom2.*;
import org.jdom2.input.*;
import org.jdom2.filter.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class Xml
{
   private static org.jdom2.Document document;
   private static Element racine;
   private ArrayList<String> nom ;
   
   public Xml(String fichier)
   {
	   nom=new ArrayList();
	  extraireNom(fichier);
   }
   
   private void lire()//Methode qui lit un xml et renvoi la liste des noms dedans
   {
      //On crée une List contenant tous les noeuds "liste" de l'Element racine
      List listEtudiants = racine.getChildren("personne");

      //On crée un Iterator sur notre liste
      Iterator i = listEtudiants.iterator();
      while(i.hasNext())
      {
         //On recrée l'Element courant à chaque tour de boucle afin de
         //pouvoir utiliser les méthodes propres aux Element comme :
         //sélectionner un nœud fils, modifier du texte, etc...
         Element courant = (Element)i.next();
         //On ajoute le nom courant à un arraylist
         nom.add(courant.getChild("nom").getText());//Permet de gérer un bug
      }

   }
   private void extraireNom(String fichier)//Metthode à utiliser, elle utilise le bon xml
   {
	   SAXBuilder sxb = new SAXBuilder();
	      try
	      {
	         //On crée un nouveau document JDOM avec en argument le fichier XML
	         //Le parsing est terminé ;)
	         document = sxb.build(new File(fichier));
	      }
	      catch(Exception e){}
	      //On initialise un nouvel élément racine avec l'élément racine du document.
	      racine = document.getRootElement();

	      //Retourne une arraylist des noms
	      lire();
   }
   
   public ArrayList<String> getNom(){
	   return nom;
   }


}
