package software.ulpgc.sio_hiperdino_backend.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DotenvConfig {

    @Bean
    public Dotenv getDotenv() {
        return Dotenv.configure().ignoreIfMissing().load();
    }
}
