package com.jcasben.jchat.domain

import android.icu.util.Calendar
import com.jcasben.jchat.data.network.FirebaseChatService
import com.jcasben.jchat.data.network.dto.MessageDto
import com.jcasben.jchat.data.network.dto.UserDto
import javax.inject.Inject

class SendMessageUseCase @Inject constructor(private val firebaseChatService: FirebaseChatService) {
    operator fun invoke(message: String) {
        val calendar = Calendar.getInstance()
        val userDto = UserDto("Test", false)
        val messageDto = MessageDto(
            message = message,
            hour = "${calendar.get(Calendar.HOUR)}:${calendar.get(Calendar.MINUTE)}",
            date = "${calendar.get(Calendar.DAY_OF_MONTH)}/${calendar.get(Calendar.MONTH) + 1}/${
                calendar.get(
                    Calendar.YEAR
                )
            }",
            user = userDto
        )
        firebaseChatService.sendMessage(messageDto)
    }
}