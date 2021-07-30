package com.michal.openfda.service.fda;

import com.michal.openfda.dto.fdaapi.response.Result;
import com.michal.openfda.exceptions.FdaConnectionException;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public interface FdaService {
    PageImpl<Result> findApplications(String manufacturerName, String brandName, Pageable pageable) throws FdaConnectionException;
}
