package co.nimblehq.demodeepcopy.ui.main.adapter

import android.view.*
import androidx.recyclerview.widget.RecyclerView
import co.nimblehq.demodeepcopy.R
import co.nimblehq.demodeepcopy.ui.main.model.MessageUiModel
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_message.*
import kotlin.properties.Delegates

class ListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(), DiffUpdateAdapter {

    var items: List<MessageUiModel> by Delegates.observable(listOf()) { _, old, new ->
        autoNotify(
            old,
            new,
            { oldItem, newItem -> oldItem.id == newItem.id },
            { oldItem, newItem ->
                oldItem.hasSeen == newItem.hasSeen && oldItem.content == newItem.content
            }
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MessageViewHolder(inflater.inflate(R.layout.item_message, parent, false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MessageViewHolder).bindData(items[position])
    }

    inner class MessageViewHolder(view: View) : RecyclerView.ViewHolder(view),
        LayoutContainer {

        override val containerView: View?
            get() = itemView

        fun bindData(messageUiModel: MessageUiModel) {
            with(messageUiModel) {
                tvMessage.text = content
                ivMessageSeen.visibility = if (hasSeen) View.VISIBLE else View.GONE
            }
        }
    }
}
