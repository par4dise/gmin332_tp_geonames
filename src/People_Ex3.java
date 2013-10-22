import java.util.Iterator;

import com.hp.hpl.jena.sparql.core.ResultBinding;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.vocabulary.OWL;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;


public class People_Ex3
{

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
		  String rdq = prolog1 + NL + prolog2 + NL + prolog3  + NL +
		  "SELECT ?person ?fname WHERE {?person  vCard:FN  ?fname }" ;

		  Query query = QueryFactory.create(rdq);
		  QueryExecution qexec = QueryExecutionFactory.create(query, m);
		  try {
			  Iterator<QuerySolution> results = qexec.execSelect();
			  RDFVisitor aVisitor = new Skos_UnVisiteur();
			  System.out.println("les noms de personne du modele : ");
			  for (;results.hasNext();)
			  {
				  QuerySolution sol = results.next();
				  RDFNode node = sol.get("person");
				  System.out.println(node.toString());
				  node.visitWith(aVisitor);
			  }
		  }
		  finally {qexec.close();}
	    }
	}