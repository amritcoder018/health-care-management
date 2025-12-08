package com.amritsolution.service;

import com.amritsolution.service.proto.DoctorInfoRequest;
import com.amritsolution.service.proto.GatewayGrpc;
import com.amritsolution.service.proto.StringResponse;
import com.amritsolution.utils.JwtUtil;
import io.grpc.CallCredentials;
import io.grpc.Metadata;
import io.grpc.Status;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executor;

@Service
public class GrpcClientService {
    @GrpcClient("myService")
    private GatewayGrpc.GatewayBlockingStub blockingStub;
    @Autowired
    private JwtUtil jwtService;

    public ResponseEntity<String> getAllDoctos(int pageSize, int pageNumber) {
        String token = jwtService.generateToken("gateway-service");

        GatewayGrpc.GatewayBlockingStub stubWithJwt = blockingStub.withCallCredentials(new CallCredentials() {
            @Override
            public void applyRequestMetadata(RequestInfo requestInfo, Executor executor, MetadataApplier metadataApplier) {
                executor.execute(() -> {
                    try {
                        Metadata metadata = new Metadata();
                        Metadata.Key<String> AUTH =
                                Metadata.Key.of("Authorization", Metadata.ASCII_STRING_MARSHALLER);

                        metadata.put(AUTH, "Bearer " + token);

                        metadataApplier.apply(metadata);

                    } catch (Throwable t) {
                        metadataApplier.fail(Status.UNAUTHENTICATED.withCause(t));
                    }
                });
            }
        });



        // 4️⃣ call gRPC
        DoctorInfoRequest doctorInfoRequest=DoctorInfoRequest.newBuilder().setPageNo(pageNumber)
                .setPageSize(pageSize).build();
        StringResponse stringResponse=stubWithJwt.getAllDoctos(doctorInfoRequest);

        // 5️⃣ map gRPC response back to HTTP
       return ResponseEntity.ok(stringResponse.getValue());
    }
}
