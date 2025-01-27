package com.demo.amplitude;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import com.amplitude.Amplitude;
import com.amplitude.AmplitudeCallbacks;
import com.amplitude.AmplitudeLog;
import com.amplitude.Event;

@RestController
public class DemoController {
  @RequestMapping("/")
  public String index() {
    Amplitude amplitude = Amplitude.getInstance("INSTANCE_NAME");
    amplitude.init("8e07b9d451a7d07bd33f6e9ba5870f21");
    amplitude.logEvent(new Event("Test Event", "test_user_id"));
    amplitude.setLogMode(AmplitudeLog.LogMode.DEBUG);
    AmplitudeCallbacks callbacks =
        new AmplitudeCallbacks() {
          @Override
          public void onLogEventServerResponse(Event event, int status, String message) {
            System.out.println(
                String.format(
                    "Event: %s sent. Status: %s, Message: %s", event.eventType, status, message));
          }
        };
    amplitude.setCallbacks(callbacks);
    amplitude.logEvent(new Event("Test Event", "test_user_id"));
    return "Amplitude Java SDK Demo: sending test event.";
  }
}
