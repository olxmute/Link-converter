package com.trendyol.linkconverter.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Some constants for deeplinks.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DeeplinkConstants {

    public static final String BASE_URI = "ty://";
    public static final String DEFAULT_PAGE = "ty://?Page=Home";

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class QueryParams {
        public static final String PAGE = "Page";
        public static final String CONTENT_ID = "ContentId";
        public static final String QUERY = "Query";
    }
}
