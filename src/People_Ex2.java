import com.hp.hpl.jena.vocabulary.OWL;
import com.hp.hpl.jena.vocabulary.RDF;
//import org.apache.jena.atlas.io.IndentedWriter;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.*;


public class People_Ex2 {
	
		 public static final String NL = System.getProperty("line.separator") ;
	   public static void main(String[] args)
	   {
	         
		   Model m = ModelFactory.createOntologyModel();
			  String fil_URL = "file:in/people_arq.rdf";
			  String people = "http://somewhere/peopleInfo#";
			  String vCard = "http://www.w3.org/2001/vcard-rdf/3.0#";
			  String prolog1 = "PREFIX rdf: <"+RDF.getURI()+">" ;
			  String prolog2 = "PREFIX info: <"+people+">" ;
			  String prolog3 = "PREFIX vCard: <"+vCard+">" ;
			  m.read(fil_URL);
			  // Query string.
			  String rdq = prolog1 + NL + prolog2 + NL + prolog3 + NL +
			  "SELECT  ?person (COUNT(?tel) AS ?nbreTel) " +
			  " WHERE { " +
			  " ?person  vCard:FN  ?fname . " +
			  " ?person vCard:TEL ?tel . } "  +
			  " GROUP BY ?person HAVING(COUNT(?tel) > 3)"  ;
			  
			  
			  Query query = QueryFactory.create(rdq);
	        
	         // afficher la requete
	        // query.serialize(new IndentedWriter(System.out,true)) ;
	         System.out.println() ;
	       
	         QueryExecution qexec = QueryExecutionFactory.create(query, m) ;
	       

	         System.out.println("Les elements du modele : ") ;

	         try {
	             
	             ResultSet rs = qexec.execSelect() ;
	             ResultSetFormatter.out(System.out, rs, query);

	         }
	         finally
	         {
	                         qexec.close() ;
	         }
	     	     
	   }

}