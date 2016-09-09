package io.ably.lib.types;

import io.ably.lib.rest.Auth.AuthOptions;
import io.ably.lib.rest.Auth.TokenParams;
import io.ably.lib.transport.Defaults;
import io.ably.lib.util.Log;
import io.ably.lib.util.Log.LogHandler;

import java.util.Map;

/**
 * Options: Ably library options for REST and Realtime APIs
 */
public class ClientOptions extends AuthOptions {

	/**
	 * Default constructor
	 */
	public ClientOptions() {}

	/**
	 * Construct an options with a single key string. The key string is obtained
	 * from the application dashboard.
	 * @param key: the key string
	 * @throws AblyException if the key is not in a valid format
	 */
	public ClientOptions(String key) throws AblyException {
		super(key);
		logLevel = Log.defaultLevel;
	}

	/**
	 * Create a new copy of object
	 * @param options source object for a copy
	 * @return copied object
	 */
	public static ClientOptions copy(ClientOptions options) {
		ClientOptions copy = new ClientOptions();
		AuthOptions.copyAttributes(copy,options);
		return ClientOptions.copyAttributes(copy, options);
	}

	/**
	 * Copy the values of each attribute
	 * @param src source object for copy attributes
	 * @param dest destination object
	 * @return source object with new attributes
	 */
	public static ClientOptions copyAttributes(ClientOptions src, ClientOptions dest) {
		src.clientId = dest.clientId;
		src.logLevel = dest.logLevel;
		src.logHandler = dest.logHandler;
		src.tls = dest.tls;
		src.headers = dest.headers;
		src.restHost = dest.restHost;
		src.realtimeHost = dest.realtimeHost;
		src.port = dest.port;
		src.tlsPort = dest.tlsPort;
		src.autoConnect = dest.autoConnect;
		src.useBinaryProtocol = dest.useBinaryProtocol;
		src.queueMessages = dest.queueMessages;
		src.echoMessages = dest.echoMessages;
		src.recover = dest.recover;
		src.proxy = dest.proxy;
		src.httpOpenTimeout = dest.httpOpenTimeout;
		src.httpRequestTimeout = dest.httpRequestTimeout;
		src.httpMaxRetryCount = dest.httpMaxRetryCount;
		src.defaultTokenParams = dest.defaultTokenParams;
		src.defaultTokenParams = dest.defaultTokenParams;
		return src;
	}

	/**
	 * The id of the client represented by this instance. The clientId is relevant
	 * to presence operations, where the clientId is the principal identifier of the
	 * client in presence update messages. The clientId is also relevant to
	 * authentication; a token issued for a specific client may be used to authenticate
	 * the bearer of that token to the service.
	 */
	public String clientId;

	/**
	 * Log level; controls the level of verbosity of log messages from the library.
	 */
	public int logLevel;

	/**
	 * Log handler: allows the client to intercept log messages and handle them in a
	 * client-specific way.
	 */
	public LogHandler logHandler;


	/**
	 * Encrypted transport: if true, TLS will be used for all connections (whether REST/HTTP
	 * or Realtime WebSocket or Comet connections).
	 */
	public boolean tls = true;

	/**
	 * FIXME: unused
	 */
	public Map<String, String> headers;

	/**
	 * For development environments only; allows a non-default Ably host to be specified.
	 */
	public String restHost = Defaults.HOST_REST;

	/**
	 * For development environments only; allows a non-default Ably host to be specified for
	 * websocket connections.
	 */
	public String realtimeHost = Defaults.HOST_REALTIME;

	/**
	 * For development environments only; allows a non-default Ably port to be specified.
	 */
	public int port;

	/**
	 * For development environments only; allows a non-default Ably TLS port to be specified.
	 */
	public int tlsPort;

	/**
	 * If false, suppresses the automatic initiation of a connection when the library is instanced.
	 */
	public boolean autoConnect = true;

	/**
	 * If false, forces the library to use the JSON encoding for REST and Realtime operations,
	 * instead of the default binary msgpack encoding.
	 */
	public boolean useBinaryProtocol = true;

	/**
	 * If false, suppresses the default queueing of messages when connection states that
	 * anticipate imminent connection (connecting and disconnected). Instead, publish and
	 * presence state changes will fail immediately if not in the connected state.
	 */
	public boolean queueMessages = true;

	/**
	 * If false, suppresses messages originating from this connection being echoed back
	 * on the same connection.
	 */
	public boolean echoMessages = true;

	/**
	 * A connection recovery string, specified by a client when initialising the library
	 * with the intention of inheriting the state of an earlier connection. See the Ably
	 * Realtime API documentation for further information on connection state recovery.
	 */
	public String recover;

	/**
	 * Proxy settings
	 */
	public ProxyOptions proxy;

	/**
	 * Spec: TO313
	 */
	public int httpOpenTimeout = Defaults.TIMEOUT_HTTP_OPEN;

	/**
	 * Spec: TO314
	 */
	public int httpRequestTimeout = Defaults.TIMEOUT_HTTP_REQUEST;

	/**
	 * Max number of fallback hosts to use as a fallback when an HTTP request to
	 * the primary host is unreachable or indicates that it is unserviceable
	 */
	public int httpMaxRetryCount = Defaults.HTTP_MAX_RETRY_COUNT;

	/**
	 * When a TokenParams object is provided, it will override
	 * the client library defaults described in TokenParams
	 * Spec: TO3j11
	 */
	public TokenParams defaultTokenParams = new TokenParams();
}
