package org.example;

// 문의 클래스.
public class Complain{
    // 문의 아이디.
    String id;
    // 문의 내용.
    String context;
    // Complain initialization
    Complain(String id_, String context_){
        id = id_; context = context_;
    }
    // 문의 아이디, 글 보기.
    public void getString() {
        System.out.println("문의 아이디: "+this.id);
        System.out.println("문의 내용: "+this.context);
    }
}