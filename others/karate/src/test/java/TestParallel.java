import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.intuit.karate.KarateOptions;
import com.intuit.karate.Results;
import com.intuit.karate.Runner;

@KarateOptions(tags = "~@ignore")
public class TestParallel {

    @Test
    public void testParallel() {
        Results results = Runner.parallel(getClass(), 2, "target/surefire-reports");
        assertEquals(results.getErrorMessages(), 0, results.getFailCount());
    }
}
