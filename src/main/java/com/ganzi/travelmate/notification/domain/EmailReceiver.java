package com.ganzi.travelmate.notification.domain;

import java.util.List;

public record EmailReceiver(List<String> to, List<String> cc, List<String> bcc) implements Target {
}
