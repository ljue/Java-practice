/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bayes;

import static java.lang.Math.log;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.TreeMap;

/**
 *
 * @author Людмила
 */
public class Bayes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here      
        training();
        
        System.out.println("Какая сегодня погода? : "+bayesClassification("Какая сегодня погода?"));
        System.out.println("Вы выиграли автомобиль! : "+bayesClassification("Вы выиграли автомобиль"));
                
    }
    
    public static void training() {
        
        dictT = new HashMap<String, Integer>();
        dictF = new HashMap<String, Integer>();
        
        trainT = new ArrayList<String>();
        trainT.add("Вы выиграли!");
        trainT.add("Спешите купить!");
        trainT.add("Ваш перевод на сумму");
        trainT.add("Как зарабатывать в кризис");
        trainT.add("Работа на дому");
        trainT.add("Вам подарок");
        trainT.add("Ваша заявка одобрена");
        trainT.add("Я сам не поверил в это");

        
        trainF = new ArrayList<String>();
        trainF.add("Не забудь зайти в магазин купить хлеб");
        trainF.add("В Эрмитаже новая выставка");
        trainF.add("Пойдем гулять!");
        trainF.add("Погода прекрасная");
        trainF.add("У меня нет времени");
        trainF.add("Сдать задание до среды");
        trainF.add("Как твои дела?");     
        
        for (String phrase : trainT) { // проводим обучение, считаем сколько слов
             StringTokenizer st1 = new StringTokenizer(phrase, " ,.!&?+="); 
             while(st1.hasMoreTokens()){
                 String token = st1.nextToken().toLowerCase();
                 Integer temp = dictT.containsKey(token) ? dictT.get(token) : 0;
                 dictT.put(token,temp+1);
                 SumWordsT++;
             }
        }
        for (String phrase : trainF) {
             StringTokenizer st2 = new StringTokenizer(phrase, " ,.!&?+="); 
             while(st2.hasMoreTokens()){
                 String token = st2.nextToken().toLowerCase();
                 Integer temp = dictF.containsKey(token) ? dictF.get(token) : 0;
                 dictF.put(token,temp+1);
                 SumWordsF++;
             }
        }
        
        power.addAll(dictT.keySet());
        power.addAll(dictF.keySet());
        
    }
    
    public static String bayesClassification(String myPhrase) {
        
        //double sds = trainT.size()/(trainT.size()+trainF.size())
        double formulaT = log((double)trainT.size()/(trainT.size()+trainF.size()));
        double formulaF = log((double)trainF.size()/(trainT.size()+trainF.size()));
        
        StringTokenizer st3 = new StringTokenizer(myPhrase, " ,.!&?+="); 
        while(st3.hasMoreTokens()){
            String token = st3.nextToken().toLowerCase();
            Integer tempT = dictT.containsKey(token) ? dictT.get(token) : 0;
            Integer tempF = dictF.containsKey(token) ? dictF.get(token) : 0;
            formulaT+= log((double)(tempT+1)/(SumWordsT+power.size()));
            formulaF+= log((double)(tempF+1)/(SumWordsF+power.size()));                               
        }
        
        String result = formulaT>formulaF ? "SPAM" : "Not a spam";
        return result;
    }
    
    private static HashMap<String, Integer> dictT; // словарь - кол-во повторений слов в спаме
    private static HashMap<String, Integer> dictF; // словарь - кол-во повторений слов в не спаме
    private static int SumWordsT;
    private static int SumWordsF;
    private static HashSet power = new HashSet(); // набор уникальных слов в выборках
    private static ArrayList<String> trainT; // обучающий набор спама
    private static ArrayList<String> trainF; // обучающий набор не спама
    
}
