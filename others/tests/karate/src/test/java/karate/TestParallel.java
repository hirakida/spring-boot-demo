package karate;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.intuit.karate.KarateOptions;
import com.intuit.karate.Results;
import com.intuit.karate.Runner;

@KarateOptions(features = "classpath:karate/features", tags = "~@ignore")
public class TestParallel {
    @Test
    public void testParallel() {
        Results results = Runner.parallel(2);
        assertEquals(0, results.getFailCount(), results.getErrorMessages());
    }
}
