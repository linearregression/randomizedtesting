package com.carrotsearch.examples.randomizedrunner;

import org.junit.Assume;
import org.junit.Test;

import com.carrotsearch.randomizedtesting.*;
import com.carrotsearch.randomizedtesting.annotations.Nightly;

/**
 * Running tests on a developer machine is often a pain, in particular when
 * certain tests are long and repetitive. If you have a dedicated continuous
 * integration environment like <a href="http://jenkins-ci.org/">Jenkins</a> or
 * <a href="http://www.atlassian.com/software/jira">Attlasian Bamboo</a> then it
 * is nice to be able to "stress" your tests a bit more during nightly or server
 * runs compared to normal developer runs.
 * 
 * <p>{@link RandomizedRunner} has a notion of a {@link Nightly} test and a few methods
 * for "scaling" the execution depending if is in nightly mode or not. In the simplest
 * case (see {@link #nightlyOnly()} a test case is run in nightly mode and ignored in 
 * normal runs. This can be done by annotating a test case or suite using {@link Nightly}
 * or by assuming nightly mode (using the result of {@link RandomizedContext#isNightly()}
 * as the condition). There is a difference between these two methods and it is left as an
 * exercise for the reader to discover what this difference is.
 * 
 * <p>For tests whose runtime depends on the amount of input data or other varying complexity,
 * one can use {@link RandomizedTest#scaledRandomIntBetween(int, int)} method or the current
 * {@link RandomizedTest#multiplier()}. These methods adjust to the nightly mode picking
 * larger values than in daily mode (see javadocs for details).
 */
public class Test011NightlyTests extends RandomizedTest {
  @Nightly
  @Test
  public void nightlyOnly() throws Exception {
    // Do nothing, but pretend we're long and slow.
  }

  @Test
  public void nightlyOnlyWithAssume() throws Exception {
    Assume.assumeTrue(isNightly());
    // Do nothing, but pretend we're long and slow.
  }

  @Test
  public void scaling() throws Exception {
    System.out.println("Mode: " + (isNightly() ? "nightly" : "daily"));
    System.out.println("Multiplier: " + multiplier());
    for (int i = 0; i < 10; i++) {
      System.out.println("random scaled int = " + scaledRandomIntBetween(0, 100));
    }
  }
}
