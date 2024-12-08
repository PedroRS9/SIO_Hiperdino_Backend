package software.ulpgc.sio_hiperdino_backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import software.ulpgc.sio_hiperdino_backend.services.InventoryService;

@Configuration
@EnableScheduling
public class SchedulingConfig {

    private final InventoryService inventoryService;

    public SchedulingConfig(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @Scheduled(fixedRate = 60000) // Verifica cada 60 segundos
    public void scheduleInventoryCheck() {
        inventoryService.checkInventoryLevels();
    }
}