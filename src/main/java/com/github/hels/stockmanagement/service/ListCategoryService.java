package com.github.hels.stockmanagement.service;

import com.github.hels.stockmanagement.dto.input.ListCategoryInputDTO;
import com.github.hels.stockmanagement.http.pagination.PageInput;
import com.github.hels.stockmanagement.http.pagination.PageOutput;
import com.github.hels.stockmanagement.model.Category;
import com.github.hels.stockmanagement.repository.ICategoryRepository;
import com.github.hels.stockmanagement.repository.specification.ListCategorySpecification;
import com.github.hels.stockmanagement.repository.specification.Operator;
import com.github.hels.stockmanagement.repository.input.ListCategoryInput;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ListCategoryService {
    private final ICategoryRepository repository;

    public PageOutput<Category> execute(ListCategoryInputDTO listCategoryInputDTO, PageInput pageInput) {
        ListCategoryInput listCategoryInput = buildInput(listCategoryInputDTO);
        Pageable pageable = buildPageable(pageInput);

        Page<Category> page = repository.findAll(new ListCategorySpecification(listCategoryInput), pageable);

        return new PageOutput<>(page);
    }

    private ListCategoryInput buildInput(ListCategoryInputDTO listCategoryInputDTO){
        ListCategoryInput input = new ListCategoryInput();
        input.addRootField("name", Operator.LIKE, listCategoryInputDTO.getName())
                .addRootField("uuid", Operator.EQUAL, listCategoryInputDTO.getUuid());
        return input;
    }

    private Pageable buildPageable(PageInput pageInput){
        Sort sort = Sort.by(Sort.Direction.fromString(pageInput.getOrderBy().toString()), pageInput.getSortBy());
        return PageRequest.of(pageInput.getOffset(), pageInput.getLimit(), sort);
    }
}
