package com.trendyol.linkconverter.deeplink;

import com.trendyol.linkconverter.deeplink.enums.DeeplinkPage;
import com.trendyol.linkconverter.deeplink.strategies.DeeplinkStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponents;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

@Component
public class DeeplinkStrategyFactory {

    private final Map<DeeplinkPage, DeeplinkStrategy> strategies;

    public DeeplinkStrategyFactory(List<DeeplinkStrategy> strategies) {
        this.strategies = strategies.stream()
                .collect(toMap(DeeplinkStrategy::getApplicableDeeplinkPage, Function.identity()));
    }

    public DeeplinkStrategy getDeeplinkStrategy(UriComponents deeplinkUri) {
        var page = deeplinkUri.getQueryParams().get("Page");
        if (page.size() != 1) {
            throw new RuntimeException("Multiple 'Page' parameters in deeplinks not supported");
        }

        return strategies.get(DeeplinkPage.fromString(page.get(0)));
    }

}