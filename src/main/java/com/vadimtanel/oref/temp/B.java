package com.vadimtanel.oref.temp;

/*******************************************************************************
 *  Created by Vadim Tanel on 02/02/2020 16:33.
 *  Copyright © 2020 Vadim Tanel.
 *  All rights reserved.
 ******************************************************************************/

public class B extends A {
    public B(int x, int y){
        super(x,y);
        System.out.println("Constructor B");
    }

    public void printX(){
        System.out.println("B printX: " + this.x);
    }

    public void printAll(){
        System.out.println("B printAll, x: " + this.x + " y: " + this.y);
    }
}
