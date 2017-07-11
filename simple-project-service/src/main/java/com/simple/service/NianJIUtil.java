package com.simple.service;

public class NianJIUtil {

	public static String increseNianJi(String current) {
		try {
			return "0"+(Integer.parseInt(current)+1);
		}catch(Exception e) {
			return null;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(increseNianJi("01"));
	}
}
