package com.example.web.cepheusservice.mappers;

public interface Mapper<A, B> {

    B mapTo(A a);
    A mapFrom(B b);

}
