import com.jaunt.*;
import com.jaunt.component.*;

public class Example18{
  public static void main(String[] args){
    try{
      UserAgent userAgent = new UserAgent(); 
      userAgent.visit("http://jaunt-api.com/examples/schedule.htm");
      Table table = userAgent.doc.getTable("<table class=schedule>");
      
      System.out.println("\nColumn having 'Mon':");
      Elements elements = table.getCol("mon");
      for(Element element : elements) System.out.println(element.outerHTML());
      
      System.out.println("\nColumn below 'Tue':");
      elements = table.getColBelow("tue");
      for(Element element : elements) System.out.println(element.outerHTML());

      System.out.println("\nFirst row:");
      elements = table.getRow(0);
      for(Element element : elements) System.out.println(element.outerHTML());
 
      System.out.println("\nRow right of '2:00pm':");
      elements = table.getRowRightOf("2:00pm");
      for(Element element : elements) System.out.println(element.outerHTML());
      
      System.out.println("\nCell for fri at 10:00am:");     
      Element element = table.getCell("fri", "10:00am");
      System.out.println(element.outerHTML());
      
      System.out.println("\nCell at position 3,3:");     
      element = table.getCell(3,3);
      System.out.println(element.outerHTML());
    }
    catch(JauntException e){
      System.err.println(e);
    }
  }
}