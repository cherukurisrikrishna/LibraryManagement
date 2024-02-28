package com.Spring.LibraryService.Model;

import lombok.Data;

@Data
public class AddressDTO {
    private int addressId;
    private String line1;
    private String line2;
    private String city;
    private String state;
    private Integer zip;
}