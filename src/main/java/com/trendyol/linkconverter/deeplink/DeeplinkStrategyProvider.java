package com.trendyol.linkconverter.deeplink;

import com.trendyol.linkconverter.constants.CommonConstants;
import com.trendyol.linkconverter.constants.DeeplinkConstants;
import com.trendyol.linkconverter.constants.ErrorMessage;
import com.trendyol.linkconverter.deeplink.enums.DeeplinkPage;
import com.trendyol.linkconverter.deeplink.strategies.DeeplinkStrategy;
import com.trendyol.linkconverter.exception.InvalidParameterException;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponents;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

/**
 * Class for providing deeplink strategies.
 */
@Component
public class DeeplinkStrategyProvider {

    private final Map<DeeplinkPage, DeeplinkStrategy> strategies;

    public DeeplinkStrategyProvider(List<DeeplinkStrategy> strategies) {
        this.strategies = strategies.stream()
                .collect(toMap(DeeplinkStrategy::getApplicableDeeplinkPage, Function.identity()));
    }

    /**
     * Get the appropriate strategy for the given deeplink page.
     */
    public DeeplinkStrategy getDeeplinkStrategy(UriComponents deeplinkUri) {
        var page = deeplinkUri.getQueryParams().get(DeeplinkConstants.QueryParams.PAGE);
        var applicableSegmentCount = 1;
        if (page.size() != applicableSegmentCount) {
            throw new InvalidParameterException(ErrorMessage.MULTIPLE_PAGE);
        }

        return strategies.get(DeeplinkPage.fromString(page.get(CommonConstants.FIRST_ELEMENT_INDEX)));
    }

}
