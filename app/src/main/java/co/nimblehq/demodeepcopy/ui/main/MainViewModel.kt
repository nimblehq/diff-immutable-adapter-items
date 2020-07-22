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
        val randomMessageUiModel = aRandomLength
            .takeSomeRandomCharacters()
            .combineTogether()
            .generateUiModel()

        val updatedList = messageUiModels.value?.map { it.copy() }?.toMutableList()
        requireNotNull(updatedList)

        updatedList.forEach {
            it.hasSeen = true
        }
        updatedList.add(randomMessageUiModel)

        messageUiModels.value = updatedList
    }

    private fun defaultList() = listOf(
        MessageUiModel(1, "hi", true),
        MessageUiModel(2, "hello world", false),
        MessageUiModel(3, "how are you doing?", false)
    )

    private fun IntRange.takeSomeRandomCharacters() =
        this.map { ('a'..'z').toList()[Random(System.nanoTime()).nextInt(0, 25)].toString() }

    private val aRandomLength = (0..Random.nextInt(20))

    private fun List<String>.combineTogether(): String = this.reduce { acc, c -> acc + c }

    private fun String.generateUiModel() = MessageUiModel(Date().time.toInt(), this, false)
}
