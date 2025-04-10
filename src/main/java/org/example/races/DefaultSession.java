package org.example.races;

import java.io.IOException;

public final class DefaultSession implements Session {
    private final SessionType sessionType;

    public DefaultSession(final SessionType sessionType) {
        this.sessionType = sessionType;
    }

    @Override
    public SessionType sessionType() {
        return null;
    }

    @Override
    public void nfo() throws IOException {

    }
}
