package coffee.kotlin.backend.service

import coffee.kotlin.backend.domain.request.ModifyMemoRequest
import coffee.kotlin.backend.domain.request.RegisterMemoRequest
import coffee.kotlin.backend.domain.request.RemoveMemoRequest
import coffee.kotlin.backend.domain.request.ViewMemoRequest
import coffee.kotlin.backend.domain.response.memo.MemoIdResponse
import coffee.kotlin.backend.domain.response.memo.ViewMemoResponse
import org.springframework.data.domain.Page
import java.awt.print.Pageable

interface MemoService {
    fun getMemos(request: ViewMemoRequest, pageable: Pageable): Page<ViewMemoResponse>
    fun addMemo(request: RegisterMemoRequest): MemoIdResponse
    fun setMemo(request: ModifyMemoRequest): MemoIdResponse
    fun removeMemo(request: RemoveMemoRequest): MemoIdResponse
}