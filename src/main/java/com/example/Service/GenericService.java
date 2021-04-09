package com.example.Service;

import com.example.Interface.GenericFuncInterface;

import java.util.List;

public class GenericService<T> {
    private List<T> targets;

    public void setTargets(List<T> targets) {
        this.targets = targets;
    }

    public void doFunc(GenericFuncInterface<T> genericFuncInterface){
        for(T t : targets) {
            genericFuncInterface.apply(t);
        }
    };
}
