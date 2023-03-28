//package nl.fontys.s3.comfyshop.security.config;
//
//import lombok.AllArgsConstructor;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Configuration
//@EnableWebSecurity
//@AllArgsConstructor
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
////    private final AppUserService appUserService;
////    private final BCryptPasswordEncoder bCryptPasswordEncoder;
////
////    @Override
////    public void configure(HttpSecurity http) throws Exception {
////        http
////                .csrf().disable().authorizeRequests()
////                .antMatchers("/registration/**")
////                .permitAll()
////                .anyRequest()
////                .authenticated().and()
////                .formLogin();
////    }
////    @Override
////    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        auth.authenticationProvider(daoAuthenticationProvider());
////    }
////    @Bean
////    public DaoAuthenticationProvider daoAuthenticationProvider() {
////        DaoAuthenticationProvider provider =
////                new DaoAuthenticationProvider();
////        provider.setPasswordEncoder(bCryptPasswordEncoder);
////        provider.setUserDetailsService(appUserService);
////        return provider;
////    }
//}
