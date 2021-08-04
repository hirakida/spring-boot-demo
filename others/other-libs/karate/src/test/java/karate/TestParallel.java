package karate;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.intuit.karate.Results;
import com.intuit.karate.Runner;

class TestParallel {
    @Test
    void testParallel() {
        Results results = Runner.path("classpath:karate").tags("~@ignore").parallel(2);
        assertEquals(0, results.getFailCount(), results.getErrorMessages());
    }
}
