//package com.dmz.basic.config;
//
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.validation.annotation.Validated;
//
//import javax.validation.constraints.NotNull;
//import java.util.Properties;
//
///**
// * @author dmz
// * @date 2017/7/17
// */
//@ConfigurationProperties(prefix = "mysql", locations = "data.properties", ignoreUnknownFields = false)
//@Validated
//public class MySQLProperties {
//
//
//    @NotNull(message = "URL不能为空")
//    private String url;
//    private String name;
//    private String password;
//    private Boolean cachePrepStmts;
//    private Integer prepStmtCacheSize;
//    private Integer prepStmtCacheSqlLimit;
//    private Boolean useServerPrepStmts;
//    private Boolean useLocalSessionState;
//    private Boolean useLocalTransactionState;
//    private Boolean rewriteBatchedStatements;
//    private Boolean cacheResultSetMetadata;
//    private Boolean cacheServerConfiguration;
//    private Boolean elideSetAutoCommits;
//    private Boolean maintainTimeStats;
//
//    public String getUrl() {
//        return url;
//    }
//
//    public void setUrl(String url) {
//        this.url = url;
//        new Properties();
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public Boolean getCachePrepStmts() {
//        return cachePrepStmts;
//    }
//
//    public void setCachePrepStmts(Boolean cachePrepStmts) {
//        this.cachePrepStmts = cachePrepStmts;
//    }
//
//    public Integer getPrepStmtCacheSize() {
//        return prepStmtCacheSize;
//    }
//
//    public void setPrepStmtCacheSize(Integer prepStmtCacheSize) {
//        this.prepStmtCacheSize = prepStmtCacheSize;
//    }
//
//    public Integer getPrepStmtCacheSqlLimit() {
//        return prepStmtCacheSqlLimit;
//    }
//
//    public void setPrepStmtCacheSqlLimit(Integer prepStmtCacheSqlLimit) {
//        this.prepStmtCacheSqlLimit = prepStmtCacheSqlLimit;
//    }
//
//    public Boolean getUseServerPrepStmts() {
//        return useServerPrepStmts;
//    }
//
//    public void setUseServerPrepStmts(Boolean useServerPrepStmts) {
//        this.useServerPrepStmts = useServerPrepStmts;
//    }
//
//    public Boolean getUseLocalSessionState() {
//        return useLocalSessionState;
//    }
//
//    public void setUseLocalSessionState(Boolean useLocalSessionState) {
//        this.useLocalSessionState = useLocalSessionState;
//    }
//
//    public Boolean getUseLocalTransactionState() {
//        return useLocalTransactionState;
//    }
//
//    public void setUseLocalTransactionState(Boolean useLocalTransactionState) {
//        this.useLocalTransactionState = useLocalTransactionState;
//    }
//
//    public Boolean getRewriteBatchedStatements() {
//        return rewriteBatchedStatements;
//    }
//
//    public void setRewriteBatchedStatements(Boolean rewriteBatchedStatements) {
//        this.rewriteBatchedStatements = rewriteBatchedStatements;
//    }
//
//    public Boolean getCacheResultSetMetadata() {
//        return cacheResultSetMetadata;
//    }
//
//    public void setCacheResultSetMetadata(Boolean cacheResultSetMetadata) {
//        this.cacheResultSetMetadata = cacheResultSetMetadata;
//    }
//
//    public Boolean getCacheServerConfiguration() {
//        return cacheServerConfiguration;
//    }
//
//    public void setCacheServerConfiguration(Boolean cacheServerConfiguration) {
//        this.cacheServerConfiguration = cacheServerConfiguration;
//    }
//
//    public Boolean getElideSetAutoCommits() {
//        return elideSetAutoCommits;
//    }
//
//    public void setElideSetAutoCommits(Boolean elideSetAutoCommits) {
//        this.elideSetAutoCommits = elideSetAutoCommits;
//    }
//
//    public Boolean getMaintainTimeStats() {
//        return maintainTimeStats;
//    }
//
//    public void setMaintainTimeStats(Boolean maintainTimeStats) {
//        this.maintainTimeStats = maintainTimeStats;
//    }
//}
