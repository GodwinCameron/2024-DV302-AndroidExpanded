package co.za.openwindow.page.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.za.openwindow.page.models.Chat
import co.za.openwindow.page.repositories.ChatsRepository
import kotlinx.coroutines.launch

class ChatViewModel(private val chatsRepository: ChatsRepository = ChatsRepository()): ViewModel() {

    private var _chatList = mutableStateListOf<Chat>()
    val chatList: List<Chat> = _chatList

    init {
        getAllChats()
    }

    private fun getAllChats() = viewModelScope.launch{
        chatsRepository.getAllChats {
            if(it != null){
                for(doc in it){
                    _chatList.add(doc)
                }
//                _chatList = it as MutableList<Chat>
            }
        }
    }
}