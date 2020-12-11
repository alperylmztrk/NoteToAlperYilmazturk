package ise308.yilmazturk.alper.notetoalperyilmazturk

import android.content.Context
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONTokener
import java.io.*
import java.lang.StringBuilder
import kotlin.jvm.Throws

class JSONSerializer(private val filename: String, private val context: Context) {
    @Throws(IOException::class, JSONException::class)
    fun save(noteList: List<Note>) {
        // Make an array in JSON format
        val jsonArray = JSONArray()
        // Load it with the notes
        for (note in noteList) {
            jsonArray.put(note.convertTOJSON())
        }
        // Write it to the private disk space of our app
        var writer: Writer? = null
        try {
            val outFile = context.openFileOutput(filename, Context.MODE_PRIVATE)
            writer = OutputStreamWriter(outFile)
            writer.write(jsonArray.toString())
        } finally {
            if (writer != null) {
                writer.close()
            }
        }
    }

    @Throws(IOException::class, JSONException::class)
    fun load(): ArrayList<Note> {
        val noteList = ArrayList<Note>()
        var reader: BufferedReader? = null
        try {
            val inFile = context.openFileInput(filename)
            reader = BufferedReader(InputStreamReader(inFile))
            val jsonString = StringBuilder()

            for (line in reader.readLine()) {
                jsonString.append(line)
            }
            val jsonArray = JSONTokener(jsonString.toString()).nextValue() as JSONArray
            for (i in 0 until jsonArray.length()) {
                noteList.add(Note(jsonArray.getJSONObject(i)))
            }
        } catch (e: FileNotFoundException) {
            // we  will ignore this one, since its happens
            // when we start fresh. You could a log statement here.
        } finally {
            // This will always run
            reader!!.close()
        }
        return noteList
    }
}