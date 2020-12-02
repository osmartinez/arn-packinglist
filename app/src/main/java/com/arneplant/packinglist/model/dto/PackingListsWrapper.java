package com.arneplant.packinglist.model.dto;

import java.io.Serializable;
import java.util.List;

public class PackingListsWrapper implements Serializable {
    private List<PackingListDTO> packingListsDTO;

    public PackingListsWrapper() {
    }

    public List<PackingListDTO> getPackingListsDTO() {
        return packingListsDTO;
    }

    public void setPackingListsDTO(List<PackingListDTO> packingListsDTO) {
        this.packingListsDTO = packingListsDTO;
    }
}
