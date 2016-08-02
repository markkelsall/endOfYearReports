package com.mk.projects.cbUtil;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.transcoder.JsonTranscoder;

public class BucketOperations {

	private Logger logger = LogManager.getLogger(getClass().getName());
	private Bucket bucket;
	
	public BucketOperations (Bucket bucket) {
		this.bucket = bucket;
	}
	
	public String readDocument(String id) {
		logger.trace("Entering BucketOperations.readDocument()");
		
		String response = null;
		if (id == null) {
			return response;
		}
		
		response = this.bucket.get(id).toString();
		
		logger.trace("Exiting BucketOperations.readDocument()");
		
		return response;
	}
	
	public String upsertDocument(String id, String json) {
		logger.trace("Entering BucketOperations.updateDocument()");
		
		String response = null;
		if (id == null) {
			return response;
		}
		
		JsonDocument document = this._stringToJsonDocument(id, json);
		response = this.bucket.upsert(document).toString();
		
		logger.trace("Exiting BucketOperations.updateDocument()");
		
		return response;
	}
	
	public String deleteDocument(String id) {
		logger.trace("Entering BucketOperations.deleteDocument()");
		
		String response = null;
		if (id == null) {
			return response;
		}
		
		response = this.bucket.remove(id).toString();
		
		logger.trace("Exiting BucketOperations.deleteDocument()");
		
		return response;
	}
	
	private JsonDocument _stringToJsonDocument (String id, String json) {
		JsonTranscoder trans = new JsonTranscoder();
		JsonObject jsonObj = null;
		JsonDocument jsonDocument = null;
		try {
			jsonObj = trans.stringToJsonObject(json);
			jsonDocument = JsonDocument.create(id, jsonObj);
		} catch (Exception e) {
			logger.error("[E551924] Error when transforming JSON string");
			logger.error(e.getLocalizedMessage());
		}
		return jsonDocument;
	}
}
