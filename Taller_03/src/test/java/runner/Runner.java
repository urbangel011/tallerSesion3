package runner;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
public class Runner {
    @Before
    public void beforeHook(){
        System.out.println("HOOK Before de cucumber");
    }

    @After
    public void afterHook(){
        System.out.println("HOOK After de cucumber");
    }


}

