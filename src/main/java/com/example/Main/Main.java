package com.example.Main;

import com.example.Data.Animal;
import com.example.Data.Base;
import com.example.Data.Child;
import com.example.Service.GenericService;
import com.example.Service.TargetClass;
import com.example.annotation.Test;

import java.awt.*;
import java.io.Console;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        System.out.println();
        System.out.println(null==null);
        System.out.println("Traditional");
        //1
        TargetClass targetClass1 = new TargetClass();
        targetClass1.setA(2);
        targetClass1.setB(3);
        targetClass1.getAdd();

        //2
        TargetClass targetClass2 = new TargetClass();
        targetClass2.setA(5);
        targetClass2.setB(4);
        targetClass2.getAdd();

        //3
        TargetClass targetClass3 = new TargetClass();
        targetClass3.setA(1);
        targetClass3.setB(2);
        targetClass3.getAdd();

        System.out.println("Method Reference");
        List<TargetClass> targets = new ArrayList<>();
        targets.add(targetClass1);
        targets.add(targetClass2);
        targets.add(targetClass3);
        GenericService<TargetClass> genericService = new GenericService<>();
        genericService.setTargets(targets);
        genericService.doFunc(TargetClass::getAdd);

        //test null
        List<TargetClass> targetClasses = new ArrayList<>();
        TargetClass canNull1 = new TargetClass();
        canNull1.setaCanNull(1);
        TargetClass canNull2 = new TargetClass();
        canNull2.setaCanNull(null);
        TargetClass canNull3 = null;
        targetClasses.addAll(Arrays.asList(canNull1, canNull2, canNull3));
//        List<Integer> canNullList = targetClasses.stream().map(TargetClass::getaCanNull).collect(Collectors.toList());
//        System.out.println(canNullList);
//
//        System.out.println("abc");
//
//        System.out.println(5%2);

        System.out.println(targetClasses);

        //test distinct

//        List<Animal> zoo = new ArrayList<>();
//        zoo.add(new Animal(1L));
//        zoo.add(new Animal(1L));
//        zoo.add(new Animal(2L));
//        System.out.println(zoo.stream().distinct().collect(Collectors.toList()));
//
//        Set<Animal> distinctZoo = new HashSet<>(zoo);
//        System.out.println(distinctZoo);
//
//        System.out.println(3|5);

        //TEST NATIVE
//        TestNative testNative = new TestNative();
//        System.out.println(testNative.getSystemTime());

        Integer[] sortTest = {1, 2, 3, 4, 5};
        Arrays.sort(sortTest, (v1,v2)->{return (int) v1-(int) v2;});
        System.out.println(Arrays.asList(sortTest));

        System.out.println(Arrays.asList("a\nb".split("\n")));

        System.out.println((int)12.5);
        System.out.println((double) 12);

        System.out.println(Color.RGBtoHSB(0,0,0,null)[2]);
        System.out.println(Color.RGBtoHSB(255,255,255,null)[2]);
        System.out.println(Color.RGBtoHSB(255,0,0,null)[2]);
        float[] hsb = Color.RGBtoHSB(163, 129, 180, null);
        float h = hsb[0];
        float s = hsb[1];
        float b = hsb[2];
        System.out.println(h+"/"+s+"/"+b);

        //TODO: some todo here
        //normal comment
        //Todo 2
        //todo 3
        //fixme asasdsa
        //conbo hjh
        //CONBO dsdsdsaadas

        System.out.println((char)33);
        Map<String, Integer> testMap = new HashMap<>();
        testMap.put("a", 5);
        testMap.put("b", 6);
        testMap.put("a", 7);
        System.out.println(testMap.get("a"));

        System.out.println("Is base anot:" + Base.class.isAnnotationPresent(Test.class));
        System.out.println("Is child anot:" + Child.class.isAnnotationPresent(Test.class));

        System.out.println("\n".matches("\\p{C}"));
        System.out.println("".length());
        System.out.println(String.valueOf((char)930));
        Base child = new Child();
        System.out.println(child instanceof Base);
        System.out.println(child instanceof Child);
    }
}
