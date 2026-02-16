package se.kth.iv1201.recruitment.service;

import se.kth.iv1201.recruitment.dto.UserRegistrationDTO;

/**
 * Tjänst för användarhantering.
 */
public interface PersonService {
    void registerUser(UserRegistrationDTO dto);
}