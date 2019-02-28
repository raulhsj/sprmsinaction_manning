package com.thoughtmechanix.licenses.services;

import com.thoughtmechanix.licenses.config.ServiceConfig;
import com.thoughtmechanix.licenses.model.License;
import com.thoughtmechanix.licenses.repository.LicenseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LicenseService {

    private LicenseRepository licenseRepository;
    ServiceConfig config;

    public LicenseService(LicenseRepository licenseRepository, ServiceConfig serviceConfig) {
        this.licenseRepository = licenseRepository;
        this.config = serviceConfig;
    }

    public License getLicense(String licenseId){
        return new License()
                .withId(licenseId)
                .withOrganizationId( UUID.randomUUID().toString() )
                .withProductName("Test Product Name")
                .withLicenseType("PerSeat");
    }

    public License getLicense(String organizationId, String licenseId) {
        final License license = licenseRepository.findByOrganizationIdAndLicenseId(organizationId, licenseId);
        return license.withComment(config.getExampleProperty());
    }

    public List<License> getLicensesByOrg(String organizationId){
        return licenseRepository.findByOrganizationId( organizationId );
    }

    public void saveLicense(License license){
        license.withId(UUID.randomUUID().toString());
        licenseRepository.save(license);
    }

    public void updateLicense(License license){

    }

    public void deleteLicense(License license){

    }

}
