package uk.ac.ucl.panda.retrieval.models;

import java.util.HashMap;
import java.util.Vector;

/**
 *	@author kensk8er
 */
public class BM25PNW implements Model {

    /**
     *  BM25PNW parameter
     */
    private float s = 0.2f;

    /**
     *  @param tf - Term Frequency, number of times term appears in the document
     *  @param df - Document Frequency, number of documents the term appears in
     *  @param idf - Inverse Document Frequency
     *  @param DL - number of terms in document
     *  @param avgDL - average number of terms in all documents
     *  @param DocNum - number of documents in the collection
     *  @param CL - Collection Length, number of terms in document collection
     *  @param CTF - Collection Term Frequency, number of times the term appears in the collection
     *  @param qTF - Query Term Frequency, number of times the term appears in the query
     */
    public double getscore(double tf, double df, double idf, double DL, double avgDL, int DocNum, double CL, int CTF, int qTF) {
        double numerator = 1.0d + (Math.log(1 + (Math.log(tf) / Math.log(2.0d))) / Math.log(2.0d));
        double denominator = (1.0d - s) + s * DL / avgDL;
        double firstTerm = numerator / denominator;
        double score = firstTerm * qTF * (Math.log((DocNum + 1) / df) / Math.log(2.0d));

        return score;
    }

    /**
     *  The following functions are not needed for Text Retrieval assignment
     */

    @Override
    public double getVSMscore(Vector<String> query, HashMap<String, Integer> TermVector) {
        return 0;
    }

    @Override
    public
    double defaultScore(double tf, double df, double idf, double DL, double avgDL, int DocNum, double CL, int CTF, int qTF) {
        return 0.0d;
    }


    public double defaultScore(double tf, double df, double idf, double DL, double avgDL, int DocNum, double CL, int CTF, int qTF, double a) {
        s = (float)a;
        return defaultScore(tf,df, idf, DL, avgDL, DocNum, CL, CTF, qTF);
    }


    public double getscore(double tf, double df, double idf, double DL, double avgDL, int DocNum, double CL, int CTF, int qTF, double a) {
        s = (float) a;
        return getscore(tf, df, idf, DL, avgDL, DocNum, CL, CTF, qTF);
    }

    @Override
    public
    double getmean(double tf, double df, double idf, double DL, double avgDL, int DocNum, double CL, int CTF, int qTF, double a) {
        double numerator = 1.0d + (Math.log(1 + (Math.log(tf) / Math.log(2.0d))) / Math.log(2.0d));
        double denominator = (1.0d - s) + s * DL / avgDL;
        double firstTerm = numerator / denominator;
        double score = firstTerm * qTF * (Math.log((DocNum + 1) / df) / Math.log(2.0d));

        return score;
    }

    @Override
    public
    double getvar(double tf, double df, double idf, double DL, double avgDL, int DocNum, double CL, int CTF, int qTF, double a) {
        return 0.0;
    }

    @Override
    public
    double defaultmean(double tf, double df, double idf, double DL, double avgDL, int DocNum, double CL, int CTF, int qTF, double a) {
        s = (float) a;
        return defaultScore(tf,df, idf, DL, avgDL, DocNum, CL, CTF, qTF);
    }

    @Override
    public
    double defaultvar(double tf, double df, double idf, double DL, double avgDL, int DocNum, double CL, int CTF, int qTF, double a) {
        return 0.0;
    }





}
