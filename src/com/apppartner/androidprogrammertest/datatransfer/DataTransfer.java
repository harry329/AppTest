package com.apppartner.androidprogrammertest.datatransfer;

import java.util.List;

import com.apppartner.androidprogrammertest.data.DataClass;

public class DataTransfer {
	private static List<DataClass> datalist=null;
	
	public DataTransfer(List<DataClass> datalist){
		this.datalist=datalist;
	}
	
	public static List<DataClass> getList(){
		return datalist;
	}
}
