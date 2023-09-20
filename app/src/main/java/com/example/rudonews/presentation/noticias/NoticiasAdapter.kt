import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rudonews.activities.MainActivity
import com.example.rudonews.R
import com.example.rudonews.domain.entity.News
import com.example.rudonews.presentation.noticias.NoticiasViewHolder

class NoticiasAdapter(private val originalDataList: List<News>) :
    RecyclerView.Adapter<NoticiasViewHolder>() {

    private var filteredDataList: List<News> = originalDataList


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticiasViewHolder {
        val context = parent.context
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_noticia, parent, false)
        return NoticiasViewHolder(view, mainActivity = MainActivity())
    }

    override fun onBindViewHolder(holder: NoticiasViewHolder, position: Int) {

        val currentNoticia = filteredDataList[position]


        holder.bind(currentNoticia)
    }

    override fun getItemCount(): Int {
        return filteredDataList.size
    }

    fun submitFilteredData(filteredData: List<News>) {
        filteredDataList = filteredData
        notifyDataSetChanged()
    }
}

