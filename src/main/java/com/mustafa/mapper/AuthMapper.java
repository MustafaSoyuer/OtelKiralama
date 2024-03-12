package com.mustafa.mapper;

import com.mustafa.domain.Auth;
import com.mustafa.dto.request.RegisterRequestDto;
import com.mustafa.dto.response.RegisterResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuthMapper {

    AuthMapper INSTANCE = Mappers.getMapper(AuthMapper.class);

    Auth fromRegisterRequestToAuth(final RegisterRequestDto dto);
    RegisterResponseDto fromAuthToRegisterResponseDto(final Auth auth);


}
