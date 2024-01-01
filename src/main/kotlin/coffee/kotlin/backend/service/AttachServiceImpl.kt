package coffee.kotlin.backend.service

import coffee.kotlin.backend.constants.messages.ErrorMessage
import coffee.kotlin.backend.domain.entity.AttachEntity
import coffee.kotlin.backend.domain.vo.FileVo
import coffee.kotlin.backend.exception.custom.NotFoundException
import coffee.kotlin.backend.repository.AttachRepository
import coffee.kotlin.backend.util.FileUtil
import org.springframework.core.io.ByteArrayResource
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.util.*
import javax.transaction.Transactional

@Service
class AttachServiceImpl(
    private val attachRepository: AttachRepository,
    private val fileUtil: FileUtil
): AttachService {
    override fun get(attachId: UUID): AttachEntity {
        val entity: Optional<AttachEntity> = attachRepository.findById(attachId)
        if(entity.isPresent) {
            return entity.get()
        } else {
            throw NotFoundException(ErrorMessage.FILE_NOT_FOUND)
        }
    }

    override fun getAllByGroupId(attachGroupId: UUID): List<AttachEntity> {
        return attachRepository.findAllByGroupIdOrderByCreatedAtAsc(attachGroupId);
    }

    override fun getFirstByGroupId(attachGroupId: UUID): AttachEntity {
        val entity: Optional<AttachEntity> = attachRepository.findFirstByGroupId(attachGroupId)
        if(entity.isPresent) {
            return entity.get()
        } else {
            throw NotFoundException(ErrorMessage.FILE_NOT_FOUND)
        }
    }

    @Transactional
    override fun create(userId: UUID, groupId: UUID?, attachFiles: MultipartFile): AttachEntity {
        val location: String? = groupId.let { it.toString() } ?: null
        val fileVo: FileVo = fileUtil.create(location, attachFiles);
        val attach: AttachEntity = AttachEntity(
            groupId = groupId, originName = fileVo.originName, targetName = fileVo.targetName,
            size = fileVo.size, path = fileVo.path)
        return  attachRepository.save(attach)
    }

    @Transactional
    override fun delete(attachId: UUID): Boolean {
        return attachRepository.deleteWithId(attachId) > 0
    }

    @Transactional
    override fun deleteAllByGroupId(attachGroupId: UUID): Boolean {
        return attachRepository.deleteAllByGroupId(attachGroupId) > 0
    }

    override fun download(attachId: UUID): ByteArrayResource? {
        val entity: AttachEntity = get(attachId)
        try {
            val file: ByteArray = fileUtil.getFile(entity.groupId.toString(), entity.targetName)
            return ByteArrayResource(file)
        } catch (e: Exception) {
            println("error message:::\t${e.message}")
        }
        return null
    }
}