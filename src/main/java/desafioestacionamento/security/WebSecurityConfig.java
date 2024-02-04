package desafioestacionamento.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Desabilita a proteção CSRF para simplificar o teste e desenvolvimento
                .csrf().disable()
                // Configurações para permitir todas as solicitações sem autenticação
                .authorizeRequests()
                .anyRequest().permitAll()
        // Pode adicionar outras configurações de segurança conforme necessário
        ;

        return http.build();
    }
}
