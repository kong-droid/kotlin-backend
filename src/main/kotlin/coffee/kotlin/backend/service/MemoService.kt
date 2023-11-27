package coffee.kotlin.backend.service

import coffee.kotlin.backend.domain.request.ModifyMemoRequest
import coffee.kotlin.backend.domain.request.RegisterMemoRequest
import coffee.kotlin.backend.domain.request.RemoveMemoRequest
import coffee.kotlin.backend.domain.request.ViewMemoRequest
import coffee.kotlin.backend.domain.response.MemoIdResponse
import coffee.kotlin.backend.domain.response.ViewMemoResponse
interface MemoService {
    fun getMemo(request: ViewMemoRequest): List<ViewMemoResponse>
    fun addMemo(request: RegisterMemoRequest): MemoIdResponse
    fun setMemo(request: ModifyMemoRequest): Boolean
    fun removeMemo(request: RemoveMemoRequest): Boolean
}