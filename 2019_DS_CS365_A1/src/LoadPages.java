/*
Name: Joseph M. Scollo
School: Suny Oswego
Course: CSC-365 Data Structures

Class: LoadPages
About Class: This class contains 5 methods and a constructor. The details on their members and how the function is documented
                  through out the code. This class manages web page loading, cache creation, and file writing.

*/



import java.util.*;
import java.io.*;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

class LoadPages{

    private Document doc;
    private String[] siteNames, delim;
    private double[] scores;
    private double max;
    private String names;
    private String terms;
    private String header;
    private BufferedReader reader;
    private ArrayList<String> termBank;
    private ArrayList<Integer>vectorA,vectorB;
    private FrequencyTable[] hashAr;
    private FrequencyTable hash;
    private FrequencyTable hashIn;
    private int initVal,siteNumber;
    private CosineSimilarity cos;

    LoadPages()throws IOException{
        String fileName = "pages.txt";
        termBank = new ArrayList<String>();
        vectorA = new ArrayList<Integer>();
        vectorB = new ArrayList<Integer>();
        reader = new BufferedReader(new FileReader(fileName));
        names = "";
        terms = "";
        cos = new CosineSimilarity();
        hash = new FrequencyTable();
        hashIn = new FrequencyTable();
        initVal = 1;
        max = 0;
    }

    void pageLoader()throws IOException{

        while((names = reader.readLine()) != null) {
            siteNames = names.split(",");
        }
        reader.close();
        hashAr = new FrequencyTable[siteNames.length];
        scores = new double[siteNames.length];

        //Lines 64 - 122 is responsible for cache creation and management.

        for (int i = 0; i < siteNames.length ; i++) {
            String urlKey = siteNames[i].replaceAll("[^a-zA-Z ]", "");
            String cachePathLastModified = "C:/Users/JScol/IdeaProjects/2019_DS_CS365_A1/cache/last_modified/" + urlKey + ".txt";
            String cachePathContent = "C:/Users/JScol/IdeaProjects/2019_DS_CS365_A1/cache/content/" + urlKey + ".txt";
            File file = new File(cachePathLastModified);
            BufferedWriter writer;
            if(!file.exists()){

                file.createNewFile();
                FileWriter fw = new FileWriter(file);
                writer = new BufferedWriter(fw);
                Connection.Response resp = Jsoup.connect(siteNames[i]).method(Connection.Method.POST).followRedirects(false).execute();
                header = resp.header("last-modified");
                writer.write(header);
                writer.close();
            }

            if(file.exists()){
                reader = new BufferedReader(new FileReader(cachePathLastModified));
                header = reader.readLine();
                reader.close();
            }

            file = new File(cachePathContent);
            Connection.Response resp = Jsoup.connect(siteNames[i]).method(Connection.Method.POST).followRedirects(false).execute();
            String header2 = resp.header("last-modified");
            if(!file.exists() || header.compareTo(header2) != 0 ){

                if(header.compareTo(header2) != 0){

                    file = new File(cachePathLastModified);
                    file.createNewFile();
                    FileWriter fw = new FileWriter(file);
                    writer = new BufferedWriter(fw);
                    writer.write(header2);
                    writer.close();
                    file = new File(cachePathContent);
                }
                file.createNewFile();
                FileWriter fw = new FileWriter(file);
                writer = new BufferedWriter(fw);
                doc = Jsoup.connect(siteNames[i]).get();
                System.out.println("connected to: " + doc.title());
                terms = doc.text().replaceAll("[^a-zA-Z ]"," ");
                terms = terms.replaceAll("\\n{2,}", "");
                terms = terms.replaceAll("\\r{2,}", "");
                terms = terms.replaceAll("\\s{2,}", " ");
                writer.write(terms);
                writer.close();
            }
            else{
                reader = new BufferedReader(new FileReader(cachePathContent));
                terms = reader.readLine();
                reader.close();
                terms = terms.replaceAll("\\n{2,}", "");
                terms = terms.replaceAll("\\r{2,}", "");
                terms = terms.replaceAll("\\s{2,}", " ");
            }

            delim = terms.split(" ");
            for (String s : delim) { // this for loop puts terms from 10 web pages either form cache content or direct doc connect and places them in term bank and frequency table.
                if (!termBank.contains(s)) { // term bank only gets current term if term is not present in array list.
                    termBank.add(s);
                }
                hash.put(s, initVal);
            }
            hashAr[i] = hash;
            hash = new FrequencyTable();
        }
    }
    void loadUserSite(String site)throws Exception{

        doc = Jsoup.connect(site).get();
        System.out.println("connected to: " + doc.title());
        terms = doc.text().replaceAll("[^a-zA-Z ]"," ");
        terms = terms.replaceAll("\\n{2,}", "");
        terms = terms.replaceAll("\\r{2,}", "");
        terms = terms.replaceAll("\\s{2,}", " ");
        delim = terms.split(" ");

        for (String s : delim) { // this loop adds terms from users input and via jsoup  doc connect, adds terms to the unique termbank and puts terms in the frequency table
            if (!termBank.contains(s)){
                termBank.add(s);
            }
            hashIn.put(s, initVal);
        }
        calcCosineSimScore();
    }


    private void calcCosineSimScore(){ // method that interacts with CosineSimilarity class to calculate cosine similarity scores.

        for (String value : termBank) {
            vectorB.add(hashIn.get(value)); // takes every term in termBank and checks to see if the word is in the frequency table.
                                            // If it is it gets its frequency value and adds it to vectorB. If the word is not there a value of zero is added to vectorB
        }
        for(int i = 0; i < hashAr.length; i++){ // takes every term in termBank and checks to see if the word is in the frequency table.
                                                // If it is it gets its frequency value and adds it to vectorA. If the word is not there a value of zero is added to vectorA
            for (String s : termBank) {
                vectorA.add(hashAr[i].get(s));
            }
            scores[i]= CosineSimilarity.cosineSimilarity(vectorA,vectorB); // both vectors are passed to cosineSimilarity method to be scored.
                                                                           // Each score for each table is assigned to the scores array.
            vectorA = new ArrayList<>();
        }

        for(int i = 0; i < scores.length ; i++) { // calculates the highest score
            if(scores[i] > max) {
                max = scores[i];
                siteNumber = i;
            }
        }


    }
    String getCosineSimScore(){
        return  siteNames[siteNumber]+ "\n it has cosine similarity score of: " + Double.toString(max); // returns the maximum cosine score.
    }
    String[] getAllCosScores(){ // returns a list of all cosine similarity scores
        String[] scoreList = new String[scores.length];
            for (int i = 0; i < scores.length; i++) {
                scoreList[i] = Double.toString(scores[i]);
            }
            return scoreList;
    }
}
