package coffee.kotlin.backend.service

import coffee.kotlin.backend.domain.entity.AttachEntity
import org.springframework.core.io.ByteArrayResource
import org.springframework.web.multipart.MultipartFile
import java.util.UUID

interface AttachService {
    fun get(attachId: UUID): AttachEntity
    fun getAllByGroupId(attachGroupId: UUID): List<AttachEntity>
    fun getFirstByGroupId(attachGroupId: UUID): AttachEntity
    fun create(userId: UUID, groupId: UUID?, attachFiles: MultipartFile): AttachEntity
    fun delete(attachId: UUID): Boolean
    fun deleteAllByGroupId(attachGroupId: UUID): Boolean
    fun download(attachId: UUID): ByteArrayResource?
}