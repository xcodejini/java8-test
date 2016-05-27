package com.piolink.test.stream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Predicate;

import com.piolink.test.stream.model.AlarmHistory;
import com.piolink.test.stream.model.Cswitch;
import com.piolink.test.stream.model.Transaction;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author jini
 *
 */
@Slf4j
public class StreamTester {

    public static List<Transaction> transactionList = new ArrayList<>();
    public static List<AlarmHistory> alarmHistoryList = new ArrayList<>();
    public static List<Cswitch> cswitchList = new ArrayList<>();
    
    public StreamTester() {
        initializeTestData();
    }

    public void stream_filter_collect_groupingBy() {

        Map<Currency, List<Transaction>> tranactionsByCurrencies = 
                transactionList.stream()
                .filter((Transaction t) -> !t.isHeavyTransaction())
                .collect(groupingBy(Transaction::getCurrency));
        
        for (Currency currency: tranactionsByCurrencies.keySet()) {
            log.info(">> no heavy currency key: {}", currency);
        }
        
       tranactionsByCurrencies = 
                transactionList.stream()
                .filter((Transaction t) -> t.getPrice() > 1500)
                .collect(groupingBy(Transaction::getCurrency));
        
       for (Currency currency: tranactionsByCurrencies.keySet()) {
           log.info(">> heavy currency key: {}", currency);
       }
    }
    
    public void predicate() {
        List<Transaction> filterTransactions = this.filterTransaction(Transaction::isHeavyTransaction);
        
        for (Transaction transaction : filterTransactions) {
            log.info(">> heavy price: {}", transaction.getPrice());
        }        
    }
    
    private List<Transaction> filterTransaction(Predicate<Transaction> p) {
        List<Transaction> resultList = new ArrayList<>();
        for (Transaction transaction : transactionList) {
            if (p.test(transaction)) {
                resultList.add(transaction);
            }
        }
        return resultList;
    }    

    public void stream_map_distinct_collect_toList() {
        List<Long> referIdList = alarmHistoryList.stream()
                .map(AlarmHistory::getReferId)
                .distinct()
                .collect(toList());
        
        for (Long referId : referIdList) {
            log.info(">> distinct referId: {}", referId);
        }
    }
    
    public void stream_map_collect_toList() {
        List<Long> referIdList = alarmHistoryList.stream()
                .map(AlarmHistory::getReferId)
                .collect(toList());
        
        for (Long referId : referIdList) {
            log.info(">> all referId: {}", referId);
        }
    }
    
    public void stream_collect_toMap() {
        Map<Long, Cswitch> cswitchById = cswitchList.stream()
                .collect(toMap(Cswitch::getId, cswitch -> cswitch));
        
        for (long id : cswitchById.keySet()) {
            log.info(">> id: {}", cswitchById.get(id).getId());
        }
    }
    
    public static void main(String[] args) {
        StreamTester tester = new StreamTester();
        tester.stream_filter_collect_groupingBy();
        tester.predicate();
        tester.stream_map_distinct_collect_toList();
        tester.stream_map_collect_toList();
        tester.stream_collect_toMap();
    }
    
    private static void initializeTestData() {
        Currency currency1 = Currency.getInstance(Locale.KOREA);
        Currency currency2 = Currency.getInstance(Locale.KOREA);

        Currency currency3 = Currency.getInstance(Locale.CHINA);
        Currency currency4 = Currency.getInstance(Locale.CHINA);
        Currency currency5 = Currency.getInstance(Locale.CHINA);

        Currency currency6 = Currency.getInstance(Locale.US);
        
        Transaction transaction1 = new Transaction(currency1, 1500);
        Transaction transaction2 = new Transaction(currency2, 1501);
        Transaction transaction3 = new Transaction(currency3, 1502);
        Transaction transaction4 = new Transaction(currency4, 1503);
        Transaction transaction5 = new Transaction(currency5, 1504);
        Transaction transaction6 = new Transaction(currency6, 1505);
        
        transactionList.add(transaction1);
        transactionList.add(transaction2);
        transactionList.add(transaction3);
        transactionList.add(transaction4);
        transactionList.add(transaction5);
        transactionList.add(transaction6);
        
        AlarmHistory alarmHistory1 = new AlarmHistory();
        alarmHistory1.setReferId(new Long(1L));
        AlarmHistory alarmHistory2 = new AlarmHistory();
        alarmHistory2.setReferId(new Long(1L));
        AlarmHistory alarmHistory3 = new AlarmHistory();
        alarmHistory3.setReferId(new Long(1L));
        AlarmHistory alarmHistory4 = new AlarmHistory();
        alarmHistory4.setReferId(new Long(4L));
        
        alarmHistoryList.add(alarmHistory1);
        alarmHistoryList.add(alarmHistory2);
        alarmHistoryList.add(alarmHistory3);
        alarmHistoryList.add(alarmHistory4);
        
        Cswitch cswitch1 = new Cswitch();
        cswitch1.setId(new Long(1L));
        Cswitch cswitch2 = new Cswitch();
        cswitch2.setId(new Long(2L));
        Cswitch cswitch3 = new Cswitch();
        cswitch3.setId(new Long(3L));
        Cswitch cswitch4 = new Cswitch();
        cswitch4.setId(new Long(4L));
        Cswitch cswitch5 = new Cswitch();
        cswitch5.setId(new Long(1L));
        
        cswitchList.add(cswitch1);
        cswitchList.add(cswitch2);
        cswitchList.add(cswitch3);
        cswitchList.add(cswitch4);
    }    
    
}
