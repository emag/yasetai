package yasetai.infrastructure.client;

import org.apache.http.HttpHost;

public class EsHost {

  private static final String DEFAULT_SCHEME = "http";

  private final String hostname;
  private final int port;
  private final String scheme;

  public EsHost(String hostname, int port, String scheme) {
    this.hostname = hostname;
    this.port = port;
    this.scheme = scheme;
  }

  public EsHost(String hostname, int port) {
    this(hostname, port, DEFAULT_SCHEME);
  }

  public HttpHost toHttpHost() {
    return new HttpHost(hostname, port, scheme);
  }

  public String getHostname() {
    return hostname;
  }

  public int getPort() {
    return port;
  }

  public String getScheme() {
    return scheme;
  }

}
