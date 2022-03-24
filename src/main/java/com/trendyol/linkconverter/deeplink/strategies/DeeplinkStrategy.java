package com.trendyol.linkconverter.deeplink.strategies;

import com.trendyol.linkconverter.deeplink.enums.DeeplinkPage;
import org.springframework.web.util.UriComponents;

public interface DeeplinkStrategy {

    String getWeblink(UriComponents deeplinkUri);

    DeeplinkPage getApplicableDeeplinkPage();

}