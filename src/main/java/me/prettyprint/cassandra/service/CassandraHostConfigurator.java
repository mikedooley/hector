package me.prettyprint.cassandra.service;


public class CassandraHostConfigurator {

  private String hosts;
  private int maxActive;
  private int maxIdle;
  private boolean lifo;
  private long minEvictableIdleTimeMillis;
  private long timeBetweenEvictionRunsMillis;

  private long maxWaitTimeWhenExhausted;
  private int cassandraThriftSocketTimeout;
  private ExhaustedPolicy exhaustedPolicy;
  private TimestampResolution timestampResolution;


  public CassandraHostConfigurator() {
    this.hosts = null;
  }

  public CassandraHostConfigurator(String hosts) {
    this.hosts = hosts;
  }

  public CassandraHost[] buildCassandraHosts() {
    if (this.hosts == null) {
      return null;
    }
    String[] hostVals = hosts.split(",");
    CassandraHost[] cassandraHosts = new CassandraHost[hostVals.length];
    for (int x=0; x<hostVals.length; x++) {
      CassandraHost cassandraHost = new CassandraHost(hostVals[x]);
      if (maxActive > 0) {
        cassandraHost.setMaxActive(maxActive);
      }
      if (maxIdle != CassandraHost.DEFAULT_MAX_IDLE) {
        cassandraHost.setMaxIdle(maxIdle);
      }
      if (lifo != CassandraHost.DEFAULT_LIFO) {
        cassandraHost.setLifo(lifo);
      }
      if (minEvictableIdleTimeMillis != CassandraHost.DEFAULT_MIN_EVICTABLE_IDLE_TIME_MILLIS) {
        cassandraHost.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
      }
      if (timeBetweenEvictionRunsMillis != CassandraHost.DEFAULT_TIME_BETWEEN_EVICTION_RUNS_MILLIS) {
        cassandraHost.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
      }
      if (maxWaitTimeWhenExhausted > 0) {
        cassandraHost.setMaxWaitTimeWhenExhausted(maxWaitTimeWhenExhausted);
      }
      if (cassandraThriftSocketTimeout > 0) {
        cassandraHost.setCassandraThriftSocketTimeout(cassandraThriftSocketTimeout);
      }
      if (exhaustedPolicy != null) {
        cassandraHost.setExhaustedPolicy(exhaustedPolicy);
      }
      if (timestampResolution != null) {
        cassandraHost.setTimestampResolution(timestampResolution);
      }

      cassandraHosts[x] = cassandraHost;
    }
    return cassandraHosts;
  }

  public void setHosts(String hosts) {
    this.hosts = hosts;
  }

  public void setMaxActive(int maxActive) {
    this.maxActive = maxActive;
  }

  public void setMaxIdle(int maxIdle) {
    this.maxIdle = maxIdle;
  }

  public void setMaxWaitTimeWhenExhausted(long maxWaitTimeWhenExhausted) {
    this.maxWaitTimeWhenExhausted = maxWaitTimeWhenExhausted;
  }

  public void setCassandraThriftSocketTimeout(int cassandraThriftSocketTimeout) {
    this.cassandraThriftSocketTimeout = cassandraThriftSocketTimeout;
  }

  public void setExhaustedPolicy(ExhaustedPolicy exhaustedPolicy) {
    this.exhaustedPolicy = exhaustedPolicy;
  }

  /**
   * @param resolutionString one of "SECONDS", "MILLISECONDS" or "MICROSECONDS"
   */
  public void setTimestampResolution(String resolutionString) {
    timestampResolution = TimestampResolution.valueOf(resolutionString);
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder();
    s.append("CassandraHostConfigurator<");
    s.append("timestampResolution=");
    s.append(timestampResolution);
    s.append("&exhaustedPolicy=");
    s.append(exhaustedPolicy);
    s.append("&cassandraThriftSocketTimeout=");
    s.append(cassandraThriftSocketTimeout);
    s.append("&maxWaitTimeWhenExhausted=");
    s.append(maxWaitTimeWhenExhausted);
    s.append("&maxIdle=");
    s.append(maxIdle);
    s.append("&maxActive=");
    s.append(maxActive);
    s.append("&hosts=");
    s.append(hosts);
    s.append(">");
    return s.toString();
  }

  public boolean getLifo() {
    return lifo;
  }

  public void setLifo(boolean lifo) {
    this.lifo = lifo;
  }

  public long getMinEvictableIdleTimeMillis() {
    return minEvictableIdleTimeMillis;
  }

  public void setMinEvictableIdleTimeMillis(long minEvictableIdleTimeMillis) {
    this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
  }

  public long getTimeBetweenEvictionRunsMillis() {
    return timeBetweenEvictionRunsMillis;
  }

  public void setTimeBetweenEvictionRunsMillis(long timeBetweenEvictionRunsMillis) {
    this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
  }

}
