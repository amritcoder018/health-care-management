package com.amritsolution.service;


import com.amritsolution.model.dto.DoctorDisplayDTO;
import com.amritsolution.service.proto.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

@GrpcService
class GrpcServerService extends GatewayGrpc.GatewayImplBase {
    @Autowired
    DoctorService doctorService;

    private static Log log = LogFactory.getLog(GrpcServerService.class);

    @Override
    public void getAllDoctos(DoctorInfoRequest req, StreamObserver<StringResponse> responseObserver) {
        Page<DoctorDisplayDTO> a=doctorService.fetchAllDoctorDtosForUI(req.getPageNo(),req.getPageSize());

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.registerModule(new Jdk8Module());
        String result="error";
        try {
            result = mapper.writeValueAsString(a);
        }catch (Exception e){
            e.printStackTrace();
        }
        StringResponse d=StringResponse.newBuilder().setValue(result).build();
        responseObserver.onNext(d);
        responseObserver.onCompleted();
    }
}