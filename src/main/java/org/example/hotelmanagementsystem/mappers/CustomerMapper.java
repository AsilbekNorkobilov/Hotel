package org.example.hotelmanagementsystem.mappers;

import org.example.hotelmanagementsystem.entity.User;
import org.example.hotelmanagementsystem.model.reqDto.RegisterDto;
import org.example.hotelmanagementsystem.model.resDto.CustomerResDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CustomerMapper {
    User toEntity(RegisterDto customerReqDto);
    CustomerResDto toDto(User user);

}