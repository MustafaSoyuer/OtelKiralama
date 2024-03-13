package com.mustafa.service;

import com.mustafa.domain.OtelOzellik;
import com.mustafa.domain.Ozellik;
import com.mustafa.dto.request.SaveOzellikRequestDto;
import com.mustafa.dto.response.GetAllParentsResponseDto;
import com.mustafa.repository.OzellikRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OzellikService {
    private final OzellikRepository ozellikRepository;
    private final OtelOzellikService otelOzellikService;
    private final CacheManager cacheManager;

    public String saveOzellik(SaveOzellikRequestDto dto) {
        Ozellik ozellik = Ozellik.builder()
                .name(dto.getName())
                .parentId(dto.getParentId())
                .parentName(dto.getParentName())
                .active(true)
                .build();
        ozellikRepository.save(ozellik);
        OtelOzellik otelOzellik = otelOzellikService.saveOtelOzellik(ozellik.getId(), dto.getOtelId());
        // buraya bakılacak
        Objects.requireNonNull(cacheManager.getCache("get-all-parents")).clear();
        return ozellik.getId();
    }

    @Cacheable(value = "get-all-parents")
    public List<GetAllParentsResponseDto> getAllParents() {
//        List<GetAllParentsResponseDto> getMyList = new ArrayList<>();
//        List<String> names = ozellikRepository.findAll().stream().map(Ozellik::getName).collect(Collectors.toList());
//        names.forEach(x->{
//            getMyList.add(
//              GetAllParentsResponseDto.builder()
//                    .ozellikName(x)
//                    .parentId(ozellikRepository.findFirstByName(x).get().getId()) // burası hata verebilir
//                    .ozellikler(ozellikRepository.findAll())
//                    .build())
//        });
//        return getMyList;
        List<GetAllParentsResponseDto> getMyList = new ArrayList<>();
        Set<String> parentNames = ozellikRepository.findAll().stream().collect(Collectors.mapping(Ozellik::getParentName, Collectors.toSet()));
        parentNames.forEach(x->{
            getMyList.add(
                    GetAllParentsResponseDto.builder()
                            .parentName(x)
                            .parentId(ozellikRepository.findAllByParentName(x).stream().map(y-> y.getParentId()).findFirst().get())
                            .ozellikler(ozellikRepository.findAllByParentName(x))
                            .build())
            ;
        });
        return getMyList;

    }
}
