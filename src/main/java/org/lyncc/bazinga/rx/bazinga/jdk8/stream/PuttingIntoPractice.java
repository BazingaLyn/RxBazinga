package org.lyncc.bazinga.rx.bazinga.jdk8.stream;

import java.util.Arrays;
import java.util.List;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

/**
 * stream 练习
 *
 * @author liguolin
 * @create 2018-01-30 13:41
 **/
public class PuttingIntoPractice {


    public static void main(String[] args) {

        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );


        // Query 1: Find all transactions from year 2011 and sort them by value (small to high).
        List<Transaction> tr2011 = transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(comparing(Transaction::getValue))
                .collect(toList());
        System.out.println(tr2011);

        // Query 2: What are all the unique cities where the traders work?
        List<String> cities =
                transactions.stream()
                        .map(transaction -> transaction.getTrader().getCity())
                        .distinct()
                        .collect(toList());
        System.out.println(cities);

        // Query 3: Find all traders from Cambridge and sort them by name.

        List<Trader> traders =
                transactions.stream()
                        .map(Transaction::getTrader)
                        .filter(trader -> trader.getCity().equals("Cambridge"))
                        .distinct()
                        .sorted(comparing(Trader::getName))
                        .collect(toList());
        System.out.println(traders);

        String traderStr =
                transactions.stream()
                        .map(transaction -> transaction.getTrader().getName())
                        .distinct()
                        .sorted()
                        .reduce("", (n1, n2) -> n1 + n2);
        System.out.println(traderStr);

        // Query 5: Are there any trader based in Milan?

        boolean milanBased =
                transactions.stream()
                        .anyMatch(transaction -> transaction.getTrader()
                                .getCity()
                                .equals("Milan")
                        );
        System.out.println(milanBased);


        // Query 6: Update all transactions so that the traders from Milan are set to Cambridge.
        transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals("Milan"))
                .forEach(trader -> trader.setCity("Cambridge"));
        System.out.println(transactions);


        // Query 7: What's the highest value in all the transactions?
        int highestValue =
                transactions.stream()
                        .map(Transaction::getValue)
                        .reduce(0, Integer::max);
        System.out.println(highestValue);
    }


    public static class Trader{

        private String name;
        private String city;

        public Trader(String n, String c){
            this.name = n;
            this.city = c;
        }

        public String getName(){
            return this.name;
        }

        public String getCity(){
            return this.city;
        }

        public void setCity(String newCity){
            this.city = newCity;
        }

        public String toString(){
            return "Trader:"+this.name + " in " + this.city;
        }
    }


    public static class Transaction{

        private Trader trader;
        private int year;
        private int value;

        public Transaction(Trader trader, int year, int value)
        {
            this.trader = trader;
            this.year = year;
            this.value = value;
        }

        public Trader getTrader(){
            return this.trader;
        }

        public int getYear(){
            return this.year;
        }

        public int getValue(){
            return this.value;
        }

        public String toString(){
            return "{" + this.trader + ", " +
                    "year: "+this.year+", " +
                    "value:" + this.value +"}";
        }
    }
}
