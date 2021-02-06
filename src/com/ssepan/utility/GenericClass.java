/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssepan.utility;

//import java.lang.reflect.Type;
//import java.lang.Class;
public class GenericClass<T1>
{

    protected  Class<T1> _type1;

    public GenericClass(Class<T1> type1)
    {
        this._type1 = type1;
    }

    public Class<T1> getType1()
    {
        return this._type1;
    }
}
