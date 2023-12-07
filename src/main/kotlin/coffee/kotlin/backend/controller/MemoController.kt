package coffee.kotlin.backend.controller

import coffee.kotlin.backend.controller.base.BaseController
import coffee.kotlin.backend.domain.request.ModifyMemoRequest
import coffee.kotlin.backend.domain.request.RegisterMemoRequest
import coffee.kotlin.backend.domain.request.RemoveMemoRequest
import coffee.kotlin.backend.domain.request.ViewMemoRequest
import coffee.kotlin.backend.domain.response.common.ApiSuccessResponse
import coffee.kotlin.backend.domain.response.memo.MemoIdResponse
import coffee.kotlin.backend.domain.response.memo.ViewMemoResponse
import coffee.kotlin.backend.service.MemoService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springdoc.api.annotations.ParameterObject
import org.springframework.context.MessageSource
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@Validated
@RestController
@RequestMapping("/memo")
@Tag(name = "메모", description = "메모 API")
class MemoController(private val memoService: MemoService, messageSource: MessageSource): BaseController(messageSource) {

    @GetMapping
    @Operation(summary = "메모 조회", description = "닉네임, 날짜별 메모 조회")
    fun memos(@ParameterObject request: ViewMemoRequest): ApiSuccessResponse<List<ViewMemoResponse>> {
        return wrap(memoService.getMemo(request))
    }

    @PostMapping
    @Operation(summary = "메모 등록", description = "메모 등록")
    fun register(@Valid @RequestBody request: RegisterMemoRequest): ApiSuccessResponse<MemoIdResponse> {
        return wrap(memoService.addMemo(request))
    }

    @PatchMapping
    @Operation(summary = "메모 수정", description = "메모 수정")
    fun modify(@Valid @RequestBody request:ModifyMemoRequest): ApiSuccessResponse<Boolean> {
        return wrap(memoService.setMemo(request))
    }

    @DeleteMapping
    @Operation(summary = "메모 삭제", description = "메모 삭제")
    fun delete(@Valid @RequestBody request:RemoveMemoRequest): ApiSuccessResponse<Boolean> {
        return wrap(memoService.removeMemo(request))
    }
}