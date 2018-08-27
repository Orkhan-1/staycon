package com.staycon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

/**
 * Created by Orkhan Gasanov on May 07, 2018
 */

@Configuration
public class ViewConfiguration {

    @Bean
    public TilesConfigurer tilesBasedConfigurer () {
        TilesConfigurer tilesConfigurer = new TilesConfigurer ();
        String [] tiles = {"/WEB-INF/tiles.xml"};
        tilesConfigurer.setDefinitions(tiles);
        return tilesConfigurer;
    }

    @Bean
    public UrlBasedViewResolver tilesBasedViewResolver () {
        UrlBasedViewResolver urlBasedViewResolver = new UrlBasedViewResolver ();
        urlBasedViewResolver.setViewClass(TilesView.class);
        return urlBasedViewResolver;
    }
}
