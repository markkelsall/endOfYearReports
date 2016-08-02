package com.mk.projects.cbUtil;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.CouchbaseCluster;

@ApplicationScoped
public class ConnectionManager {
	
	private Logger logger = LogManager.getLogger(getClass().getName());
	
	private CouchbaseCluster cluster;
	private Bucket bucket;
	private BucketOperations bucketOperations;
	
	@PostConstruct
	public void init () {
		logger.trace("Entering ConnectioManager.init()");
		
		this.cluster = CouchbaseCluster.create("localhost");
        this.bucket = cluster.openBucket("endOfYearReports");
        
        bucketOperations = new BucketOperations(this.bucket);
		
		logger.trace("Exiting ConnectioManager.init()");
	}
	
	@PreDestroy
    public void destroy() {
		logger.trace("Entering ConnectioManager.destroy()");
		if (this.bucket != null) {
			this.bucketOperations = null;
			this.bucket.close();
			this.cluster.disconnect();
		}
		logger.trace("Exiting ConnectioManager.destroy()");
	}

    public BucketOperations getBucketOperations() {
        return this.bucketOperations;
    }
}
