package com.example.simpleblog.domain.post;

import com.example.simpleblog.domain.AuditingEntity;

public class JavaPost extends AuditingEntity {



    private String name;


    public JavaPost(long id, String name) {
        this.name = name;
    }

    public JavaPost(long id) {
        this.setId(id);
    }

}
