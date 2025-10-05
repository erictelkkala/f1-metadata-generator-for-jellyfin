package erictelkkala.f1_metadata_generator_for_jellyfin.sources.jolpi;

import jakarta.json.JsonObject;

public record JsonSessionImpl(JsonObject jsonObject) implements JsonSession {

    @Override
    public String date() {
        if (this.jsonObject == null) {
            return "1970-01-01";
        }

        if (!jsonObject.containsKey("date")) {
            return "1970-01-01";
        }

        return jsonObject.getString("date");
    }

    @Override
    public String time() {
        if (this.jsonObject == null) {
            return "00:00:00Z";
        }

        if (!jsonObject.containsKey("time")) {
            return "00:00:00Z";
        }

        return jsonObject.getString("time");
    }
}
