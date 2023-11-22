package coffee.kotlin.backend.service

import coffee.kotlin.backend.domain.entity.MemoEntity
import coffee.kotlin.backend.domain.request.ModifyMemoRequest
import coffee.kotlin.backend.domain.request.RegisterMemoRequest
import coffee.kotlin.backend.domain.request.RemoveMemoRequest
import coffee.kotlin.backend.domain.request.ViewMemoRequest
import coffee.kotlin.backend.domain.response.MemoIdResponse
import coffee.kotlin.backend.domain.response.ViewMemoResponse
import coffee.kotlin.backend.repository.MemoRepository
import org.springframework.stereotype.Service

@Service
class MemoServiceImpl(private val memoRepository: MemoRepository): MemoService {
    override fun getMemo(request: ViewMemoRequest): List<ViewMemoResponse> {
        return memoRepository.findAllByName(request.name).map { ViewMemoResponse(entity = it) }
    }

    override fun addMemo(request: RegisterMemoRequest): MemoIdResponse {
        TODO("Not yet implemented")
    }

    override fun setMemo(request: ModifyMemoRequest): MemoIdResponse {
        TODO("Not yet implemented")
    }

    override fun removeMemo(request: RemoveMemoRequest): Boolean {
        TODO("Not yet implemented")
    }
}