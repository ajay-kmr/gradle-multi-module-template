package com.example.dao.conf;


import com.example.dao.entity.EntityMarker;
import com.example.dao.entity.User;
import com.example.dao.repository.RepositoryMarker;
import com.example.dao.repository.UserRepository;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@CommonsLog
@Configuration
@EnableTransactionManagement
@EntityScan(basePackageClasses = {EntityMarker.class})
@EnableJpaRepositories(basePackageClasses = {RepositoryMarker.class})
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
//@PropertySource(value = {"classpath:config/application-core.yml"})
public class JPAConfiguration {

    @Autowired
    UserRepository userRepository;

    @Bean
    public AuditorAware<User> auditorAware() {
        return new AuditorAwareImpl(userRepository);
    }
}
