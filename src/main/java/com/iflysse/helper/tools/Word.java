package com.iflysse.helper.tools;

import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;

import com.itextpdf.text.Document;

public class Word {
	private Document document;
	private ByteArrayOutputStream byteOut;
	
	public Word(Document document, ByteArrayOutputStream byteOut) {
		super();
		this.document = document;
		this.byteOut = byteOut;
	}
	
	public void close() {
		document.close();
	}
	
	public byte[] get_byte_array() {
		return byteOut.toByteArray();
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public ByteArrayOutputStream getByteOut() {
		return byteOut;
	}

	public void setByteOut(ByteArrayOutputStream byteOut) {
		this.byteOut = byteOut;
	}
	
}
