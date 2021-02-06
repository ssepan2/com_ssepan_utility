/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ssepan.utility;

import java.io.File;

/**
 *
 * @author ssepan
 */
public class Files
{

    /*
     * by http://stackoverflow.com/users/22656/jon-skeet
     * on http://stackoverflow.com/questions/412380/combine-paths-in-java
     * @param path1
     * @param path2
     * @return
     */
    public static String PathCombine(String path1,
                                     String path2)
    {
        File file1 = new File(path1);
        File file2 = new File(file1, path2);
        return file2.getPath();
    }
   
}
