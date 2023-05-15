package kafka.producer

import com.fasterxml.jackson.databind.ObjectMapper
import kafka.dto.LoanRequestDto
import kafka.enum.KafkaTopic
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class LoanRequestSender (
    private val kafkaTemplate: KafkaTemplate<String, String>,
    private val objectMapper: ObjectMapper
) {
    // fun (data class)
    fun sendMessage(topic: KafkaTopic, loanRequestDto: LoanRequestDto) {
        kafkaTemplate.send(topic.topicName, objectMapper.writeValueAsString(loanRequestDto))
    }
}