package com.postnov.library.service.OtherService.Impl;

import com.postnov.library.service.OtherService.ConvertService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ConvertServiceImpl<Dto, NotDto> implements ConvertService<Dto, NotDto> {

    private final ModelMapper modelMapper;

    public ConvertServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Set<Dto> convertToSetDto(Set<NotDto> notDtos, Class<Dto> classDto) {
        Set<Dto> dto = new HashSet<>();
        for (NotDto notDto : notDtos) {
            dto.add(convertToDto(notDto, classDto));
        }
        return dto;
    }

    @Override
    public Set<NotDto> convertFromSetDto(Set<Dto> dtos, Class<NotDto> classNotDto) {
        Set<NotDto> notDtos = new HashSet<>();
        for (Dto dto : dtos) {
            notDtos.add(convertFromDto(dto, classNotDto));
        }
        return notDtos;
    }

    @Override
    public Dto convertToDto(NotDto notDto, Class<Dto> classDto) {
        return modelMapper.map(notDto, classDto);
    }

    @Override
    public NotDto convertFromDto(Dto dto, Class<NotDto> classNotDto) {
        return modelMapper.map(dto, classNotDto);
    }
}
