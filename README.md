Installation and running
---
<h4>Tools</h4>
- JDK 17.0.7 <br/>
- Gradle 7.6.2 <br/>

Task
---
Write an app to perform a transposition. It should accept 2 parameters: an input
JSON file with a collection of notes and a number of semitones to transpose to (can be negative). It
should produce a JSON file with the transposed collection of notes. If at least one of the resulting notes
falls out of the keyboard range, your script should return an error message.

---
Request example
---
Piano transpasition Curl example:
```
curl -X POST -H "Content-Type: application/json" \
http://localhost:8080/api/transpose?semitones=-3 \
-d '[[2,1],[2,6],[2,1],[2,8],[2,1],[2,9],[2,1],[2,6],[2,1],[2,8],[2,1],[2,9],[2,1],[2,11],[2,1],[2,8],[2,1],[2,9],[2,1],[2,11],[2,1],[3,1],[2,1],[2,9],[2,1],[2,11],[2,1],[3,1],[2,1],[3,2],[2,1],[2,11],[2,1],[3,1],[2,1],[2,9],[2,1],[2,11],[2,1],[2,8],[2,1],[2,9],[2,1],[2,6],[2,1],[2,8],[2,1],[2,5],[2,1],[2,6],[2,1],[2,1],[2,1],[2,2],[2,1],[1,11],[2,1],[2,1],[2,1],[1,9],[2,1],[1,11],[2,1],[1,8],[2,1],[1,9],[2,1],[1,6],[2,1],[1,11],[2,1],[1,8],[2,1],[1,9],[2,1],[1,6],[2,1],[1,8],[2,1],[1,5],[2,1],[1,6]]'
```