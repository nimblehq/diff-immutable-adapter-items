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
        val randomMessage =
            randomLength
                .map { takeARandomCharacter() }
                .reduce { acc, c -> acc + c }

        val randomUiModel = MessageUiModel(Date().time.toInt(), randomMessage, false)
        val updatedList = messageUiModels.value?.map { it.copy() }?.toMutableList()
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

    private fun takeARandomCharacter() =
        ('a'..'z').toList()[Random(System.nanoTime()).nextInt(0, 25)].toString()

    private val randomLength = (0..Random.nextInt(20))
}
