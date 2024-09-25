package it.pagopa.devops.springbootshowcase;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class MySystemLogs {

    @EventListener
    public void onStartup(ApplicationReadyEvent event) {
        System.out.println("🚀 We are ready ----------------------- ✅");
     }

    @EventListener
    public void onShutdown(ContextStoppedEvent event) { 
        System.out.println("⏾ Goodbye");
     }
}
