import javax.sound.sampled.AudioFileFormat.Type;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.vocabulary.RDF;


public class Q2 {

	public static final String CRLF = System.getProperty("line.separator") ;
	
	public static void main(String[] args) {
		  Model m = ModelFactory.createOntologyModel();
		  
		  final String file = "file:in/ontology_v3.1.rdf";
		  m.read(file);
		  
		  String pref1 = "PREFIX skos: <http://www.w3.org/2004/02/skos/core#>";
		  String pref2 = "PREFIX rdf: <" + RDF.getURI() + ">";
		  String pref3 = "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>";
		  String pref4 = "PREFIX gn: <http://www.geonames.org/ontology#>";

		  String typeCode = "skos:Concept";		// resultats dans query2.rdf
		  String typeCode2 = "gn:Code";			// resultats dans query2.1.rdf
		  
		  String qClasses = pref1 + CRLF + pref2 + CRLF + pref3 + CRLF + pref4 + CRLF +
				            "SELECT ?s ?p ?d ?l WHERE {?s rdf:type " + typeCode2 + " . " +
				            "?s skos:inScheme ?p . " +
				            "?s skos:definition ?d . " +
				            "?s skos:prefLabel ?l " +
				            "filter(langMatches(lang(?d), \"en\") ) " +
				            "filter(langMatches(lang(?l), \"en\") )}";
		  
		  Query Q = QueryFactory.create(qClasses);
		  QueryExecution qexec = QueryExecutionFactory.create(Q, m) ;
		 
		  try {
			  try {
					FileOutputStream ost = new FileOutputStream("out/query2.1.rdf");

					//System.out.println("Codes et leur définition en anglais :");
				  ResultSet rs = qexec.execSelect() ;
				  
				  //ResultSetFormatter.out(System.out, rs, Q);
				  ResultSetFormatter.out(ost, rs, Q);
			  }

				catch (FileNotFoundException e) {
					System.out.println("pb de fichier");
				} 

		 }
		 finally {
                qexec.close() ;
         }
		  
		 //m.write(System.out, "N3");
	}

}
