package org.example.seasons;

import org.example.Nfo;
import org.example.races.Race;

import java.util.List;

public interface Season extends Nfo {
    List<Race> races();
}
