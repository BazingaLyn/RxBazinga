package org.lyncc.bazinga.rx.bazinga.guava.table;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import org.junit.Before;
import org.junit.Test;


/**
 * @author liguolin
 * @create 2018-06-19 16:12
 **/
public class GuavaTableTest {

    public static Table<String, String, Integer> universityCourseSeatTable
            = HashBasedTable.create();

    @Before
    public void givenTable_whenGet_returnsSuccessfully() {

        universityCourseSeatTable.put("Mumbai", "Chemical", 120);
        universityCourseSeatTable.put("Mumbai", "IT", 60);
        universityCourseSeatTable.put("Harvard", "Electrical", 60);
        universityCourseSeatTable.put("Harvard", "IT", 120);

    }


    @Test
    public void test1(){
        int seatCount
                = universityCourseSeatTable.get("Mumbai", "IT");
        Integer seatCountForNoEntry
                = universityCourseSeatTable.get("Oxford", "IT");

        System.out.println(seatCount);
        System.out.println(seatCountForNoEntry);
        System.out.println(universityCourseSeatTable.column("Chemical"));
        System.out.println(universityCourseSeatTable.row("Mumbai"));
    }


}
