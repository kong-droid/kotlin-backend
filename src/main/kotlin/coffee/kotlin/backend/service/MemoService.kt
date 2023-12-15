package coffee.kotlin.backend.service

import coffee.kotlin.backend.domain.request.ModifyMemoRequest
import coffee.kotlin.backend.domain.request.RegisterMemoRequest
import coffee.kotlin.backend.domain.request.RemoveMemoRequest
import coffee.kotlin.backend.domain.request.ViewMemoRequest
import coffee.kotlin.backend.domain.response.memo.MemoIdResponse
import coffee.kotlin.backend.domain.response.memo.ViewMemoResponse
interface MemoService {
    fun getMemo(request: ViewMemoRequest): List<ViewMemoResponse>
    fun addMemo(request: RegisterMemoRequest): MemoIdResponse
    fun setMemo(request: ModifyMemoRequest): MemoIdResponse
    fun removeMemo(request: RemoveMemoRequest): MemoIdResponse
}