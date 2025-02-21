package com.ganzi.soccerhub.common.infra.hashid;

import lombok.RequiredArgsConstructor;
import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HashIdService {

    private static final int MIN_LENGTH = 8;

    @Value("${hashids.salt}")
    private String salt;

    private final Hashids hashids = new Hashids(salt, MIN_LENGTH);

    public String encode(Long id) {
        return hashids.encode(id);
    }

    public Long decode(String hash) {
        long[] decoded = hashids.decode(hash);
        return decoded.length > 0 ? decoded[0] : null;
    }
}
