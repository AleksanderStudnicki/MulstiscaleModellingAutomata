package app.studnicki.mmautomata;

import org.testng.annotations.Test;

import java.util.Arrays;

import static org.testng.Assert.*;

@Test
public class ThreadTesterTest {

  public void outputsFromMultipleAndSingleShouldBeTheSame() {
    //given
    int[] outFromMultiple;
    int[] outFromSingle;

    //when
    outFromMultiple = ThreadTester.multipleThreadsArrayOperations();
    outFromSingle = ThreadTester.singleThreadArrayOperations();

    //then
    assertTrue(Arrays.equals(outFromSingle, outFromMultiple));
  }

}
