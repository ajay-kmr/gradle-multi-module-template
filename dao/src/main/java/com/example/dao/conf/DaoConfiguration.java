package com.example.dao.conf;


import lombok.extern.apachecommons.CommonsLog;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@CommonsLog
@Configuration
@ComponentScan(basePackageClasses = {JPAConfiguration.class})
public class DaoConfiguration {

}
