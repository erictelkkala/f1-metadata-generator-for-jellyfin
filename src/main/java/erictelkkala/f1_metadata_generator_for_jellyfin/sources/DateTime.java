package erictelkkala.f1_metadata_generator_for_jellyfin.sources;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public record DateTime(String date, String time, ZoneOffset zoneOffset, DateTimeFormatter dateTimeFormatter) {

    public DateTime(final String date, final String time) {
        this(
                date,
                time,
                ZoneOffset.UTC,
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
        );
    }

    public Instant asInstant() {
        return LocalDateTime.parse(date + "T" + time, this.dateTimeFormatter).toInstant(this.zoneOffset);
    }
}
