package coffee.kotlin.backend.service

import coffee.kotlin.backend.constants.messages.ErrorMessage
import coffee.kotlin.backend.domain.entity.MemoEntity
import coffee.kotlin.backend.domain.request.ModifyMemoRequest
import coffee.kotlin.backend.domain.request.RegisterMemoRequest
import coffee.kotlin.backend.domain.request.RemoveMemoRequest
import coffee.kotlin.backend.domain.request.ViewMemoRequest
import coffee.kotlin.backend.domain.response.memo.MemoIdResponse
import coffee.kotlin.backend.domain.response.memo.ViewMemoResponse
import coffee.kotlin.backend.exception.custom.NotFoundException
import coffee.kotlin.backend.repository.MemoRepository
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class MemoServiceImpl(private val memoRepository: MemoRepository): MemoService {
    override fun getMemos(request: ViewMemoRequest): Page<ViewMemoResponse> {
        return memoRepository.findAllByName(request.name).map { ViewMemoResponse(entity = it) }
    }

    override fun addMemo(request: RegisterMemoRequest): MemoIdResponse {
        val entity = MemoEntity(name = request.name, password = request.password, contents = request.contents);
        val result:UUID = memoRepository.save(entity).id;
        return MemoIdResponse(result);
    }

    override fun setMemo(request: ModifyMemoRequest): MemoIdResponse {
        val memo:MemoEntity = memoRepository.findById(request.memoId)
            .orElseThrow {throw NotFoundException(ErrorMessage.MEMO_NOT_FOUND)}
        val entity:MemoEntity = MemoEntity(memo.id, memo.name, request.password, request.contents);
        if(memo.password == request.password) {
            return MemoIdResponse(memoRepository.save(entity).id);
        } else throw NotFoundException(ErrorMessage.MEMO_PASSWORD_MISMATCH);
    }

    override fun removeMemo(request: RemoveMemoRequest): MemoIdResponse {
        val memo:MemoEntity = memoRepository.findById(request.memoId)
            .orElseThrow {throw NotFoundException(ErrorMessage.MEMO_NOT_FOUND)}
        if(memo.id == request.memoId && memo.password == request.password) {
            val effectRow: Long = memoRepository.deleteByIdAndPassword(request.memoId, request.password);
            if(effectRow > 0) return MemoIdResponse(memo.id);
        } else throw NotFoundException(ErrorMessage.MEMO_PASSWORD_MISMATCH);
        return MemoIdResponse(null);
    }
}