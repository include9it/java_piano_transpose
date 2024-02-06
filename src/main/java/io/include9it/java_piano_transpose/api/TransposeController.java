package io.include9it.java_piano_transpose.api;

import com.google.gson.JsonParser;
import io.include9it.java_piano_transpose.service.PianoTranspose;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transpose")
public class TransposeController {

    private final PianoTranspose service;

    @PostMapping
    public String transposePiano(@RequestBody String pianoNotes, @RequestParam Integer semitones) {
        return service.transposeNotes(JsonParser.parseString(pianoNotes).getAsJsonArray(), semitones).toString();
    }
}
