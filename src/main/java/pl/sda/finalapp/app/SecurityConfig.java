package pl.sda.finalapp.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login").permitAll() //każdy może wejść
                .antMatchers("/registration").permitAll() //każdy może wejść
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("pasword")
                .loginProcessingUrl("/login-process")
                .failureUrl("/login?error=1")
                .defaultSuccessUrl("/")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .usersByUsernameQuery("SELECT u.e_mail, u.password_hash, 1 FROM User u WHERE u.e_mail = ?")
                .authoritiesByUsernameQuery("SELECT u.e_mail, r.role_name, 1 " +
                        "FROM User u " +
                        "LEFT JOIN User_role ur ON u.id = ur.user_id" +
                        "LEFT JOIN Role r ON ur.role_id = r.id" +
                        "WHERE u.e_mail = ?")
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder);
    }
}
