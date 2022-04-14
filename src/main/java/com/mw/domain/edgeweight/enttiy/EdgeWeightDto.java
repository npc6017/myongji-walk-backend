package com.mw.domain.edgeweight.enttiy;

import lombok.Builder;
import lombok.Getter;

@Getter
public class EdgeWeightDto {
    private WeightCode weightCode;
    private int weightValue;

    @Builder
    public EdgeWeightDto(WeightCode weightCode, int weightValue) {
        this.weightCode = weightCode;
        this.weightValue = weightValue;
    }
}
