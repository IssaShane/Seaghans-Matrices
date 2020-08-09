package com.seaghansmatrices;

public class Posn {
	public int x, y, w, h = 0;
	
	public Posn(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	public Posn(float x, float y, float w, float h) {
		this.x = (int) x;
		this.y = (int) y;
		this.w = (int) w;
		this.h = (int) h;
	}
	
	public Posn(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Posn(Posn other) {
		this.x = other.x;
		this.y = other.y;
		this.w = other.w;
		this.h = other.h;
	}

	public void print() {
		System.out.print("(");
		System.out.print(this.x);
		System.out.print(",");
		System.out.print(this.y);
		System.out.print(",");
		System.out.print(this.w);
		System.out.print(",");
		System.out.print(this.h);
		System.out.println(")");
	}
}
