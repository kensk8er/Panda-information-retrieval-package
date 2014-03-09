package uk.ac.ucl.panda.retrieval.models;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * 
 * Implements the Vector Space Model. See course notes for a description of the
 * Vector Space Model
 * 
 * @author Marc Sloan
 */
public class VectorSpaceModel implements Model {

	/**
	 * This method should only be used in the Vector Space Model class. This
	 * method will be called instead of the getScore method below
	 * 
	 * @param query
	 *            A Vector of Strings representing the query
	 * @param TermVector
	 *            HashMap linking each term in the document with its term
	 *            frequency
	 * 
	 *            Example: query = [foreign, minor, germani] 
	 *            TermVector = {spy=1, accept=1, servic=1, visit=2, languag=1, ...
	 * 
	 * This method is called for every query and every document in the collection.
	 */
	@Override
	public double getVSMscore(Vector<String> query,
			HashMap<String, Integer> TermVector) {
        // Vector Space Model with term weights nnc.nnc
        // change query vector into HashMap
        HashMap<String, Double> QueryVector = new HashMap<String, Double>();
        for (Map.Entry<String, Integer> entry : TermVector.entrySet()) {
            String key = entry.getKey();
            if (!QueryVector.containsKey(key)) {
                QueryVector.put(key, 0.);
            }
            if (query.contains(key)) {
                QueryVector.put(key, 1.);
            }
        }

        // normalize document vector
        HashMap<String, Double> DocumentVector = new HashMap<String, Double>();

        double denominator = 0;
        for (Integer value : TermVector.values()) {
            denominator += Math.pow(value, 2);
        }
        denominator = Math.sqrt(denominator);

        for (Map.Entry<String, Integer> entry : TermVector.entrySet()) {
            DocumentVector.put(entry.getKey(), entry.getValue() / denominator);
        }

        // compute inner product
        double score = 0;
        for (String key : DocumentVector.keySet()) {
            score += QueryVector.get(key) * DocumentVector.get(key);
            /* QueryVector hasn't been normalized yet, but it doesn't affect retrieval results. */
        }

        return score;
	}

	/**
	 *  The following functions are not needed for Text Retrieval assignment
	 */

	public double getscore(double tf, double df, double idf, double DL,
			double avgDL, int DocNum, double CL, int CTF, int qTF) {
		return 0.0d;
	}

	@Override
	public double defaultScore(double tf, double df, double idf, double DL,
			double avgDL, int DocNum, double CL, int CTF, int qTF) {
		return 0.0d;
	}

	@Override
	public double defaultScore(double tf, double df, double idf, double DL,
			double avgDL, int DocNum, double CL, int CTF, int qTF, double a) {
		return 0.0d;

	}

	@Override
	public double getscore(double tf, double df, double idf, double DL,
			double avgDL, int DocNum, double CL, int CTF, int qTF, double a) {
		return 0.0d;

	}

	@Override
	public double getmean(double tf, double df, double idf, double DL,
			double avgDL, int DocNum, double CL, int CTF, int qTF, double a) {

		return 0.0d;
	}

	@Override
	public double getvar(double tf, double df, double idf, double DL,
			double avgDL, int DocNum, double CL, int CTF, int qTF, double a) {
		return 0.0;
	}

	@Override
	public double defaultmean(double tf, double df, double idf, double DL,
			double avgDL, int DocNum, double CL, int CTF, int qTF, double a) {
		return 0.0d;
	}

	@Override
	public double defaultvar(double tf, double df, double idf, double DL,
			double avgDL, int DocNum, double CL, int CTF, int qTF, double a) {
		return 0.0;
	}

}
