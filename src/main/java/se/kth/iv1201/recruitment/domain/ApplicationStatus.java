package se.kth.iv1201.recruitment.domain;

/**
 * Representerar statusen för en jobbansökan.
 */
public enum ApplicationStatus {
    /**
     * Ansökan har accepterats.
     */
    ACCEPTED,

    /**
     * Ansökan har avslagits.
     */
    REJECTED,

    /**
     * Ansökan är obehandlad.
     */
    UNHANDLED
}