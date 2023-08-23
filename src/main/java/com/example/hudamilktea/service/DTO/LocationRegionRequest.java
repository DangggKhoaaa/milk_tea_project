package com.example.hudamilktea.service.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class LocationRegionRequest {
    private String provinceId;

    private String provinceName;

    private String districtId;

    private String districtName;

    private String wardId;

    private String wardName;

    private String address;
}
