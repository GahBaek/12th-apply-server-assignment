package org.example;

public class Complain{
    String id;
    String context;
    Complain(String id_, String context_){
        id = id_; context = context_;
    }
    public void getString() {
        System.out.println("문의 아이디: "+this.id);
        System.out.println("문의 내용: "+this.context);
    }
}