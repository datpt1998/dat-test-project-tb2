package com.example.Service;

public class FuncInterfaceCall {
    public void call(int a, int b) {
        TestFuncInterface testFuncInterface = new TestFuncInterface();
        testFuncInterface.test(FuncInterfaceImpl::add, a, b);
    }
}
