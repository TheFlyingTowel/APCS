package com.Spoofy.local.Utils;

public class StackV2<T> {
	 private int size = 0;
	    
	    @SuppressWarnings("unchecked")
		private T[] stackV2 = (T[]) new Object[size];
	    
	    
	    public void showCurrentStack(){
	    	String s  = "";
	        for(int i = 0; i < stackV2.length;i++) {
	        	if(i < (stackV2.length - 1)) {
	        		s += stackV2[i]+", ";
	        	}else {
	        		s += stackV2[i];
	        	}
	        }
	        System.out.println("Stack -> ["+s+"]");
	    }
	    
	    public void setSize(int s) {
	    	if(s < size) {
	    		int v = size - s;
	    		for(int n = 0; n < v; n++) {
	    			popBottom();
	    		}
	    	}else if(s > size) {
	    		int v = s - size;
	    		for(int n = 0; n < v;n++) {
	    			pushBottom(null);
	    		}
	    	}
	    	
	    	
	    }
	    
	    @SuppressWarnings("unchecked")
		private T[] copy(T[] a) {
	    	T[] b = (T[]) new Object[a.length];
	    	for(int i = 0; i < a.length;i++) {
	    		b[i] = a[i];
	    	}
	    	return b;
	    }
	    
	    @SuppressWarnings("unchecked")
		public void pushTop(T element) {
	    	T[] old = copy(stackV2);
	    	stackV2 = (T[]) new Object[++size];
	    	stackV2[0] = element;
	    
	    		for(int i = 1; i < stackV2.length; i++) {
	    			stackV2[i] = old[i - 1];
	        	}
	    	
	      
	    }

	    @SuppressWarnings("unchecked")
		public void pushBottom(T element) {
	       T[] old = copy(stackV2);
	       stackV2 = (T[]) new Object[++size];
	       stackV2[size - 1] = element;
	       for(int i = 0; i < old.length; i++) {
	    	   stackV2[i] = old[i];
	   	}

	    }

	    @SuppressWarnings("unchecked")
		public T popTop() {
	    	T out = null;
	    	T[] old = copy(stackV2);
	    	out = stackV2[0];
	    	stackV2 = (T[]) new Object[--size];
	    	for(int i = 0; i  < stackV2.length;i++) {
	    		stackV2[i] = old[i + 1];
	    	}
	    	
	    	return out;
	    }

	    @SuppressWarnings("unchecked")
		public T popBottom() {
	    	T out = stackV2[stackV2.length - 1];
	    	T[] old = copy(stackV2);
	    	stackV2 = (T[]) new Object[--size];
	    	for(int i = 0; i < stackV2.length;i++) {
	    		stackV2[i] = old[i];
	    	}
	    	return out;
	    }

	    public int indexOf(T elemnt) {
	    	for(int i = 0; i < stackV2.length;i++) {
	    		if(stackV2[i] == elemnt)return i;
	    	}
	    	return -1;
	    }
	    
	    public T elementAt(int index) {
	    	if(((index <= stackV2.length)) && ((index >= 0)))return stackV2[index];
	    	else throw new ArrayIndexOutOfBoundsException(index);
	    }
	    
	    public int getSize() {
	    	return size;
	    }
}
