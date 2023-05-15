package com.zerobase.consumer.dto

import com.zerobase.domain.domain.LoanReview
import kafka.dto.LoanRequestDto

data class ReviewResponseDto(
    val userKey: String,
    val interest: Double,
    val limitAmount: Long
) {
    fun toLoanReviewEntity(): LoanReview =
        LoanReview(
            userKey = userKey,
            loanInterest = interest,
            loanLimitedAmount = limitAmount
        )
}