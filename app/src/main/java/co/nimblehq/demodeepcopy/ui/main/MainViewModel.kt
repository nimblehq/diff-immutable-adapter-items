package co.nimblehq.demodeepcopy.ui.main

import androidx.lifecycle.*
import co.nimblehq.demodeepcopy.ui.main.model.MessageUiModel
import java.util.*
import kotlin.random.Random

class MainViewModel : ViewModel() {
    val messageUiModels = MutableLiveData<List<MessageUiModel>>()

    init {
        val messages = defaultList()
        messageUiModels.value = messages
    }

    fun addRandomMessage() {
        val random = Random.nextInt(20)
        val randomMessage = (0..random)
            .map { ('a'..'z').toList()[Random(System.nanoTime()).nextInt(0, 25)].toString() }
            .reduce { acc, c -> acc + c }

        val randomUiModel = MessageUiModel(Date().time.toInt(), randomMessage, false)
        val updatedList = messageUiModels.value?.toMutableList()
        requireNotNull(updatedList)

        updatedList.forEach {
            it.hasSeen = true
        }
        updatedList.add(randomUiModel)

        messageUiModels.value = updatedList
    }

    private fun defaultList() = listOf(
        MessageUiModel(1, "hi", true),
        MessageUiModel(2, "hello world", false),
        MessageUiModel(3, "how are you doing?", false)
    )
}
