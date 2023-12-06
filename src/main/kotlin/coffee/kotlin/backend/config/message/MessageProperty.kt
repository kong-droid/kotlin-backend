package coffee.kotlin.backend.config.message

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.MessageSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MessageProperty(
    @Value("\${spring.messages.basename}") private val basename: String,
    @Value("\${spring.messages.encoding}") private val encoding: String,
    @Value("\${spring.messages.cache-duration}") private val cacheDuration: Int,
    @Value("\${spring.messages.always-use-message-format}") private val alwaysUseMessageFormat: Boolean,
    @Value("\${spring.messages.use-code-as-default-message}") private val useCodeAsDefaultMessage: Boolean,
    @Value("\${spring.messages.fallback-to-system-locale}") private val fallbackToSystemLocale: Boolean
) {
    @Bean
    fun messageSource(): MessageSource {
        val source = YamlMessageSource();
        source.setBasename(basename);
        source.setDefaultEncoding(encoding);
        source.setCacheSeconds(cacheDuration);
        source.setAlwaysUseMessageFormat(alwaysUseMessageFormat);
        source.setUseCodeAsDefaultMessage(useCodeAsDefaultMessage);
        source.setFallbackToSystemLocale(fallbackToSystemLocale);
        return source;
    }
}