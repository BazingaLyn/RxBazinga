package org.lyncc.bazinga.rx.bazinga.jmh;

import org.joda.time.DateTime;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

/**
 * @author liguolin
 * @create 2018-06-15 13:39
 **/
@Measurement(iterations = 3,time = 1,timeUnit = TimeUnit.SECONDS)
@Warmup(iterations = 3,time = 1,timeUnit = TimeUnit.SECONDS)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@BenchmarkMode(Mode.AverageTime)
@State(Scope.Thread)
@Fork(1)
public class DateBenchMark {


    @Benchmark
    public Calendar runCalendar(){
        return Calendar.getInstance();
    }

    @Benchmark
    public DateTime runJoda(){
        return new DateTime();
    }


    @Benchmark
    public long runSystem(){
        return System.currentTimeMillis();
    }

    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder().include(DateBenchMark.class.getSimpleName()).build();
        new Runner(opt).run();
    }
}
