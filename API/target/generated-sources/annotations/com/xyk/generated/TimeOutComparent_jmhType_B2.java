package com.xyk.generated;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
public class TimeOutComparent_jmhType_B2 extends TimeOutComparent_jmhType_B1 {
    public volatile int setupTrialMutex;
    public volatile int tearTrialMutex;
    public final static AtomicIntegerFieldUpdater<TimeOutComparent_jmhType_B2> setupTrialMutexUpdater = AtomicIntegerFieldUpdater.newUpdater(TimeOutComparent_jmhType_B2.class, "setupTrialMutex");
    public final static AtomicIntegerFieldUpdater<TimeOutComparent_jmhType_B2> tearTrialMutexUpdater = AtomicIntegerFieldUpdater.newUpdater(TimeOutComparent_jmhType_B2.class, "tearTrialMutex");

    public volatile int setupIterationMutex;
    public volatile int tearIterationMutex;
    public final static AtomicIntegerFieldUpdater<TimeOutComparent_jmhType_B2> setupIterationMutexUpdater = AtomicIntegerFieldUpdater.newUpdater(TimeOutComparent_jmhType_B2.class, "setupIterationMutex");
    public final static AtomicIntegerFieldUpdater<TimeOutComparent_jmhType_B2> tearIterationMutexUpdater = AtomicIntegerFieldUpdater.newUpdater(TimeOutComparent_jmhType_B2.class, "tearIterationMutex");

    public volatile int setupInvocationMutex;
    public volatile int tearInvocationMutex;
    public final static AtomicIntegerFieldUpdater<TimeOutComparent_jmhType_B2> setupInvocationMutexUpdater = AtomicIntegerFieldUpdater.newUpdater(TimeOutComparent_jmhType_B2.class, "setupInvocationMutex");
    public final static AtomicIntegerFieldUpdater<TimeOutComparent_jmhType_B2> tearInvocationMutexUpdater = AtomicIntegerFieldUpdater.newUpdater(TimeOutComparent_jmhType_B2.class, "tearInvocationMutex");

}
