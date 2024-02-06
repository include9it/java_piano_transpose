package io.include9it.java_piano_transpose.service;

import com.google.gson.JsonArray;
import org.springframework.stereotype.Service;

@Service
public class PianoTranspose {

    public JsonArray transposeNotes(JsonArray inputArray, int semitones) {
        JsonArray outputArray = new JsonArray();

        inputArray.forEach(element -> {
            JsonArray note = element.getAsJsonArray();
            int octave = note.get(0).getAsInt();
            int noteNumber = note.get(1).getAsInt();

            int[] transposePair = transpose(octave, noteNumber, semitones);

            JsonArray transposedNoteArray = new JsonArray();
            transposedNoteArray.add(transposePair[0]);
            transposedNoteArray.add(transposePair[1]);
            outputArray.add(transposedNoteArray);
        });

        return outputArray;
    }

    private int[] transpose(int octave, int noteNumber, int semitones) {
        int transposeRatio = octave * 12 + noteNumber + semitones;

        int transposedOctave = transposeRatio / 12;
        int transposedNote = transposeRatio % 12;

        if (!hasInKeyboardRange(transposeRatio)) {
            throw new IllegalStateException("Transposed note is out of keyboard range!");
        }

        return transposedNote <= 0 ?
                new int[]{transposedOctave - 1, 12 + transposedNote} :
                new int[]{transposedOctave, transposedNote};
    }

    private boolean hasInKeyboardRange(int transposeRatio) {
        return transposeRatio >= -26 && transposeRatio <= 61;
    }
}
