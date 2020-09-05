package com.direi.cursomc.services.exception;

	public class MethodArgumentException extends RuntimeException{

		private static final long serialVersionUID = 1L;

		public MethodArgumentException(String msg) {
			super(msg);
		}
		
		public MethodArgumentException(String msg, Throwable cause) {
			super(msg,cause);
		}
	}

