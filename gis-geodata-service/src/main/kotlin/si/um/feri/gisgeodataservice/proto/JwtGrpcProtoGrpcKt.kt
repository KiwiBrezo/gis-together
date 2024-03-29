package si.um.feri.gisgeodataservice.proto

import io.grpc.CallOptions
import io.grpc.CallOptions.DEFAULT
import io.grpc.Channel
import io.grpc.Metadata
import io.grpc.MethodDescriptor
import io.grpc.ServerServiceDefinition
import io.grpc.ServerServiceDefinition.builder
import io.grpc.ServiceDescriptor
import io.grpc.Status.UNIMPLEMENTED
import io.grpc.StatusException
import io.grpc.kotlin.AbstractCoroutineServerImpl
import io.grpc.kotlin.AbstractCoroutineStub
import io.grpc.kotlin.ClientCalls.unaryRpc
import io.grpc.kotlin.ServerCalls.unaryServerMethodDefinition
import io.grpc.kotlin.StubFor
import kotlin.String
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic
import si.um.feri.gisgeodataservice.proto.JwtGrpcGrpc.getServiceDescriptor

/**
 * Holder for Kotlin coroutine-based client and server APIs for proto.JwtGrpc.
 */
object JwtGrpcGrpcKt {
  const val SERVICE_NAME: String = JwtGrpcGrpc.SERVICE_NAME

  @JvmStatic
  val serviceDescriptor: ServiceDescriptor
    get() = JwtGrpcGrpc.getServiceDescriptor()

  val checkJwtValidityMethod: MethodDescriptor<JwtRequest, JwtValidReply>
    @JvmStatic
    get() = JwtGrpcGrpc.getCheckJwtValidityMethod()

  /**
   * A stub for issuing RPCs to a(n) proto.JwtGrpc service as suspending coroutines.
   */
  @StubFor(JwtGrpcGrpc::class)
  class JwtGrpcCoroutineStub @JvmOverloads constructor(
    channel: Channel,
    callOptions: CallOptions = DEFAULT
  ) : AbstractCoroutineStub<JwtGrpcCoroutineStub>(channel, callOptions) {
    override fun build(channel: Channel, callOptions: CallOptions): JwtGrpcCoroutineStub =
        JwtGrpcCoroutineStub(channel, callOptions)

    /**
     * Executes this RPC and returns the response message, suspending until the RPC completes
     * with [`Status.OK`][io.grpc.Status].  If the RPC completes with another status, a
     * corresponding
     * [StatusException] is thrown.  If this coroutine is cancelled, the RPC is also cancelled
     * with the corresponding exception as a cause.
     *
     * @param request The request message to send to the server.
     *
     * @param headers Metadata to attach to the request.  Most users will not need this.
     *
     * @return The single response from the server.
     */
    suspend fun checkJwtValidity(request: JwtRequest, headers: Metadata = Metadata()): JwtValidReply
        = unaryRpc(
      channel,
      JwtGrpcGrpc.getCheckJwtValidityMethod(),
      request,
      callOptions,
      headers
    )}

  /**
   * Skeletal implementation of the proto.JwtGrpc service based on Kotlin coroutines.
   */
  abstract class JwtGrpcCoroutineImplBase(
    coroutineContext: CoroutineContext = EmptyCoroutineContext
  ) : AbstractCoroutineServerImpl(coroutineContext) {
    /**
     * Returns the response to an RPC for proto.JwtGrpc.CheckJwtValidity.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [io.grpc.Status].  If this method fails with a [java.util.concurrent.CancellationException],
     * the RPC will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    open suspend fun checkJwtValidity(request: JwtRequest): JwtValidReply = throw
        StatusException(UNIMPLEMENTED.withDescription("Method proto.JwtGrpc.CheckJwtValidity is unimplemented"))

    final override fun bindService(): ServerServiceDefinition = builder(getServiceDescriptor())
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = JwtGrpcGrpc.getCheckJwtValidityMethod(),
      implementation = ::checkJwtValidity
    )).build()
  }
}
