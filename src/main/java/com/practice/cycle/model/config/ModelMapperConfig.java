package com.practice.cycle.model.config;


import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//필요시 설정
@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);

//        MatchingStrategies.LOOSE
//         -> signedDate에서 loginDate로 매칭이 가능하다. (토큰 하나 일치(Date))
//
//        MatchingStrategies.STANDARD
//          -> signedDate에서 loginDate로 매칭이 되지 않는다. (토큰 일부 불일치)
//
//        MatchingStrategies.STRICT
//          -> signedDate에서 loginDate로 매칭 시도 시 에러발생

        return modelMapper;
    }
}
