package com.example.rudonews.activities.DetailsActivity

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rudonews.R
import com.example.rudonews.domain.entity.Comment


class CommentsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val body: TextView = itemView.findViewById(R.id.commentBody)
    private val commentOwner: TextView = itemView.findViewById(R.id.commentOwner)
    private val ownersDepartments: TextView = itemView.findViewById(R.id.commentOwnersDepartments)
    private val datePosted: TextView = itemView.findViewById(R.id.commentDate)



    fun bind(comment: Comment) {

        body.text = comment.body
        commentOwner.text = comment.owner
        ownersDepartments.text = comment.departaments
        datePosted.text = comment.datePosted
    }
}