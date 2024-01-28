package com.example.springlearning.service.thrift;

import com.example.springlearning.entity.thrift.TModel;
import com.facebook.swift.service.ThriftMethod;
import com.facebook.swift.service.ThriftService;

@ThriftService
public interface TModelService {
    @ThriftMethod
    TModel queryModel();
}
