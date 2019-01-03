package com.example.commonmodel.config;


import com.example.commonmodel.ComponentScanMarker;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@CommonsLog
@Configuration
@ComponentScan(basePackageClasses = {ComponentScanMarker.class})
public class CommonModelConfiguration {
}
