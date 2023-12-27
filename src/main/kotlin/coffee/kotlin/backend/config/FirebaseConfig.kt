package coffee.kotlin.backend.config

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource

@Configuration
class FirebaseConfig {
    @Bean
    fun init() {
        try {
            val resource: ClassPathResource = ClassPathResource("fcm/fir-test-d7c74-94680ae367f5.json")
            val options: FirebaseOptions = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(resource.inputStream))
                .build();
            FirebaseApp.initializeApp(options);
        } catch (e: Exception) {
            println("${e.message}")
        }
    }
}