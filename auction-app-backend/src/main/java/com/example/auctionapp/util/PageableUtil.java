package com.example.auctionapp.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageableUtil {
    public static Pageable createPageable(final int page,
                                          final int size,
                                          final String sortField,
                                          final String sortDirection) {
        final Sort sort = createSort(sortField, sortDirection);

        return PageRequest.of(page, size, sort);
    }

    private static Sort createSort(final String sortField, final String sortDirection) {
        if (sortField != null && sortDirection != null) {
            return Sort.by(Sort.Direction.fromString(sortDirection), sortField);
        }
        
        return Sort.unsorted();
    }
}

