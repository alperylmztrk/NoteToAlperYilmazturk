package ise308.yilmazturk.alper.notetoalperyilmazturk

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.webkit.WebSettings
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.ParsePosition
// Inheriting from the RecyclerView.Adapter class
class NoteAdapter(
    private val mainActivity: MainActivity,
    private val noteList: List<Note>)
    : RecyclerView.Adapter<NoteAdapter.ListItemHolder>(){

    inner class ListItemHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        internal var title = view.findViewById<View>(R.id.textView_item_title) as TextView
        internal var description = view.findViewById<View>(R.id.textView_item_description) as TextView
        internal var status = view.findViewById<View>(R.id.textView_item_status) as TextView

        init {
            view.isClickable = true
            view.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            mainActivity.showNote(adapterPosition)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return ListItemHolder(itemView)
    }

    override fun onBindViewHolder(holder: ListItemHolder, position: Int) {
        val note = noteList[position]
        holder.title.text = note.title
        val substringLength = if (note.description!!.length < 15) note.description!!.length else 15
        val shortDesc = "${note.description!!.substring(0, substringLength)}..."
        holder.description.text = shortDesc
        when {
            note.idea -> holder.status.text = "Idea"
            note.important -> holder.status.text = "Important"
            note.todo -> holder.status.text = "To do"
        }
    }
    // Supplying the current number of items in List.
    override fun getItemCount(): Int {
        if (noteList != null) {
            return noteList.size
        }
        return -1
    }

}