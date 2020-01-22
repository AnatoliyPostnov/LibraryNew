package com.postnov.library.service.OtherService;

import java.util.List;
import java.util.Set;

public interface ConvertService<Dto, NotDto> {

    public Set<Dto> convertToSetDto(Set<NotDto> notDtos, Class<Dto> classDto);

    public Set<NotDto> convertFromSetDto(Set<Dto> dtos, Class<NotDto> classNotDto);

    public Dto convertToDto(NotDto notDto, Class<Dto> classDto);

    public NotDto convertFromDto(Dto dto, Class<NotDto> classNotDto);

}
