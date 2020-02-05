package com.vadimtanel.oref.temp;

/*******************************************************************************
 *  Created by Vadim Tanel on 02/02/2020 16:33.
 *  Copyright © 2020 Vadim Tanel.
 *  All rights reserved.
 ******************************************************************************/

public class C extends B {
    private int z;

    public C(int x, int y, int z){
        super(x,y);
        this.z = z;
        System.out.println("Constructor C");
    }

    public void printX(){
        System.out.println("C printX: " + this.x);
    }

    public void printAll(){
        System.out.println("C printAll, x: " + this.x + " y: " + this.y + " z: " + this.z);
    }

    public void printZ(){
        System.out.println("C printZ, z: " + this.z);
    }
}
