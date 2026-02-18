package se.kth.iv1201.recruitment.domain;

/**
 * Representerar möjliga statusvärden för en jobbansökan.
 * Används för att markera om en ansökan är accepterad,
 * avslagen eller ännu inte behandlad.
 */
public enum ApplicationStatus {

    /**
     * Ansökan har accepterats av rekryteraren.
     */
    ACCEPTED,

    /**
     * Ansökan har avslagits av rekryteraren.
     */
    REJECTED,

    /**
     * Ansökan är ännu inte behandlad.
     */
    UNHANDLED
}
