package com.trendyol.linkconverter.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

/**
 * Response DTO for weblink to deeplink conversion
 */
public record DeeplinkResponseDto(
        @Schema(description = "Converted deeplink", example = "ty://?Page=Product&ContentId=1925865")
        String deeplink
) implements Serializable {
}
