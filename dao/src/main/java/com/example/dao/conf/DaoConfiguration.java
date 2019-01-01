package com.example.dao.conf;


import com.example.dao.ComponentScanMarker;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@CommonsLog
@Configuration
@ComponentScan(basePackageClasses = {ComponentScanMarker.class})
public class DaoConfiguration {
}
