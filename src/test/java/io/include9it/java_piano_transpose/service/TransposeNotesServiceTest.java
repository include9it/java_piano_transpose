package io.include9it.java_piano_transpose.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class TransposeNotesServiceTest {

    @Autowired
    private PianoTranspose service;

    @Test
    void successTransposeSampleInputTest() {
        String inputJson = """
                [[2,1],[2,6],[2,1],[2,8],[2,1],[2,9],[2,1],[2,6],[2,1],[2,8],[2,1],[2,9],[2,1],[2,11],[2,1],[2,8],[2,1],[2,9],[2,1],[2,
                11],[2,1],[3,1],[2,1],[2,9],[2,1],[2,11],[2,1],[3,1],[2,1],[3,2],[2,1],[2,11],[2,1],[3,1],[2,1],[2,9],[2,1],[2,11],[2,
                1],[2,8],[2,1],[2,9],[2,1],[2,6],[2,1],[2,8],[2,1],[2,5],[2,1],[2,6],[2,1],[2,1],[2,1],[2,2],[2,1],[1,11],[2,1],[2,1],[
                2,1],[1,9],[2,1],[1,11],[2,1],[1,8],[2,1],[1,9],[2,1],[1,6],[2,1],[1,11],[2,1],[1,8],[2,1],[1,9],[2,1],[1,6],[2,1],[1,
                8],[2,1],[1,5],[2,1],[1,6]]""";

        JsonArray inputArray = JsonParser.parseString(inputJson).getAsJsonArray();

        JsonArray outputArray = service.transposeNotes(inputArray, -3);

        String expectedJson = """
                [[1,10],[2,3],[1,10],[2,5],[1,10],[2,6],[1,10],[2,3],[1,10],[2,5],[1,10],[2,6],[1,10],[2,8],[1,10],[2,5],[1,10],[2,
                6],[1,10],[2,8],[1,10],[2,10],[1,10],[2,6],[1,10],[2,8],[1,10],[2,10],[1,10],[2,11],[1,10],[2,8],[1,10],[2,10],[1,
                10],[2,6],[1,10],[2,8],[1,10],[2,5],[1,10],[2,6],[1,10],[2,3],[1,10],[2,5],[1,10],[2,2],[1,10],[2,3],[1,10],[1,10],
                [1,10],[1,11],[1,10],[1,8],[1,10],[1,10],[1,10],[1,6],[1,10],[1,8],[1,10],[1,5],[1,10],[1,6],[1,10],[1,3],[1,10],[1
                ,8],[1,10],[1,5],[1,10],[1,6],[1,10],[1,3],[1,10],[1,5],[1,10],[1,2],[1,10],[1,3]]""";
        JsonArray expectedArray = JsonParser.parseString(expectedJson).getAsJsonArray();
        assertEquals(expectedArray, outputArray);
    }

    @Test
    void successTransposePianoPositiveSemitonesTest() {
        String inputJson = "[[2,1],[2,6],[2,1],[2,8],[2,1],[2,9],[2,1],[2,6],[2,1],[2,8]]";
        JsonArray inputArray = JsonParser.parseString(inputJson).getAsJsonArray();

        JsonArray outputArray = service.transposeNotes(inputArray, 3);

        String expectedJson = "[[2,4],[2,9],[2,4],[2,11],[2,4],[2,12],[2,4],[2,9],[2,4],[2,11]]";
        JsonArray expectedArray = JsonParser.parseString(expectedJson).getAsJsonArray();
        assertEquals(expectedArray, outputArray);
    }

    @Test
    void errorTransposeInOutOfKeyboardRangeTest() {
        String inputJson = "[[2,1],[2,6],[2,1],[2,8],[2,1],[2,9],[2,1],[2,6],[2,1],[2,8]]";
        JsonArray inputArray = JsonParser.parseString(inputJson).getAsJsonArray();

        assertThrows(IllegalStateException.class, () -> service.transposeNotes(inputArray, -100));
    }

    @Test
    void errorTransposePianoNegativeSemitonesTest() {
        String inputJson = "[[2,6],[2,1],[2,8],[2,1],[-3,10],[2,9],[2,1],[2,6],[2,1],[2,8]]";
        JsonArray inputArray = JsonParser.parseString(inputJson).getAsJsonArray();

        assertThrows(IllegalStateException.class, () -> service.transposeNotes(inputArray, -3));
    }

    @Test
    void errorTransposePianoPositiveSemitonesTest() {
        String inputJson = "[[5,1],[2,6],[2,1],[2,8],[2,1],[2,9],[2,1],[2,6],[2,1],[2,8]]";
        JsonArray inputArray = JsonParser.parseString(inputJson).getAsJsonArray();

        assertThrows(IllegalStateException.class, () -> service.transposeNotes(inputArray, 3));
    }
}

