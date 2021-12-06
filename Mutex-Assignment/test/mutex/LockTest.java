/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mutex;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.locks.ReentrantLock;
import junit.framework.TestCase;
import static junit.framework.TestCase.assertEquals;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 *
 * @author admin
 */
@RunWith(Parameterized.class)
public class LockTest extends TestCase {
  private int THREADS;
  private int PER_THREAD;
  private int COUNT;
  private int counter;
  private Thread[] thread;
  long[] timeOfExecution;
  
  ReentrantLock instance = new ReentrantLock();
  
  public LockTest(int threads, int count) {
    super();
    this.THREADS = threads;
    this.COUNT = count;
    this.PER_THREAD = COUNT/THREADS;
  }
  
  @Before
  public void initialize(){
      counter = 0;
      thread = new Thread[THREADS];
      timeOfExecution = new long[THREADS];
  }
  
  @Parameterized.Parameters
  public static Collection input(){
      return Arrays.asList(new Object[][]{{1,5120},{2,5120},{4,5120},{6,5100},{8,5120},{10,5100},{12,5100},{14,5250},{16,5120},
          {1,10240},{2,10240},{4,10240},{6,10200},{8,10240},{10,10200},{12,10200},{14,10500},{16,10240},
      {1,15360},{2,15360},{4,15360},{6,15300},{8,15360},{10,15300},{12,15300},{14,15750},{16,15360}});
  } 
  
  @org.junit.Test
  public void testParallel() throws Exception {
    ThreadID.reset();
    
    for (int i = 0; i < THREADS; i++) {
      thread[i] = new MyThread();
    }
    
    for (int i = 0; i < THREADS; i++) {
      timeOfExecution[i] = System.nanoTime();
      thread[i].start();
    }
    for (int i = 0; i < THREADS; i++) {
      thread[i].join();
      timeOfExecution[i] = System.nanoTime() - timeOfExecution[i];
    }
    
    
    System.out.println("Time of execution for JavaLock for " + THREADS +  
            " per thread = " + meanTimeOfExecution(timeOfExecution, THREADS));
    
    
    FileWriter fw = new FileWriter("JavaLockOutput.txt", true);
    BufferedWriter bw = new BufferedWriter(fw);
    bw.append(THREADS + ":" + String.valueOf(meanTimeOfExecution(timeOfExecution, THREADS)));
    bw.append("\n");
    bw.flush();
    bw.close();
    
    assertEquals(COUNT, counter);
  }
  
  public long meanTimeOfExecution(long[] timeOfExecution, int THREADS){
      long sum = 0;
      for(int i = 0; i< THREADS; i++)
          sum += timeOfExecution[i];
      return (long)sum/THREADS;
  }
  
  
  class MyThread extends Thread {
    public void run() {
      for (int i = 0; i < PER_THREAD; i++) {
        instance.lock();
        try {
          counter = counter + 1;
        } finally {
          instance.unlock();
        }
      }
    }
  }
    
}
