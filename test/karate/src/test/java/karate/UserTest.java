package karate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.intuit.karate.Results;
import com.intuit.karate.Runner;

class UserTest {
    @Test
    void parallel() {
        final Results results = Runner.path("classpath:karate/user.feature")
                                      .parallel(1);
        assertEquals(0, results.getFailCount(), results.getErrorMessages());
    }
}
