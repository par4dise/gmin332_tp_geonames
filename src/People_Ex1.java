import java.util.Iterator;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;


public class People_Ex1
{

    public static final String NL = System.getProperty("line.separator") ;

	  public static void main(String[] args)
	    {
		  Model m = ModelFactory.createOntologyModel();
		  String fil_URL = "file:in/people_arq.rdf";
	
		  m.read(fil_URL);
		  // Query string.
		  String rdq = "SELECT ?s ?p ?o WHERE {?s  ?p  ?o }" ;

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