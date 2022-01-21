package test;

import ch.bbw.zork.PointOfInterest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PointOfInterestTest {
    PointOfInterest pointOfInterest;

    @Test
    public void testPointOfInterest() {
        String testDescription = "testDescription";
        String testLocation    = "testLocation";
        pointOfInterest = new PointOfInterest(testDescription, testLocation);
        assertEquals(pointOfInterest.getDescription(), testDescription);
        assertEquals(pointOfInterest.getLocation(), testLocation);
    }
}
