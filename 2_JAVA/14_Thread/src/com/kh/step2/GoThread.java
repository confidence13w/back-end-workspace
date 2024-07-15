package com.kh.step2;

public class GoThread implements Runnable{
	
	@Override
	public void run() {
		for(int i = 0; i < 20; i++) {
			
			try {
				Thread.sleep(1000); // 지정된 시간동안 스레드를 멈추게 하는 역할
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			String name = Thread.currentThread().getName();
			System.out.println("CurrentThread : " + name + ", " + i);
		}
		
	}
	
}
