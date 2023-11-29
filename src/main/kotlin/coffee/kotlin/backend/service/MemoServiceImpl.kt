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
import java.util.UUID

@Service
class MemoServiceImpl(private val memoRepository: MemoRepository): MemoService {
    override fun getMemo(request: ViewMemoRequest): List<ViewMemoResponse> {
        return memoRepository.findAllByName(request.name).map { ViewMemoResponse(entity = it) }
    }

    override fun addMemo(request: RegisterMemoRequest): MemoIdResponse {
        val entity = MemoEntity(name = request.name, password = request.password, contents = request.contents);
        val result:UUID = memoRepository.save(entity).id;
        return MemoIdResponse(result);
    }

    override fun setMemo(request: ModifyMemoRequest): Boolean {
        val memo:MemoEntity = memoRepository.findById(request.memoId).orElse(null);
        val entity:MemoEntity = MemoEntity(memo.id, memo.name, request.password, request.contents);
        return if(memo.password == request.password) {
            memoRepository.save(entity);
            true;
        } else false;
    }

    override fun removeMemo(request: RemoveMemoRequest): Boolean {
        val memo:MemoEntity = memoRepository.findById(request.memoId).orElse(null);
        return if (memo.id == request.memoId && memo.password == request.password) {
            memoRepository.deleteById(request.memoId);
            true;
        } else false;
    }

}