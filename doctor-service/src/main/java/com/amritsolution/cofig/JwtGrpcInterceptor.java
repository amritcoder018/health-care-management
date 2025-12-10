//package com.amritsolution.cofig;
//
//import io.grpc.*;
//import lombok.extern.slf4j.Slf4j;
//import net.devh.boot.grpc.server.interceptor.GrpcGlobalServerInterceptor;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.oauth2.jwt.*;
//import org.springframework.stereotype.Component;
//
//@Component
//@GrpcGlobalServerInterceptor
//@Slf4j
//public class JwtGrpcInterceptor implements ServerInterceptor {
//
//    private final JwtDecoder jwtDecoder;
//
//    public JwtGrpcInterceptor(JwtDecoder jwtDecoder) {
//        this.jwtDecoder = jwtDecoder;
//    }
//
//    @Override
//    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(
//            ServerCall<ReqT, RespT> call,
//            Metadata headers,
//            ServerCallHandler<ReqT, RespT> next) {
//
//        Metadata.Key<String> AUTH_HEADER =
//                Metadata.Key.of("Authorization", Metadata.ASCII_STRING_MARSHALLER);
//        String header = headers.get(AUTH_HEADER);
//
//        if (header == null || !header.startsWith("Bearer ")) {
//            call.close(Status.UNAUTHENTICATED.withDescription("Missing or invalid Authorization header"), new Metadata());
//            return new ServerCall.Listener<>() {};
//        }
//
//        String token = header.substring(7);
//        log.info("token:{}",token);
//
//        try {
//            Jwt jwt = jwtDecoder.decode(token);
//
//            Authentication auth = new UsernamePasswordAuthenticationToken(
//                    jwt.getSubject(),
//                    null,
//                    null
//            );
//
//            SecurityContextHolder.getContext().setAuthentication(auth);
//
//        } catch (JwtException ex) {
//            call.close(Status.UNAUTHENTICATED.withDescription("Invalid or expired token"), new Metadata());
//            return new ServerCall.Listener<>() {};
//        }
//
//        return next.startCall(call, headers);
//    }
//}
