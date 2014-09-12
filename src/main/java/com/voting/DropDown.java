/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.voting;

import javax.faces.bean.ManagedBean;

/**
 *
 * @author velma
 */

@ManagedBean(name="DropDown")
public class DropDown {
    
private int short_code;
public int getShort_code(){
    return short_code;
}
public void setShort_code(int shortcode){
    this.short_code=short_code;
}
}

