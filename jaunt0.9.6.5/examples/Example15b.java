import com.jaunt.*;
import com.jaunt.component.*;
import java.io.*;

public class Example15b{
  public static void main(String[] args){
    try{
      UserAgent userAgent = new UserAgent(); 
      userAgent.open(new File("signup2.htm"));
 
      Form form = userAgent.doc.forms[0];         //get the document's first Form
      form.set("email", "tom@mail.com");          //set the value of the textfield named 'email'
      form.set("pw", "secret");                   //set the value of the password named 'pw'
      form.set("remember", "on");                 //set the value of the checkbox named 'remember'
      form.set("account", "advanced");            //set the value of the select named 'account'
      form.set("comment", "no comment");          //set the value of the textarea named 'comment'
      form.set("inform", "no");                   //set the value of the radiobutton group named 'inform'
      form.submit("create trial account");        //click the submit button labelled 'create trial account'
      System.out.println(userAgent.getLocation());//print the current location (url)
    }
    catch(JauntException e){                   
      System.err.println(e);
    }
    catch(IOException e){
      System.err.println(e);
    }
  }
}