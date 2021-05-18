package com.easyjob.jobmanager.util;

import org.springframework.data.domain.Sort;

public class JobManagerConstants {
    public static final Integer DEFAULT_PAGE_NUMBER = 0;
    public static final Integer DEFAULT_PAGE_SIZE = 10;

    public static final Sort.Direction DEFAULT_SORT_DIRECTION = Sort.Direction.DESC;

    private JobManagerConstants() {
    }
}
