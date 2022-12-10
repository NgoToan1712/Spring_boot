package com.company.opentalk.configuration;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        // tạo obj và cấu hinh
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);//đặt loại mapping
        return modelMapper;
    }
    //*MatchingStrategies(mức dộ mapping):
    // - standard: ko quan tâm thứ tự; mọi tt nguồn pha tồn tại trong tt đích;mọi từ của tt đích phải khớp hết
    // -Loose: ko qtam tt; từ cuối cùng phải có trong thuộc tính đích, từ cuối tt đích khớp
    // -Strict: đúng tt;mọi từ tt nguồn phải khớp toàn bộ, mọi thuộc tính đích phải khớp

    //setSkipNullEnabled có cho phép bỏ qua thuộc tính null hay không
    //setDeepCopyEnabled mặc định dùng shallow copy, dùng deep copy thì sẽ chậm hơn.
    //setFieldAccessLevel chỉ định field truy cập ở mức độ nào (private, public,...)
    //setMethodAccessLevel chỉ định mức độ truy cập method, getter và setter
    //setSourceNameTokenizer chỉ định quy ước đặt tên cho thuộc tính ở source
    //setDestinationNameTokenizer chỉ định quy ước đặt tên cho thuộc tính ở đích
}
