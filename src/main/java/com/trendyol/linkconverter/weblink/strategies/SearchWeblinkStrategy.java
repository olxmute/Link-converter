package com.trendyol.linkconverter.weblink.strategies;

import com.trendyol.linkconverter.constants.CommonConstants;
import com.trendyol.linkconverter.constants.DeeplinkConstants;
import com.trendyol.linkconverter.constants.WeblinkConstants;
import com.trendyol.linkconverter.deeplink.enums.DeeplinkPage;
import com.trendyol.linkconverter.persistence.LinkRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Strategy for search weblinks.
 */
@Component
public class SearchWeblinkStrategy extends AbstractWeblinkStrategyPersistableTemplate {

    public SearchWeblinkStrategy(LinkRepository linkRepository) {
        super(linkRepository);
    }

    /**
     * Converts search page weblink to deeplink
     */
    @Override
    protected String createNewResponseLink(UriComponents weblinkUri) {
        var queryParams = weblinkUri.getQueryParams();

        var deeplinkUri = UriComponentsBuilder.fromUriString(DeeplinkConstants.BASE_URI)
                .queryParam(DeeplinkConstants.QueryParams.PAGE, DeeplinkPage.SEARCH.getValue())
                .queryParam(DeeplinkConstants.QueryParams.QUERY, queryParams.get(WeblinkConstants.QueryParam.QUERY))
                .build();

        return deeplinkUri.toString();
    }

    /**
     * Returns true if the weblink is a search weblink.
     * It means that the weblink has the only one segment "/sr".
     * For example: https://www.trendyol.com/sr?q=elbise
     */
    @Override
    public boolean isWeblinkApplicable(UriComponents weblinkUri) {
        var pathSegments = weblinkUri.getPathSegments();
        var applicableSegmentCount = 1;
        if (pathSegments.size() != applicableSegmentCount) {
            return false;
        }
        return pathSegments.get(CommonConstants.FIRST_ELEMENT_INDEX).equals(WeblinkConstants.PathSegments.SR);
    }
}
