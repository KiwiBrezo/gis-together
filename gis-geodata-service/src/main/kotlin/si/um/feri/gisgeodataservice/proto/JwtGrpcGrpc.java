package si.um.feri.gisgeodataservice.proto;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.39.0)",
    comments = "Source: JwtGrpc.proto")
public final class JwtGrpcGrpc {

  private JwtGrpcGrpc() {}

  public static final String SERVICE_NAME = "proto.JwtGrpc";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<JwtRequest,
      JwtValidReply> getCheckJwtValidityMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CheckJwtValidity",
      requestType = JwtRequest.class,
      responseType = JwtValidReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<JwtRequest,
      JwtValidReply> getCheckJwtValidityMethod() {
    io.grpc.MethodDescriptor<JwtRequest, JwtValidReply> getCheckJwtValidityMethod;
    if ((getCheckJwtValidityMethod = JwtGrpcGrpc.getCheckJwtValidityMethod) == null) {
      synchronized (JwtGrpcGrpc.class) {
        if ((getCheckJwtValidityMethod = JwtGrpcGrpc.getCheckJwtValidityMethod) == null) {
          JwtGrpcGrpc.getCheckJwtValidityMethod = getCheckJwtValidityMethod =
              io.grpc.MethodDescriptor.<JwtRequest, JwtValidReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CheckJwtValidity"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  JwtRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  JwtValidReply.getDefaultInstance()))
              .setSchemaDescriptor(new JwtGrpcMethodDescriptorSupplier("CheckJwtValidity"))
              .build();
        }
      }
    }
    return getCheckJwtValidityMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static JwtGrpcStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<JwtGrpcStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<JwtGrpcStub>() {
        @Override
        public JwtGrpcStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new JwtGrpcStub(channel, callOptions);
        }
      };
    return JwtGrpcStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static JwtGrpcBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<JwtGrpcBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<JwtGrpcBlockingStub>() {
        @Override
        public JwtGrpcBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new JwtGrpcBlockingStub(channel, callOptions);
        }
      };
    return JwtGrpcBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static JwtGrpcFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<JwtGrpcFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<JwtGrpcFutureStub>() {
        @Override
        public JwtGrpcFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new JwtGrpcFutureStub(channel, callOptions);
        }
      };
    return JwtGrpcFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class JwtGrpcImplBase implements io.grpc.BindableService {

    /**
     */
    public void checkJwtValidity(JwtRequest request,
                                 io.grpc.stub.StreamObserver<JwtValidReply> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCheckJwtValidityMethod(), responseObserver);
    }

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCheckJwtValidityMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                JwtRequest,
                JwtValidReply>(
                  this, METHODID_CHECK_JWT_VALIDITY)))
          .build();
    }
  }

  /**
   */
  public static final class JwtGrpcStub extends io.grpc.stub.AbstractAsyncStub<JwtGrpcStub> {
    private JwtGrpcStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected JwtGrpcStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new JwtGrpcStub(channel, callOptions);
    }

    /**
     */
    public void checkJwtValidity(JwtRequest request,
                                 io.grpc.stub.StreamObserver<JwtValidReply> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCheckJwtValidityMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class JwtGrpcBlockingStub extends io.grpc.stub.AbstractBlockingStub<JwtGrpcBlockingStub> {
    private JwtGrpcBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected JwtGrpcBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new JwtGrpcBlockingStub(channel, callOptions);
    }

    /**
     */
    public JwtValidReply checkJwtValidity(JwtRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCheckJwtValidityMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class JwtGrpcFutureStub extends io.grpc.stub.AbstractFutureStub<JwtGrpcFutureStub> {
    private JwtGrpcFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected JwtGrpcFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new JwtGrpcFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<JwtValidReply> checkJwtValidity(
        JwtRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCheckJwtValidityMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CHECK_JWT_VALIDITY = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final JwtGrpcImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(JwtGrpcImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CHECK_JWT_VALIDITY:
          serviceImpl.checkJwtValidity((JwtRequest) request,
              (io.grpc.stub.StreamObserver<JwtValidReply>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @Override
    @SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class JwtGrpcBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    JwtGrpcBaseDescriptorSupplier() {}

    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return JwtGrpcProto.getDescriptor();
    }

    @Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("JwtGrpc");
    }
  }

  private static final class JwtGrpcFileDescriptorSupplier
      extends JwtGrpcBaseDescriptorSupplier {
    JwtGrpcFileDescriptorSupplier() {}
  }

  private static final class JwtGrpcMethodDescriptorSupplier
      extends JwtGrpcBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    JwtGrpcMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (JwtGrpcGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new JwtGrpcFileDescriptorSupplier())
              .addMethod(getCheckJwtValidityMethod())
              .build();
        }
      }
    }
    return result;
  }
}
