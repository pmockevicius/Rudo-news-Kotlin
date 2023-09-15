import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rudonews.R
import com.example.rudonews.presentation.noticias.NoticiasViewHolder

class NoticiasAdapter(private val originalDataList: List<String>) :
    RecyclerView.Adapter<NoticiasViewHolder>() {

    private var filteredDataList: List<String> = originalDataList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticiasViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_noticia, parent, false)
        return NoticiasViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoticiasViewHolder, position: Int) {
        holder.textView.text = filteredDataList[position]
        // Set your image resource if needed.
    }

    override fun getItemCount(): Int {
        return filteredDataList.size
    }

    fun submitFilteredData(filteredData: List<String>) {
        filteredDataList = filteredData
        notifyDataSetChanged()
    }
}

