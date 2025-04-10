package org.example.races;

public enum SessionType {
    RACE(7, 7),
    SPRINT(6, 5),
    SPRINT_QUALI(5, 4),
    QUALI(4, 6),
    FP3(3, 3),
    FP2(2, 1),
    FP1(1, 1),
    /**
     * Probably for an older format?
     */
    FP(0, 0);


    SessionType(
            final int sortingNumber,
            final int olderFormatSortingNumber
    ) {
    }
}
