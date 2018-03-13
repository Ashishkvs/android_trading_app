package com.datazi.fragments;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Created by Ashish on 3/12/2018.
 */

public class Seller {
    private String name;//seller name
    private String productType; //coir or conconut
    private double qty;
    private Date createdAt;
    private boolean isDeal;


    /**
     * if qty meets to indiv req  eg.ramesh,rohan,amit
     * then filter based on did they made any transaction or not
     * suppose amit has done transact then filter based on earlier reg seller
     * and giv them this deal to transact
     *
     */
   /* double reqQty=500.0d;
    List<Seller> allSeller=new ArrayList<>();
    List<Seller> dealAssignerList=new ArrayList<>();
    public void selectSeller() {
        for (Seller seller : allSeller) {
                if (seller.isDeal == false) {

                    if (reqQty <= seller.getQty()) {
                    //add such seller to a list
                        dealAssignerList.add(seller);
                    }
            }
        }
    }
    //now based on dealAssignerList filter based on earlier date of reg
    public void earlySellerReg(){
        for(int i=0;i<dealAssignerList.size();i++){  //suppose size is 3

            //dealAssignerList[i].getDate()>dealAssignerList[i];

        }
    }
*/

}
