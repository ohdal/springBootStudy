package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Partner;
import com.example.study.model.entity.User;
import lombok.ToString;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PartnerRepositoryTest extends StudyApplicationTests {

    @Autowired
    private PartnerRepository partnerRepository;

    @Test
    public void create() {
        String name = "Partner01";
        String status = "REGISTERED";
        String address = "서울시 강남구";
        String callCenter = "070-1111-2222";
        String partnerNumber = "010-1111-2222";
        String businessNumber = "1234567890123";
        String ceoName = "홍길동";
        LocalDateTime registeredAt = LocalDateTime.now();
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer";
        Long categoryId = 1L;

        Partner partner = new Partner();
        partner.setName(name);
        partner.setStatus(status);
        partner.setAddress(address);
        partner.setCallCenter(callCenter);
        partner.setPartnerNumber(partnerNumber);
        partner.setBusinessNumber(businessNumber);
        partner.setCeoName(ceoName);
        partner.setRegisteredAt(registeredAt);
        partner.setCreatedAt(createdAt);
        partner.setCreatedBy(createdBy);
        // partner.setCategoryId(categoryId);

        Partner newPartner = partnerRepository.save(partner);
        Assertions.assertNotNull(newPartner);
        Assertions.assertEquals(newPartner.getName(), partner.getName());
    }

    @Test
    public void read() {
        Optional<Partner> newPartner = partnerRepository.findById(2L);

        newPartner.ifPresent(value -> {
            // System.out.println(value.getCategoryId());
        });

        Assertions.assertNotNull(newPartner);
    }

    @Test
    public void delete() {
        Optional<Partner> partner = partnerRepository.findById(4L);

        Assertions.assertTrue(partner.isPresent());

        partner.ifPresent(select -> {
            partnerRepository.delete(select);
        });

        Optional<Partner> delete = partnerRepository.findById(4L);

        Assertions.assertFalse(delete.isPresent());
    }
}