package com.zerobase.consumer.service

import com.zerobase.consumer.dto.ReviewResponseDto
import com.zerobase.domain.domain.LoanReview
import com.zerobase.domain.repository.LoanReviewRepository
import kafka.dto.LoanRequestDto
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.stereotype.Service
import java.time.Duration

@Service
class LoanRequestService(
    private val loanReviewRepository: LoanReviewRepository
) {
    companion object {
        const val nginxUrl = "http://nginx:8085/css/ap1/v1/request"
    }
    fun loanRequest(loanRequestDto: LoanRequestDto) {
        val reviewResult = loanRequestToCb(loanRequestDto)

        saveLoanReviewData(reviewResult.toLoanReviewEntity())
    }

    private fun loanRequestToCb(loanRequestDto: LoanRequestDto): ReviewResponseDto {
        val restTemplate = RestTemplateBuilder()
            .setConnectTimeout(Duration.ofMinutes(1000))
            .setReadTimeout(Duration.ofMinutes(1000))
            .build()

        return restTemplate.postForEntity(nginxUrl, loanRequestDto, ReviewResponseDto::class.java).body!!
    }

    fun saveLoanReviewData(loanReview: LoanReview) =
        loanReviewRepository.save(loanReview)

}