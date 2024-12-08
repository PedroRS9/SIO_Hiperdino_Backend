package software.ulpgc.sio_hiperdino_backend.services;

import java.util.List;

public interface EmailService {
    void sendEmail(List<String> recipients, String subject, String body);
}
