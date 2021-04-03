package com.hoon.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.hoon.blog.config.auth.PrincipalDetailService;

@Configuration //bean등록
@EnableWebSecurity // security 필터 추가
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근 하면 권한 및 인증을 미리 체크하겠다
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private PrincipalDetailService principalDetailService;
	
	@Bean
	public BCryptPasswordEncoder encodePWD() {
		
		return new BCryptPasswordEncoder();
	}
	
	//시큐리티가 password를 가로채는데 변환된 해쉬값을 알아야 같은 해쉬로 암호화해서 DB에 있는 해쉬랑 비교
	protected void configure(AuthenticationManagerBuilder auth)throws Exception{
		auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable() //csrf 토큰 비활성화
			.authorizeRequests()
				.antMatchers("/auth/**","/","/js/**","/css/**","/image/**")
				.permitAll() //요청이 들어왔을때 /auth 밑 주소들은 인증이 필요없다
				.anyRequest() 
				.authenticated() //그 외 다른 요청은 필요하다
			.and()
				.formLogin()
				.loginPage("/auth/loginForm")
				.loginProcessingUrl("/auth/loginProc") //시큐리티가 해당 주수로 오는 로그인 요청을 가로 챔  
				.defaultSuccessUrl("/"); //로그인 성공시 이동 경로
				
	}

}
