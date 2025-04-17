package com.ganzi.soccerhub.notification.domain;

import java.util.List;

public record EmailReceiver(List<String> to, List<String> cc, List<String> bcc) implements Target {
}
