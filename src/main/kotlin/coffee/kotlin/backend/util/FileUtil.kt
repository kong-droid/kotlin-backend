package coffee.kotlin.backend.util

import coffee.kotlin.backend.constants.messages.ErrorMessage
import coffee.kotlin.backend.domain.vo.FileVo
import coffee.kotlin.backend.exception.custom.InvalidStateException
import org.apache.commons.lang3.RandomStringUtils
import org.apache.tomcat.util.http.fileupload.FileUtils
import org.jetbrains.annotations.NotNull
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.ByteArrayInputStream
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardCopyOption
import java.util.*

@Service
class FileUtil(
    @Value("\${file.upload.path}") private val uploadPath: String
) {
    fun create(location: String?, file: MultipartFile): Any {
        val originName: String= if(file.originalFilename.isNullOrEmpty()) file.name else file.originalFilename!!

        if(originName.contains("..")) throw InvalidStateException(ErrorMessage.IO_CHECK_FILE_NAME);

        val result: FileVo = create(location, originName, file.contentType, file.bytes)
        println("Uploaded Info ::: \n$result")
        return result;
    }
    
    fun create(location: String?, originName: String, type: String?, bytes: ByteArray): FileVo {
        val isGroup: Boolean = location.isNullOrEmpty()
        var targetDir: File = File(uploadPath)
        if(isGroup) {
            targetDir = File("$uploadPath/$location")
            if(!targetDir.exists()) {
                if(!targetDir.mkdir()) {
                    throw InvalidStateException(ErrorMessage.IO_CANNOT_CREATE_FOLDER);
                }
            }
        }

        val targetName = RandomStringUtils.randomAlphanumeric(10)
        val targetLocation: Path = Paths.get(targetDir.toURI().resolve(targetName))

        Files.copy(ByteArrayInputStream(bytes), targetLocation, StandardCopyOption.REPLACE_EXISTING)

        val path: StringBuilder = StringBuilder("/")
        if(isGroup){
            path.append(location)
            path.append("/")
        }

        return FileVo(
            originName = originName, targetName = targetName, size = bytes.size, type = type,
            path = path.toString())
    }

    fun delete(location: String, targetName: String): Boolean {
        val path = getPath(location)
        return Files.deleteIfExists(Paths.get(path).toAbsolutePath().normalize().resolve(targetName))
    }

    fun deleteFolder(@NotNull location: String): Boolean {
        val path = "$uploadPath/$location"
        return try {
            FileUtils.deleteDirectory(File(path))
            true
        } catch (e: Exception) {
            false
        }
    }


    // todo:: get group func

    fun getFile(location: String, targetName: String): ByteArray {
        val path = getPath(location)
        val filePath = Paths.get(path).toAbsolutePath().normalize().resolve(targetName)
        return Files.readAllBytes(filePath);
    }

    fun getPath(location: String?): String {
        val isGroup: Boolean = location.isNullOrEmpty()
        var path:String = uploadPath;
        if(isGroup) {
            path = "$uploadPath/$location"
        }
        return path;
    }

}