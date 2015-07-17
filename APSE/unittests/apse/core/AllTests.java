package apse.core;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
/**
 * Run this test suite to perform all of the APSE.core unit tests.
 * @author Matt Gorelik
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ ActionLayerTest.class, ActorManagerTest.class, ActorTest.class, LayerManagerTest.class, ModelDelegateTest.class })
public class AllTests {

}
