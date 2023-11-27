package coffee.kotlin.backend.controller

import coffee.kotlin.backend.domain.request.ModifyMemoRequest
import coffee.kotlin.backend.domain.request.RegisterMemoRequest
import coffee.kotlin.backend.domain.request.RemoveMemoRequest
import coffee.kotlin.backend.domain.request.ViewMemoRequest
import coffee.kotlin.backend.domain.response.MemoIdResponse
import coffee.kotlin.backend.domain.response.ViewMemoResponse
import coffee.kotlin.backend.service.MemoService
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Validated
@RestController
@RequestMapping("/memo")
class MemoController(private val memoService: MemoService) {

    @PostMapping("/read")
    fun memos(@RequestBody request: ViewMemoRequest): List<ViewMemoResponse> {
        return memoService.getMemo(request);
    }

    @PostMapping
    fun register(@RequestBody request: RegisterMemoRequest): MemoIdResponse {
        return memoService.addMemo(request);
    }

    @PatchMapping
    fun modify(@RequestBody request:ModifyMemoRequest): Boolean {
        return memoService.setMemo(request);
    }

    @DeleteMapping
    fun delete(@RequestBody request:RemoveMemoRequest): Boolean {
        return memoService.removeMemo(request);
    }
}