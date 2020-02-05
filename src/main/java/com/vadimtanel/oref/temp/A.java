package com.vadimtanel.oref.temp;

/*******************************************************************************
 *  Created by Vadim Tanel on 02/02/2020 16:33.
 *  Copyright © 2020 Vadim Tanel.
 *  All rights reserved.
 ******************************************************************************/

public class A {
    protected int x;
    protected int y;

    public A(int x, int y){
        this.x = x;
        this.y = y;
        System.out.println("Constructor A");
    }

    public void printX(){
        System.out.println("A printX: " + this.x);
    }

    public void printY(){
        System.out.println("A printY: " + this.y);
    }
}
