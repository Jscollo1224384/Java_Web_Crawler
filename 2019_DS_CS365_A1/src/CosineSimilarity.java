/*
Name: Joseph M. Scollo
School: Suny Oswego
Course: CSC-365 Data Structures

Class: CosineSimilarity
About Class: This class contains 1 method. It takes 2 parameters of type ArrayList<Integer>.
It takes the integer values in both lists and generates a score using a Cosine Similarity formula.

Note: This code is not mine. It was found on stack overflow.
*/



import java.util.*;
class CosineSimilarity{


static double cosineSimilarity(ArrayList<Integer> vectorA, ArrayList<Integer> vectorB){

    double dotProduct = 0.0;
    double normA = 0.0;
    double normB = 0.0;

    for (int i = 0; i < vectorA.size(); i++) {
        dotProduct += vectorA.get(i) * vectorB.get(i);
        normA += Math.pow(vectorA.get(i), 2);
        normB += Math.pow(vectorB.get(i), 2);
    }
    return dotProduct / (Math.sqrt(normA) * Math.sqrt(normB));
  }
}
 