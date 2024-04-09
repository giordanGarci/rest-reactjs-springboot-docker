package com.giordan.apigateway;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Greeting {
    private final Long id;
    private final String content;
}
