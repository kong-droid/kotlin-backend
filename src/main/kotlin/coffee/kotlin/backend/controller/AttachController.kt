package coffee.kotlin.backend.controller

import coffee.kotlin.backend.controller.base.BaseController
import coffee.kotlin.backend.domain.entity.AttachEntity
import coffee.kotlin.backend.domain.response.common.ApiSuccessResponse
import coffee.kotlin.backend.exception.custom.InvalidStateException
import coffee.kotlin.backend.service.AttachService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.tags.Tag
import org.springdoc.api.ErrorMessage
import org.springframework.context.MessageSource
import org.springframework.core.io.ByteArrayResource
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.util.*
import java.util.concurrent.Callable

@Validated
@RestController
@RequestMapping("/attachment")
@Tag(name = "파일 첨부", description = "파일 첨부 API")
class AttachController(
    private val attachService: AttachService,
    messageSource: MessageSource
): BaseController(messageSource) {

    @GetMapping("/{attachId}")
    @Operation(summary = "개별 조회", description = "파일 개별 정보 조회")
    fun get(@Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "파일 ID") @PathVariable attachId: UUID):
            ApiSuccessResponse<AttachEntity> {
        return wrap(attachService.get(attachId))
    }

    @GetMapping("/group/{attachGroupId}")
    @Operation(summary = "그룹 조회", description = "그룹 정보 조회")
    fun group(@Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "파일 ID")
              @PathVariable attachGroupId: UUID): ApiSuccessResponse<List<AttachEntity>> {
        return wrap(attachService.getAllByGroupId(attachGroupId))
    }

    @PostMapping(consumes = ["multipart/form-data"])
    @Operation(summary = "파일 첨부", description = "파일 등록")
    fun attachment(@Schema(requiredMode = Schema.RequiredMode.NOT_REQUIRED, description = "파일 그룹 ID")
                   @RequestParam(required = false) groupId: UUID,
                   @RequestPart file: MultipartFile): Callable<ApiSuccessResponse<AttachEntity>> {
        return Callable {wrap(attachService.create(UUID.randomUUID(), groupId, file))}
    }


    @DeleteMapping("/{attachId}")
    @Operation(summary = "첨부파일 삭제")
    fun delete(
        @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "파일 ID") @PathVariable attachId: UUID
    ): Callable<ApiSuccessResponse<Boolean>> {
        return Callable { wrap(attachService.delete(attachId)) }
    }

    @DeleteMapping("/group/{attachGroupId}")
    @Operation(summary = "첨부파일 묶음 삭제")
    fun deleteGroup(@Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "파일 그룹 ID")
              @PathVariable attachGroupId: UUID): Callable<ApiSuccessResponse<Boolean>> {
        return Callable { wrap(attachService.deleteAllByGroupId(attachGroupId)) }
    }

    @PostMapping("/download/{attachId}")
    @Operation(summary = "첨부파일 다운로드(바이트 스트림 로드)")
    fun download(
        @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "파일 ID") @PathVariable attachId: UUID,
        @RequestBody fileName: String
    ): Callable<ResponseEntity<ByteArrayResource>>? {
        return Callable<ResponseEntity<ByteArrayResource>> {
            val resource: ByteArrayResource? = attachService.download(attachId)
            resource?.let {
                ResponseEntity.ok()
                    .contentLength(it.contentLength())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=$fileName")
                    .body(resource)
            }
        }
    }
}
