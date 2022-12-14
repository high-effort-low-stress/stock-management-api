package com.github.hels.stockmanagement.http.pagination;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

@Getter
@NoArgsConstructor
public class PageOutput<P> {
    private List<P> data;
    private Metadata metadata;

    public PageOutput(Page<P> page) {
        this.data = page.getContent();
        this.metadata = new Metadata(new Pagination(page.getNumber(), page.getSize(), page.getTotalElements(), page.getNumberOfElements()));
    }

    public PageOutput(List<P> data, int offset, int limit, long totalRecords, int returnedRecords) {
        this.data = data;
        this.metadata = new Metadata(new Pagination(offset, limit, totalRecords, returnedRecords));
    }

    public PageOutput(List<P> data, Metadata metadata) {
        this.data = data;
        this.metadata = metadata;
    }

    public PageOutput<P> forEach(Consumer<P> converter) {
        data.forEach(converter);
        return this;
    }

    public <O> PageOutput<O> map(Function<P, O> converter) {
        List<O> convertedData = this.data.stream().map(converter).toList();
        return new PageOutput<>(convertedData, metadata);
    }

    @Getter
    @AllArgsConstructor
    public static class Metadata {
        private Pagination pagination;
    }

    @Getter
    @AllArgsConstructor
    public static class Pagination {
        private int offset;
        private int limit;
        private long totalRecords;
        private int returnedRecords;
    }
}
