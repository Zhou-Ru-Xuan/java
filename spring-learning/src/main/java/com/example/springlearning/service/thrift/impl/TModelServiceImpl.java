package com.example.springlearning.service.thrift.impl;

import com.example.springlearning.entity.thrift.TModel;
import com.example.springlearning.service.thrift.TModelService;
import org.springframework.stereotype.Service;

@Service("tModelServiceImpl")
public class TModelServiceImpl implements TModelService {
    @Override
    public TModel queryModel() {
        TModel tModel = new TModel();
        tModel.setMBoxId(true);
        tModel.setMBoxId(false);
        return tModel;
    }
}
