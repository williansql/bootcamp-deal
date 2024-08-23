package com.bootcamp.dio.projeto_bootcamp.utils.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pagination {
    private int page;
    private int size;
    private Long offset;
    private int numberOfElements;
    private int numberOfPages;
    private Long totalNumberOfElements;
    private boolean isFirstPage;
    private boolean isLastPage;
    private boolean hasNextPage;

    public static Pagination from(Page<?> page, Pageable pageable) {
        return Pagination.builder()
                .page(page.getNumber() + 1)
                .size(pageable.getPageSize())
                .offset(page.getPageable().getOffset())
                .numberOfElements(page.getNumberOfElements())
                .numberOfPages(page.getTotalPages())
                .totalNumberOfElements(page.getTotalElements())
                .isFirstPage(page.isFirst())
                .isLastPage(page.isLast())
                .hasNextPage(page.hasNext())
                .build();
    }
}
